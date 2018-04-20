package com.example.appconrealm;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncCredentials;
import io.realm.SyncUser;
import io.realm.exceptions.RealmException;

import static io.realm.internal.SyncObjectServerFacade.getApplicationContext;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
        setOnClicks();


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
        button= findViewById(R.id.button);
        listView = findViewById(R.id.listView);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        clearFilter = findViewById(R.id.clearFilter);
    }

    public void setOnClicks() {
        floatingActionButton.setOnClickListener(this);
        button.setOnClickListener(this);
        clearFilter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if ( v == floatingActionButton ) {
            startActivity(new Intent(this, AddPersonActivity.class));
        } else if (v == button){
            //abrirPopUp();
            startActivity(new Intent(MainActivity.this, FilterActivity.class));
        } else if (v == clearFilter){
            final RealmResults<Persona> personas = realm.where(Persona.class).findAll();
            listPersonAdapter = new ListPersonAdapter(personas);
            if(personas.size()>0) listView.setAdapter(listPersonAdapter);
            listPersonAdapter.notifyDataSetChanged();
        }
    }

    void abrirPopUp(){
        /*final RealmResults<Persona> personas = realm.where(Persona.class).between("edad",20,80).findAll();
        listPersonAdapter = new ListPersonAdapter(personas);
        if(personas.size()>0) listView.setAdapter(listPersonAdapter);
        listPersonAdapter.notifyDataSetChanged();*/
        layoutInflater =(LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        popupView = layoutInflater.inflate(R.layout.popup, null);
        popupWindow = new PopupWindow(popupView, RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT);

        textv1= findViewById(R.id.textv1);
        textv2= findViewById(R.id.textv2);
        seekbar=(SeekBar) findViewById(R.id.seekBar);
        seekbar2=(SeekBar) findViewById(R.id.seekBar2);

        popupView.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.showAsDropDown(popupView, 100, 300, 1);
    }



}
