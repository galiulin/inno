package day_16.annotproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CounterInvocationHandler
        implements InvocationHandler {
    private Counter counter;
    private boolean loggingEnabled = false;

    public void setLoggingEnabled(boolean loggingEnabled) {
        this.loggingEnabled = loggingEnabled;
    }

    public CounterInvocationHandler(Counter counter) {
        this.counter = counter;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(counter
                .getClass()
                .getAnnotation(Logged.class)!= null
                    && loggingEnabled){
            System.out.println("We call method " + method.getName());
            long startTime = System.currentTimeMillis();
            Object result = method.invoke(counter, args);
            System.out.println("with time "
                                + (startTime-System.currentTimeMillis()));
            return result;
        } else {
            return method.invoke(counter, args);
        }
    }
}
