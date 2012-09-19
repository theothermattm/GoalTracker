package com.theothermattm.goaltracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class Goals extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_goals, menu);
        return true;
    }
}
