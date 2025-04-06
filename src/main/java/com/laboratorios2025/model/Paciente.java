package com.laboratorios2025.model;

import java.time.LocalDate;

public class Paciente {
    private String nombre;
    private String apellido;
    private String dui;
    private LocalDate cumple;

    public Paciente(String nombre, String apellido, String dui, LocalDate cumple) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dui = dui;
        this.cumple = cumple;
    }

    public String getDui() {
        return dui;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}