package com.gdgkazan.punkapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.gdgkazan.punkapi.activities.BeersActivity;

public class MainActivity extends AppCompatActivity {

    private CrystalRangeSeekbar rangeSeekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);

        initViews();
    }

    private void initViews() {
        final TextView abvMin = (TextView) findViewById(R.id.abvMin);
        final TextView abvMax = (TextView) findViewById(R.id.abvMax);

        rangeSeekbar = (CrystalRangeSeekbar) findViewById(R.id.rangeSeekBar);
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                abvMin.setText(String.valueOf("minAbvLs: " + minValue));
                abvMax.setText(String.valueOf(maxValue + " :maxAbvGt"));
            }
        });

        findViewById(R.id.btnNetwork).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BeersActivity.class));
            }
        });
    }
}
