public class Sinistro {
    private int id;
    private String data;
    private String endereco;

    // Construtor
    public Sinistro(String data, String endereco) {
        setId();
        this.data = data;
        this.endereco = endereco;
    }

        // Getters e setters
    public int getId() {
        return id;
    }

    public void setId() {
        double num = Math.random();
        this.id = (int) (num * 1000);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String toString() {
        String text = "";
        text += String.format("id: %d\n", id);
        text += String.format("data: %s\n", data);
        text += String.format("endereço: %s\n", endereco);

        return text;
    }

}