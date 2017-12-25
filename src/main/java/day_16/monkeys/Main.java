package day_16.monkeys;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {
        Capucin aby = new Capucin(50);

        Class<Capucin> capucinClass
                = (Class<Capucin>) aby.getClass();
        printClass(capucinClass);
        printClass(Capucin.class);
    }

    private static void printClass(Class printedClass){
        System.out.println("className=" + printedClass.getName());

        printFields(printedClass);
        printMethods(printedClass);

        if(printedClass.getSuperclass() != null)
            printClass(printedClass.getSuperclass());
    }

    private static void printMethods(Class printedClass){
        System.out.println("getMethods");
        for(Method method : printedClass.getMethods()){
            System.out.print("Name Method = " + method.getName());
            System.out.println(" Return type = " + method.getReturnType().getName());

            System.out.println("PrintParams");
            for(Class parameterClass : method.getParameterTypes()){
                System.out.println("Param type name="
                                    +parameterClass.getName());

            }
        }
    }

    private static void printFields(Class printedClass){
        System.out.println("getFields");
        for(Field field
                : printedClass.getFields()){
            System.out.print("name=" + field.getName());
            System.out.print(" type=" +field.getType().getCanonicalName());
            System.out.print(" modif=" + field.getModifiers());
            System.out.println();
        }

        System.out.println("getDeclaredFields");
        for(Field field
                : printedClass.getDeclaredFields()){
            System.out.print("name=" + field.getName());
            System.out.print(" type=" +field.getType().getCanonicalName());
            System.out.print(" modif=" + field.getModifiers());
            System.out.println();
        }
    }
}
