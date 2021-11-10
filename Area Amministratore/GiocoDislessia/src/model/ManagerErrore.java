package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerErrore {

	public synchronized static void aggiungiErrore(Errore errore) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO Errore (frase, parola, distrattore, giocatore) VALUES (?,?,?,?);";
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, errore.getFrase());
			preparedStatement.setInt(2, errore.getParola());
			preparedStatement.setInt(3, errore.getDistrattore());
			preparedStatement.setString(4, errore.getGiocatore());
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
	
	public synchronized static ArrayList<Errore> restituisciErrori() throws SQLException 
	{
		ArrayList<Errore> errori=new ArrayList<Errore>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String SQL = "SELECT * FROM Errore;";
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL);
			ResultSet rs=preparedStatement.executeQuery(SQL);
			while(rs.next())
			{
				errori.add(new Errore(rs.getInt(2),ManagerParola.trovaParola(rs.getInt(3)),ManagerParola.trovaParola(rs.getInt(4)),rs.getString(5)));
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
		return errori;
	}
	
}
