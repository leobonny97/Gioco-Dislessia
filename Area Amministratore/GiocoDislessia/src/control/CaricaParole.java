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
import model.ManagerParola;
import model.Parola;

/**
 * Servlet implementation class CaricaParole
 */
@WebServlet("/CaricaParole")
public class CaricaParole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaricaParole() {
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
		try {
			ArrayList<Parola> result=ManagerParola.caricaParola(frase);
			if(result==null)
			{
				writer.write("-2");
			}
			else
			{
				for(Parola c : result)
				{
					writer.write("" + c.getFrase() + "\t");
					writer.write("" + c.getParola() + "\t");
					writer.write("" + c.getOrdine() + "\t");
					writer.write("" + c.getStringa() + "\t");
				}
			}
		} catch (SQLException e) {
			writer.write("-1");
			e.printStackTrace();
		}
	}

}
