package br.com.github.dungeon.creator.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class MonsterDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private double level;
	private int page;
	private int biomeId;
	private int bookId;
	private int monsterTypeId;
	private boolean existsSouth;
	private boolean existsNorth;
	private boolean existsCenter;
	private boolean existsWest;
	private boolean existsEast;
}