package com.example.appconrealm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;

import static com.example.appconrealm.MainActivity.realm;

public class AddPersonActivity extends AppCompatActivity {

    EditText especialId, id, dni, nombre, apellido, edad;
    Button sendData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        findViewsByIds();


        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        int especial = Integer.parseInt(especialId.getText().toString());
                        Persona persona = new Persona(especial,id.getText().toString(),dni.getText().toString(),nombre.getText().toString(),apellido.getText().toString(),edad.getText().toString());
                        realm.copyToRealm(persona); // This will do a deep copy of everything
                    }
                });
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
