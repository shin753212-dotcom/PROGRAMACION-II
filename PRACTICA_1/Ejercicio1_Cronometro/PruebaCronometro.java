package tarea1;
import java.util.Random;
public class PruebaCronometro {
    public static void ordenacionSeleccion(int[] arreglo) {
        int n = arreglo.length;
        for (int i = 0; i < n - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < n; j++) {
                if (arreglo[j] < arreglo[indiceMenor]) {
                    indiceMenor = j;
                }
            }
            int temp = arreglo[indiceMenor];
            arreglo[indiceMenor] = arreglo[i];
            arreglo[i] = temp;
        }
    }
    public static void main(String[] args) {
        int[] numeros = new int[100000];
        Random rnd = new Random();

        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = rnd.nextInt(1000000);
        }
        Cronometro crono = new Cronometro();
        crono.inicia();
        ordenacionSeleccion(numeros);
        crono.detener();

        crono.leer();
        crono.mostrar();
    }
}
