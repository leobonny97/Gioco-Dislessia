package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Errore;
import model.ManagerErrore;

/**
 * Servlet implementation class AddErrore
 */
@WebServlet("/AddErrore")
public class AddErrore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddErrore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		int frase=Integer.parseInt(request.getParameter("frase"));
		int parola=Integer.parseInt(request.getParameter("parola"));
		int distrattore=Integer.parseInt(request.getParameter("distrattore"));
		String giocatore=request.getParameter("giocatore");
		Errore errore=new Errore(frase, parola, distrattore, giocatore);
		try {
			ManagerErrore.aggiungiErrore(errore);
			writer.write("0");
		} catch (SQLException e) {
			writer.write("1");
			e.printStackTrace();
		}
	}

}
