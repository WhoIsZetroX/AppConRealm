package com.example.appconrealm;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
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
    Realm realm;
    String[] items;
    ArrayAdapter<String> adapter;
    private ListPersonAdapter listPersonAdapter;
    int listViewPos;
    RealmResults<Persona> personas;
    // Popup
    private LayoutInflater layoutInflater;
    private View popupView;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance(); // opens "myrealm.realm"

        findViewsByIds();
        setOnClicks();
        listView.setLongClickable(true);
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
                        listPersonAdapter.notifyDataSetChanged();

                        break;
                    case 1:
                        final RealmResults<Persona> persones = realm.where(Persona.class).findAll();
                        listPersonAdapter = new ListPersonAdapter(persones);
                        if(persones.size()>0) listView.setAdapter(listPersonAdapter);
                        listPersonAdapter.notifyDataSetChanged();

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });


        registerForContextMenu(listView);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {


            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                openContextMenu(parent);

                /*This outputs the CORRECT position*/
                System.out.println("Position: " + position + ", id: " + id);
                listViewPos=position;
                return true;
            }



        });



        //realm = Realm.getDefaultInstance(); // opens "myrealm.realm"

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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_context_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        int selectedId = info.position;  // HERE the problem shows up

        // switch statement for debugging purposes
        switch (item.getItemId()){
            case R.id.deleteUser:
                System.out.println("DELETE USER");
                personas = realm.where(Persona.class).findAll();
                realm.beginTransaction();
                personas.get(listViewPos).deleteFromRealm();
                realm.commitTransaction();
                listPersonAdapter.notifyDataSetChanged();
                return true;
            case R.id.editUser:
                System.out.println("EDIT USER");
                personas = realm.where(Persona.class).findAll();
                realm.beginTransaction();
                Persona p = new Persona(personas.get(listViewPos).getEspecialId(),personas.get(listViewPos).getId(),personas.get(listViewPos).getDni(),personas.get(listViewPos).getNombre(),personas.get(listViewPos).getApellido(),personas.get(listViewPos).getEdad());
                realm.commitTransaction();
                listPersonAdapter.notifyDataSetChanged();
                Intent i = new Intent(MainActivity.this, EditPersonActivity.class);
                i.putExtra("lala", new String[]{""+p.getEspecialId(), p.getId(), p.getDni(), p.getNombre(), p.getApellido(), p.getEdad()});
                /*i.putExtra("especialId", ""+p.getEspecialId());
                i.putExtra("id", p.getId());
                i.putExtra("dni", p.getDni());
                i.putExtra("nombre", p.getNombre());
                i.putExtra("apellido", p.getApellido());
                i.putExtra("edad", p.getEdad());*/
                startActivity(i);
                return true;
            default:
                return super.onContextItemSelected(item);

        }
    }
}
