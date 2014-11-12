/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perceptron;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jessyli
 */
public class GeneralizedTableModel extends AbstractTableModel {

    List<String> titleList = new ArrayList<>();
    List<List<Object>> dataList = new ArrayList<>();

    public GeneralizedTableModel() {
        init();
    }
    
    public void init() {
        titleList.add("DocumentID");
        titleList.add("weight");
        
        Random rand = new Random();
        Double[] weights = new Double[862];
        for(int i=0; i<862; i++) {
            weights[i] = rand.nextDouble() * 2 - 1;
        }
        
        for(int i=0; i<weights.length; i++) {
            List<Object> item = new ArrayList<>();
            item.add(i+1);
            item.add(weights[i]);
            dataList.add(item);
        }
    }

    public List<List<Object>> getDataList() {
        return this.dataList;
    }

    @Override
    public String getColumnName(int column) {
        return titleList.get(column); 
    }

    
    
    @Override
    public int getRowCount() {
        return dataList.size();
    }

    @Override
    public int getColumnCount() {
        return titleList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return dataList.get(rowIndex).get(columnIndex);
    }
}
