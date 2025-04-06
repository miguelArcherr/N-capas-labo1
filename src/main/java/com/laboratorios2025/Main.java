package com.laboratorios2025;

import com.laboratorios2025.model.Doctor;
import com.laboratorios2025.model.Paciente;
import com.laboratorios2025.service.CitaService;
import com.laboratorios2025.service.DoctorService;
import com.laboratorios2025.service.PacienteService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DoctorService doctorService = new DoctorService();
    private static final PacienteService pacienteService = new PacienteService();
    private static final CitaService citaService = new CitaService(doctorService, pacienteService);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- SISTEMA DE CITAS MÉDICAS ZAUN ---");
            System.out.println("1. Agregar nuevo doctor");
            System.out.println("2. Agregar nuevo paciente");
            System.out.println("3. Agendar nueva cita");
            System.out.println("4. Listar todas las citas");
            System.out.println("5. Ver citas por código de doctor");
            System.out.println("6. Cancelar cita");
            System.out.println("7. Botón: Mundo salva vidas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1 -> agregarDoctor();
                case 2 -> agregarPaciente();
                case 3 -> agendarCita();
                case 4 -> citaService.listarCitas();
                case 5 -> mostrarCitasPorDoctor();
                case 6 -> cancelarCita();
                case 7 -> System.out.println("[BOTÓN] ¡Mundo salva vidas!");
                case 0 -> System.exit(0);
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void agregarDoctor() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("DUI: ");
        String dui = scanner.nextLine();
        System.out.print("Cumpleaños (yyyy-mm-dd): ");
        LocalDate cumple = LocalDate.parse(scanner.nextLine());
        System.out.print("Fecha de reclutamiento (yyyy-mm-dd): ");
        LocalDate reclutado = LocalDate.parse(scanner.nextLine());
        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine();

        Doctor d = new Doctor(nombre, apellido, dui, cumple, reclutado, especialidad);
        doctorService.agregarDoctor(d);
        System.out.println("Doctor agregado con código: " + d.getCodigo());
    }

    private static void agregarPaciente() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("DUI (00000000-0 si es niño): ");
        String dui = scanner.nextLine();
        System.out.print("Cumpleaños (yyyy-mm-dd): ");
        LocalDate cumple = LocalDate.parse(scanner.nextLine());

        Paciente p = new Paciente(nombre, apellido, dui, cumple);
        pacienteService.agregarPaciente(p);
        System.out.println("Paciente agregado exitosamente.");
    }

    private static void agendarCita() {
        System.out.print("Código del doctor: ");
        String codigo = scanner.nextLine();
        System.out.print("DUI del paciente: ");
        String duiPaciente = scanner.nextLine();
        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine();
        System.out.print("¿Es una cita para hoy? (s/n): ");
        String hoy = scanner.nextLine();

        LocalDate fecha;
        LocalTime hora;
        if (hoy.equalsIgnoreCase("s")) {
            fecha = LocalDate.now();
            System.out.print("Hora (HH:mm): ");
            hora = LocalTime.parse(scanner.nextLine());
        } else {
            System.out.print("Fecha (yyyy-mm-dd): ");
            fecha = LocalDate.parse(scanner.nextLine());
            hora = citaService.obtenerHoraDisponible(fecha);
        }

        System.out.print("¿El paciente asistió? (s/n): ");
        boolean asistio = scanner.nextLine().equalsIgnoreCase("s");

        citaService.agendarCita(codigo, duiPaciente, especialidad, fecha.atTime(hora), asistio);
    }

    private static void mostrarCitasPorDoctor() {
        System.out.print("Ingrese el código del doctor: ");
        String codigo = scanner.nextLine();
        citaService.mostrarCitasPorDoctor(codigo);
    }

    private static void cancelarCita() {
        System.out.print("Ingrese el ID de la cita a cancelar: ");
        UUID id = UUID.fromString(scanner.nextLine());
        citaService.cancelarCita(id);
    }
}
