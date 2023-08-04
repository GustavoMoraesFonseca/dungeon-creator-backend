package br.com.github.rpg.war.controller.common;

import static br.com.github.rpg.war.commons.CommonsUtils.conflict;
import static br.com.github.rpg.war.commons.CommonsUtils.notFound;
import static br.com.github.rpg.war.commons.CommonsUtils.ok;
import static br.com.github.rpg.war.commons.CommonsUtils.serverError;
import static br.com.github.rpg.war.commons.CommonsUtils.created;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.github.rpg.war.business.CrudBusinessImpls;
import br.com.github.rpg.war.exceptions.ConflictException;
import br.com.github.rpg.war.exceptions.NotFoundException;

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
    		int id = business.create(getDtoName(), dto);
			response = created(id);
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