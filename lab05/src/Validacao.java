package lab05.src;

public class Validacao {
    
    public static boolean validarCPF(String cpf) {
        /* valida o cpf do cliente utilizando o 
        algoritmo para determinar os digitos verificadores */
        int soma = 0, resto;
        String editedCPF;
        char firstChar;

        editedCPF = cpf.replaceAll("\\.+", "");
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

    public static boolean validarCNPJ(String cnpj) {
        /* valida o CNPJ do cliente utilizando o 
        algoritmo para determinar os digitos verificadores */
        int soma, resto;
        String editedCNPJ;
        char firstChar;
        int[] sequence = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        editedCNPJ = cnpj.replaceAll("\\.+", "");
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

    public static boolean validarNome(String nome) {
        for(int i = 0; i < nome.length(); i++) {
            if(!Character.isLetter(nome.charAt(i)) && !Character.isWhitespace(nome.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
