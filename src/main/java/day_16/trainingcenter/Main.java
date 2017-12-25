package day_16.trainingcenter;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Trainer artem = new JavaTrainer();
        TrainingCenter trainingCenter =
                new TrainingCenter(artem);

        Trainer stc =
                (Trainer) Proxy.newProxyInstance(
                        TrainingCenter.class.getClassLoader(),
                        new Class[]{Trainer.class},
                        trainingCenter
                );

        System.out.println("Without proxy");
        artem.teach();

        System.out.println("With proxy");
        stc.teach();

        Trainer itPark =
                (Trainer) Proxy.newProxyInstance(
                        TrainingCenter.class.getClassLoader(),
                        new Class[]{Trainer.class},
                        new FakeTrainingCenter()
                );
        System.out.println("It-park proxy");
        itPark.teach();

        stc.talk("Real message");
    }
}
