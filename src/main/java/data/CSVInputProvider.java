package data;

import experiment.ExperimentController;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.specific.CSVNeuralDataSet;
import org.encog.ml.data.versatile.VersatileMLDataSet;
import org.encog.ml.data.versatile.columns.ColumnDefinition;
import org.encog.ml.data.versatile.columns.ColumnType;
import org.encog.ml.data.versatile.sources.CSVDataSource;

import java.io.File;

public class CSVInputProvider {
    public static final String testMain = "testing_WITHOUT_travel_time_outliers.csv";
    public static final String trainMain = "training_WITHOUT_travel_time_outliers.csv";
    public static final String testFB = "facebook2Test.csv";
    public static final String trainFB = "facebook2Train.csv";

    public static MLDataSet getTrainingData() {
        if (ExperimentController.TEST) return getFBTrainingFile();
         else return getMainTrainingFile();
    }

    public static MLDataSet getTestingData() {
        if (ExperimentController.TEST) return getFBTestingFile();
        else return getMainTestingFile();
    }

    private static MLDataSet getMainTrainingFile() {
        File file = new File(ClassLoader.getSystemClassLoader().getResource(trainMain).getFile());
        CSVDataSource src = new CSVDataSource(file, true, ',');
        VersatileMLDataSet ds = new VersatileMLDataSet(src);
        ds.defineSingleOutputOthersInput(new ColumnDefinition("eta", ColumnType.continuous));
        ds.analyze();
        return ds;
    }

    private static MLDataSet getMainTestingFile() {
        File file = new File(ClassLoader.getSystemClassLoader().getResource(testMain).getFile());
        CSVDataSource src = new CSVDataSource(file, true, ',');
        VersatileMLDataSet ds = new VersatileMLDataSet(src);
        ds.defineSingleOutputOthersInput(new ColumnDefinition("eta", ColumnType.continuous));
        ds.analyze();
        return ds;
    }

    private static MLDataSet getFBTrainingFile() {
        File file = new File(ClassLoader.getSystemClassLoader().getResource(trainFB).getFile());
        CSVDataSource src = new CSVDataSource(file, true, ',');
        VersatileMLDataSet ds = new VersatileMLDataSet(src);
        ds.defineSingleOutputOthersInput(new ColumnDefinition("Total Interactions", ColumnType.continuous));
        ds.analyze();
        return ds;
    }

    private static MLDataSet getFBTestingFile() {
        File file = new File(ClassLoader.getSystemClassLoader().getResource(testFB).getFile());
        CSVDataSource src = new CSVDataSource(file, true, ',');
        VersatileMLDataSet ds = new VersatileMLDataSet(src);
        ds.defineSingleOutputOthersInput(new ColumnDefinition("Total Interactions", ColumnType.continuous));
        ds.analyze();
        return ds;
    }

    public static void main (String [] args) {
        MLDataSet ds = getTrainingData();
        for (MLDataPair dp : ds) {
            System.out.println(dp.getIdeal());
        }
    }
}
