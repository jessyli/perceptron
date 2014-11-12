package perceptron;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Loaddata {

//static  List<Datastructrue> doubleFeatureList = new ArrayList<Datastructrue>();


    public static List<String[]> loadData(String path) {
        List<String[]> featureList = new ArrayList<String[]>();
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        
        try {
            String str = "";
            fis = new FileInputStream(path);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String[] f = new String[10];
            while ((str = br.readLine()) != null) {

                String[] split = str.split(",");
                // System.out.println(split[8]);

                for (int i = 0; i < 10; i++) {
                    f[i] = split[i];
                }

                featureList.add(split);
            }
            for (int i = 0; i < featureList.size(); i++) {
                for (int j = 0; j < 10; j++) {
                    System.out.print(featureList.get(i)[j] + " ");
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("can't find the file");
        } catch (IOException e) {
            System.out.println("fail to read the file");
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return featureList;
    }

    public static List<Datastructrue> getfearture(List<String[]> featureList){
        List<Datastructrue> doubleFeatureList = new ArrayList<Datastructrue>();
        for (int i = 0; i < featureList.size(); i++) {
            Datastructrue data = new Datastructrue();
            if (featureList.get(i)[9].equals("positive")) {
                data.setLabel(1);
                // doubleFeatureList.get(i).setLabel(1);
            } else {
                data.setLabel(-1);
                // doubleFeatureList.get(i).setLabel(-1);
            }
            double[] features = new double[18];
            for (int j = 0; j < 9; j++) {
                if (featureList.get(i)[j].equals("x")) {
                    features[j] = 1;
                    // doubleFeatureList.get(i).getFeature()[j] = 1;
                } else {
                    features[j] = 0;
                    // doubleFeatureList.get(i).getFeature()[j] = 0;
                }
                if (featureList.get(i)[j].equals("o")) {
                    features[j + 9] = 1;
                    // doubleFeatureList.get(i).getFeature()[j + 9] = 1;
                } else {
                    features[j + 9] = 0;
                    // doubleFeatureList.get(i).getFeature()[j + 9] = 0;
                }
            }
            data.setFeature(features);
            doubleFeatureList.add(data);

        }
        return doubleFeatureList;
    }
    
    public static void main(String[] args) {
        List<String[]> featureList = loadData("/Users/jessyli/Documents/perceptrondatatxt.txt");
        List<Datastructrue> doubleFeatureList = getfearture(featureList);
        List<Datastructrue> traindatalist = new ArrayList<Datastructrue>();
        List<Datastructrue> testdatalist = new ArrayList<Datastructrue>();
        int traindatasize = (int) (featureList.size() * 0.9);
        for (int i = 0; i < traindatasize; i++) {
            Datastructrue data = new Datastructrue();
            data.setFeature(doubleFeatureList.get(i).getFeature());
            data.setLabel(doubleFeatureList.get(i).getLabel());
            traindatalist.add(data);
        }
        for (int i = traindatasize; i < featureList.size(); i++) {
            Datastructrue data = new Datastructrue();
            data.setFeature(doubleFeatureList.get(i).getFeature());
            data.setLabel(doubleFeatureList.get(i).getLabel());
            testdatalist.add(data);
        }

        Algorithm algorithm = new Algorithm();
        algorithm.setTestdatalist(testdatalist);
        algorithm.setTraindatalist(traindatalist);
        algorithm.train(traindatalist);
        algorithm.test(testdatalist);

    }
}
