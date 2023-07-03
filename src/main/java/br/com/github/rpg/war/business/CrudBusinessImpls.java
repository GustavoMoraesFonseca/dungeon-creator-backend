package br.com.github.rpg.war.business;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.github.rpg.war.bean.GenericBean;
import br.com.github.rpg.war.command.CrudCommand;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class CrudBusinessImpls<Dto> implements ICrudBusiness<Dto> {
	
	@Inject
	CrudCommand<Dto> command;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public Dto findById(String dtoName, int id) throws Exception {
		Dto dto = null;
		try {
			dto = command.findById(dtoName, id);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		return dto;
	}

	@Override
	public List<Dto> findAll(String dtoName) throws Exception {
		List<Dto> lst = null;
		try {
			lst = command.findAll(dtoName);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		return lst;
	}

	@Override
	public int create(String dtoName, Dto dto) throws Exception {
		String date = sdf.format(new Timestamp(System.currentTimeMillis()));
		GenericBean<Dto> bean = new GenericBean<>();
		int id = 0;
		try {
			bean.setDto(dto);
			bean.setDataCriacao(date);
			bean.setDataAtualizacao(date);
			id = command.create(dtoName, bean);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		return id;
	}

	@Override
	public int update(String dtoName, Dto dto) throws Exception {
		String date = sdf.format(new Timestamp(System.currentTimeMillis()));
		GenericBean<Dto> bean = new GenericBean<>();
		int retorno = 0;
		try {
			bean.setDto(dto);
			bean.setDataAtualizacao(date);
			retorno = command.update(dtoName, bean);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		return retorno;
	}

	@Override
	public int delete(String dtoName, int id) throws Exception {
		int retorno = 0;
		try {
			retorno = command.delete(dtoName, id);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		return retorno;
	}
}