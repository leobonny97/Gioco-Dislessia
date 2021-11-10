package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManagerComposizione {
	public synchronized static void aggiungiComposizione(int frase, int parola, int ordine) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO Composizione (frase,parola,ordine) VALUES (?,?,?);";
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, frase);
			preparedStatement.setInt(2, parola);
			preparedStatement.setInt(3, ordine);
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
