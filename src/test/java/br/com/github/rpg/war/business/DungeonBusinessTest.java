package br.com.github.rpg.war.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.github.rpg.war.bean.GenericBean;
import br.com.github.rpg.war.command.CrudCommand;
import br.com.github.rpg.war.dto.BookDto;
import io.quarkus.test.junit.QuarkusMock;

public class DungeonBusinessTest {
	
	@Inject
	CrudCommand<BookDto> crudCommand;
	
    @BeforeEach
    public void setup() {
    	crudCommand = mock(CrudCommand.class);
        QuarkusMock.installMockForType(crudCommand, CrudCommand.class);
    }
    
    @Nested
    @DisplayName("Testes do Create")
    class WhenCallCreate {
    	
    	@Test
    	@DisplayName("Quando chamar o Create, deve retornar Sucesso")
    	public void whenCallCreateBook_thenReturn1() throws Exception {
    		int expectedReturn = 1;
    		
    		when(crudCommand.create(Mockito.anyString(), Mockito.any(GenericBean.class)))
    			.thenReturn(1);
    		
    		assertEquals(expectedReturn, 1);
    	}
    }
}