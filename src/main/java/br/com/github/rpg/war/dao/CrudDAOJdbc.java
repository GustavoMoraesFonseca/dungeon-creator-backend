package br.com.github.rpg.war.dao;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.com.github.rpg.war.bean.GenericBean;
import br.com.github.rpg.war.dao.helper.CrudDAOHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class CrudDAOJdbc<Dto> extends CrudDAOHelper<Dto> implements ICrudDAO<Dto> {
	
	@Override
	public int insert(String dtoName, Connection conn, GenericBean<Dto> bean) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Dto dtoInstance = null;
		List<Method> getters = null;
		int id = 0;
		try {
			dtoInstance = getDtoInstance(dtoName);
			
			ps = conn.prepareStatement(getInsertSQL(dtoInstance), PreparedStatement.RETURN_GENERATED_KEYS);
			
			getters = getGetters(dtoInstance);
			getters.remove(dtoInstance.getClass().getDeclaredMethod("getId"));
			
			int qtdCampos = getters.size();
			
			for (int i = 0; i < qtdCampos; i++) {
				ps.setObject(i+1, getters.get(i).invoke(bean.getDto()));
			}
			ps.setObject(qtdCampos+1, bean.getDataCriacao());
			ps.setObject(qtdCampos+2, bean.getDataAtualizacao());
			ps.execute();
			
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		return id;
	}

	@Override
	public Dto findById(String dtoName, Connection conn, int id) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Dto dtoInstance = null;
		try {
			dtoInstance = getDtoInstance(dtoName);
			
			ps = conn.prepareStatement(getSelectSQL(dtoInstance, true));
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				resultSetterInObj(dtoInstance, getDtoFields(dtoInstance), rs);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		return dtoInstance;
	}
	
	@Override
	public List<Dto> findAll(String dtoName, Connection conn) throws Exception {
		List<Dto> lst = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Dto dtoInstance = null;
		try {
			dtoInstance = getDtoInstance(dtoName);
			
			ps = conn.prepareStatement(getSelectSQL(dtoInstance, false));
			rs = ps.executeQuery();
			
			while (rs.next()) {
				dtoInstance = getDtoInstance(dtoName);
				resultSetterInObj(dtoInstance, getDtoFields(dtoInstance), rs);
				lst.add(dtoInstance);
				dtoInstance = null;				
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		return lst;
	}

	@Override
	public int update(String dtoName, Connection conn, GenericBean<Dto> bean) throws Exception {
		PreparedStatement ps = null;
		Dto dtoInstance = null;
		int retorno = 0;
		try {
			dtoInstance = getDtoInstance(dtoName);
			
			ps = conn.prepareStatement(getUpdateSQL(dtoInstance));
			
			List<Method> getters = getGetters(dtoInstance);
			getters.remove(dtoInstance.getClass().getDeclaredMethod("getId"));
			
			int qtdCampos = getters.size();
			
			for (int i = 0; i < qtdCampos; i++) {
				if (getters.get(i).getName().substring(0, 5).equals("getIs"))
					ps.setBoolean(i+1, (boolean) getters.get(i).invoke(bean.getDto()));				
				else
					ps.setObject(i+1, getters.get(i).invoke(bean.getDto()));
			}
			ps.setObject(qtdCampos+1, bean.getDataAtualizacao());
			ps.setInt(qtdCampos+2, (int) bean.getDto().getClass().getDeclaredMethod("getId").invoke(bean.getDto()));
			retorno = ps.executeUpdate();
			
			ps.close();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		return retorno;
	}

	@Override
	public int delete(String dtoName, Connection conn, int id) throws Exception {
		PreparedStatement ps = null;
		int retorno = 0;
		try {
			ps = conn.prepareStatement(getDeleteSQL(getDtoInstance(dtoName)));
			ps.setInt(1, id);
			retorno = ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		return retorno;
	}
}