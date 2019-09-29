package com.example.iofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText filename = (EditText) findViewById(R.id.txttenfile);
        final EditText content = (EditText) findViewById(R.id.txtnoidung);

        Button btnmoi = (Button) findViewById(R.id.btnnhap);
        btnmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filename.setText("");
                content.setText("");
            }
        });
        final ArrayList<String> filenamelist = new ArrayList<>();
        filenamelist.add("Hello");
        final Spinner spinnerfilename = (Spinner)findViewById(R.id.spfilename);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,filenamelist);
        spinnerfilename.setAdapter(arrayAdapter);
        spinnerfilename.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                filename.setText(filenamelist.get(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Button btnLuu = (Button)findViewById(R.id.btnluu);
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Filename = filename.getText().toString();
                filenamelist.add(Filename);
                try {
                    FileOutputStream fout = openFileOutput(Filename, Context.MODE_PRIVATE);
                    fout.write(content.getText().toString().getBytes());
                    fout.close();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this,"Lỗi lưu file",Toast.LENGTH_LONG).show();
                }
            }
        });
        Button btnmo = (Button)findViewById(R.id.btnmo);
        btnmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = filename.getText().toString();
                StringBuffer buffer = new StringBuffer();
                String line = null;
                try{
                    FileInputStream fin = openFileInput(name);
                    BufferedReader bread = new BufferedReader(new InputStreamReader(fin));
                    while ((line = bread.readLine())!=null)
                        buffer.append(line).append("\n");
                        content.setText(buffer.toString());
                }
                catch (Exception e){

                }
            }
        });
    }
}
