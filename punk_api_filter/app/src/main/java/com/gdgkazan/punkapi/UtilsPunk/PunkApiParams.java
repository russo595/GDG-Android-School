package com.gdgkazan.punkapi.UtilsPunk;

public enum PunkApiParams {
    ABV_GT("abv_gt"), ABV_LT("abv_lt"), IBU_GT("ibu_gt"), IBU_LT("ibu_lt");

    PunkApiParams(String text) {
        this.text = text;
    }

    String text;

    public String getText() {
        return text;
    }
}

