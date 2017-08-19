package com.gdgkazan.punkapi.UtilsPunk;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import static com.gdgkazan.punkapi.UtilsPunk.PunkApiParams.ABV_GT;
import static com.gdgkazan.punkapi.UtilsPunk.PunkApiParams.ABV_LT;
import static com.gdgkazan.punkapi.UtilsPunk.PunkApiParams.IBU_GT;
import static com.gdgkazan.punkapi.UtilsPunk.PunkApiParams.IBU_LT;

public class UtilPunk extends Activity {
    @NonNull
    public Map<String, Integer> getDataMap(Intent intent) {
        Map<String, Integer> data = new HashMap<>();
        data.put(ABV_GT.getText(), getIntentFromExternalActivity(intent, ABV_LT.getText()));
        data.put(ABV_LT.getText(), getIntentFromExternalActivity(intent, ABV_GT.getText()));
        data.put(IBU_GT.getText(), getIntentFromExternalActivity(intent, IBU_LT.getText()));
        data.put(IBU_LT.getText(), getIntentFromExternalActivity(intent, IBU_GT.getText()));
        return data;
    }

    private int getIntentFromExternalActivity(Intent intent, String s) {
        return Integer.parseInt(intent.getStringExtra(s));
    }
}
