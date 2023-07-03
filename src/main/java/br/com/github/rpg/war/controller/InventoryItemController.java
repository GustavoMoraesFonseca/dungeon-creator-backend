package br.com.github.rpg.war.controller;

import javax.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.rpg.war.controller.common.AbstractCommonController;
import br.com.github.rpg.war.dto.InventoryItemDto;

@Path("/inventory-item")
@Tag(name = "Inventario de Itens", description = "Todos os Itens do Jogador.")
public class InventoryItemController extends AbstractCommonController<InventoryItemDto> {
    
	@Override
	protected String getDtoName() {
		return "InventoryItemDto";
	}
}