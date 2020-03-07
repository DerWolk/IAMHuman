package de.bitfull.iamh.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.annotation.processing.Generated;

@Data
@Generated("com.robohorse.robopojogenerator")
public class ActionsItem {

    @SerializedName("action")
    private String action;

    @SerializedName("selectType")
    private String selectType;

    @SerializedName("selector")
    private String selector;

    @SerializedName("value")
    private String value;
    
    @Override
    public String toString() {
        return
                "ActionsItem{" +
                        "action = '" + action + '\'' +
                        ",selectType = '" + selectType + '\'' +
                        ",selector = '" + selector + '\'' +
                        ",value = '" + value + '\'' +
                        "}";
    }
}