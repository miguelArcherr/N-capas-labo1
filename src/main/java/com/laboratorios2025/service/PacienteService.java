package com.laboratorios2025.service;

import com.laboratorios2025.model.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacienteService {
    private final List<Paciente> pacientes = new ArrayList<>();

    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public Paciente obtenerPorDui(String dui) {
        return pacientes.stream()
                .filter(p -> p.getDui().equalsIgnoreCase(dui))
                .findFirst()
                .orElse(null);
    }

    public List<Paciente> obtenerTodos() {
        return pacientes;
    }
}
