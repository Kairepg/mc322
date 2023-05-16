package lab04.src;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private LocalDate dataFundacao;
    private int qtdeFuncionarios;

    public ClientePJ(String nome, String endereco, 
                        ArrayList<Veiculo> listaVeiculos, String CNPJ, 
                        LocalDate dataFuncacao, int qtdeFuncionarios) {
        
        super(nome, endereco, listaVeiculos);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFuncacao;
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

        //Getter e setters
    public String getCNPJ() {
        return CNPJ;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public int getQtdeFuncionarios() {
        return qtdeFuncionarios;
    }

    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = "";
        text += String.format("CNPJ: %s\n", CNPJ);
        text += String.format("Data de Fundação: %s\n", dataFundacao.format(formato));
        text += String.format("Quantidade de Funcionários: %d\n", qtdeFuncionarios);
        text += super.toString();

        return text;
    }


    public double calculaScore() {
        return CalcSeguro.VALOR_BASE.fator * (1 + (qtdeFuncionarios/100)) * getListaVeiculos().size();
    }
}
