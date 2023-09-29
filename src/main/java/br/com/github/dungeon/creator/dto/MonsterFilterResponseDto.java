package br.com.github.dungeon.creator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonsterFilterResponseDto extends MonsterDto {

	private static final long serialVersionUID = 1L;
	
	private int monstersQtd;
	private String bookName;
	private String biomeName;
	private String monsterTypeName;
	
	@Override
	public String toString() {
		return "MonsterFilterResponseDto [monstersQtd=" + monstersQtd + ", bookName=" + bookName + ", biomeName="
				+ biomeName + ", monsterTypeName=" + monsterTypeName + ", id=" + getId() + ", name="
				+ getName() + ", level=" + getLevel() + ", page=" + getPage() + ", biomeId="
				+ getBiomeId() + ", bookId=" + getBookId() + ", monsterTypeId=" + getMonsterTypeId()
				+ ", existsSouth=" + isExistsSouth() + ", existsNorth=" + isExistsNorth()
				+ ", existsCenter=" + isExistsCenter() + ", existsWest=" + isExistsWest() + ", existsEast="
				+ isExistsEast() + "]";
	}
}