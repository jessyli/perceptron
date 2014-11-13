package perceptron;

import java.util.List;
import java.util.Random;
import java.text.DecimalFormat; 

public class Algorithm {

    List<Datastructrue> testdatalist;

    static List<Datastructrue> traindatalist;

    public List<Datastructrue> getTestdatalist() {
        return testdatalist;
    }

    public void setTestdatalist(List<Datastructrue> testdatalist) {
        this.testdatalist = testdatalist;
    }

    public List<Datastructrue> getTraindatalist() {
        return traindatalist;
    }

    public void setTraindatalist(List<Datastructrue> traindatalist) {
        this.traindatalist = traindatalist;
        h = new double[traindatalist.size()];
    }

    double alpha = 0.0000001;
    double[] weight = new double[18];
    Random ran = new Random();
    double[] h = null;
    double confusionmatrix[][] = new double[2][2];
    double percentage = 0;

    public double[] getWeight() {
        return weight;
    }
    
    public double[][] getconfusionmatrix(){
        return confusionmatrix;
    } 
    
    public double getpercentage() {
        return percentage;
    }
    
    public Algorithm() {
        super();
    }

    public void train(List<Datastructrue> traindataList) {

        for (int i = 0; i < 18; i++) {
            weight[i] = ran.nextInt();
        }

        double[] sum = new double[traindataList.size()];
        for (int i = 0; i < traindataList.size(); i++) {
            sum[i] = 0;
        }
        for (int time = 0; time < 10000; time++) {
            for (int z = 0; z < 18; z++) {
                for (int i = 0; i < traindataList.size(); i++) {

                    for (int j = 0; j < 18; j++) {
                        h[i] = weight[j] * traindatalist.get(i).getFeature()[j]
                                + h[i];
//				System.out.println(traindatalist.get(i).getFeature()[j]);
                    }
                    h[i] = Math.signum(h[i]);
                    sum[z] = (traindataList.get(i).getLabel() - h[i]) * traindataList.get(i).getFeature()[z] + sum[z];

                }
                sum[z] = alpha * sum[z];
                weight[z] = weight[z] + sum[z];
            }
        }
        for (int i = 0; i < traindataList.size(); i++) {
            for (int j = 0; j < 18; j++) {
                h[i] = weight[j] * traindatalist.get(i).getFeature()[j] + h[i];
            }
//		System.out.println(h[i]);
            h[i] = Math.signum(h[i]);
//		System.out.println(h[i]);
        }

    }

    public void test(List<Datastructrue> testdatalist) {
        double J = 0;
        for (int i = 0; i < testdatalist.size(); i++) {
            J = Math.pow((h[i] - testdatalist.get(i).getLabel()), 2) + J;
        }
        System.out.println(J);
      
       
        for (int i = 0; i < testdatalist.size(); i++) {
            if ((h[i] * testdatalist.get(i).getLabel()) > 0) {
                percentage++;
            }
            if (h[i] > 0 && testdatalist.get(i).getLabel() > 0) {
                confusionmatrix[0][0]++;
            }
            if (h[i] > 0 && testdatalist.get(i).getLabel() < 0) {
                confusionmatrix[0][1]++;
            }
            if (h[i] < 0 && testdatalist.get(i).getLabel() > 0) {
                confusionmatrix[1][0]++;
            }
            if (h[i] < 0 && testdatalist.get(i).getLabel() < 0) {
                confusionmatrix[1][1]++;
            }

        }
        percentage = percentage / testdatalist.size();
        
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 2; i++) {
                System.out.println("***" + confusionmatrix[i][j]);
            }
        }
    }

}
