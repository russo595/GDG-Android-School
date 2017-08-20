package com.rustem.chuck_norris_jokes.chuck_norris_jokes.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Joke {

    @SerializedName("type")
    private String type;

    @SerializedName("value")
    private List<Value> value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Value> getValue() {
        return value;
    }

    public void setValue(List<Value> value) {
        this.value = value;
    }
}
