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

import database.ArtistaManager;
import database.ErrorCode;
import database.OperationResult;
import model.Artista;

/**
 * Servlet implementation class InsertArtistServlet
 */
@WebServlet("/InsertArtistServlet")
public class InsertArtistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recupero dei dati dal form
		String nome = request.getParameter("nome");
		String dataNascita = request.getParameter("dataNascita");
		Date sqlDataNascita = Date.valueOf(dataNascita);
		int annoInizio = Integer.parseInt(request.getParameter("annoInizio"));
		String annoFine = request.getParameter("annoFine");
		int annoF = 0;
		if (!annoFine.isEmpty()) {
			annoF = Integer.parseInt(annoFine);
		} 
		
		//creo l'istanza di Artista
		Artista artist = new Artista(nome, sqlDataNascita, annoInizio, annoF);
		System.out.println("Istanza di Artista=" + artist); //debug
		
		//inserisco l'artista
		OperationResult res = null;
		ArtistaManager mng = new ArtistaManager();
		try {
			res = mng.insert(artist);
		} catch (SQLException e) {
			e.printStackTrace();
			int jdbcCode = e.getErrorCode();
			String jdbcMsg = e.getMessage();
			String msg = ErrorCode.getMessage(jdbcCode);
			res = new OperationResult(jdbcCode, msg + ": " + jdbcMsg.replace("'", ""));
		}
		System.out.println(res); //debug
		
		//converto res in una stringa JSON
		Gson gson = new Gson();
		String jsonRes = gson.toJson(res);
		System.out.println("jsonRes=" + jsonRes); //debug
		
		//Invio della risposta al client
		PrintWriter writer = response.getWriter();
		writer.println(jsonRes);
		writer.close();	
	}

}
