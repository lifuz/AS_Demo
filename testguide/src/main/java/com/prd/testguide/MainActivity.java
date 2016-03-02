package com.prd.testguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.prd.testguide.utils.SharePreferencesUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("tag", SharePreferencesUtils.isFirst(this) + "");

        if (SharePreferencesUtils.isFirst(this)) {
            SharePreferencesUtils.setFirst(this);
        }

    }

}
