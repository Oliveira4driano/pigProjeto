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
import modelo.ItemVenda;
import modelo.Produto;
import modelo.Venda;
import util.ControlaConexao;

/**
 *
 * @author AntonioF
 */
public class ItemVendaDAO {
    private List<ItemVenda> itensVendas;
    
    public void inserir(ItemVenda itemVenda) throws BDException{
        String inserirItemVenda = "INSERT INTO ITEMVENDA ( itemVendaQuantidade, itemVendaProdutoId, itemVendaVendaId ) VALUES(?,?,?)";
        Connection conexao = null;
        PreparedStatement instrucao = null;
        
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(inserirItemVenda);
            instrucao.setInt(1, itemVenda.getQtde());
            instrucao.setLong(2, itemVenda.getProduto().getId());
            instrucao.setLong(3, itemVenda.getVenda().getId());
            instrucao.execute();
            
        }catch(SQLException e){
            throw new BDException(e);
        } finally{
            ControlaConexao.fecharConexao(conexao);
            ControlaConexao.fecharInstrucao(instrucao);                    
        }
    }
    
    public List<ItemVenda> listar() throws BDException{
        String listarItensVendas = "SELECT itemVendaId, itemVendaQuantidade, itemVendaProdutoId, itemVendaVendaId FROM ITEMVENDA";
        Connection conexao = null;
        PreparedStatement instrucao = null;
        
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(listarItensVendas);
            itensVendas = new ArrayList<ItemVenda>();
            ResultSet results = instrucao.executeQuery();
            
            VendaDAO vendaDAO = new VendaDAO();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            while(results.next()){
                
                Venda venda = vendaDAO.buscarPorId(results.getLong("itemVendaVendaId"));
                Produto produto = produtoDAO.buscarPorId(results.getLong("itemVendaProdutoId"));
                
                ItemVenda itemVenda = new ItemVenda();
                itemVenda.setId(results.getLong("itemVendaId"));
                itemVenda.setQtde(results.getInt("itemVendaQuantidade"));
                itemVenda.setProduto(produto);
                itemVenda.setVenda(venda);
                
                itensVendas.add(itemVenda);
            }
        }catch(SQLException e){
            throw new BDException(e);
        } finally{
            ControlaConexao.fecharConexao(conexao);
            ControlaConexao.fecharInstrucao(instrucao);
        }
        return itensVendas;
    }
    
   public List<ItemVenda> listar(Venda venda) throws BDException{
        String listarItensVendas = "SELECT itemVendaId, itemVendaQuantidade, itemVendaProdutoId, itemVendaVendaId FROM ITEMVENDA where itemVendaVendaId = ?";
        Connection conexao = null;
        PreparedStatement instrucao = null;
       
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(listarItensVendas);
            instrucao.setLong(1, venda.getId());
            itensVendas = new ArrayList<ItemVenda>();
            
            ResultSet results = instrucao.executeQuery();
            
            VendaDAO vendaDAO = new VendaDAO();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            while(results.next()){
                
                venda = vendaDAO.buscarPorId(results.getLong("itemVendaVendaId"));
                Produto produto = produtoDAO.buscarPorId(results.getLong("itemVendaProdutoId"));
                
                ItemVenda itemVenda = new ItemVenda();
                itemVenda.setId(results.getLong("itemVendaId"));
                itemVenda.setQtde(results.getInt("itemVendaQuantidade"));
                itemVenda.setProduto(produto);
                itemVenda.setVenda(venda);
                
                itensVendas.add(itemVenda);
            }
        }catch(SQLException e){
            throw new BDException(e);
        } finally{
            ControlaConexao.fecharConexao(conexao);
            ControlaConexao.fecharInstrucao(instrucao);
        }
        return itensVendas;
    } 
   public void alterar(ItemVenda itemVenda) throws BDException{
       String alterarItemVenda = "UPDATE ITEMVENDA SET itemVendaQuantidade = ?, itemVendaProdutoId = ?, itemVendaVendaId = ?";
       Connection conexao = null;
       PreparedStatement instrucao = null;
       
       try{
           conexao = ControlaConexao.getConexao();
           instrucao = conexao.prepareStatement(alterarItemVenda);
           instrucao.setInt(1, itemVenda.getQtde());
           instrucao.setLong(2, itemVenda.getProduto().getId());
           instrucao.setLong(3, itemVenda.getVenda().getId());
           instrucao.execute();
       }catch(SQLException e){
           throw new BDException(e);
       } finally{
           ControlaConexao.fecharConexao(conexao);
           ControlaConexao.fecharInstrucao(instrucao);
       }
   }
   
   public void deletar(ItemVenda itemVenda) throws BDException{
       String deletarItemVenda = "DELETE FROM ITEMVENDA WHERE itemVendaId = ? and itemVendaVendaId = ?";
       Connection conexao = null;
       PreparedStatement instrucao = null;
       
       try{
           conexao = ControlaConexao.getConexao();
           instrucao = conexao.prepareStatement(deletarItemVenda);
           instrucao.setLong(1, itemVenda.getId());
           instrucao.setLong(2, itemVenda.getVenda().getId());
           instrucao.execute();
       }catch(SQLException e){
           throw new BDException(e);
       } finally{
           ControlaConexao.fecharConexao(conexao);
           ControlaConexao.fecharInstrucao(instrucao);
       }
       
   }
    
}
