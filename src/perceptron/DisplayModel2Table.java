/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perceptron;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jessyli
 */
public class DisplayModel2Table extends GeneralizedTableModel {

    @Override
    public void init() {
        titleList.add("featureid");
        titleList.add("document");

        Random rand = new Random();
        Double[] feature = new Double[9];
        for (int i = 0; i < 9; i++) {
            feature[i] = rand.nextDouble() * 2 - 1;
        }

        for (int i = 0; i < feature.length; i++) {
            List<Object> item = new ArrayList<>();
            item.add(i + 1);
            item.add(feature[i]);
            dataList.add(item);
        }
    }

}
