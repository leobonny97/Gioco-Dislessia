package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerAssociazione {
	public synchronized static Boolean trovaAssociazione(int id_parola) throws SQLException 
	{
		Boolean result=false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String SQL = "SELECT * FROM Associazione WHERE parola='" + id_parola + "';";
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
	
	public synchronized static void aggiungiAssociazione(int id_parola, int id_distrattore) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO Associazione (parola,distrattore) VALUES (?,?);";
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, id_parola);
			preparedStatement.setInt(2, id_distrattore);
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
