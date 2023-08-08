package br.com.github.dungeon.creator.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericBean<Dto> implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	private Dto dto;
	private String dataCriacao;
	private String dataAtualizacao;
}