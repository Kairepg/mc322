package lab04.src;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Sinistro {
    private final int ID;
    private LocalDate data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;

    // Construtor
    public Sinistro(LocalDate data, String endereco, Seguradora seguradora,
                    Veiculo veiculo, Cliente cliente) {
        this.ID = setId();
        this.data = data;
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

        // Getters e setters
    public int getId() {
        return ID;
    }

    //mudar
    private int setId() {
        /* gera um id aleatorio para o processo */
        double num = Math.random();
        return (int) (num * 100000);
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = "";
        text += String.format("ID: %d\n", ID);
        text += String.format("Data: %s\n", data.format(formato));
        text += String.format("Endereço: %s\n", endereco);
        text += String.format("Nome da Seguradora: %s\n", seguradora.getNome());
        text += String.format("Placa do Veiculo: %s\n", veiculo.getPlaca());
        text += String.format("Nome do Cliente: %s\n", cliente.getNome());
        if(cliente instanceof ClientePF) {
            text += String.format("CPF do Cliente: %s\n", ((ClientePF) cliente).getCPF());
        }
        if(cliente instanceof ClientePJ) {
            text += String.format("CNPJ do Cliente: %s\n", ((ClientePJ) cliente).getCNPJ());
        }

        return text;
    }
}