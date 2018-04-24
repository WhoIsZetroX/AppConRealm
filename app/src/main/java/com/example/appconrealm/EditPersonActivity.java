package com.example.appconrealm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;

public class EditPersonActivity extends AppCompatActivity {

    TextView especialId, id;
    EditText dni, nombre, apellido, edad, genero;
    String[] lala;
    Persona persona;
    Button sendData;
    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);

        findViewsByIds();

        realm = Realm.getDefaultInstance();

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            lala = extras.getStringArray("lala");
        }

        persona = new Persona(Integer.parseInt(lala[0]),Integer.parseInt(lala[1]),lala[2].toString(),lala[3].toString(),lala[4].toString(),Integer.parseInt(lala[5].toString()),lala[6].toString());

        especialId.setText(persona.getEspecialId()+"");
        id.setText(persona.getId()+"");
        dni.setHint(persona.getDni());
        nombre.setHint(persona.getNombre());
        apellido.setHint(persona.getApellido());
        edad.setHint(persona.getEdad()+"");
        genero.setHint(persona.getGenero());

        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(dni.getText().toString())){
                    dni.setText(dni.getHint().toString());
                }
                if (TextUtils.isEmpty(nombre.getText().toString())){
                    nombre.setText(nombre.getHint().toString());
                }
                if (TextUtils.isEmpty(apellido.getText().toString())){
                    apellido.setText(apellido.getHint().toString());
                }
                if (TextUtils.isEmpty(edad.getText().toString())){
                    edad.setText(edad.getHint().toString());
                }
                if (TextUtils.isEmpty(genero.getText().toString())){
                    genero.setText(genero.getHint().toString());
                }
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        persona.setAll(Integer.parseInt(especialId.getText().toString()), Integer.parseInt(id.getText().toString()), dni.getText().toString(), nombre.getText().toString(),apellido.getText().toString(), Integer.parseInt(edad.getText().toString()),genero.getText().toString().toUpperCase());
                        realm.copyToRealmOrUpdate(persona);
                    }
                });
                startActivity(new Intent(EditPersonActivity.this, MainActivity.class));
                finish();
            }
        });

    }
    void findViewsByIds(){
        especialId = findViewById(R.id.especialId);
        id = findViewById(R.id.id);
        dni = findViewById(R.id.dni);
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        edad = findViewById(R.id.edad);
        genero = findViewById(R.id.genero);
        sendData = findViewById(R.id.sendData);

    }

}
