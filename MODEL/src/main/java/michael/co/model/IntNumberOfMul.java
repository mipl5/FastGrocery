package michael.co.model;

public class IntNumberOfMul {
    private int numberMul;

    public IntNumberOfMul(){
        this.numberMul = 1;
    }
    public IntNumberOfMul(int numberMul){
        this.numberMul = numberMul;
    }

    public void setNumberMul(int numberMul){
        this.numberMul = numberMul;
    }
    public int getNumberMul(){
        return this.numberMul;
    }

    public void increment(){
        numberMul++;
    }
    public void decrement(){
        if (numberMul > 0)
            numberMul--;
    }
}
