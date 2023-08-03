package br.com.github.rpg.war.controller;

import jakarta.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.rpg.war.controller.common.AbstractCommonController;
import br.com.github.rpg.war.dto.InventoryDefenceDto;

@Path("/inventory-defence")
@Tag(name = "Inventario de Defesas", description = "Todas as Defesas do Jogador.")
public class InventoryDefenceController extends AbstractCommonController<InventoryDefenceDto> {
    
	@Override
	protected String getDtoName() {
		return "InventoryDefenceDto";
	}
}