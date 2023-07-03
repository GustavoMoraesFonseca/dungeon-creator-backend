package br.com.github.rpg.war.controller;

import javax.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.rpg.war.controller.common.AbstractCommonController;
import br.com.github.rpg.war.dto.DefenceDto;

@Path("/defence")
@Tag(name = "Defesas", description = "Todas as Defesas do Jogo.")
public class DefenceController extends AbstractCommonController<DefenceDto> {
    
	@Override
	protected String getDtoName() {
		return "DefenceDto";
	}
}