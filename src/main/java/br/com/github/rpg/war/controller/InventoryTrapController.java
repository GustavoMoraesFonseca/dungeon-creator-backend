package br.com.github.rpg.war.controller;

import javax.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.rpg.war.controller.common.AbstractCommonController;
import br.com.github.rpg.war.dto.InventoryTrapDto;

@Path("/inventory-trap")
@Tag(name = "Inventario de Armadilhas", description = "Todas as Armadilhas do Jogador.")
public class InventoryTrapController extends AbstractCommonController<InventoryTrapDto> {
    
	@Override
	protected String getDtoName() {
		return "InventoryTrapDto";
	}
}