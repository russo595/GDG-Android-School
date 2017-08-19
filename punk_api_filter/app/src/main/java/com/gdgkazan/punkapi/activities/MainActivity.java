package com.gdgkazan.punkapi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.gdgkazan.punkapi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.gdgkazan.punkapi.UtilsPunk.PunkApiParams.ABV_GT;
import static com.gdgkazan.punkapi.UtilsPunk.PunkApiParams.ABV_LT;
import static com.gdgkazan.punkapi.UtilsPunk.PunkApiParams.IBU_GT;
import static com.gdgkazan.punkapi.UtilsPunk.PunkApiParams.IBU_LT;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rangeSeekBarABV)
    CrystalRangeSeekbar rangeSeekbarABV;
    @BindView(R.id.rangeSeekBarIBU)
    CrystalRangeSeekbar rangeSeekbarIBU;
    @BindView(R.id.abvMin)
    TextView abvMin;
    @BindView(R.id.abvMax)
    TextView abvMax;
    @BindView(R.id.ibuMin)
    TextView ibuMin;
    @BindView(R.id.ibuMax)
    TextView ibuMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        rangeSeekbarABV.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                abvMin.setText(String.valueOf(minValue));
                abvMax.setText(String.valueOf(maxValue));
            }
        });

        rangeSeekbarIBU.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                ibuMin.setText(String.valueOf(minValue));
                ibuMax.setText(String.valueOf(maxValue));
            }
        });
    }

    @OnClick(R.id.btnNetwork)
    public void openIntentExampleScreen() {
        Intent intent = new Intent(this, BeersActivity.class);
        intent.putExtra(ABV_LT.getText(), abvMin.getText());
        intent.putExtra(ABV_GT.getText(), abvMax.getText());
        intent.putExtra(IBU_LT.getText(), ibuMin.getText());
        intent.putExtra(IBU_GT.getText(), ibuMax.getText());
        startActivity(intent);
    }
}
