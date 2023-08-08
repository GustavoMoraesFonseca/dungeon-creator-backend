package br.com.github.dungeon.creator.controller;

import jakarta.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.dungeon.creator.controller.common.AbstractCommonController;
import br.com.github.dungeon.creator.dto.BookDto;

@Path("/book")
@Tag(name = "Books", description = "Todos os Livros.")
public class BookController extends AbstractCommonController<BookDto> {
    
	@Override
	protected String getDtoName() {
		return "BookDto";
	}
}