package day_16.annotproxy;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Counter realCounter = new MathCounter();

        CounterInvocationHandler handler =
                new CounterInvocationHandler(realCounter);
        Counter counterProxy =
                (Counter) Proxy.newProxyInstance(
                        CounterInvocationHandler.class.getClassLoader(),
                        new Class[]{Counter.class},
                        handler
                );

        counterProxy.count();
        handler.setLoggingEnabled(true);
        counterProxy.count();
    }
}
