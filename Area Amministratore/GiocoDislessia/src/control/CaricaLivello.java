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

import model.ManagerFrase;

/**
 * Servlet implementation class CaricaLivello
 */
@WebServlet("/CaricaLivello")
public class CaricaLivello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaricaLivello() {
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
		int livello=Integer.parseInt(request.getParameter("livello"));
		try {
			ArrayList<Integer> result=ManagerFrase.caricaFrasi(livello);
			if(result==null)
			{
				writer.write("-2");
			}
			else
			{
				for(int c : result)
				{
					writer.write("" + c + "\t");
				}
			}
		} catch (SQLException e) {
			writer.write("-1");
			e.printStackTrace();
		}

		
	}

}
