package br.com.github.rpg.war.commons;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class CommonsUtils {

	public static Response ok(Object obj) {
		return Response.ok(new ResponseDto(obj, null)).build();
	}
	
	public static Response notFound(String msg) {
		return Response.status(Status.NOT_FOUND).entity(
				new ResponseDto(null, msg)
			).build();
	}
	
	public static Response serverError(String msg) {
		return Response.serverError().entity(
				new ResponseDto(null, msg)
			).build();
	}
}
