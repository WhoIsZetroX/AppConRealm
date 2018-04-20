package com.example.appconrealm;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

import static com.example.appconrealm.MainActivity.context_;
import static com.example.appconrealm.MainActivity.intentViewPerson;

public class ListPersonAdapter extends RealmBaseAdapter<Persona> implements ListAdapter {

    RealmResults<Persona> personas;
    Realm realm = Realm.getDefaultInstance();
    Context context = context_;
    Intent intent = intentViewPerson;

    public ListPersonAdapter(@Nullable OrderedRealmCollection<Persona> data) {
        super(data);

    }

    private static class ViewHolder {
        TextView txvTitol;
        TextView txvVersio;
        Button btn;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txvTitol = (TextView) convertView.findViewById(R.id.text1);
            viewHolder.txvVersio = (TextView) convertView.findViewById(R.id.text2);
            viewHolder.btn = convertView.findViewById(R.id.btn);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }



        Persona item = adapterData.get(position);
        viewHolder.txvTitol.setText(item.getNombre()+" "+item.getApellido());
        viewHolder.txvVersio.setText("ID: "+item.getId());
        viewHolder.btn.setHint(item.getId()+"");
        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personas = realm.where(Persona.class).equalTo("id", Integer.parseInt(viewHolder.btn.getHint().toString())).findAll();
                realm.beginTransaction();
                personas.deleteFirstFromRealm();
                realm.commitTransaction();
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Persona p = new Persona();
                personas = realm.where(Persona.class).equalTo("id", Integer.parseInt(viewHolder.btn.getHint().toString())).findAll();
                realm.beginTransaction();
                p.setAll(personas.first().getEspecialId(),personas.first().getId(),personas.first().getDni(),personas.first().getNombre(),personas.first().getApellido(),personas.first().getEdad());
                realm.commitTransaction();
                intent = new Intent(context, PersonActivity.class);
                intent.putExtra("lala", new String[]{""+p.getEspecialId(), ""+p.getId(), p.getDni(), p.getNombre(), p.getApellido(), ""+p.getEdad()});
                context.startActivity(intent);

            }
        });

        return convertView;
    }

}