package br.com.github.rpg.war.controller;

import jakarta.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.rpg.war.controller.common.AbstractCommonController;
import br.com.github.rpg.war.dto.ItemDto;

@Path("/item")
@Tag(name = "Item", description = "Todos os Itens do Jogo.")
public class ItemController extends AbstractCommonController<ItemDto> {
    
	@Override
	protected String getDtoName() {
		return "ItemDto";
	}
}