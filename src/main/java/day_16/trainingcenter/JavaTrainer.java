package day_16.trainingcenter;

public class JavaTrainer implements Trainer {
    @Override
    public void teach() {
        System.out.println("Java is so complicated");
    }

    @Override
    public void talk(String message) {
        System.out.println(message);
    }
}
