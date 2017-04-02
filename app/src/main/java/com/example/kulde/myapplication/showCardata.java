package com.example.kulde.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Modifier;
import java.util.List;

public class showCardata extends AppCompatActivity {
    static int p;
    static int yes;
    EditText editTextcarname,edittextCaryear, edittextinitialmiles,edittextdate, edittexdfueladdedmiles, edittextprevmiles,edittextfuelquantity;
    EditText edittextamountindollar, edittextpricepergallon;
    Button delete;
    DatabaseHandler Db = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cardata);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        editTextcarname = (EditText) findViewById(R.id.carName);

        edittextinitialmiles = (EditText) findViewById(R.id.InitialMiles);
        edittextdate = (EditText) findViewById(R.id.Date);
        edittexdfueladdedmiles = (EditText) findViewById(R.id.FuelAddedMiles);
        edittextprevmiles = (EditText) findViewById(R.id.PreviousMiles);
        edittextfuelquantity = (EditText) findViewById(R.id.FuelQuantity);
        edittextamountindollar = (EditText) findViewById(R.id.PaidAmount);
        edittextpricepergallon = (EditText) findViewById(R.id.PricePGal);

        delete = (Button)findViewById(R.id.deletecar);

        setSupportActionBar(toolbar);
        Intent intent = getIntent();

        String carid = intent.getStringExtra("carid");
        Log.d("Reading: ", "Reading Selected records..");
        try {
            List<CarDetails> contacts = Db.getSpecificContacts(Integer.parseInt(carid));
            for (CarDetails cn : contacts)
            {
                //String log = "Id: "+cn.getId()+" ,Name: " + cn.getCarname() + " , " + cn.getUTC();
                editTextcarname.setText(cn.getCarname());
                edittextinitialmiles.setText(String.valueOf(cn.getMilesCA()));
                edittextdate.setText(String.valueOf(cn.getUTC()));
                edittexdfueladdedmiles.setText(String.valueOf(cn.getMilesFA()));
                edittextprevmiles.setText("");
                edittextfuelquantity.setText(String.valueOf(cn.getFuelQty()));
                edittextamountindollar.setText(String.valueOf(cn.getAmount()));

            }
        }catch(Exception e){Log.d("Exception ShowCardata: ", e.getMessage());}


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
    }
    public void onClickDeletecar(View view){
        openSurityDeleteCarAlert();

    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(showCardata.this, SelectCar.class);
        startActivity(intent);
    }
    public void openSurityDeleteCarAlert(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(" Are you sure ?");

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(showCardata.this,"Record Deleted", Toast.LENGTH_LONG).show();
                CarDetails car = new CarDetails();
                car.setCarname(String.valueOf(editTextcarname.getText()));

                    editTextcarname.setText("# deleted #");
                    Db.deleteContactbyname(car.getCarname());
                    Log.d(" ShowCardata", "Deleted");
                    edittextinitialmiles.setText("");
                    edittextdate.setText("");
                    edittexdfueladdedmiles.setText("");
                    edittextprevmiles.setText("");
                    edittextfuelquantity.setText("");
                    edittextamountindollar.setText("");
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void onclickback(View view) {
        finish();
        Intent intent = new Intent(showCardata.this, SelectCar.class);
        startActivity(intent);
    }

}
