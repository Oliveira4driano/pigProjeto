/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Klicia
 */
public class ClienteTabelaModelo extends AbstractTableModel{
    private List<Cliente> clientes = new ArrayList<Cliente>();
    private int qtdeColunas=2;
    
    public ClienteTabelaModelo(List<Cliente> clientes){
        this.clientes=clientes;
    }
    
    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return qtdeColunas;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);
        
        switch(columnIndex){
            case 0: return cliente.getNome();
            case 1: return cliente.getTelefone();
        }
        return null;
    }
    public String getColumnName(int coluna){
        switch(coluna){
            case 0: return "Nome";
            case 1: return "Telefone";
        }
        return null;
    }
}
