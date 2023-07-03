package br.com.github.rpg.war.controller;

import javax.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.rpg.war.controller.common.AbstractCommonController;
import br.com.github.rpg.war.dto.BiomeDto;

@Path("/biome")
@Tag(name = "Biomes", description = "Todas os Biomas do Jogo.")
public class BiomeController extends AbstractCommonController<BiomeDto> {
    
	@Override
	protected String getDtoName() {
		return "BiomeDto";
	}
}