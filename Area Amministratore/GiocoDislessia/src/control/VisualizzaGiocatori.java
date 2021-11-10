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

import model.Giocatore;
import model.ManagerGiocatore;

/**
 * Servlet implementation class VisualizzaGiocatori
 */
@WebServlet("/VisualizzaGiocatori")
public class VisualizzaGiocatori extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaGiocatori() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		synchronized(session)
		{
			if(session.getAttribute("admin").equals("1"))
			{
				try {
					ArrayList<Giocatore> giocatori=ManagerGiocatore.restituisciGiocatori();
					if(giocatori!=null)
					{
						session.setAttribute("giocatori", giocatori);
						request.getRequestDispatcher("giocatori.jsp").forward(request, response);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
	}

}
