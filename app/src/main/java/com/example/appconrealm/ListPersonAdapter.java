package com.example.appconrealm;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

public class ListPersonAdapter extends RealmBaseAdapter<Persona> implements ListAdapter {

    public ListPersonAdapter(@Nullable OrderedRealmCollection<Persona> data) {
        super(data);

    }

    private static class ViewHolder {
        TextView txvTitol;
        TextView txvVersio;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txvTitol = (TextView) convertView.findViewById(android.R.id.text1);
            viewHolder.txvVersio = (TextView) convertView.findViewById(android.R.id.text2);
            convertView.setTag(viewHolder);
            //TODO: On long clic listener
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Persona item = adapterData.get(position);
        viewHolder.txvTitol.setText(item.getNombre());
        viewHolder.txvVersio.setText(item.getDni());

        return convertView;
    }
}