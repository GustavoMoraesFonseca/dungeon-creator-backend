package br.com.github.rpg.war.controller;

import jakarta.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.rpg.war.controller.common.AbstractCommonController;
import br.com.github.rpg.war.dto.PlayerDto;

@Path("/player")
@Tag(name = "Player", description = "Todas os Players do Jogo.")
public class PlayerController extends AbstractCommonController<PlayerDto> {
    
	@Override
	protected String getDtoName() {
		return "PlayerDto";
	}
}