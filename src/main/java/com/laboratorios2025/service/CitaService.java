package com.laboratorios2025.service;

import com.laboratorios2025.model.Cita;
import com.laboratorios2025.model.Doctor;
import com.laboratorios2025.model.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class CitaService {
    private final List<Cita> citas = new ArrayList<>();
    private final DoctorService doctorService;
    private final PacienteService pacienteService;

    public CitaService(DoctorService doctorService, PacienteService pacienteService) {
        this.doctorService = doctorService;
        this.pacienteService = pacienteService;
    }

    public void agendarCita(String codigoDoctor, String duiPaciente, String especialidad,
                            LocalDateTime fechaHora, boolean asistio) {
        Doctor doctor = doctorService.obtenerDoctorPorCodigo(codigoDoctor);
        Paciente paciente = pacienteService.obtenerPorDui(duiPaciente);

        if (doctor == null) {
            System.out.println("Doctor no encontrado.");
            return;
        }
        if (paciente == null) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        Cita cita = new Cita(doctor, paciente, especialidad, fechaHora, asistio);
        citas.add(cita);
        System.out.println("Cita agendada con ID: " + cita.getId());
    }

    public void listarCitas() {
        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
            return;
        }
        for (Cita c : citas) {
            System.out.println(c);
        }
    }

    public void mostrarCitasPorDoctor(String codigoDoctor) {
        boolean found = false;
        for (Cita c : citas) {
            if (c.getDoctor().getCodigo().equalsIgnoreCase(codigoDoctor)) {
                System.out.println(c);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No hay citas para este doctor.");
        }
    }

    public void cancelarCita(UUID id) {
        boolean removed = citas.removeIf(c -> c.getId().equals(id));
        if (removed) {
            System.out.println("Cita cancelada correctamente.");
        } else {
            System.out.println("Cita no encontrada.");
        }
    }

    public LocalTime obtenerHoraDisponible(LocalDate fecha) {
        List<LocalTime> posibles = Arrays.asList(
                LocalTime.of(8, 0),
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0)
        );
        for (LocalTime h : posibles) {
            boolean ocupado = citas.stream()
                    .anyMatch(c -> c.getFechaHora().toLocalDate().equals(fecha) &&
                            c.getFechaHora().toLocalTime().equals(h));
            if (!ocupado) return h;
        }
        return LocalTime.of(16, 0); // última hora por defecto
    }
}
