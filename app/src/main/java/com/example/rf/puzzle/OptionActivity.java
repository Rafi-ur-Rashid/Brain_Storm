package com.example.rf.puzzle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
