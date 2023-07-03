package br.com.github.rpg.war.controller;

import javax.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.rpg.war.controller.common.AbstractCommonController;
import br.com.github.rpg.war.dto.RaceDto;

@Path("/race")
@Tag(name = "Raças", description = "Todas as Raças do Jogo.")
public class RaceController extends AbstractCommonController<RaceDto> {
    
	@Override
	protected String getDtoName() {
		return "RaceDto";
	}
}