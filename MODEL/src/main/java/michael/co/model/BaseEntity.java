package michael.co.model;

import androidx.annotation.Nullable;

import java.util.Objects;

public class BaseEntity {
    protected String idFs;
    public BaseEntity(){}
    public String getIdFs(){
        return this.idFs;
    }
    public void setIdFs(String idFs){
        this.idFs = idFs;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof BaseEntity)){
            return false;
        }
        BaseEntity that = (BaseEntity)obj;
        return Objects.equals(idFs, that.idFs);
    }
}