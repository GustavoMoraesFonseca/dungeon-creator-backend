package br.com.github.dungeon.creator.controller;

import jakarta.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.dungeon.creator.controller.common.AbstractCommonController;
import br.com.github.dungeon.creator.dto.MonsterDto;

@Path("/monster-type")
@Tag(name = "Tipos de Monstros", description = "Todos os Tipos de Monstros do Jogo.")
public class MonsterTypeController extends AbstractCommonController<MonsterDto> {
    
	@Override
	protected String getDtoName() {
		return "MonsterTypeDto";
	}
}