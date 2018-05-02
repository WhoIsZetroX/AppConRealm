package com.example.appconrealm;

import android.util.Log;

import java.time.LocalDate;
import java.util.Calendar;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class Migration implements RealmMigration {

    @Override
    public void migrate(final DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        if(oldVersion == 0) {
            Log.d("Migration", "actualitzant a la versió 1");
            RealmObjectSchema personaSchema = schema.get("Persona");
            personaSchema.addField("añoNacimiento", int.class, FieldAttribute.REQUIRED).addIndex("añoNacimiento").transform(new RealmObjectSchema.Function() {
                @Override
                public void apply(DynamicRealmObject obj) {

                    int oldType = obj.getInt("edad");

                    int calc = Calendar.getInstance().get(Calendar.YEAR)-oldType;

                    obj.setInt("añoNacimiento", calc);

                }
            });


            oldVersion++;
        }
    }

}
