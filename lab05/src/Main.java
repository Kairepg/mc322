package lab05.src;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        MenuOpcoes op;
        ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
        
        // Parte dos testes obrigatorios
        TestesObrigatorios.testes(listaSeguradoras);

        // Parte de comunicar com o terminal
        do {
            Menu.exibirMenuExterno();
            op = Menu.lerOpcaoMenuExterno();
            Menu.executarOpcaoMenuExterno(listaSeguradoras, op);
        } while(op != MenuOpcoes.SAIR);
        System.out.println("Saiu do Sistema");
    }
}