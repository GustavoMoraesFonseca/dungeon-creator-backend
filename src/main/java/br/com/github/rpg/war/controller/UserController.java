package br.com.github.rpg.war.controller;

import javax.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.rpg.war.controller.common.AbstractCommonController;
import br.com.github.rpg.war.dto.UserDto;

@Path("/user")
@Tag(name = "Usuarios", description = "Todas os Usuarios cadastrados.")
public class UserController extends AbstractCommonController<UserDto> {

	@Override
	protected String getDtoName() {
		return "UserDto";
	}
}