package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ManagerGiocatore;

/**
 * Servlet implementation class AggiornaProgresso
 */
@WebServlet("/AggiornaProgresso")
public class AggiornaProgresso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiornaProgresso() {
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
		int progresso=Integer.parseInt(request.getParameter("progresso"));
		String username=request.getParameter("username");
		try {
			ManagerGiocatore.aggiornaProgresso(progresso, username);
			writer.write("0");
		} catch (SQLException e) {
			writer.write("1");
			e.printStackTrace();
		}
	}

}
