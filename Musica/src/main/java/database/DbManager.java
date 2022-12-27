package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//serve per creare delle abbreviazioni per connettersi al db

public class DbManager {

	//driver è una classe che consente al codice Java di comunicare con mySQL, quindi due sistemi diversi comunicano attraverso un protocollo tipo un traduttore
	//bisogna scaricarlo dalla rete  metterlo dentro la cartella di xammp poi tomcat e importarlo come jar dentro il progetto
	
	// costante
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	//username e psw del accesso al db
	//costante indirizzo database
	private static final String URL = "jdbc:mysql://localhost";
	
	//username deautiful
	private static final String USERNAME = "root";
	
	//password deatiful
	private static final String PASSWORD = "";
	
	//nome database
	private static final String CATALOG = "musica2122";
	
	//oggetto connection con var di istanza
	//importare classe da java.sql
	private Connection conn;
	
	//blocco statico per caricare il server in memoria
	{
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//creare metodi apertura e chiusura connessione
	
	public Connection openConnection() throws SQLException {
		conn = DriverManager.getConnection(URL , USERNAME , PASSWORD);
		conn.setCatalog(CATALOG);
	return conn;
	}
	
	//metodo chiusura connessione
	public void closeConnection() throws SQLException {
		conn.close();
	}
}
