package day_16.mysimple;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Taxation implements InvocationHandler {

    private House house;

    public Taxation(House house) {
        this.house = house;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        System.out.printf("in proxy... method name=%s();\n", method.getName());
        if (method.getName().equals("payAccomodation")) {
            System.out.printf("args.length=%d, args[0]=%d;\n", args.length, args.length > 0 ? args[0] : 0);

            Integer value = (Integer) args[0];
            Integer percent = (int) ((value / 100.0) * 13.0);
            System.out.printf("вы хотели оплатить %d, я возьму налогов 13 процентов = %d \n", value, percent);
            args[0] = value - percent;
            return method.invoke(house, args);
        }


        if (method.getName().equals("getHousingArea")) {
            method.invoke(house, args);
            return 9999;
        }

        return method.invoke(house, args);
//        return null;
    }
}
