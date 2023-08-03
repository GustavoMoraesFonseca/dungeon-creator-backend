package br.com.github.rpg.war.controller;

import jakarta.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.rpg.war.controller.common.AbstractCommonController;
import br.com.github.rpg.war.dto.RouteDto;

@Path("/route")
@Tag(name = "Rotas", description = "Todas as Rotas do Jogo.")
public class RouteController extends AbstractCommonController<RouteDto> {
    
	@Override
	protected String getDtoName() {
		return "RouteDto";
	}
}