package br.com.github.dungeon.creator.commons;

import java.time.Duration;
import java.time.LocalDateTime;

import br.com.github.dungeon.creator.constants.Constants;
import br.com.github.dungeon.creator.exceptions.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonsUtils {

	public static void registerTrace(LocalDateTime dtHrInicio, int status, String method) {
		LocalDateTime dtHrFinal = LocalDateTime.now();
		long duracao = Duration.between(dtHrInicio, dtHrFinal).getSeconds();
		long duracaoEmMilissegundos = Duration.ofSeconds(duracao).toMillis();
		log.info(String.format("Duration in ms: %s | Method: %s | Status: %s", 
					  		   duracaoEmMilissegundos, method, status)
		);
	}
	
	public static Response ok(Object obj) {
		return Response.ok(new ResponseDto(obj, null)).build();
	}
	
	public static Response created(int id) {
		return Response.status(Status.CREATED).entity(new ResponseDto(id, null)).build();
	}
	
	public static Response notFound() {
		return Response.status(Status.NOT_FOUND).entity(
				new ResponseDto(Constants.NOT_FOUND_MSG, null)
			).build();
	}
	
	public static Response conflict(String msg) {
		return Response.status(Status.CONFLICT).entity(
				new ResponseDto(null, msg)
			).build();
	}
	
	public static Response badRequest(String msg) {
		return Response.status(Status.BAD_REQUEST).entity(
				new ResponseDto(null, msg)
			).build();
	}
	
	public static Response serverError(String msg) {
		return Response.serverError().entity(
				new ResponseDto(null, msg)
			).build();
	}
	
	public static void notFoundChecker(int paramForCheck) throws NotFoundException {
		if (paramForCheck == 0)
			throw new NotFoundException();
	}
}