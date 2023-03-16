public class Cliente {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

    // GuiGuilhy
    public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
    }

        // marco aurélio
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
        return "guilherme";
    }

    public boolean validarCPF(String cpf) {
        int soma = 0;
        String editedCPF;
        int resto;

        editedCPF = cpf.replaceAll(".", "");
        editedCPF = editedCPF.replaceAll("-", "");

        if(editedCPF.length() != 11) {
            return false;
        }

        char firstChar = editedCPF.charAt(0);
        if(editedCPF.replaceAll(String.valueOf(firstChar), "") == "") {
            return false;
        }
        
        for(int i = 0; i < 9; i++) {
            soma += editedCPF.charAt(i) * (10 - i);
        }
        resto = (soma * 10)%11;
        
        if(resto == 10) {
            resto = 0;
        }
        if(resto != editedCPF.charAt(9)) {
            return false;
        }

        soma = 0;
        for(int i = 0; i < 10; i++) {
            soma += editedCPF.charAt(i) * (11 - i);
        }
        resto = (soma * 10)%11;
        
        if(resto == 10) {
            resto = 0;
        }
        if(resto != editedCPF.charAt(10)) {
            return false;
        }

        return true;
    }
}