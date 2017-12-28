package day_16.mysimple;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        House house = new SmallHouse();
        house.getHousingArea();
        house.payAccomodation(1000);

        House proxyHouse = (House) Proxy.newProxyInstance(House.class.getClassLoader(), new Class[]{House.class}, new Taxation(house));

        proxyHouse.payAccomodation(2000);
        System.out.println(proxyHouse.getHousingArea());
//        proxyHouse.getHousingArea();
    }
}
