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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Cliente;
import util.ControlaConexao;

/**
 *
 * @author AntonioF
 */
public class ClienteDAO {
    private List<Cliente> clientes;
    private ClienteDAO clienteDAO;
    private CidadeDAO cidadeDAO;
    
    public void inserir(Cliente cliente) throws BDException{
        String inserirCliente = "INSERT INTO CLIENTE (clienteNome, clienteEndereco, clienteTelefone, clienteCidadeId) values (?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement instrucao = null;          
        try {
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(inserirCliente);
            instrucao.setString(1, cliente.getNome());
            instrucao.setString(2, cliente.getEndereco());
            instrucao.setString(3, cliente.getTelefone());
            instrucao.setLong(4, cliente.getCidade().getId());
            instrucao.execute();
        } catch (SQLException e) {
            throw new BDException(e);   
        }  finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }
    }
    
    public void alterar(Cliente cliente) throws BDException{
        String alterarCliente = "UPDATE CLIENTE SET clienteNome = ?, clienteEndereco = ?, clienteTelefone = ?, clienteCidadeId = ? WHERE clienteId = ?";
        Connection conexao = null;
        PreparedStatement instrucao = null;   
        
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(alterarCliente);
            instrucao.setString(1, cliente.getNome());
            instrucao.setString(2, cliente.getEndereco());
            instrucao.setString(3, cliente.getTelefone());
            instrucao.setLong(4, cliente.getCidade().getId());
            instrucao.setLong(5, cliente.getId());
            instrucao.execute();
        } catch (SQLException e){
            throw new BDException(e);  
        } finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);        
        }
    
    
    }
    
    public List<Cliente> listar() throws BDException{
        String listarClientes = "SELECT clienteId, clienteNome, clienteEndereco, clienteTelefone, clienteCidadeId FROM CLIENTE";
        clientes = new ArrayList<Cliente>();
        CidadeDAO cidadeDAO = new CidadeDAO();
        Connection conexao = null;
        PreparedStatement instrucao = null;        
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(listarClientes);
            ResultSet results = instrucao.executeQuery();
            while(results.next()){
                Cliente cliente = new Cliente();
                cliente.setId(results.getLong("clienteId"));
                cliente.setNome(results.getString("clienteNome"));
                cliente.setEndereco(results.getString("clienteEndereco"));
                cliente.setTelefone(results.getString("clienteTelefone"));
                cliente.setCidade(cidadeDAO.buscarPorId(results.getLong("clienteCidadeId")));    
                clientes.add(cliente);
            } 
        } catch(SQLException e){
            throw new BDException(e);    
        } finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }  
        return clientes;
    }
    
    public void deletar(Cliente cliente) throws BDException{
        String deletarCliente = "DELETE FROM CLIENTE WHERE clienteId = ?";
        Connection conexao = null;
        PreparedStatement instrucao = null;          
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(deletarCliente);           
            instrucao.setLong(1, cliente.getId());
            instrucao.execute();          
        } catch(SQLException e){
            throw new BDException(e);   
        }  finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }  
    }
    
    public Cliente buscarPorId(long id) throws BDException{
        String buscarEstado = "SELECT * FROM CLIENTE WHERE clienteId = ?";
        Cliente cliente = null;
        Connection conexao = null;
        PreparedStatement instrucao = null;
        
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(buscarEstado);           
            instrucao.setLong(1, id);
            ResultSet results = instrucao.executeQuery();   
            clienteDAO = new ClienteDAO();
            cidadeDAO = new CidadeDAO();
            while(results.next()){
                cliente = new Cliente();
                cliente.setId(results.getLong("clienteId"));
                cliente.setNome(results.getString("clienteNome"));
                cliente.setEndereco(results.getString("clienteEndereco"));
                cliente.setTelefone(results.getString("clienteTelefone"));
                cliente.setCidade(cidadeDAO.buscarPorId(results.getLong("clienteCidadeId")));   
                return cliente;
            }             
        } catch(SQLException e){
            throw new BDException(e);   
        }  finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }          
        return null;
        
    }    
        public List<Cliente> pesquisar(Cliente cliente) throws BDException{
        List<Cliente> clientes;
        Cliente clienteSaida;
        CidadeDAO cidadeDAO = new CidadeDAO();
        
        String listarClientes = "SELECT * FROM cliente where clienteNome like ?";
        
        Connection conexao = null;
        PreparedStatement instrucao = null;        
        ResultSet resultados=null;
        
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(listarClientes);
            instrucao.setString(1,"%"+ cliente.getNome()+"%");
            resultados = instrucao.executeQuery();
                      
            clientes = new ArrayList<Cliente>();
            
            while(resultados.next()){
                clienteSaida = new Cliente();
                
                clienteSaida.setId(resultados.getLong("clienteId"));
                clienteSaida.setNome(resultados.getString("clienteNome"));
                clienteSaida.setEndereco(resultados.getString("clienteEndereco"));
                clienteSaida.setTelefone(resultados.getString("clienteTelefone"));
                clienteSaida.setCidade(cidadeDAO.buscarPorId(resultados.getLong("clienteCidadeId")));
                clientes.add(clienteSaida);
            } 
        } catch(SQLException e){
            throw new BDException(e);   
        }  finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }
        return clientes;
    }
    

}
