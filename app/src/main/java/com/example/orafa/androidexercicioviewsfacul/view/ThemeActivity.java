package com.example.orafa.androidexercicioviewsfacul.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.example.orafa.androidexercicioviewsfacul.R;

public class ThemeActivity extends AppCompatActivity {

    SeekBar customSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        customSeekBar = (SeekBar) findViewById(R.id.seekBarBrightness);

        //Inicio do SeekBar
        // perform seek bar change listener event used for getting the progress value
        customSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                //Passar dados para o Text
            }
        });
        //Fim do SeekBar
    }
}
