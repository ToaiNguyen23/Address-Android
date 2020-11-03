package com.example.address_android;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Address> addresseslist = new ArrayList<Address>();

    AddressAdapter adapter = null;
    Button btnthem;
    ListView list;
    String index1, index2, index3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnthem = (Button) findViewById(R.id.btnthem);
        list = (ListView) findViewById(R.id.lists);

        adapter = new AddressAdapter();
        list.setAdapter(adapter);

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Insert.class);

                startActivityForResult(intent, 2);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==2 && resultCode == Insert.RESULT_OK)
        {
            Address a = new Address();

            Bundle bundle = data.getBundleExtra("DATA");
            index1 = bundle.getString("name");
            index2 = bundle.getString("address");
            index3 = bundle.getString("zip");

            a.setName(index1);
            a.setAddress(index2);
            a.setZipcode(index3);

            addresseslist.add(a);

            String s = index1 + index2 + index3;

            Toast.makeText(getApplicationContext(), s ,Toast.LENGTH_SHORT).show();
        }
    }
    class AddressAdapter extends ArrayAdapter<Address> {
        public AddressAdapter(Context context, int textViewResourceId){
            super(context, textViewResourceId);
        }

        public AddressAdapter(){
            super(MainActivity.this,
                    android.R.layout.simple_list_item_1, addresseslist);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View row = convertView;
            if( row == null)
            {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row, null);

            }

            Address r = addresseslist.get(position);

            String tmp = r.getAddress() + " - " + r.getZipcode();

            ((TextView)row.findViewById(R.id.title)).setText(r.getName());
            ((TextView)row.findViewById(R.id.address)).setText(tmp);
            ImageView icon = (ImageView) row.findViewById(R.id.icon);

            String type = r.getType();
            icon.setImageResource(R.drawable.icon_d);

            return  row;
        }
    }
}