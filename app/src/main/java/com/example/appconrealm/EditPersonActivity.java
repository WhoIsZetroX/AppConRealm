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
    EditText dni, nombre, apellido, edad;
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
            System.out.println(Integer.parseInt(lala[0])+" - "+lala[0]+ " - "+lala[1].toString()+lala[2].toString()+lala[3].toString()+lala[4].toString()+lala[5].toString()+" LAAAAAAAAAA");
        }

        persona = new Persona(Integer.parseInt(lala[0]),lala[1].toString(),lala[2].toString(),lala[3].toString(),lala[4].toString(),lala[5].toString());

        especialId.setText(persona.getEspecialId()+"");
        id.setText(persona.getId());
        dni.setHint(persona.getDni());
        nombre.setHint(persona.getNombre());
        apellido.setHint(persona.getApellido());
        edad.setHint(persona.getEdad());

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
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        persona.setAll(Integer.parseInt(especialId.getText().toString()), id.getText().toString(), dni.getText().toString(), nombre.getText().toString(),apellido.getText().toString(), edad.getText().toString());
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
        sendData = findViewById(R.id.sendData);

    }

}
