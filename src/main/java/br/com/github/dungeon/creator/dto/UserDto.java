package br.com.github.dungeon.creator.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String username;
	private Boolean isOnline;
}