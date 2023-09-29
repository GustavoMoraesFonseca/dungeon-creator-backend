package br.com.github.dungeon.creator.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.github.dungeon.creator.dao.sql.SQLQueries;
import br.com.github.dungeon.creator.dto.MonsterFilterResponseDto;
import br.com.github.dungeon.creator.dto.MonsterParamsFilterDto;
import br.com.github.dungeon.creator.exceptions.BadRequestException;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MonsterDAOJdbc {

	private static String getQueryByMonsterParamsFilterDto(MonsterParamsFilterDto monsterParamsFilterDto) throws BadRequestException {
		StringBuffer sb = new StringBuffer(SQLQueries.SELECT_FILTER_MONSTER);
		
		var paramsValuesIncludeInQuery = List.of(
			" level <= "+ monsterParamsFilterDto.getLevel(),
			" book_id = "+ monsterParamsFilterDto.getBookId(),
			" biome_id = "+ monsterParamsFilterDto.getBiomeId(),
			" monster_type_id = "+ monsterParamsFilterDto.getMonsterTypeId(),
			" exists_"+monsterParamsFilterDto.getRegion()+" = "+monsterParamsFilterDto.getMonsterTypeId()
		);
		
		var paramsForCheckToIncludeInQuery = List.of(
			monsterParamsFilterDto.getLevel() != 0,
			monsterParamsFilterDto.getBookId() != 0,
			monsterParamsFilterDto.getBiomeId() != 0,
			monsterParamsFilterDto.getMonsterTypeId() != 0,
			!monsterParamsFilterDto.getRegion().equals("all")
		);
		
		if (!paramsForCheckToIncludeInQuery.contains(true)) {
			if (monsterParamsFilterDto.getPlayersLevel() != 0) {
				return sb.toString().replace("WHERE", "ORDER BY m.page");
			}
			throw new BadRequestException("Algum parâmetro deve ser informado.");
		}
		
		for (int i = 0; i < paramsValuesIncludeInQuery.size(); i++) {
			String value = paramsValuesIncludeInQuery.get(i);
			sb.append(paramsForCheckToIncludeInQuery.get(i)?
				sb.toString().length() == SQLQueries.SELECT_FILTER_MONSTER.length()?
					value : " AND "+value
				: "");
		}
		sb.append(" ORDER BY m.page");
		return sb.toString();
	}
	
	private static MonsterFilterResponseDto resultSetToMonsterDto(ResultSet rs) throws SQLException {
		MonsterFilterResponseDto monsterFilterResponseDto = new MonsterFilterResponseDto();
		
		monsterFilterResponseDto.setId(rs.getInt("id"));
		monsterFilterResponseDto.setPage(rs.getInt("page"));
		monsterFilterResponseDto.setLevel(rs.getDouble("level"));
		monsterFilterResponseDto.setName(rs.getString("monster_name"));
		monsterFilterResponseDto.setBookName(rs.getString("book_name"));
		monsterFilterResponseDto.setBiomeName(rs.getString("biome_name"));
		monsterFilterResponseDto.setMonsterTypeName(rs.getString("monster_type_name"));
		monsterFilterResponseDto.setBiomeId(rs.getInt("biome_id"));
		monsterFilterResponseDto.setBookId(rs.getInt("book_id"));
		monsterFilterResponseDto.setMonsterTypeId(rs.getInt("monster_type_id"));
		monsterFilterResponseDto.setExistsSouth(rs.getBoolean("exists_south"));
		monsterFilterResponseDto.setExistsNorth(rs.getBoolean("exists_north"));
		monsterFilterResponseDto.setExistsCenter(rs.getBoolean("exists_center"));
		monsterFilterResponseDto.setExistsWest(rs.getBoolean("exists_west"));
		monsterFilterResponseDto.setExistsEast(rs.getBoolean("exists_east"));
		
		return monsterFilterResponseDto;
	}
	
	public List<MonsterFilterResponseDto> findFilteredMonsters(Connection conn, MonsterParamsFilterDto monsterParamsFilterDto) throws Exception {
		ResultSet rs = conn.prepareStatement(getQueryByMonsterParamsFilterDto(monsterParamsFilterDto)).executeQuery();
		
		List<MonsterFilterResponseDto> lst = new ArrayList<>();
		
		while (rs.next()) {
			lst.add(resultSetToMonsterDto(rs));
		}
		
		rs.close();
		return lst;
	}
}