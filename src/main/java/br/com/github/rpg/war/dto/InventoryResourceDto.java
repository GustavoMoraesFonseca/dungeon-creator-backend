package br.com.github.rpg.war.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class InventoryResourceDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int playerId;
	private int resourceId;
}