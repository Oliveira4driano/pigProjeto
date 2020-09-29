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
public class EstadoTabelaModelo extends AbstractTableModel{
    private List<Estado> estados = new ArrayList<Estado>();
    private int qtdeColunas=1;
    
    public EstadoTabelaModelo(List<Estado> estados){
        this.estados=estados;
    }
    
    @Override
    public int getRowCount() {
        return estados.size();
    }

    @Override
    public int getColumnCount() {
        return qtdeColunas;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Estado estado=estados.get(rowIndex);
        switch(columnIndex){
            case 0: return estado.getNome();
        }
        return null;
    }
    
    public String getColumnName(int coluna){
        switch(coluna){
            case 0: return "Nome"; 
        }
        return null;
    }
    
}
