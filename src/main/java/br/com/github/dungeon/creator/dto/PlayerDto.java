package br.com.github.dungeon.creator.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class PlayerDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int userId;
	private String progress;
	private int soldados;
	private int armyArmorClass;
	private int atack;
	private int food;
}
