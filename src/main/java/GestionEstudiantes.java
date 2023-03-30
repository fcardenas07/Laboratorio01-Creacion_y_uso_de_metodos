import java.util.Arrays;
import java.util.Scanner;

public class GestionEstudiantes {
    public static void main(String[] args) {
        double[][] notasEstudiantes = new double[5][5];
        menu(notasEstudiantes);
    }

    private static void menu(double[][] notasEstudiantes) {
        mostrarOpciones();
        procesarOpcion(ingresarOpcion(), notasEstudiantes);
    }

    private static void procesarOpcion(int opcionIngresada, double[][] notasEstudiantes) {
        switch (opcionIngresada) {
            case 1 -> agregarEstudiante(notasEstudiantes);
            case 2 -> mostrarEstudiantesAprobados(notasEstudiantes);
            case 3 -> mostrarEstudiantesReprobados(notasEstudiantes);
            case 4 -> mostrarEstudiantesEnExamen(notasEstudiantes);
            case 5 -> mostrarEstadoEstudiantes(notasEstudiantes);
            case 6 -> mostrarNotasEstudiantes(notasEstudiantes);
            case 7 -> salir();
        }
        menu(notasEstudiantes);
    }

    private static void mostrarNotasEstudiantes(double[][] notasEstudiantes) {
        for (double[] estudiante : notasEstudiantes) {
            System.out.println(Arrays.toString(estudiante));
        }
    }

    private static void salir() {
        System.exit(0);
    }

    private static void mostrarEstadoEstudiantes(double[][] notasEstudiantes) {
        for (int i = 0; i < notasEstudiantes.length; i++) {
            System.out.println("Estudiante " + (i + 1) + " -> " + estadoEstudiante(notasEstudiantes[i]));
        }
    }

    private static String estadoEstudiante(double[] notasEstudiante) {
        return "Notas: " + Arrays.toString(notasEstudiante) + " Promedio: " + calcularPromedio(notasEstudiante, ponderacionesNotas());
    }

    private static void mostrarEstudiantesEnExamen(double[][] notasEstudiantes) {
        System.out.println("La cantidad de estudiantes que van examen es: " + contarEstudiantesEnExamen(notasEstudiantes));
    }

    private static int contarEstudiantesEnExamen(double[][] notasEstudiantes) {
        int estudiantesEnExamen = 0;
        for (int i = 0; i < cantidadEstudiantes(notasEstudiantes); i++) {
            double promedio = calcularPromedio(notasEstudiantes[i], ponderacionesNotas());
            if (promedio >= 3.6 && promedio < 4.0) {
                estudiantesEnExamen++;
            }
        }
        return estudiantesEnExamen;
    }

    private static void mostrarEstudiantesReprobados(double[][] notasEstudiantes) {
        System.out.println("La cantidad de estudiantes que reprueban es: " + contarEstudiantesReprobados(notasEstudiantes));
    }

    private static int contarEstudiantesReprobados(double[][] notasEstudiantes) {
        int estudiantesReprobados = 0;
        for (int i = 0; i < cantidadEstudiantes(notasEstudiantes); i++) {
            double promedio = calcularPromedio(notasEstudiantes[i], ponderacionesNotas());
            if (promedio < 3.6) {
                estudiantesReprobados++;
            }
        }
        return estudiantesReprobados;
    }

    private static void agregarEstudiante(double[][] notasEstudiantes) {
        if (matrizLlena(notasEstudiantes)) {
            System.out.println("No queda espacio para ingresar estudiantes");
            return;
        }
        int posicionVacia = buscarPosicionVacia(notasEstudiantes);
        generarNotas(notasEstudiantes[posicionVacia]);
    }

    private static void generarNotas(double[] notasEstudiante) {
        for (int i = 0; i < notasEstudiante.length; i++) {
            notasEstudiante[i] = notaAleatoria();
        }
    }

    private static double notaAleatoria() {
        double notaAleatoria = Math.random() * 5.9 + 1;
        String notaConUnDecimal = String.format("%.1f", notaAleatoria).replace(",", ".");
        return Double.parseDouble(notaConUnDecimal);
    }

    private static boolean matrizLlena(double[][] notasEstudiantes) {
        int ultimaPosicion = notasEstudiantes.length - 1;
        return !Arrays.equals(notasEstudiantes[ultimaPosicion], new double[5]);
    }

    private static int buscarPosicionVacia(double[][] notasEstudiantes) {
        for (int i = 0; i < notasEstudiantes.length; i++) {
            if (posicionVacia(notasEstudiantes[i])) {
                return i;
            }
        }
        return 0;
    }

    private static boolean posicionVacia(double[] posicion) {
        return Arrays.equals(posicion, new double[5]);
    }

    private static void mostrarEstudiantesAprobados(double[][] notasEstudiantes) {
        System.out.println("La cantidad de estudiantes que aprueban es: " + contarEstudiantesAprobados(notasEstudiantes));
    }

    private static int contarEstudiantesAprobados(double[][] notasEstudiantes) {
        int estudiantesAprobados = 0;
        for (int i = 0; i < cantidadEstudiantes(notasEstudiantes); i++) {
            double promedio = calcularPromedio(notasEstudiantes[i], ponderacionesNotas());
            if (promedio >= 4.0) {
                estudiantesAprobados++;
            }
        }
        return estudiantesAprobados;
    }

    private static int cantidadEstudiantes(double[][] notasEstudiantes) {
        for (int i = 0; i < notasEstudiantes.length; i++) {
            if (Arrays.equals(notasEstudiantes[i], new double[5])) {
                return i;
            }
        }
        return notasEstudiantes.length;
    }

    private static double calcularPromedio(double[] notasEstudiante, double[] ponderaciones) {
        double promedio = 0.0;

        for (int i = 0; i < notasEstudiante.length; i++) {
            promedio += notasEstudiante[i] * ponderaciones[i];
        }

        return promedio;
    }

    private static int ingresarOpcion() {
        Scanner teclado = new Scanner(System.in);
        try {
            return validarOpcionIngresada(teclado.nextInt());
        } catch (Exception e) {
            System.out.println("Debe ingresar un numero");
            return ingresarOpcion();
        }
    }

    private static int validarOpcionIngresada(int numero) {
        if (numero < 1 || numero > 7) {
            System.out.println("Ingrese una opcion valida");
            return ingresarOpcion();
        }
        return numero;
    }


    private static double[] ponderacionesNotas() {
        return new double[]{0.25, 0.25, 0.25, 0.15, 0.10};
    }

    private static void mostrarOpciones() {
        System.out.println("""
                              -> Menu Gestion Estudiantes
                              1. Agregar estudiante
                              2. Mostrar cantidad de estudiantes que aprueban
                              3. Mostrar cantidad de estudiantes que reprueban
                              4. Mostrar la cantidad de estudiantes que van a examen
                              5. Mostrar el estado de todos los estudiantes
                              6. Mostrar notas estudiantes
                              7. Salir
                              -> Ingrese una opcion:""");
    }
}
