package com.laboratorios2025.service;

import com.laboratorios2025.model.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorService {
    private final List<Doctor> doctores = new ArrayList<>();

    public void agregarDoctor(Doctor doctor) {
        doctores.add(doctor);
    }

    public Doctor obtenerDoctorPorCodigo(String codigo) {
        return doctores.stream()
                .filter(d -> d.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }

    public List<Doctor> obtenerTodos() {
        return doctores;
    }
}
