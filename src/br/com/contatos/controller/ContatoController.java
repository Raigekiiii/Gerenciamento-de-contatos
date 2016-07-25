package br.com.contatos.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.com.contatos.helper.MainSqlConnect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ContatoController implements Initializable{

	@FXML TextField txtNome;
	@FXML TextField txtTel;
	@FXML Button btn;
	@FXML ListView lstcontatos;
	
	private void preencherLista(){
		
		lstcontatos.getItems().clear();
		
		Connection con = MainSqlConnect.ConectarDb();
		String sql ="select * from contact";
		try {
			ResultSet rs = con.createStatement().executeQuery(sql);
			
			while(rs.next()){// pegando as posicoes do vetor
				String nome = rs.getString("name");
				String tel = rs.getString("phone");
				
				lstcontatos.getItems().add(nome + " - " + tel);
				
			}
			
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	@FXML public void inserirContato() {

		Connection con= MainSqlConnect.ConectarDb();
		
		String sql= "insert into contact(name,phone)values( ?, ?);";
		PreparedStatement parametros;
		try {
			
			parametros=con.prepareStatement(sql);
			parametros.setString(1, txtNome.getText());
			parametros.setString(2, txtTel.getText());
			
			parametros.executeUpdate();
			con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		preencherLista();
		//String contato = txtNome.getText() + " - " + txtTel.getText();
		//lstcontatos.getItems().add(contato);
			
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		preencherLista();
	}

}