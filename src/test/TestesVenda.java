/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.ClienteDAO;
import dao.FormaPagtoDAO;
import dao.ItemVendaDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import excecao.BDException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.FormaPagto;
import modelo.ItemVenda;
import modelo.Produto;
import modelo.Venda;

/**
 *
 * @author AntonioF
 */
public class TestesVenda {
    public static void main(String[] args) throws BDException{
        VendaDAO dao = new VendaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        FormaPagtoDAO formaDAO = new FormaPagtoDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
        
        
        //CRiando Venda
        Cliente cliente = clienteDAO.buscarPorId(1);
        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setFormaPagto(formaDAO.buscarPorId(1));
        venda.setId(3);//eu criei
        //dao.inserir(venda);
       //Criando lista de compras
       
       
        List<ItemVenda> itens = new ArrayList<>();
        
        ItemVenda item = new ItemVenda();
        
        
        item.setProduto(produtoDAO.buscarPorId(1));
        item.setQtde(2);
        item.setVenda(venda);
        itens.add(item);
        itemVendaDAO.inserir(item);
        
        
        item.setProduto(produtoDAO.buscarPorId(2));
        item.setQtde(3);
        item.setVenda(venda);
        itens.add(item);
        itemVendaDAO.inserir(item);
        
        
        //itens.add(e)
        
        
        //FormaPagto formPag = formaDAO.buscarPorId(2);
        //Criando venda
        
        //
        //venda.setFormaPagto(formPag);
        //venda.setItens(itens);
        
        
        
        
        
    }    
}
