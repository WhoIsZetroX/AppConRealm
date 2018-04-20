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

    private int especialId;

    @PrimaryKey
    private int id;

    @Index
    private String dni;

    private String nombre, apellido, genero;
    private int edad;

    public Persona(int especialId, int id, String dni, String nombre, String apellido, int edad, String genero) {
        this.especialId = especialId;
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero=genero;
    }

    public Persona() {}

    public int getEspecialId() {
        return especialId;
    }

    public void setEspecialId(int especialId) {
        this.especialId = especialId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setAll(int especialId, int id, String dni, String nombre, String apellido, int edad, String genero) {
        this.especialId = especialId;
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero=genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "especialId=" + especialId +
                ", id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", genero='" + genero + '\'' +
                ", edad=" + edad +
                '}';
    }
}