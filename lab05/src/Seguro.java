package lab05.src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Seguro {
    private final int ID;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Seguradora seguradora;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Condutor> listaCondutores;
    private double valorMensal = 0;
    private static int count = 0;

    //Construtor
    public Seguro(LocalDate dataInicio, LocalDate dataFim,
                    Seguradora seguradora, ArrayList<Sinistro> listaSinistros, 
                    ArrayList<Condutor> listaCondutores) {

        this.ID = gerarId();
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        this.listaSinistros = listaSinistros;
        this.listaCondutores = listaCondutores;
    }

        //Getters e Setters
    private int gerarId() {
        count++;
        return count;
    }

    public int getId() {
        return ID;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public ArrayList<Condutor> getListaCondutores() {
        return listaCondutores;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = "";
        text += String.format("ID do Seguro: %d\n", ID);
        text += String.format("Seguradora: \n%s\n", seguradora);
        text += String.format("Data de Início: %s\n", formato.format(dataInicio));
        text += String.format("Data de Fim: %s\n", formato.format(dataFim));
        text += String.format("Valor Mensal: %.2f\n", valorMensal);
        text += "Lista de Condutores: \n";
        for(int i = 0; i < listaCondutores.size(); i++) {
            text += String.format("CPF do Condutor: %s,\n", (listaCondutores.get(i)).getCpf());
        }
        text += "Lista de Sinistros: \n";
        for(int i = 0; i < listaSinistros.size(); i++) {
            text += String.format("ID: %s\n", (listaSinistros.get(i)).getId());
        }
        text += "\n";
        return text;
    }


    public boolean desautorizarCondutor(String cpf) {
        for(int i = 0; i < listaCondutores.size(); i++) {
            if(listaCondutores.get(i).getCpf().equals(cpf)) {
                listaCondutores.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean autorizarCondutor(Condutor condutor) {
        if(listaCondutores.contains(condutor)) {
            return false;
        }
        listaCondutores.add(condutor);
        return true;
    }

    public boolean gerarSinistro(LocalDate data, String endereco, Condutor condutor) {
        if(listaCondutores.contains(condutor)) {
            Sinistro sinistro = new Sinistro(data, endereco, condutor, this);
            condutor.adicionarSinistro(sinistro);
            listaSinistros.add(sinistro);
            return true;
        } else {
            return false;
        }
    }


    public int qtdeSinistrosCondutor() {
        int sum = 0;
        for(int i = 0; i < getListaCondutores().size(); i++) {
            sum += getListaCondutores().get(i).getListaSinistros().size();
        }
        return sum;
    }
    
    public abstract double calcularValor();
}
