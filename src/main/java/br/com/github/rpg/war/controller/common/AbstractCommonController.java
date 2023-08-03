package br.com.github.rpg.war.controller.common;

import static br.com.github.rpg.war.commons.CommonsUtils.conflict;
import static br.com.github.rpg.war.commons.CommonsUtils.notFound;
import static br.com.github.rpg.war.commons.CommonsUtils.ok;
import static br.com.github.rpg.war.commons.CommonsUtils.serverError;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import br.com.github.rpg.war.business.CrudBusinessImpls;
import br.com.github.rpg.war.exceptions.ConflictException;

@Produces(MediaType.APPLICATION_JSON)
public abstract class AbstractCommonController<Dto> {

	@Inject
	CrudBusinessImpls<Dto> business;
	
	protected abstract String getDtoName();
	
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") int id) {
    	Response response = null;
    	try {
			var obj = business.findById(getDtoName(), id);
			response = ok(obj);
    	} catch (NotFoundException e) {
    		response = notFound();
    	} catch (Exception e) {
			response = serverError(e.getMessage());
		}
        return response;
    }
    
    @GET
    public Response findAll() {
    	Response response = null;
    	try {
			var lst = business.findAll(getDtoName());
			response = ok(lst);
    	} catch (NotFoundException e) {
    		response = notFound();
		} catch (Exception e) {
			response = serverError(e.getMessage());
		}
        return response;
    }
    
    @POST
    public Response create(Dto dto) {
    	Response response = null;
    	try {
    		int retorno = business.create(getDtoName(), dto);
			response = ok(retorno);
    	} catch (ConflictException e) {
    		response = conflict(e.getMessage());
		} catch (Exception e) {
			response = serverError(e.getMessage());
		}
    	return response;
    }
    
    @PUT
    public Response update(Dto dto) {
    	Response response = null;
    	try {
    		business.update(getDtoName(), dto);
			response = ok("Alteração feita com Sucesso");
    	} catch (ConflictException e) {
    		response = conflict(e.getMessage());
    	} catch (NotFoundException e) {
    		response = notFound();
    	} catch (Exception e) {
			response = serverError(e.getMessage());
		}
    	return response;
    }
    
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
    	Response response = null;
    	try {
    		business.delete(getDtoName(), id);
    		response = ok("Exclusão feita com Sucesso");
    	} catch (NotFoundException e) {
    		response = notFound();
		} catch (Exception e) {
			response = serverError(e.getMessage());
		}
    	return response;
    }
}