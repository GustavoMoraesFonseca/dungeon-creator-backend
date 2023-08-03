package br.com.github.rpg.war.command;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.github.rpg.war.bean.GenericBean;
import br.com.github.rpg.war.config.MySQLConfig;
import br.com.github.rpg.war.dao.ICrudDAO;

@ApplicationScoped
public class CrudCommand<Dto> {
	
	@Inject
	ICrudDAO<Dto> crudDAOImpls; 
	
	public int create(String dtoName, GenericBean<Dto> bean) throws Exception {
		Connection conn = MySQLConfig.getConnection();
		int id = 0;
		try {
			id = crudDAOImpls.insert(dtoName, conn, bean);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return id;
	}

	public Dto findById(String dtoName, int id) throws Exception {
		Connection conn = MySQLConfig.getConnection();
		Dto dto;
		try {
			dto = crudDAOImpls.findById(dtoName, conn, id);
		} finally {
			conn.close();
		}
		return dto;
	}	
	
	public List<Dto> findAll(String dtoName) throws Exception {
		Connection conn = MySQLConfig.getConnection();
		List<Dto> lst = new ArrayList<Dto>();
		try {
			lst = crudDAOImpls.findAll(dtoName, conn);
		} finally {
			conn.close();
		}
		return lst;
	}
	
	public int update(String dtoName, GenericBean<Dto> bean) throws Exception {
		Connection conn = MySQLConfig.getConnection();
		int retorno = 0;
		try {
			retorno = crudDAOImpls.update(dtoName, conn, bean);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return retorno;
	}
	
	public int delete(String dtoName, int id) throws Exception {
		Connection conn = MySQLConfig.getConnection();
		int retorno = 0;
		try {
			retorno = crudDAOImpls.delete(dtoName, conn, id);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return retorno;
	}
}
