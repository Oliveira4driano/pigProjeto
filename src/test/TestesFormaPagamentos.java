/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.FormaPagtoDAO;
import excecao.BDException;
import modelo.FormaPagto;

/**
 *
 * @author AntonioF
 */
public class TestesFormaPagamentos {
    public  static void main(String[] args) throws BDException{
        FormaPagto pagamento = new FormaPagto();
        FormaPagtoDAO pagamentoDAO = new FormaPagtoDAO();
        
        /*
        INSERIR PAGAMENTO - OK
        pagamento.setDescricao("Cartao Credito");
        pagamento.setQtdeParcelas(5);
        pagamentoDAO.inserir(pagamento);       
        */
        
        /*
        LISTAR PAGAMENTO - OK
        for(FormaPagto f: pagamentoDAO.listar()){
            System.out.println("Pagamento: " + f.getDescricao());
            System.out.println("Parcelas: " + f.getQtdeParcelas());
        }        
        */
        
        /*
        DELETAR PAGAMENTO - OK
         pagamento = pagamentoDAO.listar().get(0);
        pagamento.setDescricao("A VISTA");
        pagamento.setQtdeParcelas(1);
        pagamentoDAO.alterar(pagamento);
        */
        
        /*
         pagamento = pagamentoDAO.listar().get(0);
         pagamentoDAO.deletar(pagamento);
        
        */
        
    
    }
    
}
