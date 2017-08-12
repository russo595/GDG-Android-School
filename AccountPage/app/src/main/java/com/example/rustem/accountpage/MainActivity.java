package com.example.rustem.accountpage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textNotification;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textNotification = (TextView) findViewById(R.id.on_off_notification);
        imageView = (ImageView) findViewById(R.id.check);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    imageView.setImageResource(R.mipmap.checkbox_marked);
                    textNotification.setText(R.string.on_notification);
                    flag = !flag;
                } else {
                    imageView.setImageResource(R.mipmap.checkbox_blank_outline);
                    textNotification.setText(R.string.off_notification);
                    flag = !flag;
                }
            }
        });
    }
}
