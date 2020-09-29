/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.CidadeDAO;
import dao.EstadoDAO;
import excecao.BDException;
import java.util.List;
import modelo.Cidade;
import modelo.Estado;

/**
 *
 * @author AntonioF
 */
public class TestesCidade {
    public static void main(String[] args) throws BDException{
        Estado estado = new Estado();
        EstadoDAO estadoDAO = new EstadoDAO();
        Cidade cidade = new Cidade();
        CidadeDAO cidadeDAO = new CidadeDAO();
        List<Cidade> cidades;

        

        /*
        INSERIR CIDADE - OK
        cidade.setNome("Manaus");
        estado = estadoDAO.listar().get(0);
        cidade.setEstado(estado);
        cidadeDAO.inserir(cidade);
        */
      
       
      
        /*
        ALTERAR CIDADE - OK
        estado = estadoDAO.listar().get(1);
        cidade = cidadeDAO.listar().get(0);
        cidade.setNome("Belém");
        cidade.setEstado(estado);
        cidadeDAO.alterar(cidade);              
        */
             
        /*
        LISTAR CIDADE - OK
        cidades = cidadeDAO.listar();
        System.out.println("Cidades");
        for(Cidade c: cidades){
            System.out.println("Id: " + c.getId());
            System.out.println("Nome: " + c.getNome());
            System.out.println("Estado: " + c.getEstado().getNome());
        }        
        */
       
        /*       
        EXCLUIR CIDADE - OK
        cidade = cidadeDAO.listar().get(0);
        cidadeDAO.deletar(cidade);
        */
      
         
    }
    
}
