package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ManagerParola;
import model.Parola;

/**
 * Servlet implementation class VisualizzaFrase
 */
@WebServlet("/VisualizzaFrase")
public class VisualizzaFrase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaFrase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		synchronized(session)
		{
			if(session.getAttribute("admin").equals("1"))
			{
				try {
					String id=(String) request.getParameter("id_frase");
					ArrayList<Parola> parole=ManagerParola.caricaParole(Integer.parseInt(id));
					session.setAttribute("id_frase", id);
					session.setAttribute("parole", parole);
					request.getRequestDispatcher("visualizzaFrase.jsp").forward(request, response);
				} catch (SQLException e) {
					request.getRequestDispatcher("visualizzaFrase.jsp").forward(request, response);
					e.printStackTrace();
				}
			}
			else
			{
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
