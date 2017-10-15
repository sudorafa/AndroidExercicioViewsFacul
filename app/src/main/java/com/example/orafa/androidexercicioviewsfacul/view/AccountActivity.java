package com.example.orafa.androidexercicioviewsfacul.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.orafa.androidexercicioviewsfacul.R;
import com.example.orafa.androidexercicioviewsfacul.dao.DUser;
import com.example.orafa.androidexercicioviewsfacul.model.User;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        this.mViewHolder.buttonEditProfile = (Button) findViewById(R.id.buttonEditProfile);
        this.mViewHolder.buttonChangePassword = (Button) findViewById(R.id.buttonChangePassword);
        this.mViewHolder.textViewName = (TextView) findViewById(R.id.textViewName);
        this.mViewHolder.textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        this.mViewHolder.textViewNick = (TextView) findViewById(R.id.textViewNick);

        this.mViewHolder.buttonEditProfile.setOnClickListener(this);
        this.mViewHolder.buttonChangePassword.setOnClickListener(this);

        runUser();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonEditProfile:
                Intent intentEditProfile = new Intent(this, EditProfileActivity.class);
                intentEditProfile.putExtra("user", user);
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
        TextView textViewName;
        TextView textViewEmail;
        TextView textViewNick;
    }

    private void runUser() {
        DUser dUser = new DUser(this);
        user = dUser.findUser();
        if (user != null) {
            mViewHolder.textViewName.setText(user.getName());
            mViewHolder.textViewEmail.setText(user.getEmail());
            mViewHolder.textViewNick.setText("@" +user.getNick());
        }else{
            user = new User();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        runUser();
    }
}
