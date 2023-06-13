package lab05.src;

import java.util.ArrayList;

public class Frota {
    private int code;
    private ArrayList<Veiculo> listaVeiculos;
    private static int count = 0;

    //Construtor
    public Frota(ArrayList<Veiculo> listaVeiculos) {
        this.code = setCode();
        this.listaVeiculos = listaVeiculos;
    }

        //Getters e Setters
    public int getCode() {
        return code;
    }

    private int setCode() {
        count++;
        return count;
    }


    public ArrayList<Veiculo> getListaVeiculo() {
        return listaVeiculos;
    }

    @Override
    public String toString() {
        String text = "";
        text += String.format("Código: %s\n", code);
        text += "Lista de Carros na Frota: \n";
        for(int i = 0; i < listaVeiculos.size(); i++) {
            text += String.format("%s;\n", (listaVeiculos.get(i)).getPlaca());
        }
        text += "\n";
        return text;
    }


    public boolean addVeiculo(Veiculo veiculo) {
        if(listaVeiculos.contains(veiculo)) {
            return false;
        }
        listaVeiculos.add(veiculo);
        return true;
    }

    
    public boolean removeVeiculo(String placa) {
        for(int i = 0; i < listaVeiculos.size(); i++) {
            if(listaVeiculos.get(i).getPlaca() == placa) {
                listaVeiculos.remove(i);
                return true;
            }
        }
        return false;
    }
}
