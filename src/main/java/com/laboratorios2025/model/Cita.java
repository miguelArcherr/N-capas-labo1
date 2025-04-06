package com.laboratorios2025.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Cita {
    private UUID id;
    private Doctor doctor;
    private Paciente paciente;
    private String especialidad;
    private LocalDateTime fechaHora;
    private boolean asistio;

    public Cita(Doctor doctor, Paciente paciente, String especialidad, LocalDateTime fechaHora, boolean asistio) {
        this.id = UUID.randomUUID();
        this.doctor = doctor;
        this.paciente = paciente;
        this.especialidad = especialidad;
        this.fechaHora = fechaHora;
        this.asistio = asistio;
    }

    public UUID getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    @Override
    public String toString() {
        return "Cita ID: " + id + "\n" +
                "Doctor: " + doctor.getNombreCompleto() + " (" + doctor.getCodigo() + ")\n" +
                "Paciente: " + paciente.getNombreCompleto() + "\n" +
                "Especialidad: " + especialidad + "\n" +
                "Fecha y hora: " + fechaHora + "\n" +
                "Asistió: " + (asistio ? "Sí" : "No") + "\n";
    }
}