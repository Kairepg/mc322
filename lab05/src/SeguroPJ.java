package lab05.src;

import java.time.LocalDate;
import java.util.ArrayList;

public class SeguroPJ extends Seguro{
    private Frota frota;
    private ClientePJ cliente;

    //Construtor
    public SeguroPJ(LocalDate dataInicio, LocalDate dataFim,
                    Seguradora seguradora, ArrayList<Sinistro> listaSinistros, 
                    ArrayList<Condutor> listaCondutores, 
                    Frota frota, ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora, listaSinistros, listaCondutores);
        this.frota = frota;
        this.cliente = cliente;
        setValorMensal(calcularValor());
    }

        //Getters e Setters
    public Frota getFrota() {
        return frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    public ClientePJ getCliente() {
        return cliente;
    }

    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }

    public double getValorMensal() {
        setValorMensal(calcularValor());
        return super.getValorMensal();
    }

    @Override
    public String toString() {
        String text = "";
        text += String.format("CNPJ do Cliente: %s\n", cliente.getCNPJ());
        text += String.format("Código da Frota: %s\n", frota.getCode());
        text += super.toString();
        return text;
    }


    public double calcularValor() {
        int anosDaFundc = LocalDate.now().getYear() - cliente.getDataFundacao().getYear();
        int numCar = 0;
        for(int i = 0; i < cliente.getListaFrota().size(); i++) {
            numCar += cliente.getListaFrota().get(i).getListaVeiculo().size();
        }
        double val = (CalcSeguro.VALOR_BASE.fator * (10 + cliente.getQtdeFuncionarios()/10) 
                        * (1 + 1/(numCar + 2)) * (1 + 1/(anosDaFundc + 2))
                        * (2 + getListaSinistros().size()/10)
                        * (5 + qtdeSinistrosCondutor()/10)           
        );
        return val;
    }
}
