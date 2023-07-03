package br.com.github.rpg.war.controller.common;

import static br.com.github.rpg.war.commons.CommonsUtils.notFound;
import static br.com.github.rpg.war.commons.CommonsUtils.ok;
import static br.com.github.rpg.war.commons.CommonsUtils.serverError;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.github.rpg.war.business.CrudBusinessImpls;
import br.com.github.rpg.war.constants.Constants;

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
			response = obj.equals(obj.getClass().getConstructor().newInstance())? notFound(Constants.NOT_FOUND_MSG) : ok(obj);
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
			response = lst.isEmpty()? notFound(Constants.NOT_FOUND_MSG) : ok(lst);
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
		} catch (Exception e) {
			response = serverError(e.getMessage());
		}
    	return response;
    }
    
    @PUT
    public Response update(Dto dto) {
    	Response response = null;
    	try {
    		int retorno = business.update(getDtoName(), dto);
			response = retorno == 0? notFound(Constants.NOT_FOUND_MSG) : ok("Alteração feita com Sucesso");
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
    		int retorno = business.delete(getDtoName(), id);
    		response = retorno == 0? notFound(Constants.NOT_FOUND_MSG) : ok("Exclusão feita com Sucesso");
    	} catch (NotFoundException e) {
    		response = notFound(e.getMessage());
		} catch (Exception e) {
			response = serverError(e.getMessage());
		}
    	return response;
    }
}