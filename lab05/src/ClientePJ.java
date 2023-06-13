package lab05.src;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private LocalDate dataFundacao;
    private ArrayList<Frota> listaFrota;
    private int qtdeFuncionarios;

    //Construtor
    public ClientePJ(String nome, String telefone, String endereco, 
                        String email, String CNPJ, LocalDate dataFuncacao, 
                        ArrayList<Frota> listaFrota, int qtdeFuncionarios) {
        
        super(nome, telefone, endereco, email);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFuncacao;
        this.listaFrota = listaFrota;
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

    public ArrayList<Frota> getListaFrota() {
        return listaFrota;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = "";
        text += super.toString();
        text += String.format("CNPJ: %s\n", CNPJ);
        text += String.format("Data de Fundação: %s\n", dataFundacao.format(formato));
        text += String.format("Quantidade de Funcionários: %d\n", qtdeFuncionarios);
        text += "Lista de Códigos das Frotas: ";
        for(int i = 0; i < listaFrota.size(); i++) {
            text += String.format("%s;\n", (listaFrota.get(i)).getCode());
        }
        text += "\n";

        return text;
    }


    public boolean cadastrarFrota(Frota frota) {
        if(listaFrota.contains(frota)) {
            return false;
        }
        listaFrota.add(frota);
        return true;
    }

    public boolean atualizarFrota(int code, Veiculo veiculo) {
        for(int i = 0; i < listaFrota.size(); i++) {
            if(listaFrota.get(i).getCode() == (code)) {
                if(listaFrota.get(i).getListaVeiculo().contains(veiculo)) {
                    return false;
                }
                listaFrota.get(i).getListaVeiculo().add(veiculo);
                return true;
            }
        }
        return false;
    }


    public boolean atualizarFrota(int code, String placa) {
        for(int i = 0; i < listaFrota.size(); i++) {
            if(listaFrota.get(i).getCode() == (code)) {
                for(int j = 0; j < listaFrota.get(i).getListaVeiculo().size(); j++) {
                    if(listaFrota.get(i).getListaVeiculo().get(j).getPlaca().equals(placa)) {
                        listaFrota.get(i).getListaVeiculo().remove(j);
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }


    public boolean atualizarFrota(int code) {
        for(int i = 0; i < listaFrota.size(); i++) {
            if(listaFrota.get(i).getCode() == (code)) {
                listaFrota.remove(i);
                return true;
            }
        }
        return false;
    }


    public void getVeiculos() {
        System.out.println("Carros do Cliente: ");
        for(int i = 0; i < listaFrota.size(); i++) {
            for(int j = 0; j < listaFrota.get(i).getListaVeiculo().size(); j++) {
                System.out.println(listaFrota.get(i).getListaVeiculo().get(i).getPlaca());
            }
        }
        System.out.println();
    }
}
