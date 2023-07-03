package br.com.github.rpg.war.business;

import java.util.List;

public interface ICrudBusiness<Dto> {

	Dto findById(String dtoName, int id) throws Exception;
	List<Dto> findAll(String dtoName) throws Exception;
	int create(String dtoName, Dto dto) throws Exception;
	int update(String dtoName, Dto dto) throws Exception;
	int delete(String dtoName, int id) throws Exception;
}