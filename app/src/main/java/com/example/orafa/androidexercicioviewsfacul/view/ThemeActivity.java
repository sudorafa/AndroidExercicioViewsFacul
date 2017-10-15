package com.example.orafa.androidexercicioviewsfacul.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orafa.androidexercicioviewsfacul.R;

public class ThemeActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        this.mViewHolder.seekBarBrightness = (SeekBar) findViewById(R.id.seekBarBrightness);
        this.mViewHolder.textViewDisplayBrightness = (TextView) findViewById(R.id.textViewDisplayBrightness);

        //Inicio do SeekBar
        mViewHolder.seekBarBrightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mViewHolder.textViewDisplayBrightness.setText(getString(R.string.display_brightness) + " " + progress);
            }
        });
        //Fim do SeekBar
    }

    private static class ViewHolder {
        TextView textViewDisplayBrightness;
        SeekBar seekBarBrightness;
    }
}
