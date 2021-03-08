package com.example.tp3_exercice2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.UUID;

public class Inscription extends AppCompatActivity {

    EditText nom , prenom , age , ntel , mPasse;
    TextView id;
     Button sub;
     FileOutputStream fos;
    OutputStreamWriter outputWriter;
    private static final String PRENOM = "prenom";
    private static final String NOM = "nom";
    private static final String AGE = "age";
    private static final String NTEL = "tel";
    private static final String ID = "id";
    private static final String FILENAME = "fich";
   private static int compte =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom =  findViewById(R.id.nom);
        prenom =  findViewById(R.id.prenom);
        age =  findViewById(R.id.age);
        ntel =  findViewById(R.id.nTel);
        id = findViewById(R.id.identite);
         sub =  findViewById(R.id.sub);
         mPasse = findViewById(R.id.passe);
        if (savedInstanceState != null)
        {
            nom.setText(savedInstanceState.getString(NOM));
            prenom.setText(savedInstanceState.getString(PRENOM));
            age.setText(savedInstanceState.getString(AGE));
            ntel.setText(savedInstanceState.getString(NTEL));
            id.setText(savedInstanceState.getString(ID));

        }
        if (id.getText().toString().equals(""))
        {
            id.setText(generateId());
        }

       getLifecycle().addObserver(new LifeCycleAware());


        final Intent intent = new Intent(this,Profil.class);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     intent.putExtra("my_file" , outputWriter);

                try {
                    fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));

                    String concatination = nom.getText().toString() + id.getText().toString();

                    bufferedWriter.write(concatination);
                    bufferedWriter.newLine();
                    bufferedWriter.write(prenom.getText().toString());
                    bufferedWriter.newLine();
                    bufferedWriter.write(age.getText().toString());
                    bufferedWriter.newLine();
                    bufferedWriter.write(ntel.getText().toString());
                    bufferedWriter.newLine();
                    bufferedWriter.write(mPasse.getText().toString());
                    bufferedWriter.newLine();
                    bufferedWriter.write(String.valueOf(compte));
                    bufferedWriter.newLine();
                    bufferedWriter.close();

                } catch (IOException e) {
                    Log.e("STACKOVERFLOW", e.getMessage(), e);
                }
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putString(NOM , nom.getText().toString());
        outState.putString(PRENOM , prenom.getText().toString());
        outState.putString(AGE , age.getText().toString());
        outState.putString(NTEL , ntel.getText().toString());
        outState.putString(ID ,id.getText().toString());

    }

    private String generateId()
    {
        // String secure = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        String secure = UUID.randomUUID().toString();
        return secure;
    }
    public static int getCompte()
    {
        return compte++;
    }

}
