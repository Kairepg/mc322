package lab05.src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Condutor {
    final private String CPF;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private LocalDate dataNasc;
    private ArrayList<Sinistro> listaSinistros;

        //Construtor
    public Condutor(String CPF, String nome, String telefone,
                    String endereco, String email, LocalDate dataNasc,
                    ArrayList<Sinistro> listaSinistros) {
        this.CPF = CPF;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNasc = dataNasc;
        this.listaSinistros = listaSinistros;
    }

    //Getter e Setters
    public String getCpf() {
        return CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = "";
        text += String.format("Nome do Condutor: %s\n", nome);
        text += String.format("CPF: %s\n", CPF);
        text += String.format("Telefone: %s\n", telefone);
        text += String.format("Endereco: %s\n", endereco);
        text += String.format("Email: %s\n", email);
        text += String.format("Data de Nascimento: %s\n", dataNasc.format(formato));   
        text += "Lista de Sinistros: \n";
        for(int i = 0; i < listaSinistros.size(); i++) {
            text += String.format("ID: %s\n", (listaSinistros.get(i)).getId());
        }
        text += "\n";
        return text;
    }


    public boolean adicionarSinistro(Sinistro sinistro) {
        if(listaSinistros.contains(sinistro)) {
            return false;
        }
        listaSinistros.add(sinistro);
        return true;
    }
}
