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

import model.Distrattore;
import model.ManagerFrase;
import model.Parola;

/**
 * Servlet implementation class AggiungiFrase
 */
@WebServlet("/AggiungiFrase")
public class AggiungiFrase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiFrase() {
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
		HttpSession session=request.getSession();
		synchronized(session)
		{
			if(session.getAttribute("admin").equals("1"))
			{
				try {
					ArrayList<Parola> parole=new ArrayList<Parola>();
					int livello=Integer.parseInt(request.getParameter("livello"));
					int c=0;
					do
					{
						ArrayList<Distrattore> distrattori=null;
						String parola=request.getParameter("parola" + c);
						String distrattore1=request.getParameter("dis" + c);
						if(!distrattore1.isEmpty())
						{
							distrattori=new ArrayList<Distrattore>();
							distrattori.add(new Distrattore(distrattore1));
							String distrattore2=request.getParameter("dist" + c);
							if(distrattore2.isEmpty())
							{
								distrattore2=distrattore1;
							}
							distrattori.add(new Distrattore(distrattore2));
						}
						parole.add(new Parola(c+1, parola, distrattori));
						c++;
					}while(request.getParameter("parola" + c)!=null);
					ManagerFrase.aggiungiFrase(livello, parole);
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

}
