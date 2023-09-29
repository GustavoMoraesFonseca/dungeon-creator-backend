package br.com.github.dungeon.creator.business;

import static br.com.github.dungeon.creator.commons.CommonsUtils.notFoundChecker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.github.dungeon.creator.command.MonsterCommand;
import br.com.github.dungeon.creator.dto.MonsterFilterResponseDto;
import br.com.github.dungeon.creator.dto.MonsterParamsFilterDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class MonsterBusiness {

	@Inject
	MonsterCommand monsterCommand;
	
	public List<MonsterFilterResponseDto> findFilteredMonsters(MonsterParamsFilterDto monsterParamsFilterDto) throws Exception {
		List<MonsterFilterResponseDto> lst = null;
		try {
			lst = monsterCommand.findFilteredMonsters(monsterParamsFilterDto);
			notFoundChecker(lst.size());
			lst = setMonstersQtdInEachMonster(monsterParamsFilterDto, lst);
			if (monsterParamsFilterDto.isRandomMonster()) {
				Collections.shuffle(lst);
				lst = List.of(lst.get(0));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		return lst;
	}
	
	private static List<MonsterFilterResponseDto> setMonstersQtdInEachMonster(MonsterParamsFilterDto monsterParamsFilterDto, 
																			  List<MonsterFilterResponseDto> lst) throws Exception {
		var finalList = new ArrayList<MonsterFilterResponseDto>();
		if (monsterParamsFilterDto.getPlayersLevel() != 0) {
			lst.stream().forEach(mon -> {
				int result = (int) (monsterParamsFilterDto.getPlayersLevel() / mon.getLevel());
				
				if (result != 0) {
					mon.setMonstersQtd(result);
					finalList.add(mon);
				}
			});
			return finalList;
		}
		return lst;
	}
}