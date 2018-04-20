package com.example.appconrealm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;

public class PersonActivity extends AppCompatActivity {
    TextView especialId, id, dni, nombre, apellido, edad;
    //EditText dni, nombre, apellido, edad;
    String[] lala;
    Persona persona;
    Button sendData;
    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        findViewsByIds();

        realm = Realm.getDefaultInstance();

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            lala = extras.getStringArray("lala");
        }

        persona = new Persona(Integer.parseInt(lala[0]),Integer.parseInt(lala[1]),lala[2].toString(),lala[3].toString(),lala[4].toString(),Integer.parseInt(lala[5].toString()));

        especialId.setText(persona.getEspecialId()+"");
        id.setText(persona.getId()+"");
        dni.setHint(persona.getDni());
        nombre.setHint(persona.getNombre());
        apellido.setHint(persona.getApellido());
        edad.setHint(persona.getEdad()+"");

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
                persona.setAll(Integer.parseInt(especialId.getText().toString()), Integer.parseInt(id.getText().toString()), dni.getText().toString(), nombre.getText().toString(),apellido.getText().toString(), Integer.parseInt(edad.getText().toString()));
                Intent i = new Intent(new Intent(PersonActivity.this, EditPersonActivity.class));
                i.putExtra("lala", new String[]{""+persona.getEspecialId(), ""+persona.getId(), persona.getDni(), persona.getNombre(), persona.getApellido(), ""+persona.getEdad()});
                startActivity(i);
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

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PersonActivity.this, MainActivity.class));
        finish();
    }

}
