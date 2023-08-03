package br.com.github.rpg.war.controller;

import jakarta.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.rpg.war.controller.common.AbstractCommonController;
import br.com.github.rpg.war.dto.InventoryResourceDto;

@Path("/inventory-resource")
@Tag(name = "Inventario de Recursos", description = "Todos os Recursos do Jogador.")
public class InventoryResourceController extends AbstractCommonController<InventoryResourceDto> {
    
	@Override
	protected String getDtoName() {
		return "InventoryResourceDto";
	}
}