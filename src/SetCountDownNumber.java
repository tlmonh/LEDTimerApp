
public class SetCountDownNumber {
    private int sA;
    private int sB;
    private int mA;
    private int mB;
    private int hA;
    private int hB;
    //Constructor that allows conversion of 2 digits to separate digits
    public SetCountDownNumber(int seconds,int minutes,int hours){
        sA = seconds % 10;
        sB = seconds / 10;
        mA = minutes % 10;
        mB = minutes / 10;
        hA = hours % 10;
        hB = hours / 10;
    }
    //All these functions allow a return of the separate digits of hour min and second for printout
    public int sec1(){
        return sA;
    }
    public int sec2(){
        return sB;
    }
    public int min1(){
        return mA;
    }
    public int min2(){
        return mB;
    }
    public int hour1(){
        return hA;
    }
    public int hour2(){
        return hB;
    }
}
