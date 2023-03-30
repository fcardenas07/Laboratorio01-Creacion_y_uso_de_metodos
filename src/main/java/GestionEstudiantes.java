import java.util.Arrays;
import java.util.Scanner;

public class GestionEstudiantes {
    public static void main(String[] args) {
        double[][] notasEstudiantes = new double[50][5];
        menu(notasEstudiantes);
    }

    private static void menu(double[][] notasEstudiantes) {
        mostrarOpciones();
        int opcionIngresada = ingresarOpcion();
        procesarOpcion(opcionIngresada, notasEstudiantes);
    }

    private static void procesarOpcion(int opcionIngresada, double[][] notasEstudiantes) {
        switch (opcionIngresada) {
            case 1 -> agregarEstudiante(notasEstudiantes);
            case 2 -> mostrarEstudiantesQueAprueban(notasEstudiantes);
            case 3 -> mostrarEstudiantesQueReprueban(notasEstudiantes);
        }
    }

    private static void mostrarEstudiantesQueReprueban(double[][] notasEstudiantes) {
        System.out.println("La cantidad de estudiantes que reprueban es: " + contarEstudiantesQueReprueban(notasEstudiantes));
    }

    private static int contarEstudiantesQueReprueban(double[][] notasEstudiantes) {
        int estudiantesQueReprueban = 0;
        for (double[] estudiante : notasEstudiantes) {
            double promedio = calcularPromedio(estudiante);
            if (promedio < 3.6) {
                estudiantesQueReprueban++;
            }
        }
        return estudiantesQueReprueban;
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
        double notaAleatoria = Math.random() * 6.9 + 1;
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

    private static void mostrarEstudiantesQueAprueban(double[][] notasEstudiantes) {
        System.out.println("La cantidad de estudiantes que aprueban es: " + contarEstudiantesQueAprueban(notasEstudiantes));
    }

    private static int contarEstudiantesQueAprueban(double[][] notasEstudiantes) {
        int estudiantesQueAprueban = 0;
        for (double[] estudiante : notasEstudiantes) {
            double promedio = calcularPromedio(estudiante);
            if (promedio >= 4.0) {
                estudiantesQueAprueban++;
            }
        }
        return estudiantesQueAprueban;
    }

    private static double calcularPromedio(double[] notasEstudiante) {
        double[] ponderaciones = new double[]{0.25, 0.25, 0.25, 0.15, 0.10};
        double sumaNotas = 0.0;

        for (int i = 0; i < notasEstudiante.length; i++) {

        }

        for (double nota : notasEstudiante) {
            sumaNotas += nota;
        }
        return sumaNotas / notasEstudiante.length;
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
        if (numero < 1 || numero > 6) {
            System.out.println("Ingrese una opcion valida");
            return ingresarOpcion();
        }
        return numero;
    }

    private static void mostrarOpciones() {
        System.out.println("""
                              1. Agregar estudiante
                              2. Mostrar cantidad de estudiantes que aprueban
                              3. Mostrar cantidad de estudiantes que reprueban
                              4. Mostrar la cantidad de estudiantes que van a examen
                              5. Mostrar el estado de todos los estudiantes
                              6. Salir""");
    }
}
