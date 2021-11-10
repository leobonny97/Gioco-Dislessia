package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class ManagerGiocatore {
	
	public synchronized static void aggiungiGiocatore(Giocatore giocatore) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		//String insertSQL = "SELECT * FROM Giocatore WHERE username=?;";
		String insertSQL = "INSERT INTO Giocatore (username, password, progresso) VALUES (?,?,?);";
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, giocatore.getUsername());
			preparedStatement.setString(2, giocatore.getPassword());
			preparedStatement.setInt(3, giocatore.getProgresso());
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
	
	public synchronized static boolean trovaUsername(String username) throws SQLException 
	{
		boolean result=false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String SQL = "SELECT * FROM Giocatore WHERE username='" + username + "';";
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

	public synchronized static int trovaGiocatore(Giocatore giocatore) throws SQLException 
	{
		int result;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String SQL = "SELECT progresso FROM Giocatore WHERE username='" + giocatore.getUsername() + "' AND password='" + giocatore.getPassword() + "';";
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL);
			ResultSet rs=preparedStatement.executeQuery(SQL);
			if(rs.next())
			{
				result=rs.getInt(1);
			}
			else
			{
				result=0;
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
	
	public synchronized static void aggiornaProgresso(int progresso, String username) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL = "UPDATE Giocatore SET progresso=? WHERE username=?;";
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setInt(1, progresso);
			preparedStatement.setString(2, username);
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
	
	public synchronized static ArrayList<Giocatore> restituisciGiocatori() throws SQLException 
	{
		ArrayList<Giocatore> giocatori=new ArrayList<Giocatore>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String SQL = "SELECT * FROM Giocatore;";
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL);
			ResultSet rs=preparedStatement.executeQuery(SQL);
			while(rs.next())
			{
				giocatori.add(new Giocatore(rs.getString(1),rs.getString(2),rs.getInt(3)));
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
		return giocatori;
	}
	
}
