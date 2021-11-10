package control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ManagerFrase;

/**
 * Servlet implementation class RimuoviFrase
 */
@WebServlet("/RimuoviFrase")
public class RimuoviFrase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimuoviFrase() {
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
					System.out.println(id);
					ManagerFrase.rimuoviFrase(Integer.parseInt(id));
					request.getRequestDispatcher("VisualizzaFrasi").forward(request, response);
				} catch (SQLException e) {
					request.getRequestDispatcher("visualizzaFrasi.jsp").forward(request, response);
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
		
	}

}
