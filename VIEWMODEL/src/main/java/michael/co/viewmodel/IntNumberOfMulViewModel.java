package michael.co.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import michael.co.model.IntNumberOfMul;

public class IntNumberOfMulViewModel extends ViewModel {
    private IntNumberOfMul intNumberOfMul;
    private MutableLiveData<IntNumberOfMul> number;

    public IntNumberOfMulViewModel(){
        intNumberOfMul = new IntNumberOfMul();
        number = new MutableLiveData<>();
    }

    public void setNumber(int value){
        intNumberOfMul.setNumberMul(value);
        number.setValue(intNumberOfMul);
    }

    public MutableLiveData<IntNumberOfMul> getMutableLiveData(){
        return number;
    }

    public void increment(){
        intNumberOfMul.increment();
        number.setValue(intNumberOfMul);
    }
    public void decrement(){
        intNumberOfMul.decrement();
        number.setValue(intNumberOfMul);
    }
}
