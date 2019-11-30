package sample;

public class HomeInvadedException extends RuntimeException{

    private static int num = 0;

    public HomeInvadedException(){
        super();
        num++;
    }

    public int getNum(){
        return num;
    }
}
