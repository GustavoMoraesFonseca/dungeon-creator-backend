package br.com.github.dungeon.creator.controller;

import jakarta.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.dungeon.creator.controller.common.AbstractCommonController;
import br.com.github.dungeon.creator.dto.MonsterDto;

@Path("/monstros")
@Tag(name = "Monstros", description = "Todos os Monstros do Jogo.")
public class MonsterController extends AbstractCommonController<MonsterDto> {
    
	@Override
	protected String getDtoName() {
		return "MonsterDto";
	}
}