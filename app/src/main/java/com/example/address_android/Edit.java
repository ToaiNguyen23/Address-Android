package com.example.address_android;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Edit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String arr[] = {"Long An","Ho Chi Minh","Binh Dinh","Dong Nai"};
    String zip[] = {"7000","5000","3000","60000"};
    String arr2[] = {"Viet Nam","Anh","Phap","My"};
    String zip2[] = {"7001","5001","3001","60001"};

    Spinner spncountry;
    ArrayAdapter<String> adapter;
    Address a = new Address();
    EditText code;
    EditText name;
    EditText address;
    Button btnsua;
    boolean rdbchecked = false;
    String index1="";
    String index2="";
    String index3="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        spncountry = (Spinner) findViewById(R.id.spinnercountry);

        RadioGroup type  = (RadioGroup)findViewById(R.id.type);
        code = (EditText) findViewById(R.id.zipcode);
        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.addr);

        btnsua = (Button) findViewById(R.id.btnsua);

        rdbchecked = false;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spncountry.setAdapter(adapter);
        spncountry.setOnItemSelectedListener(this);

        //edit
        Intent data = getIntent();
        Bundle bundle = data.getBundleExtra("DATA2");
        index1 = bundle.getString("name");
        index2 = bundle.getString("address");
        index3 = bundle.getString("zip");

        name.setText(index1);
        address.setText(index2);
        code.setText(index3);
    }


    public void onClickRdb(View view) {
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
            code.setText(zip2[position]);
            Toast.makeText(getApplicationContext(), "Selected User: "+zip2[position] ,Toast.LENGTH_SHORT).show();
        }else{
            code.setText(zip[position]);
            Toast.makeText(getApplicationContext(), "Selected User: "+zip[position] ,Toast.LENGTH_SHORT).show();
        }



    }

    public void onClickSua(View view){

        String names  = name.getText().toString();
        String addresss = address.getText().toString();
        String zipcodes = code.getText().toString();

        Intent intent = new Intent();

        Bundle bundle = new Bundle();
        bundle.putString("name", names);
        bundle.putString("address", addresss);
        bundle.putString("zip", zipcodes);
        intent.putExtra("DATA3",bundle);
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}