/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import excecao.BDException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import modelo.Cliente;
import modelo.Estado;
import modelo.FormaPagto;
import modelo.Produto;
import modelo.Venda;
import util.ControlaConexao;

/**
 *
 * 
 */
public class VendaDAO {
    public long inserir(Venda venda) throws BDException{
        String inserirVenda ="INSERT INTO VENDA  VALUES ()";
        long idRetornado = 0;
        Connection conexao=null;
        PreparedStatement instrucao=null;
        
        try{
            conexao=ControlaConexao.getConexao();
            instrucao=conexao.prepareStatement(inserirVenda, Statement.RETURN_GENERATED_KEYS);
            
            //instrucao.setLong(1, venda.getCliente().getId());
            //instrucao.setLong(2, venda.getFormaPagto().getId());
            

            instrucao.execute();
            
            ResultSet resultados = instrucao.getGeneratedKeys();

            if(resultados.next()){
                idRetornado = resultados.getInt(1);
   
              }

            System.out.println("ID VENDA: " + idRetornado );
            return idRetornado;
        }catch(SQLException e){
            throw new BDException(e);
        }finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }
    }
    
    
        public List<Venda> pesquisar(Venda venda) throws BDException{
            List<Venda> vendas;
            Venda vendaSaida;
            Calendar calendar = null;
            String pesquisarVenda = "SELECT vendaId,vendaData, vendaFormaPagamentoId,vendaClienteId FROM VENDA WHERE vendaId=?";
            
            Connection conexao=null;
            PreparedStatement instrucao=null;
            ResultSet resultados=null;
            
            try{
                conexao=ControlaConexao.getConexao();
                instrucao=conexao.prepareStatement(pesquisarVenda);
                instrucao.setLong(1,venda.getId());
                resultados=instrucao.executeQuery();
                
                vendas=new ArrayList<Venda>();
                
                while(resultados.next()){
                    vendaSaida=new Venda();
                    vendaSaida.setId(resultados.getInt("vendaId"));
                    calendar.setTime(resultados.getDate("vendaData"));
                    vendaSaida.setData(calendar);
                    
                    FormaPagtoDAO formaDao=new FormaPagtoDAO();
                    FormaPagto formaPagamento=formaDao.buscarPorId(resultados.getInt("vendaFormaPagamentoId"));
                    vendaSaida.setFormaPagto(formaPagamento);
                    
                    ClienteDAO clienteDao=new ClienteDAO();
                    Cliente cliente = clienteDao.buscarPorId(resultados.getInt("vendaClienteId"));
                    vendaSaida.setCliente(cliente);
                    
                    
                    vendas.add(vendaSaida);
                }
                return vendas;
            }catch(SQLException e){
                throw new BDException(e);
            }finally{
                
                ControlaConexao.fecharInstrucao(instrucao);
                ControlaConexao.fecharConexao(conexao);
            }
                
        }
    
        public List<Venda> listar() throws BDException{
            List<Venda> vendas;
            Venda vendaSaida;
            Calendar calendar = Calendar.getInstance();
            String pesquisarVenda = "SELECT vendaId,vendaData, vendaFormaPagamentoId,vendaClienteId FROM VENDA WHERE vendaClienteId >=1";
        
            Connection conexao=null;
            PreparedStatement instrucao=null;
            ResultSet resultados=null;
            
            try{
                conexao=ControlaConexao.getConexao();
                instrucao=conexao.prepareStatement(pesquisarVenda);
                
                resultados=instrucao.executeQuery();
                
                vendas=new ArrayList<Venda>();
                
                while(resultados.next()){
                    vendaSaida = new Venda();
                    vendaSaida.setId(resultados.getInt("vendaId"));
                    calendar.setTime(resultados.getDate("vendaData"));
                    vendaSaida.setData(calendar);
                    
                    if (resultados.getInt("vendaFormaPagamentoId") > 0){
                        FormaPagtoDAO formaDao=new FormaPagtoDAO();
                        FormaPagto formaPagamento=formaDao.buscarPorId(resultados.getInt("vendaFormaPagamentoId"));
                        vendaSaida.setFormaPagto(formaPagamento);
                    }
                    ClienteDAO clienteDao=new ClienteDAO();
                    Cliente cliente = clienteDao.buscarPorId(resultados.getInt("vendaClienteId"));
                    vendaSaida.setCliente(cliente);
                    
                    
                    vendas.add(vendaSaida);
                }
                return vendas;
            }catch(SQLException e){
                throw new BDException(e);
            }finally{
                
                ControlaConexao.fecharInstrucao(instrucao);
                ControlaConexao.fecharConexao(conexao);
            }
    }
        
        
    public void alterar(Venda venda) throws BDException{
        String alterarVenda ="UPDATE VENDA SET vendaFormaPagamentoId = ?, vendaClienteId = ? WHERE vendaId=?" ;
        
        Connection conexao=null;
        PreparedStatement instrucao=null;
        
        try{
            conexao=ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(alterarVenda);
            
            
            instrucao.setLong(1, venda.getFormaPagto().getId());
            instrucao.setLong(2, venda.getCliente().getId());
            instrucao.setLong(3, venda.getId());
            
            instrucao.execute();
        }catch(SQLException e){
            throw new BDException(e);
        }finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }
    }
    
    public void deletar(Venda venda) throws BDException{
        String deletarVenda = "DELETE FROM venda WHERE vendaId = ?";
        
        Connection conexao=null;
        PreparedStatement instrucao=null;
        
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(deletarVenda);
            instrucao.setLong(1,venda.getId());
        instrucao.execute();
            
        } catch (SQLException e){
            throw new BDException(e);
        }finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }
    }
    
    public Venda buscarPorId(long id) throws BDException{
        String buscarVenda = "SELECT * FROM VENDA WHERE vendaId = ?";
        Venda venda = null;
        Connection conexao = null;
        PreparedStatement instrucao = null;       
        Calendar c = Calendar.getInstance();
        try{
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(buscarVenda);           
            instrucao.setLong(1, id);
            ResultSet results = instrucao.executeQuery();   
            while(results.next()){
                venda = new Venda();
                venda.setId(results.getLong("vendaId"));
                c.setTime(results.getDate("vendaData"));
                venda.setData(c);
                return venda;
            }             
        } catch(SQLException e){
            throw new BDException(e);   
        }  finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }          
        return venda;
    }

    public void buscarPorId(Venda venda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}    