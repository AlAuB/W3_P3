package com.example.temperatureconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    SeekBar bar1, bar2;
    TextView temp_c, temp_f, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar1 = findViewById(R.id.celsius_bar);
        bar2 = findViewById(R.id.fahrenheit_bar);
        temp_c = findViewById(R.id.celsius);
        temp_f = findViewById(R.id.fahrenheit);
        message = findViewById(R.id.message);

        bar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // boolean b is a fromUser parameter, so when bar1 is changed by the user, the bar should change
                // if not, it should not change
                // bar2.setProgress((int) far) will trigger bar2's onProgressChanged, which in turn will trigger bar1's onProgressChanged
                if(b) {
                    double cel = i;
                    double far = trans_C_F(i);
                    temp_c.setText(String.format("%.02f", cel));
                    temp_f.setText(String.format("%.02f", far));
                    bar2.setProgress((int) far);

                    if (cel <= 20.0){
                        message.setText(R.string.warmer);
                    }else{
                        message.setText(R.string.colder);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (Double.parseDouble(temp_c.getText().toString()) <= 20.0){
                    message.setText(R.string.warmer);
                }else{
                    message.setText(R.string.colder);
                }
            }
        });

        bar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // same thing here as above
                // bar1.setProgress((int) far) will trigger bar1's onProgressChanged, which in turn will trigger bar2's onProgressChanged
                if(b) {
                    double far = i;
                    double cel = trans_F_C(i);
                    temp_c.setText(String.format("%.02f", cel));
                    temp_f.setText(String.format("%.02f", far));
                    bar2.setProgress((int) far);
                    bar1.setProgress((int)cel);

                    if (cel <= 20.0){
                        message.setText(R.string.warmer);
                    }else{
                        message.setText(R.string.colder);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (bar2.getProgress() < 32){
                    bar2.setProgress(32);
                    temp_f.setText("32.00");
                    temp_c.setText("0.00");
                }

                if (Double.parseDouble(temp_f.getText().toString()) <= 68.0){
                    message.setText(R.string.warmer);
                }else{
                    message.setText(R.string.colder);
                }
            }
        });

    }

    private double trans_C_F(int i){
        double f = i * (9.0/5.0) + 32.0;
        return f;
    }

    private double trans_F_C(int i){
        double c = (i - 32.0) * (5.0/9.0);
        return c;
    }
}