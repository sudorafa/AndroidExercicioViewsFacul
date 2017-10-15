package com.example.orafa.androidexercicioviewsfacul.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import com.example.orafa.androidexercicioviewsfacul.R;
import com.example.orafa.androidexercicioviewsfacul.constantes.AccountConstants;
import com.example.orafa.androidexercicioviewsfacul.dao.DUser;
import com.example.orafa.androidexercicioviewsfacul.model.User;
import com.example.orafa.androidexercicioviewsfacul.util.SecurityPreferences;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private User user;
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        this.mViewHolder.buttonEditProfile = (Button) findViewById(R.id.buttonEditProfile);
        this.mViewHolder.buttonChangePassword = (Button) findViewById(R.id.buttonChangePassword);
        this.mViewHolder.textViewName = (TextView) findViewById(R.id.textViewName);
        this.mViewHolder.textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        this.mViewHolder.textViewNick = (TextView) findViewById(R.id.textViewNick);
        this.mViewHolder.checkBoxPrivateAccount = (CheckBox) findViewById(R.id.checkBoxPrivateAccount);
        this.mViewHolder.checkBoxEmailAvailable = (CheckBox) findViewById(R.id.checkBoxEmailAvailable);
        this.mViewHolder.switchActiveAccount = (Switch) findViewById(R.id.switchActiveAccount);

        this.mViewHolder.buttonEditProfile.setOnClickListener(this);
        this.mViewHolder.buttonChangePassword.setOnClickListener(this);

        this.mSecurityPreferences = new SecurityPreferences(this);

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

    @Override
    protected void onResume() {
        super.onResume();
        runUser();
        verifyPreference();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveStats();
    }

    private void runUser() {
        DUser dUser = new DUser(this);
        user = dUser.findUser();
        if (user != null) {
            mViewHolder.textViewName.setText(user.getName());
            mViewHolder.textViewEmail.setText(user.getEmail());
            mViewHolder.textViewNick.setText("@" + user.getNick());
        } else {
            user = new User();
        }
    }

    private void saveStats() {
        //Private AccountKey
        if (this.mViewHolder.checkBoxPrivateAccount.isChecked()) {
            this.mSecurityPreferences.storeString(AccountConstants.PRIVATEACCOUNT, AccountConstants.PRIVATEACCOUNT_YES);
        } else {
            this.mSecurityPreferences.storeString(AccountConstants.PRIVATEACCOUNT, AccountConstants.PRIVATEACCOUNT_NO);
        }

        //Email Available
        if (this.mViewHolder.checkBoxEmailAvailable.isChecked()) {
            this.mSecurityPreferences.storeString(AccountConstants.EMAILAVAIBLE, AccountConstants.EMAILAVAIBLE_YES);
        } else {
            this.mSecurityPreferences.storeString(AccountConstants.EMAILAVAIBLE, AccountConstants.EMAILAVAIBLE_NO);
        }

        //Active Account
        if (this.mViewHolder.switchActiveAccount.isChecked()) {
            this.mSecurityPreferences.storeString(AccountConstants.ACTIVEACCOUNT, AccountConstants.ACTIVEACCOUNT_YES);
        } else {
            this.mSecurityPreferences.storeString(AccountConstants.ACTIVEACCOUNT, AccountConstants.ACTIVEACCOUNT_NO);
        }
    }

    private void verifyPreference() {
        //Private AccountKey
        String accountPrivate = this.mSecurityPreferences.getStoredString(AccountConstants.PRIVATEACCOUNT);
        if (accountPrivate.equals(AccountConstants.PRIVATEACCOUNT_YES)) {
            this.mViewHolder.checkBoxPrivateAccount.setChecked(true);
        } else {
            this.mViewHolder.checkBoxPrivateAccount.setChecked(false);
        }
        //Email Available
        String emailAvailable = this.mSecurityPreferences.getStoredString(AccountConstants.EMAILAVAIBLE);
        if (emailAvailable.equals(AccountConstants.EMAILAVAIBLE_YES)) {
            this.mViewHolder.checkBoxEmailAvailable.setChecked(true);
        } else {
            this.mViewHolder.checkBoxEmailAvailable.setChecked(false);
        }

        //Active Account
        String activeAccount = this.mSecurityPreferences.getStoredString(AccountConstants.ACTIVEACCOUNT);
        if (activeAccount.equals(AccountConstants.ACTIVEACCOUNT_YES)) {
            this.mViewHolder.switchActiveAccount.setChecked(true);
        } else {
            this.mViewHolder.switchActiveAccount.setChecked(false);
        }
    }

    private static class ViewHolder {
        Button buttonEditProfile;
        Button buttonChangePassword;
        TextView textViewName;
        TextView textViewEmail;
        TextView textViewNick;
        CheckBox checkBoxPrivateAccount;
        CheckBox checkBoxEmailAvailable;
        Switch switchActiveAccount;
    }
}