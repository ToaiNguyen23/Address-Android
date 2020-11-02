package com.example.address_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Insert extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String arr[] = {"Long An","Ho Chi Minh","Binh Dinh","Dong Nai"};
    String zip[] = {"7000","5000","3000","60000"};
    String arr2[] = {"Viet Nam","Anh","Phap","My"};
    String zip2[] = {"7001","5001","3001","60001"};

    Spinner spncountry;
    ArrayAdapter<String> adapter;
    Address a = new Address();
    EditText zipcode;
    boolean rdbchecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        spncountry = (Spinner) findViewById(R.id.spinnercountry);

        RadioGroup type  = (RadioGroup)findViewById(R.id.type);
        zipcode = (EditText) findViewById(R.id.zipcode);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spncountry.setAdapter(adapter);
        spncountry.setOnItemSelectedListener(this);

    }
    public void onClick(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rdbtrongnuoc:
                if(checked){
                    adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arr);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                    spncountry.setAdapter(adapter);
                    spncountry.setOnItemSelectedListener(this);
                    rdbchecked = false;
                }
                break;
            case R.id.rdbngoainuoc:
                if(checked){
                    adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arr2);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                    spncountry.setAdapter(adapter);
                    spncountry.setOnItemSelectedListener(this);
                    rdbchecked = true;
                }
                break;
        }

    }
    
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (rdbchecked){
            zipcode.setText(zip2[position]);
            Toast.makeText(getApplicationContext(), "Selected User: "+zip2[position] ,Toast.LENGTH_SHORT).show();
        }else{
            zipcode.setText(zip[position]);
            Toast.makeText(getApplicationContext(), "Selected User: "+zip[position] ,Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}