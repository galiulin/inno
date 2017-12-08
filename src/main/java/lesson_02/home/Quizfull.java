package lesson_02.home;

public class Quizfull {
    public static void main(String[] args) {
        boolean b = false;
        if (b == false)
            if (b = false)
                System.out.println("if statement");
        else
            System.out.println("else statement");
    }
}


class Integ{
    int i;
    Integer se = new Integer(44);

    Integ(int i){
        this.i = i;
    }

    void setI(int i){
        this.i = i;
    }

    int getI(){
        return i;
    }
}
