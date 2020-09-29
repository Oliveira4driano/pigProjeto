/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author AntonioF
 */
public class ControlaConexao {

    public static Connection getConexao() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost/pigprova", "root", "root");
    }
    
    public static void fecharInstrucao(PreparedStatement instrucao){
        if(instrucao != null){
            try{
                instrucao.close();
            } catch(SQLException e){
                System.out.println("Erro: " + e.getMessage());
            }
        }        
    }
    
    public static void fecharConexao(Connection conexao){
        if(conexao != null){
            try{
                conexao.close();
            } catch(SQLException e){
                System.out.println("Erro: " + e.getMessage());
            }
        }   
    }
    
}
