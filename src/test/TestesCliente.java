/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.CidadeDAO;
import dao.ClienteDAO;
import excecao.BDException;
import java.util.List;
import modelo.Cidade;
import modelo.Cliente;

/**
 *
 * @author AntonioF
 */
public class TestesCliente {
    public static void main(String[] args) throws BDException{
        CidadeDAO cidadeDAO = new CidadeDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        Cidade cidade;
        Cliente cliente;
        
        
        /*
        INSERIR CLIENTE - OK
        cidade = cidadeDAO.listar().get(0);
        cliente = new Cliente();
        cliente.setNome("Antonio");
        cliente.setEndereco("Rua 3");
        cliente.setTelefone("98250-8828");
        cliente.setCidade(cidade);
        clienteDAO.inserir(cliente);
        */
  
     
        /*
        ALTERAR CLIENTE - OK
        cliente = clienteDAO.listar().get(0);
        cliente.setNome("Antonio 2");
        cliente.setEndereco("Rua 3 2");
        cliente.setTelefone("92222-2222");
        cliente.setCidade(cidadeDAO.listar().get(1));
        clienteDAO.alterar(cliente);        
        */

        
        /*
        LISTAR CLIENTE - OK 
        
        for(Cliente c: clienteDAO.listar()){
            System.out.println("Cliente ID: " + c.getId());
            System.out.println("Cliente Nome: " + c.getNome());
            System.out.println("Cliente Endereço: " + c.getEndereco());
            System.out.println("Cliente Telefone: " + c.getTelefone());
            System.out.println("Cliente Cidade: " + c.getCidade().getNome());
        }        
        
        */
        
        /*
        EXCLUIR CLIENTE -  OK
        cliente = clienteDAO.listar().get(0);
        clienteDAO.deletar(cliente);
        */
        
       

    }

    
    
}
