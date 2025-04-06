package com.laboratorios2025.model;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

public class Doctor {
    private final String nombre;
    private final String apellido;
    private final String dui;
    private final LocalDate cumple;
    private final LocalDate reclutado;
    private final String especialidad;
    private final String codigo;

    public Doctor(String nombre, String apellido, String dui, LocalDate cumple, LocalDate reclutado, String especialidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dui = dui;
        this.cumple = cumple;
        this.reclutado = reclutado;
        this.especialidad = especialidad;
        this.codigo = generarCodigo();
    }

    private String generarCodigo() {
       int x = new Random().nextInt(10);
       char A = (char) ('A' + new Random().nextInt(26));;
        return "ZNH-" + x + A + x + "-" + "MD" +"-" + A+x;

    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}