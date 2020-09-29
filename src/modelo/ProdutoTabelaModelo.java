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
public class ProdutoTabelaModelo extends AbstractTableModel{
    
    private List<Produto> produtos= new ArrayList<Produto>();
    private int qtdeColuna=1;
    
    public ProdutoTabelaModelo(List<Produto> produtos){
        this.produtos=produtos;
    }
    
    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return qtdeColuna;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produto = produtos.get(rowIndex);
        
        switch(columnIndex){
            case 0: return produto.getDescricao();
        }
        return null;
    }
    
    public String getColumnName(int coluna){
        switch(coluna){
            case 0: return "Descrição";
        }
        return null;
    }
    
}
