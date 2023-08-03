package br.com.github.rpg.war.business;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import br.com.github.rpg.war.bean.GenericBean;
import br.com.github.rpg.war.command.CrudCommand;
import br.com.github.rpg.war.exceptions.ConflictException;
import br.com.github.rpg.war.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class CrudBusinessImpls<Dto> implements ICrudBusiness<Dto> {
	
	@Inject
	CrudCommand<Dto> command;
	
	@Override
	public Dto findById(String dtoName, int id) throws Exception {
		Dto dto = null;
		try {
			dto = command.findById(dtoName, id);
			notFoundChecker((int) dto.getClass().getMethod("getId", null).invoke(dto));
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
			notFoundChecker(lst.size());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		return lst;
	}

	@Override
	public int create(String dtoName, Dto dto) throws ConflictException, Exception {
		GenericBean<Dto> bean = new GenericBean<>();
		String date = getDate();
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
	public void update(String dtoName, Dto dto) throws ConflictException, NotFoundException, Exception {
		GenericBean<Dto> bean = new GenericBean<>();
		try {
			bean.setDto(dto);
			bean.setDataAtualizacao(getDate());
			int affectedRows = command.update(dtoName, bean);
			notFoundChecker(affectedRows);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void delete(String dtoName, int id) throws NotFoundException, Exception {
		try {
			int affectedRows = command.delete(dtoName, id);
			notFoundChecker(affectedRows);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	private static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Timestamp(System.currentTimeMillis()));
	}
	
	private void notFoundChecker(int paramForCheck) throws NotFoundException {
		if (paramForCheck == 0)
			throw new NotFoundException();
	}
}