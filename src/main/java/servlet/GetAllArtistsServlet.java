package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

/**
 * Servlet implementation class GetAllArtistsServlet
 */
@WebServlet("/GetAllArtistsServlet")
public class GetAllArtistsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ottengo tutti gli artisti
		OperationResult res = null;
		ArtistaManager mng = new ArtistaManager();
		try {
			res = mng.getAll();
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