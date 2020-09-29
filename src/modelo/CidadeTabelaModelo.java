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
public class CidadeTabelaModelo extends AbstractTableModel {
    private List<Cidade> cidades = new ArrayList<Cidade>();
    private int qtdeColunas=2;
    
    public CidadeTabelaModelo(List<Cidade> cidades){
        this.cidades=cidades;
    }
    
    @Override
    public int getRowCount() {
        return cidades.size();
    }

    @Override
    public int getColumnCount() {
        return qtdeColunas;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cidade cidade=cidades.get(rowIndex);
        
        switch(columnIndex){
            case 0: return cidade.getNome();
            case 1: return cidade.getEstado().getNome();
        }
        return null;
    }
    public String getColumnName(int coluna){
        switch(coluna){
            case 0: return "Nome";
            case 1: return "Estado";
        }
        return null;
    }
        
}
