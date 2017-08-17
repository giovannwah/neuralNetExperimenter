package experiment;

import com.github.neuralnetworks.architecture.NeuralNetworkImpl;
import com.github.neuralnetworks.architecture.types.NNFactory;
import com.github.neuralnetworks.samples.xor.XorOutputError;
import com.github.neuralnetworks.training.TrainerFactory;
import com.github.neuralnetworks.training.backpropagation.BackPropagationTrainer;
import com.github.neuralnetworks.training.events.LogTrainingListener;
import com.github.neuralnetworks.training.random.MersenneTwisterRandomInitializer;
import com.github.neuralnetworks.training.random.NNRandomInitializer;

public class Experimenter {

    public static void main (String[] args) {
        //raw test
        NeuralNetworkImpl mlp = NNFactory.mlpSigmoid(new int[] { 2, 2, 1 }, true);

        // create training and testing input providers
        SimpleInputProvider input = new SimpleInputProvider(new float[][] { {0, 0}, {0, 1}, {1, 0}, {1, 1} }, new float[][] { {0}, {1}, {1}, {0} });

        // create backpropagation trainer for the network
        BackPropagationTrainer<?> bpt = TrainerFactory.backPropagation(mlp, input, input, new XorOutputError(), new NNRandomInitializer(new MersenneTwisterRandomInitializer(-0.01f, 0.01f)), 0.1f, 0.9f, 0f, 0f, 0f, 1, 1, 100000);

        // add logging
        bpt.addEventListener(new LogTrainingListener(Thread.currentThread().getStackTrace()[1].getMethodName()));

        // early stopping
        //bpt.addEventListener(new EarlyStoppingListener(testingInput, 10, 0.1f));

        // train
        bpt.train();

        // test
        bpt.test();
    }
}
