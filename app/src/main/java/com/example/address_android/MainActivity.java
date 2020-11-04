package com.example.address_android;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
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

    int selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnthem = (Button) findViewById(R.id.btnthem);
        list = (ListView) findViewById(R.id.lists);

        adapter = new AddressAdapter();
        list.setOnItemClickListener(onListClick);
        list.setOnItemLongClickListener(onLongListClick);
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
        if (requestCode==3 && resultCode == Edit.RESULT_OK)
        {
            Address a = new Address();

            Bundle bundle = data.getBundleExtra("DATA3");
            index1 = bundle.getString("name");
            index2 = bundle.getString("address");
            index3 = bundle.getString("zip");


            a.setName(index1);
            a.setAddress(index2);
            a.setZipcode(index3);

            addresseslist.set(selected, a);
            adapter.notifyDataSetChanged();


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
    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Address a = addresseslist.get(position); // lấy item được chọn

            selected = position;

            String s =  a.getName() +" "+ a.getAddress() +" "+a.getZipcode();
            Toast.makeText(getApplicationContext(), s ,Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this,Edit.class);
            Bundle bundle = new Bundle();
            bundle.putString("name", a.getName());
            bundle.putString("address", a.getAddress());
            bundle.putString("zip", a.getZipcode());
            intent.putExtra("DATA2",bundle);
            startActivityForResult(intent, 3);
        }
    };

    private AdapterView.OnItemLongClickListener onLongListClick = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Thong Bao")
                    .setMessage("Ban co muon Xoa?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            addresseslist.remove(position);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this,"Ban da xoa item",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this,"da huy thao tac xoa",Toast.LENGTH_SHORT).show();
                        }
                    });
            //Creating dialog box
            AlertDialog dialog  = builder.create();
            dialog.show();

            return false;
        }
    };

}