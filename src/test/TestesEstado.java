/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.EstadoDAO;
import excecao.BDException;
import java.util.List;
import modelo.Estado;

/**
 *
 * @author AntonioF
 */
public class TestesEstado {
    public static void main(String[] args) throws BDException{
        Estado estado = new Estado();
        EstadoDAO estadoDAO = new EstadoDAO();
        
        
      /*INSERIR ESTADO - OK
        estado.setNome("Amazonas");       
        estadoDAO.inserir(estado);  
        */
               
        
        /*
        ALTERAR ESTADO - OK
        List<Estado> estados = estadoDAO.listar();
        estado = estados.get(0);
        estado.setNome("Pará");
        estadoDAO.alterar(estado);   
        */
     
        /*
        LISTAR ESTADO - OK
        List<Estado> estados = estadoDAO.listar();
        for(Estado est: estados){
        System.out.println("EstadoID: " + est.getId());
        System.out.println("Estado: " + est.getNome());
         }
       
        */

        /*
        EXCLUIR ESTADO - OK
        estado = estadoDAO.listar().get(0);
        estadoDAO.deletar(estado);

        */
      

    }
    
}
