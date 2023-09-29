package br.com.github.dungeon.creator.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonsterParamsFilterDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@QueryParam("isRandomMonster")
	@NotNull
	private boolean isRandomMonster;
	
	@QueryParam("level")
	@PositiveOrZero(message="Nível da Criatura deve ser igual ou maior que 0")
	private double level;
	
	@QueryParam("bookId")
	@PositiveOrZero(message="Id do Livro deve ser igual ou maior que 0")
	private int bookId;
	
	@QueryParam("biomeId")
	@PositiveOrZero(message="Id do Bioma deve ser igual ou maior que 0")
	private int biomeId;
	
	@QueryParam("monsterTypeId")
	@PositiveOrZero(message="Id do Tipo da Criatura deve ser igual ou maior que 0")
	private int monsterTypeId;
	
	@QueryParam("playersLevel")
	@PositiveOrZero(message="Nível dos Jogadores deve ser igual ou maior que 0")	
	private int playersLevel;
	
	@QueryParam("region")
	@Pattern(regexp = "south|north|center|west|east|all", message = "Região deve ser umas dessas: south, north, center, west ou east")
	private String region;
}