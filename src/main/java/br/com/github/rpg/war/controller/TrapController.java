package br.com.github.rpg.war.controller;

import javax.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.rpg.war.controller.common.AbstractCommonController;
import br.com.github.rpg.war.dto.TrapDto;

@Path("/trap")
@Tag(name = "Armadilhas", description = "Todas as Armadilhas do Jogo.")
public class TrapController extends AbstractCommonController<TrapDto> {
    
	@Override
	protected String getDtoName() {
		return "TrapDto";
	}
}