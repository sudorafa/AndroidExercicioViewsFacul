package com.example.orafa.androidexercicioviewsfacul.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.orafa.androidexercicioviewsfacul.R;
import com.example.orafa.androidexercicioviewsfacul.constantes.ThemeConstants;
import com.example.orafa.androidexercicioviewsfacul.util.SecurityPreferences;

public class ThemeActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        this.mViewHolder.seekBarBrightness = (SeekBar) findViewById(R.id.seekBarBrightness);
        this.mViewHolder.textViewDisplayBrightness = (TextView) findViewById(R.id.textViewDisplayBrightness);
        this.mViewHolder.switchNightMode = (Switch) findViewById(R.id.switchNightMode);
        this.mViewHolder.spinnerFontStyle = (Spinner) findViewById(R.id.spinnerFontStyle);
        this.mViewHolder.spinnerFontSize = (Spinner) findViewById(R.id.spinnerFontSize);
        this.mViewHolder.radioGroupColor = (RadioGroup) findViewById(R.id.radioGroupColor);

        this.mSecurityPreferences = new SecurityPreferences(this);

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
                //Salvar Status na Constante do Brightness para o SecurityPreferences
                mSecurityPreferences.storeString(ThemeConstants.BRIGHTNESS, String.valueOf(progress));
            }
        });
        //Fim do SeekBar

        //Salvar Status na Constante do spinnerFontStyle para o SecurityPreferences
        this.mViewHolder.spinnerFontStyle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int fontStyle = parent.getSelectedItemPosition();
                mSecurityPreferences.storeString(ThemeConstants.FONTESTYLE, String.valueOf(fontStyle));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        //Fim do spinnerFontStyle

        //Salvar Status na Constante do spinnerFontSize para o SecurityPreferences
        this.mViewHolder.spinnerFontSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int fontSize = parent.getSelectedItemPosition();
                mSecurityPreferences.storeString(ThemeConstants.FONTESIZE, String.valueOf(fontSize));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        //Fim do spinnerFontSize
    }

    @Override
    protected void onResume() {
        super.onResume();
        verifyPreference();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveStats();
    }

    private void saveStats() {
        //Night Mode
        if (this.mViewHolder.switchNightMode.isChecked()) {
            this.mSecurityPreferences.storeString(ThemeConstants.NIGHTMODE, ThemeConstants.NIGHTMODE_YES);
        } else {
            this.mSecurityPreferences.storeString(ThemeConstants.NIGHTMODE, ThemeConstants.NIGHTMODE_NO);
        }

        // Color
        int idRadioColor = mViewHolder.radioGroupColor.getCheckedRadioButtonId();
        this.mSecurityPreferences.storeString(ThemeConstants.RADIOCOLOR, String.valueOf(idRadioColor));
    }

    private void verifyPreference() {
        //Brightness
        String brightness = this.mSecurityPreferences.getStoredString(ThemeConstants.BRIGHTNESS);
        this.mViewHolder.textViewDisplayBrightness.setText(getString(R.string.display_brightness) + " " + brightness);
        this.mViewHolder.seekBarBrightness.setProgress(Integer.parseInt(brightness));

        //Night Mode
        String emailAvailable = this.mSecurityPreferences.getStoredString(ThemeConstants.NIGHTMODE);
        if (emailAvailable.equals(ThemeConstants.NIGHTMODE_YES)) {
            this.mViewHolder.switchNightMode.setChecked(true);
        } else {
            this.mViewHolder.switchNightMode.setChecked(false);
        }

        //Font Style
        String fontStyle = this.mSecurityPreferences.getStoredString(ThemeConstants.FONTESTYLE);
        this.mViewHolder.spinnerFontStyle.setSelection(Integer.parseInt(fontStyle));

        //Font Size
        String fontSize = this.mSecurityPreferences.getStoredString(ThemeConstants.FONTESIZE);
        this.mViewHolder.spinnerFontSize.setSelection(Integer.parseInt(fontSize));

        //Color
        String idRadioColor = this.mSecurityPreferences.getStoredString(ThemeConstants.RADIOCOLOR);
        if (!idRadioColor.equals("")) {
            this.mViewHolder.radioGroupColor.check(Integer.parseInt(idRadioColor));
        }

    }

    private static class ViewHolder {
        TextView textViewDisplayBrightness;
        SeekBar seekBarBrightness;
        Switch switchNightMode;
        Spinner spinnerFontStyle;
        Spinner spinnerFontSize;
        RadioGroup radioGroupColor;
    }
}
