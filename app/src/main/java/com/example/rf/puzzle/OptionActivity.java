package com.example.rf.puzzle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class OptionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void goPuzzle(View view){
        //view.setBackgroundColor(Color.GREEN);
        Intent intent=new Intent(OptionActivity.this,puzzleActivity.class);
        startActivity(intent);
    }
    public void goPyramid(View view){
        //view.setBackgroundColor(Color.GREEN);
        Intent intent=new Intent(OptionActivity.this,num_pyramidActivity.class);
        startActivity(intent);
    }
    public void goMemory(View view){
        //view.setBackgroundColor(Color.GREEN);
        Intent intent=new Intent(OptionActivity.this,Short_term_memoryActivity.class);
        startActivity(intent);
    }
}
