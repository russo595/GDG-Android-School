package com.rustem.rustem.weatherinkazan.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
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
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GraphicActivity extends AppCompatActivity {

    private static final String DATE_PATTERN = "dd.MM.yyyy";

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
        setTemperatureChart();
    }

    private void setTemperatureChart() {
        lineChart.animateY(1000);
        lineChart.setTouchEnabled(true);
        lineChart.setPinchZoom(true);

        XAxis x = lineChart.getXAxis();
        x.setEnabled(true);
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setDrawGridLines(false);

        YAxis yLeft = lineChart.getAxisLeft();
        yLeft.setEnabled(true);
        yLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        yLeft.setDrawAxisLine(false);
        yLeft.setDrawGridLines(true);
        yLeft.enableGridDashedLine(5f, 10f, 0f);
        yLeft.setGridColor(Color.parseColor("#333333"));
        yLeft.setXOffset(15);
    }

    private void setDataSet(LineDataSet dataSet) {
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setColors(ColorTemplate.getHoloBlue());
        dataSet.setDrawCircles(false);
        dataSet.notifyDataSetChanged();
        dataSet.setDrawFilled(false);
        dataSet.setValueTextSize(10f);
        dataSet.setFormSize(10f);
        dataSet.setLineWidth(3f);
        dataSet.setCubicIntensity(0.2f);
        dataSet.setDrawValues(false);
        dataSet.setColor(Color.parseColor("#E84E40"));
        dataSet.setHighlightEnabled(false);
    }

    private Long getDate(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("dd");
        return Long.valueOf(format.format(date));
    }

    private String setDateRange(List<Long> longs) {
        Date date1 = new Date(longs.get(0));
        Date date2 = new Date(longs.get(longs.size() - 1));
        Format format = new SimpleDateFormat(DATE_PATTERN, new Locale("ru"));
        return String.format("%s - %s", format.format(date1), format.format(date2));
    }
}