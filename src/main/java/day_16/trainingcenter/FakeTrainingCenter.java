package day_16.trainingcenter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class FakeTrainingCenter implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("We do nothing!");

        if(method.getName().equals("teach")){
            return 37;
        }

        return 42;
    }
}
