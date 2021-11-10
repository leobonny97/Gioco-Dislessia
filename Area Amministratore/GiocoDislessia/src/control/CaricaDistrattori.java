package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Distrattore;
import model.ManagerParola;

/**
 * Servlet implementation class CaricaDistrattori
 */
@WebServlet("/CaricaDistrattori")
public class CaricaDistrattori extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaricaDistrattori() {
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
		int parola=Integer.parseInt(request.getParameter("parola"));
		try {
			ArrayList<Distrattore> result=ManagerParola.caricaDistrattori(parola);
			if(result==null)
			{
				writer.write("-2");
			}
			else
			{
				for(Distrattore c : result)
				{
					writer.write("" + c.getId() + "\t");
					writer.write("" + c.getStringa() + "\t");
				}
			}
		} catch (SQLException e) {
			writer.write("-1");
			e.printStackTrace();
		}
	}

}
