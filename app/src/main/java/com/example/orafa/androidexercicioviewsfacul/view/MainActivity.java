package com.example.orafa.androidexercicioviewsfacul.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.orafa.androidexercicioviewsfacul.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    ArrayAdapter<String> mAdapter;
    List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.listViewMenu = (ListView) findViewById(R.id.listViewMenu);

        mList = new ArrayList<>();
        mList.add(getString(R.string.settings_account));
        mList.add(getString(R.string.settings_theme));

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mList);
        this.mViewHolder.listViewMenu.setAdapter(mAdapter);

        mViewHolder.listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent itentAccount = new Intent(MainActivity.this, AccountActivity.class);
                        startActivity(itentAccount);
                        break;
                    case 1:
                        Intent itentTheme = new Intent(MainActivity.this, ThemeActivity.class);
                        startActivity(itentTheme);
                        break;
                }

            }
        });
    }

    private static class ViewHolder {
        ListView listViewMenu;
    }
}
