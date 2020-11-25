package br.com.HealthTrack.Singleton;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	private static ConnectionManager instance;
	
	public static ConnectionManager getInstance() {
		if(instance == null) {
			instance = new ConnectionManager();
		}
		
		return instance;
	}
	
	public Connection getConnection() {
		Connection conexao = null;
	      try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	  
//	        conexao = DriverManager.getConnection(
//		            "jdbc:oracle:thin:@127.0.0.1:1521/orcl_teste", "SYSTEM", "1234");
	        conexao = DriverManager.getConnection(
		            "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "RM84893", "310791");
	        
	  
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	      return conexao;
	}
	
	
}
