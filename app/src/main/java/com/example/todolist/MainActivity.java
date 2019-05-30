package com.example.todolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;
    EditText newitem;
    Button add;
    ArrayList <String> itemlist = new ArrayList<String>();

    ArrayAdapter<String> veriAdaptoru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.listview);
        newitem = findViewById(R.id.editText);
        add = findViewById(R.id.button);

         itemlist = filehelper .readData(this);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoitem = newitem.getText().toString();

                itemlist.add(todoitem);

                newitem.setText("");
                filehelper.writeData(itemlist, getApplicationContext());

                veriAdaptoru.notifyDataSetChanged();


                // list.setAdapter(veriAdaptoru);
            }
        });

        veriAdaptoru=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemlist);
        list.setAdapter(veriAdaptoru);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Delete");
                    alert.setMessage("Do you want to delete this item from list? ");
                    alert.setCancelable(true);

                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();

                        }
                    });

                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            itemlist.remove(position);
                            veriAdaptoru.notifyDataSetChanged();
                            filehelper.writeData(itemlist, getApplicationContext());

                        }
                    });

                    AlertDialog al = alert.create();
                    al.show();
                }
        });

    }


}
