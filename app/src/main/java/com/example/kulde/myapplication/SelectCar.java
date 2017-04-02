package com.example.kulde.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

public class SelectCar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseHandler Db = new DatabaseHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_car);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TableLayout tl = (TableLayout) findViewById(R.id.Table);

        // Reading all contacts
        try {
            Log.d("Reading: ", "Reading all contacts..");
            List<CarDetails> contacts = Db.getAllContacts();

            for (CarDetails cn : contacts) {
                //String log = "Id: "+cn.getId()+" ,Name: " + cn.getCarname() + " , " + cn.getUTC();
                String log = cn.getCarname() ;
                {
                    // create a new TableRow
                        TableRow row = new TableRow(this);

                        // create a new TextView
                        TextView t = new TextView(this);
                        // set the text to "text xx"
                        t.setText(log);
                        // add the TextView and the CheckBox to the new TableRow
                        row.setId(cn.getId());

                        Button btn=new Button(this);
                        btn.setId(cn.getId());
                        btn.setText(log);
                        btn.setTextColor(getResources().getColor(R.color.black));
                        btn.setBackground(getResources().getDrawable(R.drawable.buttonlist));
                        btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            System.out.println("car ID is:- " + v.getId());
                            {
                                finish();
                                Intent intent = new Intent(SelectCar.this, showCardata.class);
                                intent.putExtra("carid", String.valueOf(v.getId()));
                                startActivity(intent);
                            }
                        }
                    });
                    //btn.setBackgroundColor(getResources().getColor(R.color.burlywood));
                    //btn.setGravity(Gravity.CENTER_HORIZONTAL);

                    row.addView(btn);
                    //row.addView(t);
                    // add the TableRow to the TableLayout
                    tl.addView(row,new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                }
                // Writing Contacts to log
                Log.d("Car Details: ", log);
            }

        }catch (Exception e){}

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(SelectCar.this, Welcomepage.class);
        startActivity(intent);
    }
    public void onclickBackButton(View view) {
        finish();
        Intent intent = new Intent(SelectCar.this, Welcomepage.class);
        startActivity(intent);
    }
}
