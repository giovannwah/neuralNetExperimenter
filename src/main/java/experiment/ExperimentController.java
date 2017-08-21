package experiment;

import data.CSVInputProvider;
import org.encog.Encog;
import org.encog.ml.MLMethod;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.BasicNetwork;
import wah.giovann.csvhandler.CSVArray;
import wah.giovann.csvhandler.CSVFileFormat;
import wah.giovann.csvhandler.CSVWriter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Used to run an Experimenter instance, and end that experiment early if necessary.
 */
public class ExperimentController implements Runnable {
    public static String linroot = "/home/giovadmin/thesis/";
    public static String winroot = "C:/Users/Giovann/Desktop/";
    public static String root = "";
    public static final boolean TEST = true;

    static {
        root = linroot;
    }

    public void run() {
        while(true) {
            Scanner s = new Scanner(System.in);
            String ans = s.nextLine();
            if (ans.toLowerCase().equals("exit") ||
                    ans.toLowerCase().equals("quit") ||
                    ans.toLowerCase().equals("stop") ||
                    ans.toLowerCase().equals("end")) {
                wrapUp();
                System.exit(0);
            }
            else if (ans.toLowerCase().equals("detail") ||
                    ans.toLowerCase().equals("info")) {
                moreInfo();
            }
        }
    }

    public void wrapUp() {
        System.out.println("Wrapping up experiment...");
    }

    public void moreInfo(){

    }

    public void _run() {
        MLDataSet trainingSet = CSVInputProvider.getTrainingData();
        MLDataSet testingSet = CSVInputProvider.getTestingData();
    }

    public void startExperiment() {
        Thread t = new Thread(this);
        t.start();
        _run();
    }

    public static void saveResultsAndModel(BasicNetwork model, MLDataSet testSet) {
        CSVArray outFile = new CSVArray();
        CSVWriter writer = new CSVWriter(CSVFileFormat.DEFAULT_FORMAT);
        ArrayList<String> h = new ArrayList();
        if (TEST) {
            h.add("Predicted");
            h.add("Actual");
            h.add("Absolute_Error");
            h.add("Relative_Error");
            h.add("Squared_Error");
            outFile.putHeader(h);
        }
        else {
            h.add("Day_Of_Week");
            h.add("Time_Of_Day");
            h.add("Section");
            h.add("Predicted");
            h.add("Actual");
            h.add("Absolute_Error");
            h.add("Relative_Error");
            h.add("Squared_Error");
            outFile.putHeader(h);
            for (MLDataPair dp : testSet) {
                double actual = 0;
                double predicted = 0;
                MLData output = model.compute(dp.getInput());
                predicted = output.getData(0);
                actual = dp.getIdeal().getData(0);
                h = new ArrayList();
                h.add(getDayOfWeek(dp.getInput()));
                h.add(dp.getInput().getData(7)+"");
                h.add(getSection(dp.getInput()));
                h.add(predicted + "");
                h.add(actual + "");
                h.add(getAbsoluteError(actual, predicted)+"");
                h.add(getRelativeError(actual, predicted)+"");
                h.add(getSquaredError(actual, predicted)+"");
                outFile.insertData(h);
            }
            writer.write(outFile, root+"Results/final/",false);
        }
    }

    public static String getDayOfWeek(MLData d) {
        if (d.getData(2) == 1) {
            return "mon";
        }
        else if (d.getData(3) == 1) {
            return "tue";
        }
        else if (d.getData(4) == 1) {
            return "wed";
        }
        else if (d.getData(5) == 1) {
            return "thu";
        }
        else return "fri";
    }

    public static String getSection(MLData d) {
        String s = "section_";
        if (d.getData(8) == 1) {
            return s+"1";
        }
        else if (d.getData(9) == 1) {
            return s+"2";
        }
        else if (d.getData(10) == 1) {
            return s+"3";
        }
        else if (d.getData(11) == 1) {
            return s+"4";
        }
        else if (d.getData(12) == 1) {
            return s+"5";
        }
        else if (d.getData(13) == 1) {
            return s+"6";
        }
        else if (d.getData(14) == 1) {
            return s+"7";
        }
        else if (d.getData(15) == 1) {
            return s+"8";
        }
        else if (d.getData(16) == 1) {
            return s+"9";
        }
        else if (d.getData(17) == 1) {
            return s+"10";
        }
        else if (d.getData(18) == 1) {
            return s+"11";
        }
        else if (d.getData(19) == 1) {
            return s+"12";
        }
        else if (d.getData(20) == 1) {
            return s+"13";
        }
        else if (d.getData(21) == 1) {
            return s+"14";
        }
        else if (d.getData(22) == 1) {
            return s+"15";
        }
        else if (d.getData(23) == 1) {
            return s+"16";
        }
        else if (d.getData(24) == 1) {
            return s+"17";
        }
        else if (d.getData(25) == 1) {
            return s+"18";
        }
        else if (d.getData(26) == 1) {
            return s+"19";
        }
        else if (d.getData(27) == 1) {
            return s+"20";
        }
        else if (d.getData(28) == 1) {
            return s+"21";
        }
        else if (d.getData(29) == 1) {
            return s+"22";
        }
        else return s+"23";
    }

    private static double getAbsoluteError(double actual, double predicted) {
        return Math.abs(actual-predicted);
    }

    private static double getRelativeError(double actual, double predicted) {
        return (1.0 - (predicted/actual));
    }

    private static double getSquaredError(double actual, double predicted) {
        return Math.pow(getAbsoluteError(actual, predicted), 2);
    }

    public static void main (String[] args) {
        ExperimentController ec = new ExperimentController();
        ec.startExperiment();
    }
}
