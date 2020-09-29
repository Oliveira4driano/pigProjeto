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
import modelo.Cliente;
import modelo.Estado;
import util.ControlaConexao;

/**
 *
 * @author AntonioF
 */
public class CidadeDAO {
    private List<Cidade> cidades;
    private EstadoDAO estadoDAO;
    
    public void inserir(Cidade cidade) throws BDException{
        String inserirCidade = "INSERT INTO CIDADE(cidadeNome, cidadeEstadoId) VALUES(?,?)";
        Connection conexao = null;
        PreparedStatement instruction = null;          
        try{
            conexao = ControlaConexao.getConexao();
            instruction = conexao.prepareStatement(inserirCidade);
            
            instruction.setString(1, cidade.getNome());
            instruction.setLong(2, cidade.getEstado().getId());
            instruction.execute();           
        } catch(SQLException e){
            throw new BDException(e);   
        }  finally{
            ControlaConexao.fecharInstrucao(instruction);
            ControlaConexao.fecharConexao(conexao);
        }   
    }
 
    public void alterar(Cidade cidade) throws BDException{
        String alterarCidade = "UPDATE CIDADE SET cidadeNome = ?, cidadeEstadoId = ? WHERE cidadeId = ?";
        Connection conexao = null;
        PreparedStatement instruction = null;          
        try{
            conexao = ControlaConexao.getConexao();
            instruction = conexao.prepareStatement(alterarCidade);
            
            instruction.setString(1, cidade.getNome());
            instruction.setLong(2, cidade.getEstado().getId());
            instruction.setLong(3, cidade.getId());
            
            instruction.execute();
        } catch(SQLException e){
            throw new BDException(e);   
        }  finally{
            ControlaConexao.fecharInstrucao(instruction);
            ControlaConexao.fecharConexao(conexao);
        }
    }
    
    public List<Cidade> listar() throws BDException{
        String listarCidades = "SELECT cidadeId, cidadeNome, cidadeEstadoId FROM CIDADE";
        cidades = new ArrayList<Cidade>();
        Connection conexao = null;
        PreparedStatement instrucao = null;        
        
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(listarCidades);
            ResultSet results = instrucao.executeQuery();
            estadoDAO = new EstadoDAO();
           
            while(results.next()){
                Cidade cidade = new Cidade();
                Estado estado = new Estado();
                cidade.setId(results.getLong("cidadeId"));
                cidade.setNome(results.getString("cidadeNome"));
                estado = estadoDAO.buscarPorId(results.getLong("cidadeEstadoId"));
                cidade.setEstado(estado);
                cidades.add(cidade);
            } 
        } catch(SQLException e){
            throw new BDException(e);   
        }  finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }
        return cidades;
    } 
    
    public void deletar(Cidade cidade) throws BDException{
        String deletarCidade = "DELETE FROM CIDADE WHERE cidadeId = ?";
        Connection conexao = null;
        PreparedStatement instrucao = null;          
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(deletarCidade);           
            instrucao.setLong(1, cidade.getId());
            instrucao.execute();          
        } catch(SQLException e){
            throw new BDException(e);   
        }  finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }  
    }
  
    public Cidade buscarPorId(long id) throws BDException{
        String buscarEstado = "SELECT * FROM CIDADE WHERE cidadeId = ?";
        Cidade cidade = null;
        Connection conexao = null;
        PreparedStatement instrucao = null;
        
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(buscarEstado);           
            instrucao.setLong(1, id);
            ResultSet results = instrucao.executeQuery();   
            estadoDAO = new EstadoDAO();
            while(results.next()){
                cidade = new Cidade();
                cidade.setId(results.getLong("cidadeId"));
                cidade.setNome(results.getString("cidadeNome"));
                cidade.setEstado(estadoDAO.buscarPorId(results.getLong("cidadeEstadoId")));
                return cidade;
            }             
        } catch(SQLException e){
            throw new BDException(e);   
        }  finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }          
        return null;
        
    }    
    
    public List<Cidade> pesquisar(Cidade cidade) throws BDException{
        List<Cidade> cidades;
        Cidade cidadeSaida;
        EstadoDAO estadoDAO;
        
        String listarCidades = "SELECT cidadeId, cidadeNome, cidadeEstadoId FROM CIDADE where cidadeNome like ?";
        
        Connection conexao = null;
        PreparedStatement instrucao = null;        
        ResultSet resultados=null;
        
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(listarCidades);
            instrucao.setString(1,"%"+ cidade.getNome()+"%");
            System.out.println(cidade.getNome());
            resultados = instrucao.executeQuery();
            estadoDAO = new EstadoDAO();
           
            cidades = new ArrayList<Cidade>();
            
            while(resultados.next()){
                cidadeSaida = new Cidade();
 
                cidadeSaida.setId(resultados.getLong("cidadeId"));
                cidadeSaida.setNome(resultados.getString("cidadeNome"));
                
                Estado estado = new Estado();
                estado = estadoDAO.buscarPorId(resultados.getLong("cidadeEstadoId"));
                
                cidadeSaida.setEstado(estado);
                cidades.add(cidadeSaida);
            } 
        } catch(SQLException e){
            throw new BDException(e);   
        }  finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }
        return cidades;
    }

   
    
        
}
