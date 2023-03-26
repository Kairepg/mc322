public class Cliente {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

    // Construtor
    public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
    }

        // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String toString() {
        String text = "";
        text += String.format("nome: %s\n", nome);
        text += String.format("cpf: %s\n", cpf);
        text += String.format("data de nascimento: %s\n", dataNascimento);
        text += String.format("idade: %d\n", idade);
        text += String.format("endereço: %s\n", endereco);

        return text;
    }

    public boolean validarCPF() {
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
}