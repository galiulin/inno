package lesson_02;

import java.io.IOException;

public class lec_01 {
    public static void main(String[] args) {

    }

    private String met01(String string)
    {
        return string += "met01";
    }
    private String met02(String string)
    {
        return string += "met01";
    }
}

class Parent{
    protected void myVoidFunc(Number number, String string){
        System.out.println("i myVoidFunc");
    }

    ReturnValueParent myValueFunc(){
        System.out.println("i myValueFunc");

        return null;
    }

    void exceptionFunc() throws IOException{
        System.out.println("i exceptionFunc");
    }
}

class Child extends Parent {
    /*возможно увеличить область видимости*/
    @Override
    public void myVoidFunc(Number number, String string) {
        System.out.println("i myVoidFunc child");
    }


    /*возможно изменить возвращаемое значение
    * если происходит уточнение */
    @Override
    ReturnValueChild myValueFunc() {
        super.myValueFunc();
        return null;
    }

    /*Можно убрать пробрасываемые exception,
    * можно изменить exception в случае расширения
    * можно добавить неотслеживаемые исключения*/
    @Override
    void exceptionFunc() throws NullPointerException {
//        super.exceptionFunc();
    }
}

class ReturnValueParent {

}


class ReturnValueChild extends ReturnValueParent {

}