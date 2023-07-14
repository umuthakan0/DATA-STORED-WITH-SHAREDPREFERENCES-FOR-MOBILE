package com.demirgroup.streddatabasic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import kotlin.random.Random;

public class MainActivity extends AppCompatActivity {

    EditText txtage;
    TextView lboltext;
    SharedPreferences sharedPreferences;
    AlertDialog.Builder alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtage=findViewById(R.id.txtage);
        lboltext=findViewById(R.id.textView);
        alert=new AlertDialog.Builder(this);
        alert.setMessage("Are you sure?");
        alert.setTitle("save");
        sharedPreferences=this.getSharedPreferences("com.demirgroup.streddatabasic" , Context.MODE_PRIVATE);
        int storedage = sharedPreferences.getInt("age" , 0);
        if(storedage==0){
            lboltext.setText("your age: ");
        }else {
            lboltext.setText("your age: " + storedage);
        }
    }
    int age;
    public void showage(View view){
        if (txtage.getText().toString().matches("")){
            lboltext.setText("Enter age.");
        }else {

            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    age = Integer.parseInt(txtage.getText().toString());
                    lboltext.setText("you age: " + age);
                    txtage.setText("");
                    sharedPreferences.edit().putInt("age",age).apply();
                    Toast.makeText(getApplicationContext(),"saved",Toast.LENGTH_LONG).show();
                }
            });
            alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getApplicationContext(),"not saved",Toast.LENGTH_LONG).show();
                }
            });
            alert.show();


        }
    }
    public void deleteage(View view){
        int storeddata=sharedPreferences.getInt("age",0);
        if(storeddata != 0){
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    sharedPreferences.edit().remove("age").apply();
                    lboltext.setText("your age: ");
                    Toast.makeText(getApplicationContext(),"deleted",Toast.LENGTH_LONG).show();
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getApplicationContext(),"not deleted",Toast.LENGTH_LONG).show();
                }
            });
            alert.show();

        }
    }

}