package br.com.github.dungeon.creator.controller;

import static br.com.github.dungeon.creator.commons.CommonsUtils.notFound;
import static br.com.github.dungeon.creator.commons.CommonsUtils.badRequest;
import static br.com.github.dungeon.creator.commons.CommonsUtils.ok;
import static br.com.github.dungeon.creator.commons.CommonsUtils.registerTrace;
import static br.com.github.dungeon.creator.commons.CommonsUtils.serverError;

import java.time.LocalDateTime;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.github.dungeon.creator.business.MonsterBusiness;
import br.com.github.dungeon.creator.controller.common.AbstractCommonController;
import br.com.github.dungeon.creator.dto.MonsterDto;
import br.com.github.dungeon.creator.dto.MonsterParamsFilterDto;
import br.com.github.dungeon.creator.exceptions.BadRequestException;
import br.com.github.dungeon.creator.exceptions.NotFoundException;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/monstros")
@Tag(name = "Monstros", description = "Todos os Monstros do Jogo.")
public class MonsterController extends AbstractCommonController<MonsterDto> {
    
	@Inject
	MonsterBusiness monsterBusiness;
	
	@Override
	protected String getDtoName() {
		return "MonsterDto";
	}
	
    @GET
    @Path("/filter")
    public Response findMonstersByParams(@Valid @BeanParam MonsterParamsFilterDto monsterParamsFilterDto) {
    	LocalDateTime dtHrStart = LocalDateTime.now();
    	Response response = null;
    	try {
			var monsters = monsterBusiness.findFilteredMonsters(monsterParamsFilterDto);
			response = ok(monsters);
    	} catch (NotFoundException e) {
    		response = notFound();
    	} catch (BadRequestException e) {
    		response = badRequest(e.getMessage());
    	} catch (Exception e) {
			response = serverError(e.getMessage());
		} finally {
			registerTrace(dtHrStart, response.getStatus(), "Filter Monster");
		}
        return response;
    }
    
}