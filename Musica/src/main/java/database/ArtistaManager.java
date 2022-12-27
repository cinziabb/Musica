package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Artista;
import util.Messages;

public class ArtistaManager {

	private DbManager dbm = null;
	private Connection conn = null;
	private OperationResult res = null;

	public ArtistaManager() {
		this.dbm = new DbManager();
	}

	//inserisce un artista
	public OperationResult insert(Artista artist) throws SQLException{
		//definizione della query SQL
		String sqlQuery = "INSERT INTO artista(nome, dataNascita, annoInizio, annoFine) VALUES(?, ?, ?, ?)";
		//apertura della connessione
		conn = dbm.openConnection();
		//precompilazione della query
		PreparedStatement ps = conn.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, artist.getNome());
		ps.setDate(2, artist.getDataNascita());
		ps.setInt(3, artist.getAnnoInizio());
		ps.setInt(4, artist.getAnnoFine());
		//esecuzione della query
		int row = ps.executeUpdate();
		if (row == 0) {
			res = new OperationResult(Messages.NO_ROW_AFFECTED);
		} else {
			//otteniamo la primary key generata dal DBMS
			ResultSet primaryKey = ps.getGeneratedKeys();
			if (primaryKey.next()) {
				//aggiungiamo l'id all'istanza di Artista
				artist.setId(primaryKey.getInt(1));
				res = new OperationResult(Messages.PK_GENERATED, artist);
			} else {
				res = new OperationResult(Messages.NO_PK_GENERATED);
			}
		}
		//chiusura connessioni
		ps.close();
		dbm.closeConnection();
		return res;
	}

	//restituisce tutti gli artisti
	public OperationResult getAll() throws SQLException{
		//lista contenente gli artisti recuperati dal database
		List<Artista> artisti = new ArrayList<Artista>(); 
		//definizione della query SQL
		String sqlQuery = "SELECT * FROM artista ORDER BY id, nome";
		//apertura della connessione
		conn = dbm.openConnection();
		//precompilazione della query
		PreparedStatement ps = conn.prepareStatement(sqlQuery);
		//esecuzione della query
		ResultSet rs = ps.executeQuery();
		//analisi del resultset
		while(rs.next()) {
			Integer id = rs.getInt("id");
			String nome = rs.getString("nome");
			Date dataNascita = rs.getDate("dataNascita");
			int annoInizio = rs.getInt("annoInizio");
			int annoFine = rs.getInt("annoFine");
			artisti.add(new Artista(id, nome, dataNascita, annoInizio, annoFine));
		}
		if (!artisti.isEmpty()) {
			res = new OperationResult(Messages.GET_OPERATION_OK, artisti);
		} else {
			res = new OperationResult(Messages.GET_OPERATION_KO);
		}
		//chiusura connessioni
		rs.close();
		ps.close();
		dbm.closeConnection();
		return res;
	}
}