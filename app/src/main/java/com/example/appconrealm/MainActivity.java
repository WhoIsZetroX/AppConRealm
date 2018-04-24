package com.example.appconrealm;

import android.content.Context;
import android.content.Intent;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static ListView listView;
    FloatingActionButton item_create, item_filter;
    FloatingActionMenu floatingActionMenu;
    Button button, clearFilter, cerrarPopup;
    Realm realm;
    public static ListPersonAdapter listPersonAdapter;
    int listViewPos;
    RealmResults<Persona> personas;
    //public static String id;
    public static Context context_;
    public static Intent intentViewPerson;
    SeekBar seekbar, seekbar2;
    TextView textv1, textv2;
    EditText edit1, edit2, edit3;

    // Popup
    private LayoutInflater layoutInflater;
    private View popupView;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance(); // opens "myrealm.realm"
        context_ = getApplicationContext();


        /*realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (int i = 1; i<7;i++) {
                    Persona p = new Persona(i,i,i+"",i+"",i+"",i, "F");
                    realm.copyToRealm(p);
                }
                Persona p1 = new Persona(41,208,"123123", "Aleix","Aragon",72,"M");
                Persona p2 = new Persona(52,209,"456465","Brian","Adalid",24, "F");
                Persona p3 = new Persona(63,210,"78998","ZetroX","zombieassasin7",99, "M");
                realm.copyToRealm(p1);
                realm.copyToRealm(p2);
                realm.copyToRealm(p3);
            }
        });*/

        findViewsByIds();
        setOnClicks();


        final RealmResults<Persona> personas = realm.where(Persona.class).findAll();
        listPersonAdapter = new ListPersonAdapter(personas);
        if(personas.size()>0) listView.setAdapter(listPersonAdapter);
        listPersonAdapter.notifyDataSetChanged();

        listView.setClickable(true);

    }

    void findViewsByIds(){
        floatingActionMenu = findViewById(R.id.fab);
        listView = findViewById(R.id.listView);
        item_create = findViewById(R.id.item_create);
        item_filter = findViewById(R.id.item_Filter);
        clearFilter = findViewById(R.id.clearFilter);
    }

    public void setOnClicks() {
//        floatingActionButton.setOnClickListener(this);
        item_create.setOnClickListener(this);
        item_filter.setOnClickListener(this);
        clearFilter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if ( v == item_create ) {
            floatingActionMenu.close(true);
            startActivity(new Intent(this, AddPersonActivity.class));
        } else if (v == item_filter){
            floatingActionMenu.close(true);
            abrirPopUp();
            //startActivity(new Intent(MainActivity.this, FilterActivity.class));
        } else if (v == clearFilter){
            final RealmResults<Persona> personas = realm.where(Persona.class).findAll();
            listPersonAdapter = new ListPersonAdapter(personas);
            if(personas.size()>0) listView.setAdapter(listPersonAdapter);
            listPersonAdapter.notifyDataSetChanged();
        }
    }

    void abrirPopUp(){
        layoutInflater =(LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        popupView = layoutInflater.inflate(R.layout.popup, null);
        popupWindow = new PopupWindow(popupView, RadioGroup.LayoutParams.MATCH_PARENT,
                RadioGroup.LayoutParams.WRAP_CONTENT);

        opciones();

        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(listView, Gravity.CENTER, 0, 0);
    }

    void opciones() {

        textv1 = popupView.findViewById(R.id.edadMin);
        textv2 = popupView.findViewById(R.id.edadMax);
        seekbar = (SeekBar) popupView.findViewById(R.id.seekBar);
        seekbar2 = (SeekBar) popupView.findViewById(R.id.seekBar2);
        edit1 = popupView.findViewById(R.id.edadE);
        edit2 = popupView.findViewById(R.id.generoE);
        edit3 = popupView.findViewById(R.id.nombreE);
        RadioButton nombreR = popupView.findViewById(R.id.nombreR);
        final LinearLayout nombres = popupView.findViewById(R.id.nombres);
        RadioButton edadR = popupView.findViewById(R.id.edadR);
        final LinearLayout edads = popupView.findViewById(R.id.edads);
        RadioButton generoR = popupView.findViewById(R.id.generoR);
        final LinearLayout generos = popupView.findViewById(R.id.generos);
        RadioButton edadesR = popupView.findViewById(R.id.edadesR);
        final LinearLayout edades = popupView.findViewById(R.id.edades);

        nombres.setVisibility(View.VISIBLE);
        edads.setVisibility(View.INVISIBLE);
        generos.setVisibility(View.INVISIBLE);
        edades.setVisibility(View.INVISIBLE);

        nombreR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                   if (isChecked) {nombres.setVisibility(View.VISIBLE);}else {nombres.setVisibility(View.INVISIBLE);}
               }
           }
        );

        edadR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                   if (isChecked) {edads.setVisibility(View.VISIBLE);}else {edads.setVisibility(View.INVISIBLE);}
                                               }
                                           }
        );

        generoR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                   if (isChecked) {generos.setVisibility(View.VISIBLE);}else {generos.setVisibility(View.INVISIBLE);}
                                               }
                                           }
        );

        edadesR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                   if (isChecked) {edades.setVisibility(View.VISIBLE);}else {edades.setVisibility(View.INVISIBLE);}
                                               }
                                           }
        );

        textv1.setText(0 + "");
        textv2.setText(9999 + "");

        edit3.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                if (s.length()==0){

                    final RealmResults<Persona> personas = realm.where(Persona.class).findAll();
                    listPersonAdapter = new ListPersonAdapter(personas);
                    if (personas.size() > 0) listView.setAdapter(listPersonAdapter);
                    listPersonAdapter.notifyDataSetChanged();

                }else {

                    final RealmResults<Persona> personas = realm.where(Persona.class).contains("nombre",edit3.getText().toString()).findAll();
                    listPersonAdapter = new ListPersonAdapter(personas);
                    if(personas.size()>0) listView.setAdapter(listPersonAdapter);
                    listPersonAdapter.notifyDataSetChanged();

                    System.out.println(s.length());
                }
            }


            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        edit2.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                if (s.length()==0){

                    final RealmResults<Persona> personas = realm.where(Persona.class).findAll();
                    listPersonAdapter = new ListPersonAdapter(personas);
                    if (personas.size() > 0) listView.setAdapter(listPersonAdapter);
                    listPersonAdapter.notifyDataSetChanged();

                }else {

                    final RealmResults<Persona> personas = realm.where(Persona.class).contains("genero",edit2.getText().toString()).findAll();
                    listPersonAdapter = new ListPersonAdapter(personas);
                    if(personas.size()>0) listView.setAdapter(listPersonAdapter);
                    listPersonAdapter.notifyDataSetChanged();

                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
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

                    final RealmResults<Persona> personas = realm.where(Persona.class).findAll();
                    listPersonAdapter = new ListPersonAdapter(personas);
                    if (personas.size() > 0) listView.setAdapter(listPersonAdapter);
                    listPersonAdapter.notifyDataSetChanged();

                }else {

                    try {
                        int num = Integer.parseInt(edit1.getText().toString());
                        Log.i("",num+" is a number");
                        final RealmResults<Persona> personas = realm.where(Persona.class).equalTo("edad",Integer.parseInt(edit1.getText().toString())).findAll();
                        listPersonAdapter = new ListPersonAdapter(personas);
                        if(personas.size()>0) listView.setAdapter(listPersonAdapter);
                        listPersonAdapter.notifyDataSetChanged();
                    } catch (NumberFormatException e) {
                        Toast.makeText(getApplicationContext(),"Introduce un numero",Toast.LENGTH_SHORT).show();
                    }



                    System.out.println(s.length());
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

    }

}
