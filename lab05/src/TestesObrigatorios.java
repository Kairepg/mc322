package lab05.src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TestesObrigatorios {
    public static void testes(ArrayList<Seguradora> listaSeguradoras) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("---------INÍCIO DOS TESTES OBRIGATÓRIOS---------\n");
        // Criação de clientes pessoa física
        ArrayList<Veiculo> carrosAlexandre = new ArrayList<Veiculo>();
        Veiculo carroAlexandre1 = new Veiculo("ABC-1234", "Ferrari", "F8", 2023);
        carrosAlexandre.add(carroAlexandre1);
        LocalDate nascimentoAlexandre = LocalDate.parse("15/08/2003", formato);

        ClientePF aleSeixas = new ClientePF("Ale Seixas", "21 98765-4321", "Rua Fernando Matheus 8", 
                                        "aleseixas@gmail.com", "464.177.428-57", "Masculino", 
                                            "Ensino Superior Incompleto", nascimentoAlexandre, carrosAlexandre);
        Condutor aleCondutor = new Condutor("464.177.428-57", "Ale Seixas", "21 98765-4321", 
                                            "Rua Fernando Matheus 8", "aleseixas@gmail.com",
                                             nascimentoAlexandre, new ArrayList<Sinistro> ());
    
        ArrayList<Veiculo> carrosBruna = new ArrayList<Veiculo>();
        Veiculo carroBruna1 = new Veiculo("ABC-6789", "Gol", "Mini", 2012);
        carrosBruna.add(carroBruna1);
        LocalDate nascimentoBruna = LocalDate.parse("06/06/2002", formato);

        ClientePF brunaPereira = new ClientePF("Bruna Pereira", "14 99153-3064", "Rua Alexandre Eduardo 81", 
                                                "brunap@gmail.com", "937.340.400-86", "Feminino", 
                                                "Ensino Superior Completo", nascimentoBruna, carrosBruna);
        Condutor brunaCondutor = new Condutor("937.340.400-86", "Bruna Pereira", "14 99153-3064", 
                                                "Rua Alexandre Eduardo 81", "brunap@gmail.com", 
                                                nascimentoBruna, new ArrayList<Sinistro>());
        

        // Criação de um cliente pessoa jurídica
        ArrayList<Veiculo> carrosBoralli = new ArrayList<Veiculo>();
        Veiculo carroBoralli1 = new Veiculo("MNO-1234", "Chevrolet", "Onix", 2005);
        Veiculo carroBoralli2 = new Veiculo("XYZ-1234", "Porsche", "Panameira", 2027);
        carrosBoralli.add(carroBoralli1);
        carrosBoralli.add(carroBoralli2);
        Frota frotaBoralli = new Frota(carrosBoralli);
        ArrayList<Frota> allFrotasBoralli = new ArrayList<Frota>();
        allFrotasBoralli.add(frotaBoralli);
        LocalDate fundacaoBoralli = LocalDate.parse("10/07/1908", formato);
                                        
        ClientePJ boralliInc = new ClientePJ("Boralli Inc", "14 99185-1010", "Rua Getulio Vargas 203",
                                            "boralliCarros@gmail.com", "01.881.237/0001-91", fundacaoBoralli, 
                                            allFrotasBoralli, 72);


        // Validação dos cps, manualmente
        System.out.println("-> Dados dos Futuros Clientes: ");
        System.out.println(aleSeixas);
        System.out.println(boralliInc);

        System.out.println("-> Dados de um Carro: ");
        System.out.println(carroBoralli1);

        System.out.println("-> Validação de CPF e CNPJ: ");
        if(Validacao.validarCPF(aleSeixas.getCPF())) {
            System.out.println("O CPF eh valido");
        } else {
            System.out.println("O CPF eh invalido");
        }
        if(Validacao.validarCNPJ(boralliInc.getCNPJ())) {
            System.out.println("O CNPJ eh valido");
        } else {
            System.out.println("O CNPJ eh invalido");
        }
        System.out.println();


        // Testes com as seguradoras
        ArrayList<Cliente> clientesClaudio = new ArrayList<Cliente>();
        ArrayList<Seguro> segurosClaudio = new ArrayList<Seguro>();
        Seguradora seguradoraClaudio = new Seguradora("57.134.940/0001-90", "Claudio Seguradoras", "21 98888-1111", 
                                                        "seguradoraclaudio@gmail.com", "Rua Joaquim Augusto 47",
                                                        clientesClaudio, segurosClaudio);
        listaSeguradoras.add(seguradoraClaudio);
        System.out.println("-> Dados Iniciais da Seguradora:");
        System.out.println((seguradoraClaudio));

        System.out.println("-> Adicionando Clientes: ");
        seguradoraClaudio.cadastrarCliente(aleSeixas);
        seguradoraClaudio.cadastrarCliente(boralliInc);
        seguradoraClaudio.cadastrarCliente(brunaPereira);

        seguradoraClaudio.listarClientes("PF");
        seguradoraClaudio.listarClientes("PJ");


        // Parte dos Seguros
        SeguroPF seguroAle = new SeguroPF(LocalDate.parse("08/05/2022", formato), LocalDate.parse("08/05/2023", formato), seguradoraClaudio, 
                                            new ArrayList<Sinistro> (), new ArrayList<Condutor> (), carroAlexandre1, aleSeixas);
        seguradoraClaudio.gerarSeguro(seguroAle);
        seguroAle.autorizarCondutor(aleCondutor);

        SeguroPF seguroBruna = new SeguroPF(LocalDate.parse("03/09/2021", formato), LocalDate.parse("03/09/2022", formato), seguradoraClaudio, 
                                            new ArrayList<Sinistro> (), new ArrayList<Condutor> (), carroBruna1, brunaPereira);
        seguradoraClaudio.gerarSeguro(seguroBruna);
        seguroBruna.autorizarCondutor(brunaCondutor);

        SeguroPJ seguroBoralli = new SeguroPJ(LocalDate.parse("01/01/2022", formato), LocalDate.parse("01/01/2023", formato), seguradoraClaudio, 
                                            new ArrayList<Sinistro> (), new ArrayList<Condutor> (), frotaBoralli, boralliInc);
        seguradoraClaudio.gerarSeguro(seguroBoralli);
        seguroBoralli.autorizarCondutor(brunaCondutor);

        // Parte do Sinistros
        System.out.println("-> Gerando e Visualizando Sinistros: ");
        seguroAle.gerarSinistro(LocalDate.parse("21/03/2023", formato), "Rua Albert Einstein 76", aleCondutor);
        System.out.println(seguradoraClaudio.getSinistrosPorCliente(aleSeixas.getCPF()));

        seguroBoralli.gerarSinistro(LocalDate.parse("20/07/2001", formato), "Avenida Caio de Almeida 201", brunaCondutor);
        seguroBoralli.gerarSinistro(LocalDate.parse("08/05/2004", formato), "Rua do Limoeiro 27", brunaCondutor);
        System.out.println(seguradoraClaudio.getSinistrosPorCliente(boralliInc.getCNPJ()));

        seguroBruna.gerarSinistro(LocalDate.parse("22/01/2020", formato), "Rua Eduardo Silva 301", brunaCondutor);
        System.out.println(seguradoraClaudio.getSinistrosPorCliente(brunaPereira.getCPF()));
        
        System.out.println("-> Clientes Físicos Antes da Remoção do Cliente: ");
        seguradoraClaudio.listarClientes("PF");

        seguradoraClaudio.removerCliente(brunaPereira.getCPF());
        System.out.println("-> Clientes Físicos Depois da Remoção do Cliente: ");
        seguradoraClaudio.listarClientes("PF");

        System.out.println(String.format("-> Dados Finais da Seguradora: %s", seguradoraClaudio));

        seguradoraClaudio.calcularReceita();
        System.out.println("---------FIM DOS TESTES OBRIGATÓRIOS---------\n");

    }
}
