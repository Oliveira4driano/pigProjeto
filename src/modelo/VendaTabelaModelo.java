/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Klicia
 */
public class VendaTabelaModelo extends AbstractTableModel{
    private List<Venda> vendas=new ArrayList<Venda>();
    
    public VendaTabelaModelo(List<Venda> vendas){
        this.vendas=vendas;
    } 
    
    @Override
    public int getRowCount() {
        return vendas.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Venda venda=vendas.get(rowIndex);
        
       
        switch(columnIndex){
            case 0: return venda.getId();
            case 1: return fmt.format(venda.getData().getTime());
            case 2: return venda.getCliente().getNome();
        }
        return null;
    }
    
    public String getColumnName(int coluna){
        
        switch(coluna){
            case 0: return "Número";
            case 1: return "Data";
            case 2: return "Cliente";
        }
        
        return null;
    }
}
