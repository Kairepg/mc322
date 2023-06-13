package lab05.src;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClientePF extends Cliente {
    private final String CPF;
    private String genero;
    private String educacao;
    private LocalDate dataNasc;
    private ArrayList<Veiculo> listaVeiculos;


    public ClientePF(String nome, String telefone, String endereco, 
                        String email, String CPF, String genero, 
                        String educacao, LocalDate dataNasc,
                        ArrayList<Veiculo> listaVeiculos) {
        
        super(nome, telefone, endereco, email);
        this.CPF = CPF;
        this.genero = genero;
        this.educacao = educacao;
        this.dataNasc = dataNasc;
        this.listaVeiculos = listaVeiculos;
    }

        //Getter e setters
    public String getCPF() {
        return CPF;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = "";
        text += super.toString();
        text += String.format("CPF: %s\n", CPF);
        text += String.format("Data de Nascimento: %s\n", dataNasc.format(formato));
        text += String.format("Genero: %s\n", genero);
        text += String.format("Educação: %s\n", educacao);
        text += "Lista de Placas dos Veículos: ";
        for(int i = 0; i < listaVeiculos.size(); i++) {
            text += String.format("%s;\n", (listaVeiculos.get(i)).getPlaca());
        }
        text += "\n";

        return text;
    }


    public boolean cadastrarVeiculo(Veiculo veiculo) {
        if(listaVeiculos.contains(veiculo)) {
            return false;
        }
        listaVeiculos.add(veiculo);
        return true;
    }

    
    public boolean removerVeiculo(String placa) {
        for(int i = 0; i < listaVeiculos.size(); i++) {
            if((listaVeiculos.get(i)).getPlaca() == placa) {
                listaVeiculos.remove(i);
                return true;
            }
        }
        return false;
    }
}
