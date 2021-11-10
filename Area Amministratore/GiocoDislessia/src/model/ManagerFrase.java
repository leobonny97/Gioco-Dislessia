package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class ManagerFrase {
	
	public synchronized static ArrayList<Integer> caricaFrasi(int livello) throws SQLException 
	{
		ArrayList<Integer> result=null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String SQL = "SELECT id FROM Frase WHERE livello='" + livello + "';";
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL);
			ResultSet rs=preparedStatement.executeQuery(SQL);
			if(rs.next())
			{
				result=new ArrayList<Integer>();
				result.add(rs.getInt(1));
				while(rs.next())
				{
					result.add(rs.getInt(1));
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
	
	public synchronized static ArrayList<Frase> caricaAllFrasi() throws SQLException 
	{
		ArrayList<Frase> result=null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String SQL = "SELECT * FROM Frase;";
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL);
			ResultSet rs=preparedStatement.executeQuery(SQL);
			if(rs.next())
			{
				result=new ArrayList<Frase>();
				result.add(new Frase(rs.getInt(1),rs.getInt(2)));
				while(rs.next())
				{
					result.add(new Frase(rs.getInt(1),rs.getInt(2)));
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

	public synchronized static void rimuoviFrase(int id) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String removeSQL = "DELETE FROM Frase WHERE id=?;";
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(removeSQL);
			preparedStatement.setInt(1, id);
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
	
	public synchronized static void aggiungiFrase(int livello, ArrayList<Parola> parole) throws SQLException 
	{
		int id_frase=ManagerFrase.caricaIdUltimaFrase() + 1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO Frase (id,livello) VALUES (?,?);";
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, id_frase);
			preparedStatement.setInt(2, livello);
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
		for(Parola parola : parole)
		{
			ManagerParola.aggiungiParola(parola,id_frase);
		}		
	}
	
	public synchronized static int caricaIdUltimaFrase() throws SQLException 
	{
		int result=0;;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String SQL = "SELECT MAX(id) FROM Frase;";
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
