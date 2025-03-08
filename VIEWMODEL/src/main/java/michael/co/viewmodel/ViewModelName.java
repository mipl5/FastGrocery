package michael.co.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelName extends ViewModel {
    // class members:
    private String name;
    private final MutableLiveData<String> nameStoreMutableLiveData;
    // constructors:
    public ViewModelName(){
        this.name = null;
        this.nameStoreMutableLiveData = new MutableLiveData<>();
    }
    // methods;
    public void setName(String value){
        this.name = value;
        nameStoreMutableLiveData.setValue(name);
    }
    // getters:
    public MutableLiveData<String> getMutableLiveData() {
        return nameStoreMutableLiveData;
    }
}
