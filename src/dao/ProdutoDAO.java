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
import modelo.Cliente;
import modelo.Produto;
import util.ControlaConexao;

/**
 *
 * @author AntonioF
 */
public class ProdutoDAO {
    private List<Produto> produtos;
    
    public void inserir(Produto produto) throws BDException{
        String inserirProduto = "INSERT INTO PRODUTO (produtoDescricao) VALUES(?)";
        Connection conexao = null;
        PreparedStatement instrucao = null;
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(inserirProduto);
            instrucao.setString(1, produto.getDescricao());
            instrucao.execute();
        } catch(SQLException e){
            throw new BDException(e);
        } finally{
            ControlaConexao.fecharConexao(conexao);
            ControlaConexao.fecharInstrucao(instrucao);
        }
    }
    
    public void alterar(Produto produto) throws BDException{
        String alterarProduto = "UPDATE PRODUTO SET produtoDescricao = ? WHERE produtoId = ?";
        Connection conexao = null;
        PreparedStatement instrucao = null;
        
        try{
          conexao = ControlaConexao.getConexao();
          instrucao = conexao.prepareStatement(alterarProduto);
          instrucao.setString(1, produto.getDescricao());
          instrucao.setLong(2, produto.getId());
          instrucao.execute();          
        } catch(SQLException e){
            throw new BDException(e);
        } finally{
            ControlaConexao.fecharConexao(conexao);
            ControlaConexao.fecharInstrucao(instrucao);
        }    
    }
    
    public List<Produto> listar() throws BDException{       
        String listarProdutos = "SELECT produtoId, produtoDescricao FROM PRODUTO";
        Connection conexao = null;
        PreparedStatement instrucao = null;
        
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(listarProdutos);
            ResultSet results = instrucao.executeQuery();
            produtos = new ArrayList<Produto>();
            while(results.next()){
                Produto produto = new Produto();
                produto.setId(results.getLong("produtoId"));
                produto.setDescricao(results.getString("produtoDescricao"));
                produtos.add(produto);
            }
           
        } catch(SQLException e){
            throw new BDException(e);
        } finally{
            ControlaConexao.fecharConexao(conexao);
            ControlaConexao.fecharInstrucao(instrucao);
        }  
        return produtos;
    }
    
     public Produto buscarPorId(long id) throws BDException{
        String buscarProduto = "SELECT * FROM PRODUTO WHERE produtoId = ?";
        Produto produto = null;
        Connection conexao = null;
        PreparedStatement instrucao = null;
        
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(buscarProduto);           
            instrucao.setLong(1, id);
            ResultSet results = instrucao.executeQuery();   
            
            while(results.next()){
                produto = new Produto();
                produto.setId(results.getLong("produtoId"));
                produto.setDescricao(results.getString("produtoDescricao"));
                 
                return produto;
            }             
        } catch(SQLException e){
            throw new BDException(e);   
        }  finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }          
        return null;
        
    }
    
    public void deletar(Produto produto) throws BDException{
        String deletarProduto = "DELETE FROM PRODUTO WHERE produtoId = ?";
        Connection conexao = null;
        PreparedStatement instrucao = null;
        
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(deletarProduto);
            instrucao.setLong(1, produto.getId());
            instrucao.execute();
        } catch(SQLException e){
            throw new BDException(e);
        } finally{
            ControlaConexao.fecharConexao(conexao);
            ControlaConexao.fecharInstrucao(instrucao);
        }
    
    }
    
    public List<Produto> pesquisar(Produto produto) throws BDException{
        List<Produto> produtos;
        Produto produtoSaida;
        
        String listarProdutos = "SELECT * FROM produto where produtoDescricao like ?";
        
        Connection conexao = null;
        PreparedStatement instrucao = null;        
        ResultSet resultados=null;
        
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(listarProdutos);
            instrucao.setString(1,"%"+ produto.getDescricao()+"%");
            resultados = instrucao.executeQuery();
                      
            produtos = new ArrayList<Produto>();
            
            while(resultados.next()){
                produtoSaida = new Produto();
                
                produtoSaida.setId(resultados.getLong("produtoId"));
                produtoSaida.setDescricao(resultados.getString("produtoDescricao"));
                produtos.add(produtoSaida);
            } 
        } catch(SQLException e){
            throw new BDException(e);   
        }  finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }
        return produtos;
    }
    

    
}
