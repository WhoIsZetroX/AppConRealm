package com.example.appconrealm;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class FilterActivity extends AppCompatActivity {

    ListView listView;
    FloatingActionButton floatingActionButton;
    Button button, clearFilter;
    Realm realm;
    private ListPersonAdapter listPersonAdapter;
    int listViewPos;
    RealmResults<Persona> personas;
    //public static String id;
    public static Context context_;
    public static Intent intentViewPerson;
    SeekBar seekbar, seekbar2;
    TextView textv1, textv2;
    EditText edit1, edit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        realm = Realm.getDefaultInstance(); // opens "myrealm.realm"

       /* realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (int i = 1; i<17;i++) {
                    Persona p = new Persona(i,i,i+"",i+"",i+"",i);
                    realm.copyToRealm(p);
                }
                Persona p1 = new Persona(41,208,"123123", "Aleix","Aragon",72);
                Persona p2 = new Persona(52,209,"456465","Brian","Adalid",24);
                Persona p3 = new Persona(63,210,"78998","ZetroX","zombieassasin7",99);
                realm.copyToRealm(p1);
                realm.copyToRealm(p2);
                realm.copyToRealm(p3);
            }
        });*/

        findViewsByIds();

        textv1.setText(0+"");
        textv2.setText(9999+"");

        final RealmResults<Persona> personas = realm.where(Persona.class).findAll();
        listPersonAdapter = new ListPersonAdapter(personas);
        if(personas.size()>0) listView.setAdapter(listPersonAdapter);
        listPersonAdapter.notifyDataSetChanged();

        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(adapterView.getSelectedView().getId() + " LALALALALA " + i);
                //Intent intent = new Intent(Activity.this,destinationActivity.class);
                //based on item add info to intent
//                startActivity(intent);
            }


        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textv1.setText("" + progress);
                final RealmResults<Persona> personas = realm.where(Persona.class).between("edad",Integer.parseInt(textv1.getText().toString()), Integer.parseInt(textv2.getText().toString())).findAll();
                listPersonAdapter = new ListPersonAdapter(personas);
                if(personas.size()>0) listView.setAdapter(listPersonAdapter);
                listPersonAdapter.notifyDataSetChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textv2.setText("" + progress);
                final RealmResults<Persona> personas = realm.where(Persona.class).between("edad",Integer.parseInt(textv1.getText().toString()), Integer.parseInt(textv2.getText().toString())).findAll();
                listPersonAdapter = new ListPersonAdapter(personas);
                if(personas.size()>0) listView.setAdapter(listPersonAdapter);
                listPersonAdapter.notifyDataSetChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        edit1.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                if (s.length()==0){
                    if (TextUtils.isEmpty(edit2.getText().toString())){
                        final RealmResults<Persona> personas = realm.where(Persona.class).between("edad", 0, 9999).findAll();
                        listPersonAdapter = new ListPersonAdapter(personas);
                        if (personas.size() > 0) listView.setAdapter(listPersonAdapter);
                        listPersonAdapter.notifyDataSetChanged();
                    }else {
                        final RealmResults<Persona> personas = realm.where(Persona.class).between("edad", 0, Integer.parseInt(edit2.getText().toString())).findAll();
                        listPersonAdapter = new ListPersonAdapter(personas);
                        if (personas.size() > 0) listView.setAdapter(listPersonAdapter);
                        listPersonAdapter.notifyDataSetChanged();
                    }
                }else {
                    if (TextUtils.isEmpty(edit2.getText().toString())){

                        final RealmResults<Persona> personas = realm.where(Persona.class).between("edad",Integer.parseInt(edit1.getText().toString()), 9999).findAll();
                        listPersonAdapter = new ListPersonAdapter(personas);
                        if(personas.size()>0) listView.setAdapter(listPersonAdapter);
                        listPersonAdapter.notifyDataSetChanged();

                    } else{

                        final RealmResults<Persona> personas = realm.where(Persona.class).between("edad",Integer.parseInt(edit1.getText().toString()), Integer.parseInt(edit2.getText().toString())).findAll();
                        listPersonAdapter = new ListPersonAdapter(personas);
                        if(personas.size()>0) listView.setAdapter(listPersonAdapter);
                        listPersonAdapter.notifyDataSetChanged();

                    }
                System.out.println(s.length());
                }
            }


            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });


        edit2.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                if (s.length()==0){
                    if (TextUtils.isEmpty(edit1.getText().toString())){
                        final RealmResults<Persona> personas = realm.where(Persona.class).between("edad", 0, 9999).findAll();
                        listPersonAdapter = new ListPersonAdapter(personas);
                        if (personas.size() > 0) listView.setAdapter(listPersonAdapter);
                        listPersonAdapter.notifyDataSetChanged();
                    }else {
                        final RealmResults<Persona> personas = realm.where(Persona.class).between("edad", Integer.parseInt(edit1.getText().toString()), 9999).findAll();
                        listPersonAdapter = new ListPersonAdapter(personas);
                        if (personas.size() > 0) listView.setAdapter(listPersonAdapter);
                        listPersonAdapter.notifyDataSetChanged();
                    }
                }else {
                    if (TextUtils.isEmpty(edit1.getText().toString())){

                        final RealmResults<Persona> personas = realm.where(Persona.class).between("edad",0, Integer.parseInt(edit2.getText().toString())).findAll();
                        listPersonAdapter = new ListPersonAdapter(personas);
                        if(personas.size()>0) listView.setAdapter(listPersonAdapter);
                        listPersonAdapter.notifyDataSetChanged();

                    } else{

                        final RealmResults<Persona> personas = realm.where(Persona.class).between("edad",Integer.parseInt(edit1.getText().toString()), Integer.parseInt(edit2.getText().toString())).findAll();
                        listPersonAdapter = new ListPersonAdapter(personas);
                        if(personas.size()>0) listView.setAdapter(listPersonAdapter);
                        listPersonAdapter.notifyDataSetChanged();

                    }
                    System.out.println(s.length());
                }
            }


            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });


    }

  /*  @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        final RealmResults<Persona> personas = realm.where(Persona.class).greaterThan("edad", seekbar1.getProgress()).and().lessThan("edad",seekbar2.getProgress()).findAll();
        listPersonAdapter = new ListPersonAdapter(personas);
        if(personas.size()>0) listView.setAdapter(listPersonAdapter);
        listPersonAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }*/

    void findViewsByIds(){
        listView = findViewById(R.id.listView);
        textv1= findViewById(R.id.textv1);
        textv2= findViewById(R.id.textv2);
        seekbar=(SeekBar) findViewById(R.id.seekBar);
        seekbar2=(SeekBar) findViewById(R.id.seekBar2);
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(FilterActivity.this, MainActivity.class));
        finish();
    }

}
