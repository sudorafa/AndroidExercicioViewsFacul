package com.example.orafa.androidexercicioviewsfacul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        this.mViewHolder.buttonEditProfile = (Button) findViewById(R.id.buttonEditProfile);
        this.mViewHolder.buttonChangePassword = (Button) findViewById(R.id.buttonChangePassword);

        this.mViewHolder.buttonEditProfile.setOnClickListener(this);
        this.mViewHolder.buttonChangePassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonEditProfile:
                Intent intentEditProfile = new Intent(this, EditProfileActivity.class);
                startActivity(intentEditProfile);
                break;
            case R.id.buttonChangePassword:
                Intent intentChangePassword = new Intent(this, ChangePasswordActivity.class);
                startActivity(intentChangePassword);
                break;
        }

    }
    private static class ViewHolder {
        Button buttonEditProfile;
        Button buttonChangePassword;
    }

}
