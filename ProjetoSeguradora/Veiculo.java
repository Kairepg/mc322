public class Veiculo {
    private String placa;
    private String marca;
    private String modelo;

    // Construtor
    public Veiculo(String placa, String marca, String modelo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
    }

        // Getters e setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String toString() {
        String text = "";
        text += String.format("placa: %s\n", placa);
        text += String.format("marca: %s\n", marca);
        text += String.format("modelo: %s\n", modelo);

        return text;
    }
}