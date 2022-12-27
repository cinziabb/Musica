package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.AlbumManager;
import database.ArtistaManager;
import database.ErrorCode;
import database.OperationResult;
import model.Album;
import model.Artista;

/**
 * Servlet implementation class InsertAlbumServlet
 */
@WebServlet("/InsertAlbumServlet")
public class InsertAlbumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recupero dei dati dal form
		String nomeAlbum = request.getParameter("nomeAlbum");
		String dataUscita = request.getParameter("dataUscita");
		Date sqlDataUscita = null;
		if (!dataUscita.isEmpty()) {
			sqlDataUscita = Date.valueOf(dataUscita);
		}
		String numTracce = request.getParameter("numTracce");
		int intNumTracce = 0;
		if (!numTracce.isEmpty()) {
			intNumTracce = Integer.parseInt(numTracce);
		}
		//recupero i dati dell'artista
		String artista = request.getParameter("artista");
		Gson gson = new Gson();
		Artista artist = gson.fromJson(artista, Artista.class);
		System.out.println("ARTISTA= " + artist); //debug

		//creo l'istanza di Album
		Album album = new Album(nomeAlbum, sqlDataUscita, intNumTracce);
		//aggiungo le FK 
		album.setArtist(artist);
		System.out.println("Istanza di Album=" + album); //debug

		//inserisco l'album
		OperationResult res = null;
		AlbumManager mng = new AlbumManager();
		try {
			res = mng.insert(album);
		} catch (SQLException e) {
			e.printStackTrace();
			int jdbcCode = e.getErrorCode();
			String jdbcMsg = e.getMessage();
			String msg = ErrorCode.getMessage(jdbcCode);
			res = new OperationResult(jdbcCode, msg + ": " + jdbcMsg.replace("'", ""));
		}
		System.out.println(res); //debug

		//converto res in una stringa JSON
		String jsonRes = gson.toJson(res);
		System.out.println("jsonRes=" + jsonRes); //debug

		//Invio della risposta al client
		PrintWriter writer = response.getWriter();
		writer.println(jsonRes);
		writer.close();	
	}

}
