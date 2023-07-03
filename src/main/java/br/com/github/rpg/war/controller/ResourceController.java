package br.com.github.rpg.war.controller;

import javax.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.rpg.war.controller.common.AbstractCommonController;
import br.com.github.rpg.war.dto.ResourceDto;

@Path("/resource")
@Tag(name = "Recursos", description = "Todos os Recursos do Jogo.")
public class ResourceController extends AbstractCommonController<ResourceDto> {
    
	@Override
	protected String getDtoName() {
		return "ResourceDto";
	}
}