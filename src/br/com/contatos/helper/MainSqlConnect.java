package br.com.contatos.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class MainSqlConnect {

	
	public static Connection ConectarDb(){
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://10.107.134.61/contatos","root","root");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}
