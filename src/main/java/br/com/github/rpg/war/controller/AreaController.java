package br.com.github.rpg.war.controller;

import javax.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.rpg.war.controller.common.AbstractCommonController;
import br.com.github.rpg.war.dto.AreaDto;

@Path("/area")
@Tag(name = "Areas", description = "Todas as Areas do Jogo.")
public class AreaController extends AbstractCommonController<AreaDto> {
    
	@Override
	protected String getDtoName() {
		return "AreaDto";
	}
}