package lab03.src;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private Date dataFundacao;

    public ClientePJ(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, 
                        String CNPJ, Date dataFuncacao) {
        
        super(nome, endereco, listaVeiculos);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFuncacao;
    }

        //Getter e setters
    public String getCNPJ() {
        return CNPJ;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String text = "";
        text += String.format("CNPJ: %s\n", CNPJ);
        text += String.format("Data de Fundação: %s\n", formato.format(dataFundacao));
        text += super.toString();

        return text;
    }


    public boolean validarCNPJ() {
        /* valida o CNPJ do cliente utilizando o 
        algoritmo para determinar os digitos verificadores */
        int soma, resto;
        String editedCNPJ;
        char firstChar;
        int[] sequence = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        editedCNPJ = CNPJ.replaceAll("\\.+", "");
        editedCNPJ = editedCNPJ.replaceAll("-", "");
        editedCNPJ = editedCNPJ.replaceAll("/", "");


        if(editedCNPJ.length() != 14) {
            return false;
        }

        firstChar = editedCNPJ.charAt(0);
        if(editedCNPJ.replaceAll(String.valueOf(firstChar), "") == "") {
            return false;
        }
        
        soma = 0;
        for(int i = 0; i < 12; i++) {
            soma += Character.getNumericValue(editedCNPJ.charAt(i)) * sequence[i+1];
        }
        resto = soma % 11;
        if(resto == 0 || resto == 1) {
            if(Character.getNumericValue(editedCNPJ.charAt(12)) != 0) {
                return false;
            }
        }
        if(resto != 11 - Character.getNumericValue(editedCNPJ.charAt(12))) {
            return false;
        }

        soma = 0;
        for(int i = 0; i < 13; i++) {
            soma += Character.getNumericValue(editedCNPJ.charAt(i)) * sequence[i];
        }
        resto = soma % 11;
        if(resto == 0 || resto == 1) {
            if(Character.getNumericValue(editedCNPJ.charAt(13)) != 0) {
                return false;
            }
        }
        if(resto != 11 - Character.getNumericValue(editedCNPJ.charAt(13))) {
            return false;
        }

        return true;
    }
}
