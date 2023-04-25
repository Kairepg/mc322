package lab03.src;
import java.util.ArrayList;

public class Cliente {
    private String nome;
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos;

    // Construtor
    public Cliente(String nome, String endereco, ArrayList<Veiculo> listaVeiculos) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = listaVeiculos;
    }

        // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void addVeiculo(Veiculo veiculo) {
        listaVeiculos.add(veiculo);
    }

    public boolean removeVeiculo(String placa) {
        for(int i = 0; i < listaVeiculos.size(); i++) {
            if((listaVeiculos.get(i)).getPlaca() == placa) {
                listaVeiculos.remove(i);
                return true;
            }
        }
        return false;

    }

    public String toString() {
        String text = "";
        text += String.format("Nome: %s\n", nome);
        text += String.format("Endereço: %s\n", endereco);
        text += "Lista de Placas dos Veículos: ";
        for(int i = 0; i < listaVeiculos.size(); i++) {
            text += String.format("%s;\n", (listaVeiculos.get(i)).getPlaca());
        }
        text += "\n";

        return text;
    }
}