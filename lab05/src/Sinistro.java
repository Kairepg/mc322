package lab05.src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Sinistro {
    private final int ID;
    private LocalDate data;
    private String endereco;
    private Condutor condutor;
    private Seguro seguro;
    static int count = 0;

    // Construtor
    public Sinistro(LocalDate data, String endereco,
                    Condutor condutor, Seguro seguro) {
        this.ID = setId();
        this.data = data;
        this.endereco = endereco;
        this.condutor = condutor;
        this.seguro = seguro;
    }

        // Getters e setters
    public int getId() {
        return ID;
    }

    private int setId() {
        count++;
        return count;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Condutor geCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguradora(Seguro seguro) {
        this.seguro = seguro;
    }


    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = "";
        text += String.format("ID do Sinistro: %d\n", ID);
        text += String.format("Data: %s\n", data.format(formato));
        text += String.format("Endereço: %s\n", endereco);
        text += String.format("Nome do Condutor: %s\n", condutor.getNome());
        text += String.format("ID do Seguro: %d\n", seguro.getId());

        return text;
    }
}