package com.example.appconrealm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                try{
                    if (TextUtils.isEmpty(especialId.getText().toString())){
                        especialId.setError("The item name cannot be empty.");
                        especialId.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(id.getText().toString())){
                        id.setError("The item name cannot be empty.");
                        id.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(dni.getText().toString())){
                        dni.setError("The item name cannot be empty.");
                        dni.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(nombre.getText().toString())){
                        nombre.setError("The item name cannot be empty.");
                        nombre.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(apellido.getText().toString())){
                        apellido.setError("The item name cannot be empty.");
                        apellido.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(edad.getText().toString())){
                        edad.setError("The item name cannot be empty.");
                        edad.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(genero.getText().toString())){
                        genero.setError("The item name cannot be empty.");
                        genero.requestFocus();
                        return;
                    }
                    if (!genero.getText().toString().equals("M")&&!genero.getText().toString().equals("F")&&!genero.getText().toString().equals("m")&&!genero.getText().toString().equals("f")){
                        genero.setError("M-m or F-f.");
                        genero.requestFocus();
                        return;
                    }

                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            int especial = Integer.parseInt(especialId.getText().toString());
                            Persona persona = new Persona(especial,Integer.parseInt(id.getText().toString()),dni.getText().toString(),nombre.getText().toString(),apellido.getText().toString(),Integer.parseInt(edad.getText().toString()),genero.getText().toString().toUpperCase());
                            realm.copyToRealm(persona); // This will do a deep copy of everything
                        }
                    });
                    startActivity(new Intent(AddPersonActivity.this, MainActivity.class));
                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                }
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
