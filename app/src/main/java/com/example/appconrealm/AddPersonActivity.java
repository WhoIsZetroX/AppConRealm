package com.example.appconrealm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;

public class AddPersonActivity extends AppCompatActivity {

    EditText especialId, id, dni, nombre, apellido, edad, genero;
    Button sendData;
    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        findViewsByIds();

        realm = Realm.getDefaultInstance();

        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        int especial = Integer.parseInt(especialId.getText().toString());
                        Persona persona = new Persona(especial,Integer.parseInt(id.getText().toString()),dni.getText().toString(),nombre.getText().toString(),apellido.getText().toString(),Integer.parseInt(edad.getText().toString()),genero.getText().toString());
                        realm.copyToRealm(persona); // This will do a deep copy of everything
                    }
                });
                startActivity(new Intent(AddPersonActivity.this, MainActivity.class));
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
