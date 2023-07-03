package br.com.github.rpg.war.controller;

import javax.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.rpg.war.controller.common.AbstractCommonController;
import br.com.github.rpg.war.dto.ChampionDto;

@Path("/champion")
@Tag(name = "Champions", description = "Todos os Champions do Jogo.")
public class ChampionController extends AbstractCommonController<ChampionDto> {
    
	@Override
	protected String getDtoName() {
		return "ChampionDto";
	}
}