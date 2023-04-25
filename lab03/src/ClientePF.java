package lab03.src;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClientePF extends Cliente {
    private final String CPF;
    private String genero;
    private Date dataLicenca;
    private String educacao;
    private Date dataNascimento;
    private String classeEconomica;


    public ClientePF(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, 
                        String CPF, String genero, Date dataLicenca, String educacao,
                        Date dataNascimento, String classeEconomica) {
        
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

    public Date getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca(Date dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
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
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String text = "";
        text += String.format("CPF: %s\n", CPF);
        text += String.format("Data de Nascimento: %s\n", formato.format(dataNascimento));
        text += String.format("Genero: %s\n", genero);
        text += String.format("Data da Licença: %s\n", formato.format(dataLicenca));
        text += String.format("Educação: %s\n", educacao);
        text += String.format("Classe Econômica: %s\n", classeEconomica);
        text += super.toString();

        return text;
    }


    public boolean validarCPF() {
        /* valida o cpf do cliente utilizando o 
        algoritmo para determinar os digitos verificadores */
        int soma = 0, resto;
        String editedCPF;
        char firstChar;

        editedCPF = CPF.replaceAll("\\.+", "");
        editedCPF = editedCPF.replaceAll("-", "");

        if(editedCPF.length() != 11) {
            return false;
        }

        firstChar = editedCPF.charAt(0);
        if(editedCPF.replaceAll(String.valueOf(firstChar), "") == "") {
            return false;
        }
        
        for(int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(editedCPF.charAt(i)) * (i + 1);
        }
        resto = soma % 11;
        if(resto == 10) {
            resto = 0;
        }
        if(resto != Character.getNumericValue(editedCPF.charAt(9))) {
            return false;
        }

        soma = 0;
        for(int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(editedCPF.charAt(i)) * i;
        }
        resto = soma % 11;
        if(resto == 10) {
            resto = 0;
        }
        if(resto != Character.getNumericValue(editedCPF.charAt(10))) {
            return false;
        }

        return true;
    }
}
