package br.com.github.rpg.war.controller;

import javax.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.rpg.war.controller.common.AbstractCommonController;
import br.com.github.rpg.war.dto.BookDto;

@Path("/book")
@Tag(name = "Books", description = "Todos os Livros.")
public class BookController extends AbstractCommonController<BookDto> {
    
	@Override
	protected String getDtoName() {
		return "BookDto";
	}
}