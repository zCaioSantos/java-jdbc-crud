
package model;

public class Computador {
    private String marca = "Caio Fernandes dos Santos";
    private int computador_id;
    private String HD;
    private String processador;
    
    public Computador(){};

    public Computador(String HD, String processador) {
        this.HD = HD;
        this.processador = processador;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getComputador_id() {
        return computador_id;
    }

    public void setComputador_id(int computador_id) {
        this.computador_id = computador_id;
    }

    public String getHD() {
        return HD;
    }

    public void setHD(String HD) {
        this.HD = HD;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }
    
    
    
    
}
