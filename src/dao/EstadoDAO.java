/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import excecao.BDException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Cidade;
import modelo.Estado;
import util.ControlaConexao;

/**
 *
 * @author AntonioF
 */
public class EstadoDAO {
    private List<Estado> estados;

    
    public void inserir(Estado estado) throws BDException{
        String inserirEstado = "INSERT INTO ESTADO(estadoNome) VALUES(?)";
        Connection conexao = null;
        PreparedStatement instrucao = null;
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(inserirEstado);
            
            instrucao.setString(1, estado.getNome());
            
            instrucao.execute();           
        } catch(SQLException e){
            throw new BDException(e);
        }  finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }    
    }
 
    public void alterar(Estado estado) throws BDException{
        String alterarEstado = "UPDATE ESTADO SET estadoNome = ? WHERE estadoId = ?";
        Connection conexao = null;
        PreparedStatement instrucao = null;        
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(alterarEstado);
            
            instrucao.setString(1, estado.getNome());
            instrucao.setLong(2, estado.getId());
            
            instrucao.execute();
        } catch(SQLException e){
            throw new BDException(e);  
        }  finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }  
    }
    
    public List<Estado> listar() throws BDException{
        String listarEstados = "SELECT estadoId, estadoNome FROM ESTADO";
        estados = new ArrayList<Estado>();
        Connection conexao = null;
        PreparedStatement instrucao = null;        
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(listarEstados);
            ResultSet results = instrucao.executeQuery();
            while(results.next()){
                Estado estado = new Estado();
                estado.setId(results.getLong("estadoId"));
                estado.setNome(results.getString("estadoNome"));
    
                estados.add(estado);
            } 
        } catch(SQLException e){
            throw new BDException(e);    
        } finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }  
        return estados;
    } 
    
    public void deletar(Estado estado) throws BDException{
        String deletarEstado = "DELETE FROM ESTADO WHERE estadoId = ?";
        Connection conexao = null;
        PreparedStatement instrucao = null;         
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(deletarEstado);           
            instrucao.setLong(1, estado.getId());           
            instrucao.execute();          
        } catch(SQLException e){
            throw new BDException(e);  
        }  finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }     
    }
    
    public Estado buscarPorId(long id) throws BDException{
        String buscarEstado = "SELECT * FROM ESTADO WHERE estadoId = ?";
        Estado estado = null;
        Connection conexao = null;
        PreparedStatement instrucao = null;         
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(buscarEstado);           
            instrucao.setLong(1, id);
            ResultSet results = instrucao.executeQuery();   
            while(results.next()){
                estado = new Estado();
                estado.setId(results.getLong("estadoId"));
                estado.setNome(results.getString("estadoNome"));
                return estado;
            }             
        } catch(SQLException e){
            throw new BDException(e);   
        }  finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }          
        return estado;
    }
    
    public List<Estado> pesquisar(Estado estado) throws BDException{
        List<Estado> estados;
        Estado estadoSaida;
        
        String listarEstados = "SELECT * FROM estado where estadoNome like ?";
        
        Connection conexao = null;
        PreparedStatement instrucao = null;        
        ResultSet resultados=null;
        
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(listarEstados);
            instrucao.setString(1,"%"+ estado.getNome()+"%");
            resultados = instrucao.executeQuery();
                      
            estados = new ArrayList<Estado>();
            
            while(resultados.next()){
                estadoSaida = new Estado();
                
                estadoSaida.setId(resultados.getLong("estadoId"));
                estadoSaida.setNome(resultados.getString("estadoNome"));
                estados.add(estadoSaida);
            } 
        } catch(SQLException e){
            throw new BDException(e);   
        }  finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }
        return estados;
    }
    
    
}
