package com.example.orafa.androidexercicioviewsfacul.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.orafa.androidexercicioviewsfacul.R;
import com.example.orafa.androidexercicioviewsfacul.dao.DUser;
import com.example.orafa.androidexercicioviewsfacul.model.User;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        this.mViewHolder.editTextName = (EditText) findViewById(R.id.editTextName);
        this.mViewHolder.editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        this.mViewHolder.editTextNick = (EditText) findViewById(R.id.editTextNick);
        this.mViewHolder.buttonSaveProfile = (Button) findViewById(R.id.buttonSaveProfile);

        this.mViewHolder.buttonSaveProfile.setOnClickListener(this);

        runUser();
    }

    private void runUser() {
        DUser dUser = new DUser(this);
        user = dUser.findUser();
        if (user != null) {
            mViewHolder.editTextName.setText(user.getName());
            mViewHolder.editTextEmail.setText(user.getEmail());
            mViewHolder.editTextNick.setText(user.getNick());
        } else {
            user = new User();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSaveProfile:
                user.setName(mViewHolder.editTextName.getText().toString());
                user.setEmail(mViewHolder.editTextEmail.getText().toString());
                user.setNick("@" + mViewHolder.editTextNick.getText().toString());
                DUser dUserSave = new DUser(EditProfileActivity.this);
                dUserSave.save(user);
                finish();
                break;
        }
    }

    private static class ViewHolder {
        EditText editTextName;
        EditText editTextEmail;
        EditText editTextNick;
        Button buttonSaveProfile;
    }
}
