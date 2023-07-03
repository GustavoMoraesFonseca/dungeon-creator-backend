package br.com.github.rpg.war.dao.sql;

public interface SQLQueries {

	String DELETE
	="DELETE FROM rpg_war.tab_%s "
	+"WHERE tab_%s.id = ?";
	
	String INSERT
	= "INSERT INTO rpg_war.tab_%s"
	+ "	(%s, dthr_criacao, dthr_atualizacao) "
	+ "VALUES "
	+ "	(%s, ?, ?)";
	
	String SELECT_ALL
	= "SELECT %s "
	+ "FROM rpg_war.tab_%s ";
	
	String SELECT_BY_ID
	= SELECT_ALL
	+ "WHERE id = ?";
	
	String UPDATE
	="UPDATE rpg_war.tab_%s SET "
	+ 	"%s = ?, "
	+	"dthr_atualizacao = ? "
	+"WHERE id = ?";
}
