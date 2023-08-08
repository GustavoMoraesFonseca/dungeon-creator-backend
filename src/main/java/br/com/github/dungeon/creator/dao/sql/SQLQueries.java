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
	+ "FROM dungeon_creator.tab_%s ";
	
	String SELECT_BY_ID
	= SELECT_ALL
	+ "WHERE id = ?";
	
	String UPDATE
	="UPDATE dungeon_creator.tab_%s SET "
	+ 	"%s = ?, "
	+	"dthr_atualizacao = ? "
	+"WHERE id = ?";
}
