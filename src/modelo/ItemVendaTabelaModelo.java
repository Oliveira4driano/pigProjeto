/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Klicia
 */
public class ItemVendaTabelaModelo extends AbstractTableModel{
    private List<ItemVenda> itens;
    private int qtdColunas = 2;
    

    public ItemVendaTabelaModelo(List<ItemVenda> itensIncluidos) {
        this.itens = itensIncluidos;
         }

    @Override
    public int getRowCount() {
        return this.itens.size();
    }

    @Override
    public int getColumnCount() {
        return this.qtdColunas;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ItemVenda item=itens.get(rowIndex);
        
        switch(columnIndex){
            case 0: return item.getProduto().getDescricao();
            case 1: return item.getQtde();
        }
        return null;
    }
    
    public String getColumnName(int coluna){
        switch(coluna){
            case 0: return "Produto";
            case 1: return "Quantidade";
        }
        return null;
    }
    
}
