package br.com.github.rpg.war.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.github.rpg.war.business.CrudBusinessImpls;
import br.com.github.rpg.war.constants.Constants;
import br.com.github.rpg.war.dto.BookDto;
import br.com.github.rpg.war.exceptions.ConflictException;
import br.com.github.rpg.war.exceptions.NotFoundException;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class DungeonControllerTest {
	
	@Inject
	CrudBusinessImpls<BookDto> crudBusiness;
	
    @BeforeEach
    public void setup() {
        crudBusiness = mock(CrudBusinessImpls.class);
        QuarkusMock.installMockForType(crudBusiness, CrudBusinessImpls.class);
    }
    
    @Nested
    @DisplayName("Testes do Create")
    class WhenCallCreate {
    	
    	@Test
    	@DisplayName("Quando chamar o Create, deve retornar Sucesso")
    	public void whenCallCreateBook_thenReturnOk() throws Exception {
    		BookDto requestBody = new BookDto(); 
    		requestBody.setName("Book");
    		
    		when(crudBusiness.create(Mockito.anyString(), Mockito.any(BookDto.class)))
    			.thenReturn(1);
    		
    		given()
    			.when()
    				.contentType(ContentType.JSON)
    				.body(requestBody)
    				.post("/book")
    			.then()
    				.statusCode(201)
    				.body("data", is(1))
    				.body("error", nullValue());
    	}
    	
    	@Test
    	@DisplayName("Quando chamar o Create, deve retornar Conflict")
    	public void whenCallCreateBook_thenReturnConflict() throws Exception {
    		BookDto requestBody = new BookDto(); 
    		requestBody.setName("Book");
    		
    		when(crudBusiness.create(Mockito.anyString(), Mockito.any(BookDto.class)))
    			.thenThrow(new ConflictException("Já cadastrado"));
    		
    		given()
    			.when()
    				.contentType(ContentType.JSON)
    				.body(requestBody)
    				.post("/book")
    			.then()
    				.statusCode(409)
    				.body("data", nullValue())
    				.body("error", is("Já cadastrado"));
    	}
    	
    	@Test
    	@DisplayName("Quando chamar o Create, deve retornar Internal Server Error")
    	public void whenCallCreateBook_thenReturnInternalServerError() throws Exception {
    		BookDto requestBody = new BookDto(); 
    		requestBody.setName("Book");
    		
    		when(crudBusiness.create(Mockito.anyString(), Mockito.any(BookDto.class)))
    			.thenThrow(new SQLException("Erro ao se conectar ao Banco"));
    		
    		given()
    			.when()
    				.contentType(ContentType.JSON)
    				.body(requestBody)
    				.post("/book")
    			.then()
    				.statusCode(500)
    				.body("data", nullValue())
    				.body("error", is("Erro ao se conectar ao Banco"));
    	}
    }
    
    @Nested
    @DisplayName("Testes do Update")
    class WhenCallUpdate {
    	
        @Test
        @DisplayName("Quando chamar o Update, deve retornar Sucesso")
        public void whenCallUpdateBook_thenReturnOk() throws Exception {
        	BookDto requestBody = new BookDto(); 
        	requestBody.setId(1);
        	requestBody.setName("New Book");
        	
        	doNothing().when(crudBusiness).update(Mockito.anyString(), Mockito.any(BookDto.class));
        	
        	given()
              .when()
              	 .contentType(ContentType.JSON)
              	 .body(requestBody)
              	 .put("/book")
              .then()
                 .statusCode(200)
                 .body("data", is("Alteração feita com Sucesso"))
                 .body("error", nullValue());
        }
        
    	@Test
    	@DisplayName("Quando chamar o Update, deve retornar Conflict")
    	public void whenCallUpdateBook_thenReturnConflict() throws Exception {
    		BookDto requestBody = new BookDto();
    		requestBody.setId(1);
    		requestBody.setName("Book");
    		
    		doThrow(new ConflictException("Já cadastrado"))
    		    .when(crudBusiness).update(Mockito.anyString(), Mockito.any(BookDto.class));
    		
    		given()
    			.when()
    				.contentType(ContentType.JSON)
    				.body(requestBody)
    				.put("/book")
    			.then()
    				.statusCode(409)
    				.body("data", nullValue())
    				.body("error", is("Já cadastrado"));
    	}
    	
    	@Test
    	@DisplayName("Quando chamar o Update, deve retornar Not Found")
    	public void whenCallUpdateBook_thenReturnNotFound() throws Exception {
    		BookDto requestBody = new BookDto();
    		requestBody.setId(2);
    		requestBody.setName("Other Book");
    		
    		doThrow(NotFoundException.class)
    		    .when(crudBusiness).update(Mockito.anyString(), Mockito.any(BookDto.class));
    		
    		given()
    			.when()
    				.contentType(ContentType.JSON)
    				.body(requestBody)
    				.put("/book")
    			.then()
    				.statusCode(404)
    				.body("data", is(Constants.NOT_FOUND_MSG))
    				.body("error", nullValue());
    	}
    	
    	@Test
    	@DisplayName("Quando chamar o Update, deve retornar Internal Server Error")
    	public void whenCallUpdateBook_thenReturnInternalServerError() throws Exception {
    		BookDto requestBody = new BookDto(); 
    		requestBody.setId(1);
    		requestBody.setName("Book");
    		
    		doThrow(new SQLException("Erro ao se conectar ao Banco"))
		    	.when(crudBusiness).update(Mockito.anyString(), Mockito.any(BookDto.class));
    		
    		given()
    			.when()
    				.contentType(ContentType.JSON)
    				.body(requestBody)
    				.put("/book")
    			.then()
    				.statusCode(500)
    				.body("data", nullValue())
    				.body("error", is("Erro ao se conectar ao Banco"));
    	}
    }
    
    @Nested
    @DisplayName("Testes do Find By Id")
    class WhenCallFindById {
        @Test
        @DisplayName("Quando chamar Find By Id, deve retornar Sucesso")
        public void whenCallFindBookById_thenReturnOk() throws Exception {
        	BookDto expectedFindByIdReturn = new BookDto(); 
        	expectedFindByIdReturn.setId(1);
        	expectedFindByIdReturn.setName("Book");
        	
            when(crudBusiness.findById(Mockito.anyString(), Mockito.anyInt()))
        		.thenReturn(expectedFindByIdReturn);
        	
        	given()
              .when()
    		   	 .pathParam("id", 1)
    		   	 .get("/book/{id}")
              .then()
                 .statusCode(200)
                 .body("data.id", is(1))
                 .body("data.name", is("Book"))
                 .body("error", nullValue());
        }
        
    	@Test
    	@DisplayName("Quando chamar o Find By id, deve retornar Not Found")
    	public void whenCallFindBookById_thenReturnNotFound() throws Exception {
            when(crudBusiness.findById(Mockito.anyString(), Mockito.anyInt()))
        		.thenThrow(NotFoundException.class);
    		
    		given()
    			.when()
		   		   	.pathParam("id", 1)
		   		   	.get("/book/{id}")
    			.then()
    				.statusCode(404)
    				.body("data", is(Constants.NOT_FOUND_MSG))
    				.body("error", nullValue());
    	}
    	
    	@Test
    	@DisplayName("Quando chamar o Find By id, deve retornar Internal Server Error")
    	public void whenCallFindBookById_thenReturnInternalServerError() throws Exception {
            when(crudBusiness.findById(Mockito.anyString(), Mockito.anyInt()))
        		.thenThrow(new SQLException("Erro ao conectar com o Banco"));
    		
    		given()
    			.when()
		   		   	.pathParam("id", 1)
		   		   	.get("/book/{id}")
    			.then()
    				.statusCode(500)
    				.body("data", nullValue())
    				.body("error", is("Erro ao conectar com o Banco"));
    	}
    }
	
    @Nested
    @DisplayName("Testes do Find All")
    class WhenCallFindAll {
        @Test
        @DisplayName("Quando chamar o Find All, deve retornar Sucesso")
        public void whenCallFindAllBook_thenReturnOk() throws Exception {
        	BookDto expectedFisrtItemReturn = new BookDto(); 
        	expectedFisrtItemReturn.setId(1);
        	expectedFisrtItemReturn.setName("Book");
        	
        	BookDto expectedSecondItemReturn = new BookDto(); 
        	expectedSecondItemReturn.setId(2);
        	expectedSecondItemReturn.setName("New Book");
        	
        	List<BookDto> expectedFindAllReturn = List.of(expectedFisrtItemReturn, expectedSecondItemReturn);
        	
            when(crudBusiness.findAll(Mockito.anyString()))
        		.thenReturn(expectedFindAllReturn);
        	
        	given()
              .when()
              	 .get("/book")
              .then()
                 .statusCode(200)
                 .body("data[0].id", is(1))
                 .body("data[0].name", is("Book"))
                 .body("data[1].id", is(2))
                 .body("data[1].name", is("New Book"))
                 .body("error", nullValue());
        }
    	
    	@Test
    	@DisplayName("Quando chamar o Find All, deve retornar Not Found")
    	public void whenCallFindAllBooks_thenReturnNotFound() throws Exception {
            when(crudBusiness.findAll(Mockito.anyString()))
        		.thenThrow(NotFoundException.class);
    		
    		given()
    			.when()
		   		   	.get("/book")
    			.then()
    				.statusCode(404)
    				.body("data", is(Constants.NOT_FOUND_MSG))
    				.body("error", nullValue());
    	}
    	
    	@Test
    	@DisplayName("Quando chamar o Find All, deve retornar Internal Server Error")
    	public void whenCallFindAllBooks_thenReturnInternalServerError() throws Exception {
            when(crudBusiness.findAll(Mockito.anyString()))
        		.thenThrow(new SQLException("Erro ao conectar com o Banco"));
    		
    		given()
    			.when()
		   		   	.get("/book")
    			.then()
    				.statusCode(500)
    				.body("data", nullValue())
    				.body("error", is("Erro ao conectar com o Banco"));
    	}
    }
    
    @Nested
    @DisplayName("Testes do Delete")
    class WhenCallDelete {
        @Test
        @DisplayName("Quando chamar o Delete, deve retornar Sucesso")
        public void whenCallDeleteBook_thenReturnOk() throws Exception {
        	doNothing().when(crudBusiness).delete(Mockito.anyString(), Mockito.anyInt());
        	
        	given()
              .when()
              	 .pathParam("id", 1)
              	 .delete("/book/{id}")
              .then()
                 .statusCode(200)
                 .body("data", is("Exclusão feita com Sucesso"))
                 .body("error", nullValue());
        }
        
    	@Test
    	@DisplayName("Quando chamar o Delete, deve retornar Not Found")
    	public void whenCallDeleteBook_thenReturnNotFound() throws Exception {
    		BookDto requestBody = new BookDto();
    		requestBody.setId(2);
    		requestBody.setName("Other Book");
    		
    		doThrow(NotFoundException.class)
    		    .when(crudBusiness).delete(Mockito.anyString(), Mockito.anyInt());
    		
    		given()
    			.when()
	             	.pathParam("id", 1)
	             	.delete("/book/{id}")
    			.then()
    				.statusCode(404)
    				.body("data", is(Constants.NOT_FOUND_MSG))
    				.body("error", nullValue());
    	}
    	
    	@Test
    	@DisplayName("Quando chamar o Delete, deve retornar Internal Server Error")
    	public void whenCallDeleteBook_thenReturnInternalServerError() throws Exception {
    		BookDto requestBody = new BookDto(); 
    		requestBody.setId(1);
    		requestBody.setName("Book");
    		
    		doThrow(new SQLException("Erro ao se conectar ao Banco"))
    	    	.when(crudBusiness).delete(Mockito.anyString(), Mockito.anyInt());
    		
    		given()
    			.when()
	             	.pathParam("id", 1)
	             	.delete("/book/{id}")
    			.then()
    				.statusCode(500)
    				.body("data", nullValue())
    				.body("error", is("Erro ao se conectar ao Banco"));
    	}
    }
}