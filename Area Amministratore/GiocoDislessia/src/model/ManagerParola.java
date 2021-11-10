package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerParola {

	public synchronized static ArrayList<Parola> caricaParola(int frase) throws SQLException 
	{
		ArrayList<Parola> result=null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String SQL = "SELECT Composizione.frase AS frase, Composizione.parola AS parola, Composizione.ordine AS ordine, Parola.parola AS stringa FROM Composizione INNER JOIN Parola ON Composizione.parola=Parola.id WHERE Composizione.frase='" + frase + "' ORDER BY 'Composizione.ordine';";
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL);
			ResultSet rs=preparedStatement.executeQuery(SQL);
			if(rs.next())
			{
				result=new ArrayList<Parola>();
				result.add(new Parola(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4)));
				while(rs.next())
				{
					result.add(new Parola(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4)));
				}
			}
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				ConnectionPool.releaseConnection(connection);
			}
		}
		return result;
	}
	
	public synchronized static ArrayList<Distrattore> caricaDistrattori(int parola) throws SQLException 
	{
		ArrayList<Distrattore> result=null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String SQL = "SELECT Associazione.distrattore AS distrattore, Parola.parola AS stringa FROM Associazione INNER JOIN Parola ON Associazione.distrattore=Parola.id WHERE Associazione.parola='" + parola + "';";
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL);
			ResultSet rs=preparedStatement.executeQuery(SQL);
			if(rs.next())
			{
				result=new ArrayList<Distrattore>();
				result.add(new Distrattore(rs.getInt(1),rs.getString(2)));
				while(rs.next())
				{
					result.add(new Distrattore(rs.getInt(1),rs.getString(2)));
				}
			}
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				ConnectionPool.releaseConnection(connection);
			}
		}
		return result;
	}
	
	public synchronized static String trovaParola(int parola) throws SQLException 
	{
		String result=null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String SQL = "SELECT parola FROM parola WHERE id=" + parola + ";";
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL);
			ResultSet rs=preparedStatement.executeQuery(SQL);
			if(rs.next())
			{
				result=rs.getString(1);
			}
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				ConnectionPool.releaseConnection(connection);
			}
		}
		return result;
	}
	
	public synchronized static ArrayList<Parola> caricaParole(int frase) throws SQLException 
	{
		ArrayList<Parola> parole=null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String SQL = "SELECT Composizione.parola AS id, Composizione.ordine AS ordine, Parola.parola AS parola FROM Composizione INNER JOIN Parola ON Composizione.parola=Parola.id WHERE Composizione.frase='" + frase + "' ORDER BY 'Composizione.ordine';";
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL);
			ResultSet rs=preparedStatement.executeQuery(SQL);
			if(rs.next())
			{
				parole=new ArrayList<Parola>();
				ArrayList<Distrattore> distrattori=ManagerParola.caricaDistrattori(rs.getInt(1));
				parole.add(new Parola(frase,rs.getInt(1),rs.getInt(2),rs.getString(3),distrattori));
				while(rs.next())
				{
					distrattori=ManagerParola.caricaDistrattori(rs.getInt(1));
					parole.add(new Parola(frase,rs.getInt(1),rs.getInt(2),rs.getString(3),distrattori));
				}
			}
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				ConnectionPool.releaseConnection(connection);
			}
		}
		return parole;
	}
	
	public synchronized static void aggiungiParola(Parola parola, int id_frase) throws SQLException 
	{
		if(!ManagerParola.trovaParolaByString(parola.getStringa()))
		{
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String insertSQL = "INSERT INTO Parola (parola) VALUES (?);";
			try {
				connection = ConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setString(1, parola.getStringa());
				preparedStatement.executeUpdate();
				connection.commit();
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					ConnectionPool.releaseConnection(connection);
				}
			}	
		}
		int id_parola=ManagerParola.getIdByString(parola.getStringa());
		if(parola.getDistrattori()!=null)
		{			
			if(id_parola!=-1)
			{
				if(!ManagerAssociazione.trovaAssociazione(id_parola))
				{
					ManagerParola.aggiungiDistrattore(parola.getDistrattori().get(0).getStringa());
					ManagerParola.aggiungiDistrattore(parola.getDistrattori().get(1).getStringa());
					int id_distrattore1=ManagerParola.getIdByString(parola.getDistrattori().get(0).getStringa());
					int id_distrattore2=ManagerParola.getIdByString(parola.getDistrattori().get(1).getStringa());
					ManagerAssociazione.aggiungiAssociazione(id_parola, id_distrattore1);
					ManagerAssociazione.aggiungiAssociazione(id_parola, id_distrattore2);
				}
			}
		}
		if(id_parola!=-1)
		{
			ManagerComposizione.aggiungiComposizione(id_frase, id_parola, parola.getOrdine());
		}
	}
	
	public synchronized static void aggiungiDistrattore(String distrattore) throws SQLException 
	{
		if(!ManagerParola.trovaParolaByString(distrattore))
		{
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String insertSQL = "INSERT INTO Parola (parola) VALUES (?);";
			try {
				connection = ConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setString(1, distrattore);
				preparedStatement.executeUpdate();
				connection.commit();
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					ConnectionPool.releaseConnection(connection);
				}
			}	
		}
	}
	
	public synchronized static Boolean trovaParolaByString(String parola) throws SQLException 
	{
		Boolean result=false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String SQL = "SELECT * FROM parola WHERE parola='" + parola + "';";
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL);
			ResultSet rs=preparedStatement.executeQuery(SQL);
			if(rs.next())
			{
				result=true;
			}
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				ConnectionPool.releaseConnection(connection);
			}
		}
		return result;
	}
	
	public synchronized static int getIdByString(String parola) throws SQLException 
	{
		int result=-1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String SQL = "SELECT id FROM Parola WHERE parola='" + parola + "';";
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL);
			ResultSet rs=preparedStatement.executeQuery(SQL);
			if(rs.next())
			{
				result=rs.getInt(1);
			}
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				ConnectionPool.releaseConnection(connection);
			}
		}
		return result;
	}
}
