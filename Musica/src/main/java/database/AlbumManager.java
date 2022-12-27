package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import model.Album;
import model.Artista;
import util.Messages;

public class AlbumManager {
	
	private DbManager dbm;
	private Connection conn = null;
	private OperationResult res = null;
		
	public AlbumManager() {
		this.dbm = new DbManager();
	}

	//inserisce un album 
	
	public OperationResult insert(Album album) throws SQLException{
		//definizione della query SQL
		String sqlQuery = "INSERT INTO album(nome, dataUscita, numTracce, idArtista) VALUES(?, ?, ?, ?)";
		//apertura della connessione
		conn = dbm.openConnection();
		//precompilazione della query
		PreparedStatement ps = conn.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, album.getNomeAlbum());
		ps.setDate(2, album.getDataUscita());
		ps.setInt(3, album.getNumTracce());
		if (album.getArtist() != null) {
			ps.setInt(4, album.getArtist().getId());
		} else {
			ps.setNull(4, Types.INTEGER);
		}
		//esecuzione della query
		int row = ps.executeUpdate();
		if (row == 0) {
			res = new OperationResult(Messages.NO_ROW_AFFECTED);
		} else {
			//otteniamo la primary key generata dal DBMS
			ResultSet primaryKey = ps.getGeneratedKeys();
			if (primaryKey.next()) {
				//aggiungiamo l'id all'istanza di Artista
		        album.setId(primaryKey.getInt(1));
		        res = new OperationResult(Messages.PK_GENERATED, album);
			} else {
				res = new OperationResult(Messages.NO_PK_GENERATED);
			}
		}
		//chiusura connessioni
		ps.close();
		dbm.closeConnection();
		return res;
	}	
	
}
