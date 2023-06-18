package Entidades;

public class pilotoActivo {
    private String name;

    private int cantidadDeOcurrencias;

    public pilotoActivo(String name) {
        this.name = name;
        this.cantidadDeOcurrencias = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCantidadDeOcurrencias() {
        return cantidadDeOcurrencias;
    }

    public void setCantidadDeOcurrencias(int cantidadDeOcurrencias) {
        this.cantidadDeOcurrencias = cantidadDeOcurrencias;
    }
}
