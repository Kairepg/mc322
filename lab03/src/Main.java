package lab03.src;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        System.out.println("---------INÍCIO DOS TESTES OBRIGATÓRIOS---------\n");

        // Criação de clientes pessoa física
        ArrayList<Veiculo> carrosAlexandre = new ArrayList<Veiculo>();
        Veiculo carroAlexandre1 = new Veiculo("ABC-1234", "Ferrari", "F8", 2023);
        carrosAlexandre.add(carroAlexandre1);
        Date licencaAlexandre = new Date("03/17/2022"); // mm/dd/aaaa
        Date nascimentoAlexandre = new Date("08/15/2003");

        ClientePF aleSeixas = new ClientePF("Ale Seixas", "Rua Fernando Matheus 8", carrosAlexandre, "464.177.428-57", "Masculino", 
                                                licencaAlexandre, "Ensino Superior Incompleto", nascimentoAlexandre, "Classe Alta");
               
    
        ArrayList<Veiculo> carrosBruna = new ArrayList<Veiculo>();
        Veiculo carroBruna1 = new Veiculo("ABC-6789", "Gol", "Mini", 2012);
        carrosBruna.add(carroBruna1);
        Date licencaBruna = new Date("03/07/2021");
        Date nascimentoBruna = new Date("06/06/2002");

        ClientePF brunaPereira = new ClientePF("Bruna Pereira", "Rua Alexandre Eduardo 81", carrosBruna, "937.340.400-86", "Feminino", 
                                                licencaBruna, "Ensino Superior Completo", nascimentoBruna, "Classe Média");
               
        
        // Criação de um cliente pessoa jurídica
        ArrayList<Veiculo> carrosBoralli = new ArrayList<Veiculo>();
        Veiculo carroBoralli1 = new Veiculo("MNO-1234", "Chevrolet", "Onix", 2005);
        Veiculo carroBoralli2 = new Veiculo("XYZ-1234", "Porsche", "Panameira", 2027);
        carrosBoralli.add(carroBoralli1);
        carrosBoralli.add(carroBoralli2);
        Date fundacaoBoralli = new Date("07/10/1908");
                                        
        ClientePJ boralliInc = new ClientePJ("Boralli Inc", "Rua Getulio Vargas 203", carrosBoralli, "01.881.237/0001-91", fundacaoBoralli);


        // Validação dos cps, manualmente
        System.out.println("-> Dados dos Futuros Clientes: ");
        System.out.println(aleSeixas);
        System.out.println(boralliInc);

        System.out.println("-> Dados de um Carro: ");
        System.out.println(carroBoralli1);

        System.out.println("-> Validação de CPF e CNPJ: ");
        if(aleSeixas.validarCPF()) {
            System.out.println("O CPF eh valido");
        } else {
            System.out.println("O CPF eh invalido");
        }
        if(boralliInc.validarCNPJ()) {
            System.out.println("O CNPJ eh valido");
        } else {
            System.out.println("O CNPJ eh invalido");
        }
        System.out.println();


        // Testes com as seguradoras
        Seguradora seguradoraClaudio = new Seguradora("Claudio Seguradoras", "21 98888-1111", "seguradoraclaudio@gmail.com", "Rua Joaquim Augusto 47");
        System.out.println("-> Dados Iniciais da Seguradora:");
        System.out.println((seguradoraClaudio));

        System.out.println("-> Adicionando Clientes: ");
        seguradoraClaudio.cadastrarCliente(aleSeixas);
        seguradoraClaudio.cadastrarCliente(boralliInc);
        seguradoraClaudio.cadastrarCliente(brunaPereira);
        seguradoraClaudio.listarClientes("pessoa fisica");
        seguradoraClaudio.listarClientes("pessoa juridica");

        System.out.println("-> Gerando e Visualizando Sinistros: ");
        seguradoraClaudio.gerarSinistro("21/03/2023", "Rua Albert Einstein 76", carroAlexandre1, aleSeixas);
        seguradoraClaudio.visualizarSinistro(aleSeixas.getCPF());

        seguradoraClaudio.gerarSinistro("20/07/2001", "Avenida Caio de Almeida 201", carroBoralli2, boralliInc);
        seguradoraClaudio.gerarSinistro("08/05/2004", "Rua do Limoeiro 27", carroBoralli1, boralliInc);
        seguradoraClaudio.visualizarSinistro(boralliInc.getCNPJ());

        seguradoraClaudio.gerarSinistro("22/01/2020", "Rua Eduardo Silva 301", carroBruna1, brunaPereira);
        seguradoraClaudio.visualizarSinistro(brunaPereira.getCPF());
        
        System.out.println("-> Clientes Físicos Antes da Remoção do Cliente: ");
        seguradoraClaudio.listarClientes("pessoa fisica");
        System.out.println("-> Sinistros Antes da Remoção do Cliente: ");
        seguradoraClaudio.listarSinistros();

        seguradoraClaudio.removerCliente(brunaPereira.getCPF());
        System.out.println("-> Clientes Físicos Depois da Remoção do Cliente: ");
        seguradoraClaudio.listarClientes("pessoa fisica");
        System.out.println("-> Sinistros Depois da Remoção de um Cliente");
        seguradoraClaudio.listarSinistros();

        System.out.println(String.format("-> Dados Finais da Seguradora: %s", seguradoraClaudio));

        System.out.println("---------FIM DOS TESTES OBRIGATÓRIOS---------\n");

        // Parte de comunicar com o terminal
        String message = "";
        Scanner entrada = new Scanner(System.in);
        
        String texto = "";
        texto += "Digite um dos códigos a seguir, para os comandos que exigem um parâmetro, dê um espaço e o digite: \n";
        texto += "identificador = CPF ou CNPJ\n";
        texto += "(0) Parar o programa\n";
        texto += "(1) mostrar clientes PF\n";
        texto += "(2) mostrar clientes PJ\n";
        texto += "(3) mostrar todos os sinistros\n";
        texto += "(4 {identificador}) mostrar sinistros do cliente {identificador}\n";
        texto += "(5 {identificador}) apagar cliente {identificador}\n";
        texto += "(6) mostrar dados da seguradora\n";
        System.out.println(texto);

        while(!message.equals("0")) {
            System.out.println("Digite o comando: ");
            message = entrada.nextLine();
            if(message.charAt(0) == '1') {
                seguradoraClaudio.listarClientes("pessoa fisica");
            } else if(message.charAt(0) == '2') {
                seguradoraClaudio.listarClientes("pessoa juridica");
            } else if(message.charAt(0) == '3') {
                seguradoraClaudio.listarSinistros();
            } else if(message.charAt(0) == '4') {
                seguradoraClaudio.visualizarSinistro(message.split(" ")[1]);
            } else if(message.charAt(0) == '5') {
                if(seguradoraClaudio.removerCliente(message.split(" ")[1])) {
                    System.out.println("Cliente removido com sucesso\n");
                } else {
                    System.out.println("O cliente não está nesse seguradora\n");
                }
            } else if(message.charAt(0) == '6') {
                System.out.println(seguradoraClaudio);
            }

        }
        entrada.close();
    }
}