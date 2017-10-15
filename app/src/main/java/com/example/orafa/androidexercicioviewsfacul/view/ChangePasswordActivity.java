package com.example.orafa.androidexercicioviewsfacul.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.orafa.androidexercicioviewsfacul.R;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        this.mViewHolder.buttonSavePassword = (Button) findViewById(R.id.buttonSavePassword);

        this.mViewHolder.buttonSavePassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSavePassword:
                Toast.makeText(getApplication(), R.string.password_updated_successfully, Toast.LENGTH_LONG).show();
                break;
        }

    }

    private static class ViewHolder {
        Button buttonSavePassword;
    }
}
