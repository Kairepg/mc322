package lab04.src;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    private static void exibirMenuExterno() {
        MenuOpcoes menuOpcoes[] = MenuOpcoes.values();
        System.out.println("Menu principal");
        for(MenuOpcoes op: menuOpcoes) {
            System.out.println(op.ordinal() + " - " + op.getDescricao());
        }
    }

    private static void exibirSubmenu(MenuOpcoes op) {
        SubmenuOpcoes[] submenu = op.getSubmenu();
        System.out.println(op.getDescricao());
        for(int i = 0; i < submenu.length; i++) {
            System.out.println(i + " - " + submenu[i].getDescricao());
        }
    }

    private static MenuOpcoes lerOpcaoMenuExterno() {
		Scanner scanner = new Scanner(System.in);
		int opUsuario;
		MenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = scanner.nextInt();
		}while(opUsuario < 0 || opUsuario > MenuOpcoes.values().length - 1);
		opUsuarioConst = MenuOpcoes.values()[opUsuario];
        
		return opUsuarioConst;
	}
	
	private static SubmenuOpcoes lerOpcaoSubmenu(MenuOpcoes op) {
		Scanner scanner = new Scanner(System.in);
		int opUsuario;
		SubmenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = scanner.nextInt();
		}while(opUsuario < 0 || opUsuario > op.getSubmenu().length - 1);
		opUsuarioConst = op.getSubmenu()[opUsuario];
        
		return opUsuarioConst;
	}
	
	private static void executarOpcaoMenuExterno(ArrayList<Seguradora> listaSeguradoras, MenuOpcoes op) {
        Scanner scanner = new Scanner(System.in);
        String nomeSeguradora, identificador;
        Seguradora seguradora;
        int index;
        Cliente cliente;
        begin:
		switch(op) {
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				executarSubmenu(listaSeguradoras, op);
				break;
			case GERAR_SINISTRO:
                System.out.println("Digite o nome da seguradora:");
                nomeSeguradora = scanner.nextLine();
                seguradora = null;
                for(int i = 0; i < listaSeguradoras.size(); i++) {
                    if(listaSeguradoras.get(i).getNome().equals(nomeSeguradora)) {
                        seguradora = listaSeguradoras.get(i);
                        break;
                    }
                }
                if(seguradora == null) {
                    System.out.println("A seguradora digitada não existe");
                    break;
                }
                System.out.println("Digite o CPF/CNPJ do cliente dono do veículo:");
                identificador = scanner.nextLine();
                index = seguradora.encontrarCliente(identificador);
                while(index == -1) {
                    System.out.println("O CPF/CNPJ digitado não está cadastrado, digite um outro, para sair, digite 'voltar':");
                    identificador = scanner.nextLine();
                    if(identificador.equals("voltar")) {
                        break begin;
                    }
                    index = seguradora.encontrarCliente(identificador);
                }
                cliente = seguradora.getListaClientes().get(index);

                System.out.println("Digite a placa do carro envolvido no acidente:");
                String placaVeiculo = scanner.nextLine();
                Veiculo veiculo = null;
                for(int i = 0; i < cliente.getListaVeiculos().size(); i++) {
                    if(cliente.getListaVeiculos().get(i).getPlaca().equals(placaVeiculo)) {
                        veiculo = cliente.getListaVeiculos().get(i);
                        break;
                    }
                }
                if(veiculo == null) {
                    System.out.println("A placa digitada não está associada ao cliente.");
                    break;
                }
                System.out.println("Digite o local do acidente: ");
                String enderecoAcidente = scanner.nextLine();
                System.out.println("Digite a data do acidente, no modelo (dd/mm/aaaa)");
                LocalDate dataAcidente = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                seguradora.gerarSinistro(dataAcidente, enderecoAcidente, veiculo, cliente);
                seguradora.calcularPrecoSeguroCliente(cliente);
				break;

			case TRANSFERIR_SEGURO:
                System.out.println("Digite o nome da seguradora:");
                nomeSeguradora = scanner.nextLine();
                seguradora = null;
                for(int i = 0; i < listaSeguradoras.size(); i++) {
                    if(listaSeguradoras.get(i).getNome().equals(nomeSeguradora)) {
                        seguradora = listaSeguradoras.get(i);
                        break;
                    }
                }
                if(seguradora == null) {
                    System.out.println("A seguradora digitada não existe");
                    break;
                }
                System.out.println("Digite o CPF/CNPJ do cliente atual:");
                identificador = scanner.nextLine();
                index = seguradora.encontrarCliente(identificador);
                while(index == -1) {
                    System.out.println("O CPF/CNPJ digitado não está cadastrado, digite um outro, para sair, digite 'voltar':");
                    identificador = scanner.nextLine();
                    if(identificador.equals("voltar")) {
                        break begin;
                    }
                    index = seguradora.encontrarCliente(identificador);
                }
                Cliente prevCliente = seguradora.getListaClientes().get(index);

                System.out.println("Digite o CPF/CNPJ do novo cliente:");
                identificador = scanner.nextLine();
                index = seguradora.encontrarCliente(identificador);
                while(index == -1) {
                    System.out.println("O CPF/CNPJ digitado não está cadastrado, digite um outro, para sair, digite 'voltar':");
                    identificador = scanner.nextLine();
                    if(identificador.equals("voltar")) {
                        break begin;
                    }
                    index = seguradora.encontrarCliente(identificador);
                }
                Cliente newCliente = seguradora.getListaClientes().get(index);
                for(int i = 0; i < prevCliente.getListaVeiculos().size(); i++) {
                    newCliente.addVeiculo(prevCliente.getListaVeiculos().get(i));
                    prevCliente.removeVeiculo(prevCliente.getListaVeiculos().get(i).getPlaca());
                }
                seguradora.calcularPrecoSeguroCliente();
				break;

			case CALCULAR_RECEITA:
                System.out.println("Digite o nome da seguradora:");
                nomeSeguradora = scanner.nextLine();
                seguradora = null;
                for(int i = 0; i < listaSeguradoras.size(); i++) {
                    if(listaSeguradoras.get(i).getNome().equals(nomeSeguradora)) {
                        seguradora = listaSeguradoras.get(i);
                        break;
                    }
                }
                if(seguradora == null) {
                    System.out.println("A seguradora digitada não existe");
                    break;
                }
                seguradora.calcularReceita();
				break;

			case SAIR:
		}
        
	}
	
	public static void executarOpcaoSubMenu(ArrayList<Seguradora> listaSeguradoras, SubmenuOpcoes opSubmenu) {
        Scanner scanner = new Scanner(System.in);
        String nomeSeguradora, identificador, placaVeiculo, marcaVeiculo, modeloVeiculo;
        Seguradora seguradora;
        int index, anoFabricacao;
        Cliente cliente;
        boolean ans;

        begin:
		switch(opSubmenu) {
		case CADASTRAR_CLIENTE:
            System.out.println("Digite o nome da seguradora: ");
            nomeSeguradora = scanner.nextLine();
            seguradora = null;
            for(int i = 0; i < listaSeguradoras.size(); i++) {
                if(listaSeguradoras.get(i).getNome().equals(nomeSeguradora)) {
                    seguradora = listaSeguradoras.get(i);
                    break;
                }
            }
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            System.out.println("Digite o nome do cliente:");
            String nome = scanner.nextLine();
            while(!Validacao.validarNome(nome)) {
                System.out.println("O nome digitado não é valido, digite outro:");
                nome = scanner.nextLine();
            }
            System.out.println("Digite o endereço do cliente:");
            String endereco = scanner.nextLine();
            System.out.println("Digite se o cliente é pessoa física (PF) ou pessoa jurídica (PJ):");
            String tipoCliente = scanner.nextLine();
            if(tipoCliente.equals("PF")) {
                System.out.println("Digite seu CPF: ");
                String cpf = scanner.nextLine();
                while(!Validacao.validarCPF(cpf)) {
                    System.out.println("O CPF digitado não é valido, digite outro, para sair, digite 'voltar':");
                    cpf = scanner.nextLine();
                    if(cpf.equals("voltar")) {
                        break begin;
                    }
                }
                System.out.println("Digite seu gênero: ");
                String genero = scanner.nextLine();
                System.out.println("Digite sua educação: ");
                String educacao = scanner.nextLine();
                System.out.println("Digite sua classe social: ");
                String classeEconomica = scanner.nextLine();
                System.out.println("Digite sua data de nascimento, no modelo (dd/mm/aaaa)");
                LocalDate dataNascimento = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                System.out.println("Digite a data da sua licença de motorista, no modelo (dd/mm/aaaa)");
                LocalDate dataLicenca = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));


                System.out.println("Digite o número de carros para cadastrar: ");
                int numCarros = Integer.parseInt(scanner.nextLine());
                ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
                for(int i = 0; i < numCarros; i++) {
                    System.out.println("Digite a placa do carro");
                    placaVeiculo = scanner.nextLine();
                    System.out.println("Digite a marca do carro: ");
                    marcaVeiculo = scanner.nextLine();
                    System.out.println("Digite o modelo do carro: ");
                    modeloVeiculo = scanner.nextLine();
                    System.out.println("Digite o ano de fabricação do carro: ");
                    anoFabricacao = Integer.parseInt(scanner.nextLine());
                    listaVeiculos.add(new Veiculo(placaVeiculo, marcaVeiculo, modeloVeiculo, anoFabricacao));
                }

                cliente = new ClientePF(nome, endereco, listaVeiculos, cpf, genero, dataLicenca, educacao, dataNascimento, classeEconomica);
                seguradora.cadastrarCliente(cliente);
                seguradora.calcularPrecoSeguroCliente(cliente);
            } else if(tipoCliente.equals("PJ")) {
                System.out.println("Digite seu CNPJ: ");
                String cnpj = scanner.nextLine();
                while(!Validacao.validarCNPJ(cnpj)) {
                    System.out.println("O CNPJ digitado não é valido, digite outro, para sair, digite 'voltar':");
                    cnpj = scanner.nextLine();
                    if(cnpj.equals("voltar")) {
                        break begin;
                    }
                }
                System.out.println("Digite a quantidade de funcionários da empresa: ");
                int qtdeFuncionarios = Integer.parseInt(scanner.nextLine());
                System.out.println("Digite a data de fundação, no modelo (dd/mm/aaaa)");
                LocalDate dataFundacao = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                System.out.println("Digite o número de carros para cadastrar: ");
                int numCarros = Integer.parseInt(scanner.nextLine());
                ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
                for(int i = 0; i < numCarros; i++) {
                    System.out.println("Digite a placa do carro");
                    placaVeiculo = scanner.nextLine();
                    System.out.println("Digite a marca do carro: ");
                    marcaVeiculo = scanner.nextLine();
                    System.out.println("Digite o modelo do carro: ");
                    modeloVeiculo = scanner.nextLine();
                    System.out.println("Digite o ano de fabricação do carro: ");
                    anoFabricacao = Integer.parseInt(scanner.nextLine());
                    listaVeiculos.add(new Veiculo(placaVeiculo, marcaVeiculo, modeloVeiculo, anoFabricacao));
                }

                cliente = new ClientePJ(nome, endereco, listaVeiculos, cnpj, dataFundacao, qtdeFuncionarios);
                seguradora.cadastrarCliente(cliente);
                seguradora.calcularPrecoSeguroCliente(cliente);
            }
			break;
            
		case CADASTRAR_VEICULO:
            System.out.println("Digite o nome da seguradora: ");
            nomeSeguradora = scanner.nextLine();
            seguradora = null;
            for(int i = 0; i < listaSeguradoras.size(); i++) {
                if(listaSeguradoras.get(i).getNome().equals(nomeSeguradora)) {
                    seguradora = listaSeguradoras.get(i);
                    break;
                }
            }
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            System.out.println("Digite o CPF/CNPJ do cliente dono do veículo:");
            identificador = scanner.nextLine();
            index = seguradora.encontrarCliente(identificador);
            while(index == -1) {
                System.out.println("O CPF/CNPJ digitado não está cadastrado, digite um outro, para sair, digite 'voltar':");
                identificador = scanner.nextLine();
                if(identificador.equals("voltar")) {
                    break begin;
                }
                index = seguradora.encontrarCliente(identificador);
            }
            cliente = seguradora.getListaClientes().get(index);

            System.out.println("Digite a placa do carro");
            placaVeiculo = scanner.nextLine();
            System.out.println("Digite a marca do carro: ");
            marcaVeiculo = scanner.nextLine();
            System.out.println("Digite o modelo do carro: ");
            modeloVeiculo = scanner.nextLine();
            System.out.println("Digite o ano de fabricação do carro: ");
            anoFabricacao = Integer.parseInt(scanner.nextLine());

            Veiculo veiculo = new Veiculo(placaVeiculo, marcaVeiculo, modeloVeiculo, anoFabricacao);
            cliente.addVeiculo(veiculo);
            seguradora.calcularPrecoSeguroCliente(cliente);
			break;

		case CADASTRAR_SEGURADORA:
            System.out.println("Digite o nome da seguradora: ");
            nomeSeguradora = scanner.nextLine();
            System.out.println("Digite o número de telefone da seguradora: ");
            String telefone = scanner.nextLine();
            System.out.println("Digite o email da seguradora: ");
            String email = scanner.nextLine();
            System.out.println("Digite o endereço da seguradora: ");
            String enderecoSeguradora = scanner.nextLine();
            seguradora = new Seguradora(nomeSeguradora, telefone, email, enderecoSeguradora);
            listaSeguradoras.add(seguradora);
			break;

		case LISTAR_CLIENTES:
            System.out.println("Digite o nome da seguradora:");
            nomeSeguradora = scanner.nextLine();
            seguradora = null;
            for(int i = 0; i < listaSeguradoras.size(); i++) {
                if(listaSeguradoras.get(i).getNome().equals(nomeSeguradora)) {
                    seguradora = listaSeguradoras.get(i);
                    break;
                }
            }
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            seguradora.listarClientes("PF");
            seguradora.listarClientes("PJ");
			break;

		case LISTAR_SINISTROS:
            System.out.println("Digite o nome da seguradora:");
            nomeSeguradora = scanner.nextLine();
            seguradora = null;
            for(int i = 0; i < listaSeguradoras.size(); i++) {
                if(listaSeguradoras.get(i).getNome().equals(nomeSeguradora)) {
                    seguradora = listaSeguradoras.get(i);
                    break;
                }
            }
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            seguradora.listarSinistros();
            break;

		case LISTAR_VEICULOS:
            System.out.println("Digite o nome da seguradora:");
            nomeSeguradora = scanner.nextLine();
            seguradora = null;
            for(int i = 0; i < listaSeguradoras.size(); i++) {
                if(listaSeguradoras.get(i).getNome().equals(nomeSeguradora)) {
                    seguradora = listaSeguradoras.get(i);
                    break;
                }
            }
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            System.out.println("Veículos associados a seguradora:");
            for(int i = 0; i < seguradora.getListaClientes().size(); i++){
                for(int j = 0; j < seguradora.getListaClientes().get(i).getListaVeiculos().size(); j++) {
                    System.out.print(seguradora.getListaClientes().get(i).getListaVeiculos().get(j));
                }
            }
            break;

		case EXCLUIR_CLIENTE:
            System.out.println("Digite o nome da seguradora:");
            nomeSeguradora = scanner.nextLine();
            seguradora = null;
            for(int i = 0; i < listaSeguradoras.size(); i++) {
                if(listaSeguradoras.get(i).getNome().equals(nomeSeguradora)) {
                    seguradora = listaSeguradoras.get(i);
                    break;
                }
            }
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }

            System.out.println("Digite o CPF/CNPJ do cliente:");
            identificador = scanner.nextLine();
            ans = seguradora.removerCliente(identificador);
            if(ans) {
                System.out.println("Cliete removido com sucesso.");
            } else {
                System.out.println("O Clinete não fazia parte dessa seguradora.");
            }
            break;

		case EXCLUIR_VEICULO:
            System.out.println("Digite o nome da seguradora: ");
            nomeSeguradora = scanner.nextLine();
            seguradora = null;
            for(int i = 0; i < listaSeguradoras.size(); i++) {
                if(listaSeguradoras.get(i).getNome().equals(nomeSeguradora)) {
                    seguradora = listaSeguradoras.get(i);
                    break;
                }
            }
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            System.out.println("Digite o CPF/CNPJ do cliente dono do veículo:");
            identificador = scanner.nextLine();
            index = seguradora.encontrarCliente(identificador);
            while(index == -1) {
                System.out.println("O CPF/CNPJ digitado não está cadastrado, digite um outro, para sair, digite 'voltar':");
                identificador = scanner.nextLine();
                if(identificador.equals("voltar")) {
                    break begin;
                }
                index = seguradora.encontrarCliente(identificador);
            }
            cliente = seguradora.getListaClientes().get(index);

            System.out.println("Digite a placa do carro");
            placaVeiculo = scanner.nextLine();
            ans = cliente.removeVeiculo(placaVeiculo);
            seguradora.calcularPrecoSeguroCliente(cliente);
            
            if(ans) {
                System.out.println("Veículo removido com sucesso.");
            } else {
                System.out.println("O Veícuo não estava cadastrado com esse cliente.");
            }
            break;

		case EXCLUIR_SINISTRO:
            System.out.println("Digite o nome da seguradora: ");
            nomeSeguradora = scanner.nextLine();
            seguradora = null;
            for(int i = 0; i < listaSeguradoras.size(); i++) {
                if(listaSeguradoras.get(i).getNome().equals(nomeSeguradora)) {
                    seguradora = listaSeguradoras.get(i);
                    break;
                }
            }
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            int id = Integer.parseInt(scanner.nextLine());
            for(int i = 0; i < seguradora.getListaSinistros().size(); i++) {
                if(seguradora.getListaSinistros().get(i).getId() == id) {
                    seguradora.getListaSinistros().remove(i);
                    System.out.println("O sinistro foi excluído com sucesso");
                    break;
                }
            }
            seguradora.calcularPrecoSeguroCliente();
			break;

		case VOLTAR:
			break;
		}
        
	}
	
	private static void executarSubmenu(ArrayList<Seguradora> listaSeguradoras, MenuOpcoes op) {
		SubmenuOpcoes opSubmenu;
		do {
			exibirSubmenu(op);
			opSubmenu = lerOpcaoSubmenu(op);
			executarOpcaoSubMenu(listaSeguradoras, opSubmenu);
		}while(opSubmenu != SubmenuOpcoes.VOLTAR);
	}

    public static void main(String[] args){
        MenuOpcoes op;
        ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        

        System.out.println("---------INÍCIO DOS TESTES OBRIGATÓRIOS---------\n");

        // Criação de clientes pessoa física
        ArrayList<Veiculo> carrosAlexandre = new ArrayList<Veiculo>();
        Veiculo carroAlexandre1 = new Veiculo("ABC-1234", "Ferrari", "F8", 2023);
        carrosAlexandre.add(carroAlexandre1);
        LocalDate licencaAlexandre = LocalDate.parse("17/03/2022", formato);
        LocalDate nascimentoAlexandre = LocalDate.parse("15/08/2003", formato);

        ClientePF aleSeixas = new ClientePF("Ale Seixas", "Rua Fernando Matheus 8", carrosAlexandre, "464.177.428-57", "Masculino", 
                                                licencaAlexandre, "Ensino Superior Incompleto", nascimentoAlexandre, "Classe Alta");
               
    
        ArrayList<Veiculo> carrosBruna = new ArrayList<Veiculo>();
        Veiculo carroBruna1 = new Veiculo("ABC-6789", "Gol", "Mini", 2012);
        carrosBruna.add(carroBruna1);
        LocalDate licencaBruna = LocalDate.parse("07/03/2021", formato);
        LocalDate nascimentoBruna = LocalDate.parse("06/06/2002", formato);

        ClientePF brunaPereira = new ClientePF("Bruna Pereira", "Rua Alexandre Eduardo 81", carrosBruna, "937.340.400-86", "Feminino", 
                                                licencaBruna, "Ensino Superior Completo", nascimentoBruna, "Classe Média");
               
        
        // Criação de um cliente pessoa jurídica
        ArrayList<Veiculo> carrosBoralli = new ArrayList<Veiculo>();
        Veiculo carroBoralli1 = new Veiculo("MNO-1234", "Chevrolet", "Onix", 2005);
        Veiculo carroBoralli2 = new Veiculo("XYZ-1234", "Porsche", "Panameira", 2027);
        carrosBoralli.add(carroBoralli1);
        carrosBoralli.add(carroBoralli2);
        LocalDate fundacaoBoralli = LocalDate.parse("10/07/1908", formato);
                                        
        ClientePJ boralliInc = new ClientePJ("Boralli Inc", "Rua Getulio Vargas 203" ,carrosBoralli, 
                                            "01.881.237/0001-91", fundacaoBoralli, 72);


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
        Seguradora seguradoraClaudio = new Seguradora("Claudio Seguradoras", "21 98888-1111", "seguradoraclaudio@gmail.com", "Rua Joaquim Augusto 47");
        System.out.println("-> Dados Iniciais da Seguradora:");
        System.out.println((seguradoraClaudio));

        System.out.println("-> Adicionando Clientes: ");
        seguradoraClaudio.cadastrarCliente(aleSeixas);
        seguradoraClaudio.cadastrarCliente(boralliInc);
        seguradoraClaudio.cadastrarCliente(brunaPereira);

        seguradoraClaudio.calcularPrecoSeguroCliente(boralliInc);
        seguradoraClaudio.calcularPrecoSeguroCliente(aleSeixas);
        seguradoraClaudio.calcularPrecoSeguroCliente(brunaPereira);

        seguradoraClaudio.listarClientes("PF");
        seguradoraClaudio.listarClientes("PJ");

        System.out.println("-> Gerando e Visualizando Sinistros: ");
        seguradoraClaudio.gerarSinistro(LocalDate.parse("21/03/2023", formato), "Rua Albert Einstein 76", carroAlexandre1, aleSeixas);
        seguradoraClaudio.visualizarSinistro(aleSeixas.getCPF());

        seguradoraClaudio.gerarSinistro(LocalDate.parse("20/07/2001", formato), "Avenida Caio de Almeida 201", carroBoralli2, boralliInc);
        seguradoraClaudio.gerarSinistro(LocalDate.parse("08/05/2004", formato), "Rua do Limoeiro 27", carroBoralli1, boralliInc);
        seguradoraClaudio.visualizarSinistro(boralliInc.getCNPJ());

        seguradoraClaudio.gerarSinistro(LocalDate.parse("22/01/2020", formato), "Rua Eduardo Silva 301", carroBruna1, brunaPereira);
        seguradoraClaudio.visualizarSinistro(brunaPereira.getCPF());
        
        System.out.println("-> Clientes Físicos Antes da Remoção do Cliente: ");
        seguradoraClaudio.listarClientes("PF");
        System.out.println("-> Sinistros Antes da Remoção do Cliente: ");
        seguradoraClaudio.listarSinistros();

        seguradoraClaudio.removerCliente(brunaPereira.getCPF());
        System.out.println("-> Clientes Físicos Depois da Remoção do Cliente: ");
        seguradoraClaudio.listarClientes("PF");
        System.out.println("-> Sinistros Depois da Remoção de um Cliente");
        seguradoraClaudio.listarSinistros();

        System.out.println(String.format("-> Dados Finais da Seguradora: %s", seguradoraClaudio));

        seguradoraClaudio.calcularReceita();
        System.out.println("---------FIM DOS TESTES OBRIGATÓRIOS---------\n");


        // Parte de comunicar com o terminal
        do {
            exibirMenuExterno();
            op = lerOpcaoMenuExterno();
            executarOpcaoMenuExterno(listaSeguradoras, op);
        } while(op != MenuOpcoes.SAIR);
        System.out.println("Saiu do Sistema");
    }
}