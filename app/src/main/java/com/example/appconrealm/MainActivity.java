package com.example.appconrealm;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncCredentials;
import io.realm.SyncUser;
import io.realm.exceptions.RealmException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listView;
    FloatingActionButton floatingActionButton;
    Spinner spinner;
    public static Realm realm;
    String[] items;
    ArrayAdapter<String> adapter;
    private ListPersonAdapter listPersonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewsByIds();
        setOnClicks();

        items = new String[]{"Filtro 1", "Filtro 2"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (position) {
                    case 0:
                        final RealmResults<Persona> personas = realm.where(Persona.class).findAll();
                        listPersonAdapter = new ListPersonAdapter(personas);
                        if(personas.size()>0) listView.setAdapter(listPersonAdapter);
/*
                        selectedData = SelectedData.FILM;
*/
                        listPersonAdapter.notifyDataSetChanged();

                        break;
                    case 1:
                        final RealmResults<Persona> persones = realm.where(Persona.class).findAll();
                        listPersonAdapter = new ListPersonAdapter(persones);
                        if(persones.size()>0) listView.setAdapter(listPersonAdapter);
/*
                        selectedData = SelectedData.CINEMA;
*/
                        listPersonAdapter.notifyDataSetChanged();

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });


        realm = Realm.getDefaultInstance(); // opens "myrealm.realm"

        /* try {

            *//*final Persona p = new Persona(1,"qwer","asd","asd","asd","asd");
            realm.beginTransaction();
            realm.copyToRealm(p); // This will do a deep copy of everything
            realm.commitTransaction();*//*

        }catch (RealmException r){
            r.printStackTrace();
        }finally {
            realm.close();
        }*/

    }
    void findViewsByIds(){
        spinner= findViewById(R.id.spinner);
        listView = findViewById(R.id.listView);
        floatingActionButton = findViewById(R.id.floatingActionButton);
    }

    public void setOnClicks() {
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if ( v == floatingActionButton ) {
            startActivity(new Intent(this, AddPersonActivity.class));
        }
    }
}
