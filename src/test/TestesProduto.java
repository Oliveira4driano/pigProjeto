/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.ProdutoDAO;
import excecao.BDException;
import modelo.Produto;

/**
 *
 * @author AntonioF
 */
public class TestesProduto {
    public static void main(String[] args) throws BDException{
        Produto produto = new Produto();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        /*
        INSERIR PRODUTO - OK
        produto.setDescricao("Produto 1");
        produtoDAO.inserir(produto);
        */
       
        
        /*
        ALTERAR PRODUTO - OK
         produto = produtoDAO.listar().get(0);
        produto.setDescricao("Produto 2");
        produtoDAO.alterar(produto);
        
        */
        
        /*
        LISTAR PRODUTO - OK
         for(Produto p: produtoDAO.listar()){
            System.out.println("Produto Id: " + p.getId());
            System.out.println("Produto Nome: " + p.getDescricao());
        }
        */
        
        /*
        DELETAR PRODUTO - OK
        produto = produtoDAO.listar().get(0);
        produtoDAO.deletar(produto);
        */
        
       
       
        produto = produtoDAO.buscarPorId(1);
        
        System.out.println("Produto: " + produto.getDescricao());
    
    }
}
