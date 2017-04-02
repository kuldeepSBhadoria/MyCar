package com.example.kulde.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class addCar extends AppCompatActivity {

    public  TextView myTimeTextview,EditTextModel;
    public EditText  EditTExtYear, EditTextMiles;
    CarDetails Cd = new CarDetails();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        myTimeTextview = (TextView) findViewById(R.id.editTextDate);
        EditTextModel = (TextView) findViewById(R.id.textViewEnterModel);
        EditTExtYear = (EditText) findViewById(R.id.textViewEnterYear);
        EditTextMiles = (EditText) findViewById(R.id.textViewEnterMiles);

        setSupportActionBar(toolbar);
        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
        myTimeTextview.setText(currentDateTimeString);
        /*
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
*/

       /*
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
                // Writing Contacts to log
        Log.d("Name: ", log);

        */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
    public void cleartext(){

        EditTextModel.setText("");
        EditTExtYear.setText("");
        EditTextMiles.setText("");
    }
    public void onclickBackbutton(View view){
        finish();
        Intent intent = new Intent(addCar.this, Welcomepage.class);
        startActivity(intent);
    }

    public void onclickAddbutton(View view){
        DatabaseHandler Db = new DatabaseHandler(this);
        try {
            Cd.setUTC(String.valueOf(myTimeTextview.getText()));
            Cd.setCarname(String.valueOf(EditTextModel.getText()));
            Cd.setYear(Integer.parseInt(EditTExtYear.getText().toString()));
            Cd.setMilesCA(Double.parseDouble(EditTextMiles.getText().toString()));
        }catch (Exception e1){
            Toast.makeText(getApplicationContext(),e1.getMessage(), Toast.LENGTH_LONG);
        }

        try {

        Log.d("Insert:", "Inserting.9----. ");
        // CarDetails( String carname, int year, long milesCA,  String UTC,long milesFA,long fuelQty,long amount )

            Db.addCar(new CarDetails(Cd.getCarname(), Cd.getYear(), Cd.getMilesCA(), Cd.getUTC(), 0,0,0) );
        }catch (Exception e1){
            Toast.makeText(addCar.this,e1.getMessage(), Toast.LENGTH_LONG);
        }

        //Toast.makeText(addCar.this,"Added Successfully",Toast.LENGTH_LONG).show();
        LayoutInflater inflater = getLayoutInflater();
        View toastLayout = inflater.inflate(R.layout.mytoast, (ViewGroup) findViewById(R.id.custom_toast_layout));

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastLayout);
        toast.show();
        cleartext();


    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(addCar.this, Welcomepage.class);
        startActivity(intent);
    }
}

