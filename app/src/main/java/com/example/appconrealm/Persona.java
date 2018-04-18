package com.example.appconrealm;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by dam2a on 13/04/18.
 */

/*
 *
 * DOCUMENTACIÓN SOBRE LAS ANOTACIONES
 * https://realm.io/docs/java/latest/
 *
 */

public class Persona extends RealmObject {
    @Ignore
    private int especialId;

    @PrimaryKey
    private String id;

    @Index
    private String dni;

    private String nombre, apellido, edad;


    public Persona(int especialId, String id, String dni, String nombre, String apellido, String edad) {
        this.especialId = especialId;
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public Persona() {
        /*
        this.especialId = especialId;
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        */
    }

    public int getEspecialId() {
        return especialId;
    }

    public void setEspecialId(int especialId) {
        this.especialId = especialId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setAll(int especialId, String id, String dni, String nombre, String apellido, String edad) {
        this.especialId = especialId;
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "especialId=" + especialId +
                ", id='" + id + '\'' +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad='" + edad + '\'' +
                '}';
    }
}