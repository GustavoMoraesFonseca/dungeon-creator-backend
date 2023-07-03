package br.com.github.rpg.war.commons;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Object data;
	private String error;
}