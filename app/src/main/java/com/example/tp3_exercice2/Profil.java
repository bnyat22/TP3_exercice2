package com.example.tp3_exercice2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;

public class Profil extends AppCompatActivity {
    TextView nom , prenom , age ,ntel , mPasse , nombre ;
    private static final String FILENAME = "fichier.txt";
    InputStream fis ;
    InputStreamReader isr;
    BufferedReader reader ;
    String str;
    String[] info = {"text1" , "text2" , "text3" , "text4" , "text5" , "text6"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_activity);
        nom =  findViewById(R.id.nomm);
        prenom =  findViewById(R.id.prenome);
        age =  findViewById(R.id.agee);
        ntel = findViewById(R.id.numero);
        mPasse = findViewById(R.id.mPasse);
        nombre = findViewById(R.id.nombre_utilisation);

        try {
            fis = openFileInput("fich");

isr = new InputStreamReader(fis);
            reader = new BufferedReader(isr);
            str = "";
            int i = 0;

            while ((str = reader.readLine()) != null) {
              //  Log.i(this.getClass().getSimpleName() , "heyyyyyyy" + str);

                info[i] = str;
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

            nom.setText(info[0]);
            prenom.setText(info[1]);
            age.setText(info[2]);
            ntel.setText(info[3]);
            mPasse.setText(info[4]);
            nombre.setText(info[5]);


    }




    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
