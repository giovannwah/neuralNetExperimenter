package experiment;

import org.encog.Encog;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Used to run an Experimenter instance, and end that experiment early if necessary.
 */
public class ExperimentController implements Runnable {

    public ExperimentController() {

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

    public void someTestFunction() {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Hello");
                }
        }, 2000, 2000);
    }

    public void startExperiment() {
        Thread t = new Thread(this);
        t.start();
        someTestFunction();
    }

    public static void main (String[] args) {
        ExperimentController ec = new ExperimentController();
        ec.startExperiment();
    }
}
