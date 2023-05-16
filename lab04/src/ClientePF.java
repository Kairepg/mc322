package lab04.src;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClientePF extends Cliente {
    private final String CPF;
    private String genero;
    private LocalDate dataLicenca;
    private String educacao;
    private LocalDate dataNascimento;
    private String classeEconomica;


    public ClientePF(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, 
                        String CPF, String genero, LocalDate dataLicenca, String educacao,
                        LocalDate dataNascimento, String classeEconomica) {
        
        super(nome, endereco, listaVeiculos);
        this.CPF = CPF;
        this.genero = genero;
        this.dataLicenca = dataLicenca;
        this.educacao = educacao;
        this.dataNascimento = dataNascimento;
        this.classeEconomica = classeEconomica;
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

    public LocalDate getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca(LocalDate dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getClasseEconomica() {
        return classeEconomica;
    }

    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = "";
        text += String.format("CPF: %s\n", CPF);
        text += String.format("Data de Nascimento: %s\n", dataNascimento.format(formato));
        text += String.format("Genero: %s\n", genero);
        text += String.format("Data da Licença: %s\n", dataLicenca.format(formato));
        text += String.format("Educação: %s\n", educacao);
        text += String.format("Classe Econômica: %s\n", classeEconomica);
        text += super.toString();

        return text;
    }


    public double calculaScore() {
        int age = LocalDate.now().getYear() - dataNascimento.getYear();
        return CalcSeguro.VALOR_BASE.fator * CalcSeguro.fatorIdade(age) * getListaVeiculos().size();
    }
}
