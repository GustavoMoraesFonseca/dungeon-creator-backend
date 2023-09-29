package br.com.github.dungeon.creator.dao.sql;

public interface SQLQueries {

	String DELETE
	="DELETE FROM dungeon_creator.tab_%s "
	+"WHERE tab_%s.id = ?";
	
	String INSERT
	= "INSERT INTO dungeon_creator.tab_%s"
	+ "	(%s, dthr_criacao, dthr_atualizacao) "
	+ "VALUES "
	+ "	(%s, ?, ?)";
	
	String SELECT_ALL
	= "SELECT %s "
	+ "FROM dungeon_creator.tab_%s "
	+ "ORDER BY id";
	
	String SELECT_BY_ID
	= SELECT_ALL
	+ "WHERE id = ?";
	
	String UPDATE
	="UPDATE dungeon_creator.tab_%s SET "
	+ 	"%s = ?, "
	+	"dthr_atualizacao = ? "
	+"WHERE id = ?";
	
	String SELECT_FILTER_MONSTER
	= "SELECT "
	+ "  m.id, m.page, m.level, "
	+ "  m.name AS monster_name, bk.name AS book_name, "
	+ "  bio.name AS biome_name, mt.name as monster_type_name, "
	+ "  m.biome_id, m.book_id, m.monster_type_id,  "
	+ "  m.exists_south, m.exists_north, m.exists_center, m.exists_west, m.exists_east "
	+ "FROM dungeon_creator.tab_monster m "
	+ "  JOIN dungeon_creator.tab_book bk on bk.id = m.book_id "
	+ "  JOIN dungeon_creator.tab_biome bio on bio.id = m.biome_id "
	+ "  JOIN dungeon_creator.tab_monster_type mt on mt.id = m.monster_type_id "
	+ "WHERE";
}