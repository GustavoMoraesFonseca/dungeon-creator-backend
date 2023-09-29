package br.com.github.dungeon.creator.command;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.com.github.dungeon.creator.config.MySQLConfig;
import br.com.github.dungeon.creator.dao.MonsterDAOJdbc;
import br.com.github.dungeon.creator.dto.MonsterFilterResponseDto;
import br.com.github.dungeon.creator.dto.MonsterParamsFilterDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MonsterCommand {

	@Inject
	MonsterDAOJdbc monsterDAO;
	
	public List<MonsterFilterResponseDto> findFilteredMonsters(MonsterParamsFilterDto monsterParamsFilterDto) throws Exception {
		Connection conn = MySQLConfig.getConnection();
		List<MonsterFilterResponseDto> lst = new ArrayList<>();
		try {
			lst = monsterDAO.findFilteredMonsters(conn, monsterParamsFilterDto);
		} finally {
			conn.close();
		}
		return lst;
	}
}