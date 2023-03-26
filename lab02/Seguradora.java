public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;

    // Construtor
    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;    
    }

        // Getters e setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        text += String.format("telefone: %s\n", telefone);
        text += String.format("email: %s\n", email);
        text += String.format("endereco: %s\n", endereco);

        return text;
    }

}