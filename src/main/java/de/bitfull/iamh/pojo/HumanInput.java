package de.bitfull.iamh.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.annotation.processing.Generated;
import java.util.List;

@Data
@Generated("com.robohorse.robopojogenerator")
public class HumanInput {

    @SerializedName("targetUrl")
    private String targetUrl;

    @SerializedName("actions")
    private List<ActionsItem> actions;

    @Override
    public String toString() {
        return
                "HumanInput{" +
                        "targetUrl = '" + targetUrl + '\'' +
                        ",actions = '" + actions + '\'' +
                        "}";
    }
}