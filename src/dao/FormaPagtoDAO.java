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
import modelo.FormaPagto;
import util.ControlaConexao;

/**
 *
 * @author AntonioF
 */
public class FormaPagtoDAO {
    private List<FormaPagto> pagamentos;
    
 

public void inserir(FormaPagto formaPagto) throws BDException {
    String inserirFormaPagamento = "INSERT INTO formapagamento (formaPagamentoDescricao,formaPagamentoParcelas) values (?,?)";
    Connection conexao = null;
    PreparedStatement instrucao = null;
    try {
        conexao = ControlaConexao.getConexao();
        instrucao = conexao.prepareStatement(inserirFormaPagamento);
        instrucao.setString(1, formaPagto.getDescricao());
        instrucao.setInt(2, formaPagto.getQtdeParcelas());
        instrucao.execute();
       
        } catch (SQLException e) {
            throw new BDException(e);
        } finally {
            ControlaConexao.fecharConexao(conexao);
            ControlaConexao.fecharInstrucao(instrucao);
        }
}

public void deletar(FormaPagto formaPagto) throws BDException  {
    String deletarFormaPagamento = "DELETE FROM formapagamento WHERE  formaPagamentoId = ?";
    Connection conexao = null;
    PreparedStatement instrucao = null;
    try{
        conexao = ControlaConexao.getConexao();
        instrucao = conexao.prepareStatement(deletarFormaPagamento);
        instrucao.setLong(1, formaPagto.getId());
        instrucao.execute();
    }catch(SQLException e){
        throw new BDException(e);
        
    } finally{
        ControlaConexao.fecharConexao(conexao);
        ControlaConexao.fecharInstrucao(instrucao);
    }  
}

public void alterar(FormaPagto formaPagto) throws BDException {
    String alterarFormaPagamento = "UPDATE formapagamento SET formaPagamentoDescricao = ?, formaPagamentoParcelas = ? WHERE formaPagamentoId = ?";
    Connection conexao = null;
    PreparedStatement instrucao = null;
    
    try {
        conexao = ControlaConexao.getConexao();
        instrucao = conexao.prepareStatement(alterarFormaPagamento);
        instrucao.setString(1, formaPagto.getDescricao());
        instrucao.setInt(2, formaPagto.getQtdeParcelas());
        instrucao.setLong(3, formaPagto.getId());
        instrucao.execute(); 
    } catch (SQLException e) {
        throw new BDException(e);
    } finally {
        ControlaConexao.fecharConexao(conexao);
        ControlaConexao.fecharInstrucao(instrucao);
   }
}



public List<FormaPagto> listar() throws BDException {
    String listarPagamentos = "SELECT formaPagamentoId,formaPagamentoDescricao,formaPagamentoParcelas FROM formapagamento";
    Connection conexao = null;
    PreparedStatement instrucao = null;
 
    try {
        conexao = ControlaConexao.getConexao();
        instrucao = conexao.prepareStatement(listarPagamentos);
        ResultSet results = instrucao.executeQuery();
        pagamentos = new ArrayList<FormaPagto>();
        while (results.next()) {
            FormaPagto formaPagto = new FormaPagto();
            formaPagto.setId(results.getLong("formaPagamentoId"));
            formaPagto.setDescricao(results.getString("formaPagamentoDescricao"));
            formaPagto.setQtdeParcelas(results.getInt("formaPagamentoParcelas"));
            pagamentos.add(formaPagto);
        }
    } catch (SQLException e) {
        throw new BDException(e);
    } finally {
        ControlaConexao.fecharInstrucao(instrucao);
        ControlaConexao.fecharConexao(conexao);
    }
    return pagamentos;
    }

public FormaPagto buscarPorId(long id) throws BDException{
    String buscarPorId = "SELECT formaPagamentoId,formaPagamentoDescricao,formaPagamentoParcelas FROM formapagamento WHERE formaPagamentoId = ?";
    Connection conexao = null;
    PreparedStatement instrucao = null;
    FormaPagto pagamento = null;
    try{
        conexao = ControlaConexao.getConexao();
        instrucao = conexao.prepareStatement(buscarPorId);
        instrucao.setLong(1, id);
        ResultSet results = instrucao.executeQuery();
        while(results.next()){
            pagamento = new FormaPagto();
            pagamento.setId(results.getLong("formaPagamentoId"));
            pagamento.setDescricao(results.getString("formaPagamentoDescricao"));
            pagamento.setQtdeParcelas(results.getInt("formaPagamentoParcelas"));           
        }
         return pagamento;
    }catch(SQLException e){
        throw new BDException(e);
    } finally{
        ControlaConexao.fecharConexao(conexao);
        ControlaConexao.fecharInstrucao(instrucao);
    } 
    
}

  public List<FormaPagto> pesquisar(FormaPagto formaPagto) throws BDException{
        List<FormaPagto> formaPagtos;
        FormaPagto formaPagtoSaida;
        
        String listarFormaPagtos = "SELECT * FROM formaPagamento where formaPagamentoDescricao like ?";
        
        Connection conexao = null;
        PreparedStatement instrucao = null;        
        ResultSet resultados=null;
        
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(listarFormaPagtos);
            instrucao.setString(1,"%"+ formaPagto.getDescricao()+"%");
            resultados = instrucao.executeQuery();
                      
            formaPagtos = new ArrayList<FormaPagto>();
            
            while(resultados.next()){
                formaPagtoSaida = new FormaPagto();
                
                formaPagtoSaida.setId(resultados.getLong("formaPagamentoId"));
                formaPagtoSaida.setDescricao(resultados.getString("formaPagamentoDescricao"));
                formaPagtoSaida.setQtdeParcelas(resultados.getInt("formaPagamentoParcelas"));
                formaPagtos.add(formaPagtoSaida);
            } 
        } catch(SQLException e){
            throw new BDException(e);   
        }  finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }
        return formaPagtos;
    }


}
