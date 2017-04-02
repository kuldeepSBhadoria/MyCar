package com.example.kulde.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class Welcomepage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void ButtonAddNewCar(View view){
        finish();
        Intent intent = new Intent(Welcomepage.this, addCar.class);
        startActivity(intent);
    }
    public void ViewcarButton(View view){
        int count =0;
        try {
//            DatabaseHandler Db = new DatabaseHandler(this);
//            List<CarDetails> contacts = Db.getAllContacts();
//
//            for (CarDetails cn : contacts) {
//
//                count++;
//            }

            //count = Db.getContactsCount();
            count = 1;
            if(count == 0)
            {
                Toast.makeText(Welcomepage.this,"No Car in Garage First Add A car", Toast.LENGTH_LONG).show();
            }
            else
            {
                finish();
                Intent intent = new Intent(Welcomepage.this, SelectCar.class);
                startActivity(intent);
            }

        }catch (Exception e){ Toast.makeText(Welcomepage.this,e.getMessage(), Toast.LENGTH_LONG).show();}

    }

}
