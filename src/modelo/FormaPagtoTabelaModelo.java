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
public class FormaPagtoTabelaModelo extends AbstractTableModel{
    private List<FormaPagto> formaPagtos = new ArrayList<FormaPagto>();
    private int qtdeColunas=2;
    
    public FormaPagtoTabelaModelo(List<FormaPagto> formaPagtos){
        this.formaPagtos=formaPagtos;
    }
    
    @Override
    public int getRowCount() {
        return formaPagtos.size();
    }

    @Override
    public int getColumnCount() {
        return qtdeColunas;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FormaPagto formaPagto = formaPagtos.get(rowIndex);
        
        switch(columnIndex){
            case 0: return formaPagto.getDescricao();
            case 1: return formaPagto.getQtdeParcelas();
        }
        return null;
    }
    public String getColumnName(int coluna){
        switch(coluna){
            case 0: return "Descrição";
            case 1: return "Qtde de Parcelas";
        }
        return null;
    }
}
