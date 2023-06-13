package lab05.src;

import java.time.LocalDate;
import java.util.ArrayList;

public class SeguroPF extends Seguro{
    private Veiculo veiculo;
    private ClientePF cliente;

    //Construtor
    public SeguroPF(LocalDate dataInicio, LocalDate dataFim,
                    Seguradora seguradora, ArrayList<Sinistro> listaSinistros, 
                    ArrayList<Condutor> listaCondutores, 
                    Veiculo veiculo, ClientePF cliente) {
        super(dataInicio, dataFim, seguradora, listaSinistros, listaCondutores);
        this.veiculo = veiculo;
        this.cliente = cliente;
        setValorMensal(calcularValor());
    }

        //Getters e Setters
    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public ClientePF getCliente() {
        return cliente;
    }

    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }

    public double getValorMensal() {
        setValorMensal(calcularValor());
        return super.getValorMensal();
    }

    @Override
    public String toString() {
        String text = "";
        text += String.format("CPF do Cliente: %s\n", cliente.getCPF());
        text += String.format("Placa do Veiculo: %s\n", veiculo.getPlaca());
        text += super.toString();
        return text;
    }


    public double calcularValor() {
        int idade = LocalDate.now().getYear() - cliente.getDataNasc().getYear();
        double val = (CalcSeguro.VALOR_BASE.fator * CalcSeguro.fatorIdade(idade) 
                        * (1 + 1/(cliente.getListaVeiculos().size() + 2))
                        * (2 + getListaSinistros().size()/10)
                        * (5 + qtdeSinistrosCondutor()/10)
        );

        return val;
    }

}
