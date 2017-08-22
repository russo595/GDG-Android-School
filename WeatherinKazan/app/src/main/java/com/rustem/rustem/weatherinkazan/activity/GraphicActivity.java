package com.rustem.rustem.weatherinkazan.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.rustem.rustem.weatherinkazan.R;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GraphicActivity extends AppCompatActivity {

    @BindView(R.id.lineChart)
    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);
        ButterKnife.bind(this);

        HashMap<Long, Double> extra = (HashMap<Long, Double>) getIntent().getSerializableExtra("map");
        addDataToChart(extra);
    }

    private void addDataToChart(HashMap<Long, Double> extra) {
        ArrayList<Entry> entries = new ArrayList<>();
        TreeMap<Long, Double> map = new TreeMap<>();
        map.putAll(extra);
        List<Long> longs = new ArrayList<>();
        for (Map.Entry<Long, Double> entry : map.entrySet()) {
            Long key = getDate(entry.getKey());
            longs.add(entry.getKey());
            Double value = entry.getValue();
            entries.add(new Entry(key.floatValue(), (int) value.floatValue()));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Данные за 7 дней");

        LineData data = new LineData(dataSet);

        setDataSet(dataSet);

        lineChart.setData(data);
        Description description = new Description();
        description.setText(setDateRange(longs));
        description.setTextSize(15f);
        lineChart.setDescription(description);
        lineChart.animateY(1000);
    }

    private void setDataSet(LineDataSet dataSet){
        dataSet.setColors(ColorTemplate.getHoloBlue());
        dataSet.setDrawCircles(true);
        dataSet.notifyDataSetChanged();
        dataSet.setDrawFilled(true);
        dataSet.setValueTextSize(10f);
        dataSet.setFormSize(10f);
        dataSet.setLineWidth(3f);
        dataSet.setCircleRadius(5f);
    }

    private Long getDate(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("dd");
        return Long.valueOf(format.format(date));
    }

    private String setDateRange(List<Long> longs) {
        Date date1 = new Date(longs.get(0));
        Date date2 = new Date(longs.get(longs.size() - 1));
        Format format = new SimpleDateFormat("dd.MM.yyyy");
        return String.format("%s - %s", format.format(date1), format.format(date2));
    }
}