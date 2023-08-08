package br.com.github.dungeon.creator.controller;

import jakarta.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.dungeon.creator.controller.common.AbstractCommonController;
import br.com.github.dungeon.creator.dto.UserDto;

@Path("/user")
@Tag(name = "Usuarios", description = "Todas os Usuarios cadastrados.")
public class UserController extends AbstractCommonController<UserDto> {

	@Override
	protected String getDtoName() {
		return "UserDto";
	}
}