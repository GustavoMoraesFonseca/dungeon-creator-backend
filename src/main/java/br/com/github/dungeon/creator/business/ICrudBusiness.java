package br.com.github.dungeon.creator.business;

import java.util.List;

public interface ICrudBusiness<Dto> {

	Dto findById(String dtoName, int id) throws Exception;
	List<Dto> findAll(String dtoName) throws Exception;
	int create(String dtoName, Dto dto) throws Exception;
	void update(String dtoName, Dto dto) throws Exception;
	void delete(String dtoName, int id) throws Exception;
}