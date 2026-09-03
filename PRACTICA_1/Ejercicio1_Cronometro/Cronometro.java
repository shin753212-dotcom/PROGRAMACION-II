package tarea1;
public class Cronometro {
    private long inicia;
    private long finaliza;
    public Cronometro() {
        this.inicia = System.currentTimeMillis();
        this.finaliza = 0;
    }
    public long getInicia() {
        return this.inicia;
    }
    public long getFinaliza() {
        return this.finaliza;
    }
    public void inicia() {
        this.inicia = System.currentTimeMillis();
    }
    public void detener() {
        this.finaliza = System.currentTimeMillis();
    }
    public long lapsoDeTiempo() {
        return this.finaliza - this.inicia;
    }
    public void leer() {
        System.out.println("Tiempo de inicio: " + this.inicia + " ms");
        System.out.println("Tiempo de finalizacion: " + this.finaliza + " ms");
    }
    public void mostrar() {
        System.out.println("Tiempo transcurrido: " + this.lapsoDeTiempo() + " milisegundos");
    }
}




