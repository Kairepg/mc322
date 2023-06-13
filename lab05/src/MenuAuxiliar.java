package lab05.src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuAuxiliar {
    private static final Scanner scanner = new Scanner(System.in);

    public static LocalDate lerData() {
        LocalDate data = null;
        String text = scanner.nextLine();
        if(text.equals("sair")) {
            return null;
        }
        try {
            data = LocalDate.parse(text, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch(Exception e) {
            System.out.println("O formato de data não foi inserida corretamente");
            System.out.println("Se quiser sair, digite sair");
            data = lerData();
        }
        return data;
    }


    public static Seguradora conferirSeguradora(ArrayList<Seguradora> listaSeguradoras) {
        System.out.println("Digite o cnpj da seguradora:");
        String cnpjSeguradora = scanner.nextLine();
        Seguradora seguradora = null;
        for(int i = 0; i < listaSeguradoras.size(); i++) {
            if(listaSeguradoras.get(i).getCNPJ().equals(cnpjSeguradora)) {
                seguradora = listaSeguradoras.get(i);
                return seguradora;
            }
        }
        return seguradora;
    }


    public static void gerarSinistro(Seguradora seguradora) {
        System.out.println("Digite o código do seguro do carro envolvido no acidente:");
        int codigo = Integer.parseInt(scanner.nextLine());
        int indexSeg = seguradora.encontrarSeguro(codigo);
        while(indexSeg == -1) {
            System.out.println("O codigo digitado não é valido, digite outro, para sair, digite -1:");
            codigo = Integer.parseInt(scanner.nextLine());
            if(codigo == -1) {
                return;
            }
            indexSeg = seguradora.encontrarSeguro(codigo);
        }
        Seguro seguro = seguradora.getListaSeguros().get(indexSeg);

        System.out.println("Digite o cpf do condutor envolvido no acidente");
        String cpf = scanner.nextLine();
        Condutor condutor = null;
        boolean outFlag = false;
        while(true) {
            for(int i = 0; i < seguro.getListaCondutores().size(); i++) {
                if(seguro.getListaCondutores().get(i).getCpf().equals(cpf)) {
                    condutor = seguro.getListaCondutores().get(i);
                    outFlag = true;
                    break;
                }
            }
            if(outFlag) {
                break;
            }
            System.out.println("O cpf digitado não é valido, digite outro, para sair, voltar:");
            cpf = scanner.nextLine();
            if(cpf.equals("voltar")) {
                return;
            }
        }
        
        System.out.println("Digite o local do acidente: ");
        String enderecoAcidente = scanner.nextLine();
        System.out.println("Digite a data do acidente, no modelo (dd/mm/aaaa)");
        LocalDate dataAcidente = lerData();
        if(dataAcidente == null) {
            return;
        }

        seguro.gerarSinistro(dataAcidente, enderecoAcidente, condutor);
        System.out.println("Sinistro gerado");
    }


    public static void cadastrarCliente(Seguradora seguradora) {
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
                    return;
                }
            }
            System.out.println("Digite seu gênero: ");
            String genero = scanner.nextLine();
            System.out.println("Digite seu número de telefone: ");
            String telefone = scanner.nextLine();
            System.out.println("Digite sua educação: ");
            String educacao = scanner.nextLine();
            System.out.println("Digite seu email: ");
            String email = scanner.nextLine();
            System.out.println("Digite sua data de nascimento, no modelo (dd/mm/aaaa)");
            LocalDate dataNascimento = lerData();
            if(dataNascimento == null) {
                return;
            }

            System.out.println("Digite o número de carros para cadastrar: ");
            int numCarros = Integer.parseInt(scanner.nextLine());
            ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
            for(int i = 0; i < numCarros; i++) {
                System.out.println("Digite a placa do carro");
                String placaVeiculo = scanner.nextLine();
                System.out.println("Digite a marca do carro: ");
                String marcaVeiculo = scanner.nextLine();
                System.out.println("Digite o modelo do carro: ");
                String modeloVeiculo = scanner.nextLine();
                System.out.println("Digite o ano de fabricação do carro: ");
                int anoFabricacao = Integer.parseInt(scanner.nextLine());
                listaVeiculos.add(new Veiculo(placaVeiculo, marcaVeiculo, modeloVeiculo, anoFabricacao));
            }

            Cliente cliente = new ClientePF(nome, telefone, endereco, email, cpf, genero, educacao, dataNascimento, listaVeiculos);
            seguradora.cadastrarCliente(cliente);

        } else if(tipoCliente.equals("PJ")) {
            System.out.println("Digite seu CNPJ: ");
            String cnpj = scanner.nextLine();
            while(!Validacao.validarCNPJ(cnpj)) {
                System.out.println("O CNPJ digitado não é valido, digite outro, para sair, digite 'voltar':");
                cnpj = scanner.nextLine();
                if(cnpj.equals("voltar")) {
                    return;
                }
            }
            System.out.println("Digite a quantidade de funcionários da empresa: ");
            int qtdeFuncionarios = Integer.parseInt(scanner.nextLine());
            System.out.println("Digite a data de fundação, no modelo (dd/mm/aaaa)");
            LocalDate dataFundacao = lerData();
            if(dataFundacao == null) {
                return;
            }
            System.out.println("Digite o telefone da empresa: ");
            String telefone = scanner.nextLine();
            System.out.println("Digite o email da empresa: ");
            String email = scanner.nextLine();

            System.out.println("Digite o número de frotas para cadastrar: ");
            int numFrotas = Integer.parseInt(scanner.nextLine());
            ArrayList<Frota> listaFrota = new ArrayList<Frota> ();
            int numCarros;
            for(int j = 0; j < numFrotas; j++) {
                System.out.println("Digite o número de carros na frota: ");
                numCarros = Integer.parseInt(scanner.nextLine());
                listaFrota.add(new Frota(new ArrayList<Veiculo> ()));
                for(int i = 0; i < numCarros; i++) {
                    System.out.println("Digite a placa do carro");
                    String placaVeiculo = scanner.nextLine();
                    System.out.println("Digite a marca do carro: ");
                    String marcaVeiculo = scanner.nextLine();
                    System.out.println("Digite o modelo do carro: ");
                    String modeloVeiculo = scanner.nextLine();
                    System.out.println("Digite o ano de fabricação do carro: ");
                    int anoFabricacao = Integer.parseInt(scanner.nextLine());
                    listaFrota.get(j).addVeiculo(new Veiculo(placaVeiculo, marcaVeiculo, modeloVeiculo, anoFabricacao));
                }
            }
            Cliente cliente = new ClientePJ(nome, telefone, endereco, email, cnpj, dataFundacao, listaFrota, qtdeFuncionarios);
            seguradora.cadastrarCliente(cliente);
        }
    }


    public static void cadastrarVeiculo(Seguradora seguradora) {
        System.out.println("Digite se o cliente é pessoa física (PF) ou pessoa jurídica (PJ):");
        String tipoCliente = scanner.nextLine();
        if(tipoCliente.equals("PF")) {
            System.out.println("Digite o CPF do cliente: ");
            String cpf = scanner.nextLine();
            int index = seguradora.encontrarCliente(cpf);
            while(index == -1) {
                System.out.println("O CPF digitado não é valido, digite outro, para sair, digite 'voltar':");
                cpf = scanner.nextLine();
                if(cpf.equals("voltar")) {
                    return;
                }
                index = seguradora.encontrarCliente(cpf);
            }
            Cliente cliente = seguradora.getListaClientes().get(index);
            
            System.out.println("Digite a placa do carro");
            String placaVeiculo = scanner.nextLine();
            System.out.println("Digite a marca do carro: ");
            String marcaVeiculo = scanner.nextLine();
            System.out.println("Digite o modelo do carro: ");
            String modeloVeiculo = scanner.nextLine();
            System.out.println("Digite o ano de fabricação do carro: ");
            int anoFabricacao = Integer.parseInt(scanner.nextLine());
            Veiculo carro = new Veiculo(placaVeiculo, marcaVeiculo, modeloVeiculo, anoFabricacao);
            
            ((ClientePF)cliente).cadastrarVeiculo(carro);

        } else if(tipoCliente.equals("PJ")) {
            System.out.println("Digite o CNPJ do cliente: ");
            String cnpj = scanner.nextLine();
            int index = seguradora.encontrarCliente(cnpj);
            while(index == -1) {
                System.out.println("O CNPJ digitado não é valido, digite outro, para sair, digite 'voltar':");
                cnpj = scanner.nextLine();
                if(cnpj.equals("voltar")) {
                    return;
                }
                index = seguradora.encontrarCliente(cnpj);
            }
            Cliente cliente = seguradora.getListaClientes().get(index);

            System.out.println("Digite o código da frota: ");
            int codigo = Integer.parseInt(scanner.nextLine());
            boolean flag_n = false;
            while(true) {
                for(Frota atual : ((ClientePJ)cliente).getListaFrota()) {
                    if(atual.getCode() == codigo) {
                        flag_n = true;
                    }
                }
                if(flag_n) {
                    break;
                }
                System.out.println("O código digitado não é valido, digite outro, para sair, digite -1:");
                codigo = Integer.parseInt(scanner.nextLine());
                if(codigo == -1) {
                    return;
                }
            }

            System.out.println("Digite a placa do carro");
            String placaVeiculo = scanner.nextLine();
            System.out.println("Digite a marca do carro: ");
            String marcaVeiculo = scanner.nextLine();
            System.out.println("Digite o modelo do carro: ");
            String modeloVeiculo = scanner.nextLine();
            System.out.println("Digite o ano de fabricação do carro: ");
            int anoFabricacao = Integer.parseInt(scanner.nextLine());
            
            ((ClientePJ)cliente).atualizarFrota(codigo, new Veiculo(placaVeiculo, marcaVeiculo, modeloVeiculo, anoFabricacao));
        }
    }


    public static void cadastrarSeguradora(ArrayList<Seguradora> listaSeguradoras) {
        System.out.println("Digite o nome da seguradora: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o CNPJ da seguradora: ");
        String cnpj = scanner.nextLine();
        System.out.println("Digite o número de telefone da seguradora: ");
        String telefone = scanner.nextLine();
        System.out.println("Digite o email da seguradora: ");
        String email = scanner.nextLine();
        System.out.println("Digite o endereço da seguradora: ");
        String endereco = scanner.nextLine();
        Seguradora seguradora = new Seguradora(cnpj, nome, telefone, email, endereco, new ArrayList<Cliente>(), new ArrayList<Seguro> ());
        listaSeguradoras.add(seguradora);
    }


    public static void cadastrarFrota(Seguradora seguradora) {
        System.out.println("Digite o CNPJ do cliente: ");
        String cnpj = scanner.nextLine();
        int index = seguradora.encontrarCliente(cnpj);
        while(index == -1) {
            System.out.println("O CNPJ digitado não está cadastrado, digite um outro. Para sair, digite 'voltar':");
            cnpj = scanner.nextLine();
            if(cnpj.equals("voltar")) {
                return;
            }
            index = seguradora.encontrarCliente(cnpj);
        }
        Cliente cliente = seguradora.getListaClientes().get(index);
        
        System.out.println("Digite o número de carros na frota: ");
        int numCarros = Integer.parseInt(scanner.nextLine());
        Frota frota = new Frota(new ArrayList<Veiculo> ());
        for(int i = 0; i < numCarros; i++) {
            System.out.println("Digite a placa do carro");
            String placaVeiculo = scanner.nextLine();
            System.out.println("Digite a marca do carro: ");
            String marcaVeiculo = scanner.nextLine();
            System.out.println("Digite o modelo do carro: ");
            String modeloVeiculo = scanner.nextLine();
            System.out.println("Digite o ano de fabricação do carro: ");
            int anoFabricacao = Integer.parseInt(scanner.nextLine());
            frota.addVeiculo(new Veiculo(placaVeiculo, marcaVeiculo, modeloVeiculo, anoFabricacao));
        }
        ((ClientePJ)cliente).cadastrarFrota(frota);
    }


    public static void cadastrarCondutor(Seguradora seguradora) {
        System.out.println("Digite o código do seguro: ");
        int codigo = Integer.parseInt(scanner.nextLine());

        int index = seguradora.encontrarSeguro(codigo);
        while(index == -1) {
            System.out.println("O código digitado não está cadastrado, digite um outro. Para sair, digite '-1':");
            codigo = Integer.parseInt(scanner.nextLine());
            if(codigo == -1) {
                return;
            }
            index = seguradora.encontrarSeguro(codigo);
        }
        
        System.out.println("Digite o nome do condutor:");
        String nome = scanner.nextLine();
        while(!Validacao.validarNome(nome)) {
            System.out.println("O nome digitado não é valido, digite outro:");
            nome = scanner.nextLine();
        }
        System.out.println("Digite o endereço do condutor:");
        String endereco = scanner.nextLine();
        System.out.println("Digite o CPF: ");
        String cpf = scanner.nextLine();
        while(!Validacao.validarCPF(cpf)) {
            System.out.println("O CPF digitado não é valido, digite outro, para sair, digite 'voltar':");
            cpf = scanner.nextLine();
            if(cpf.equals("voltar")) {
                return;
            }
        }
        System.out.println("Digite o número de telefone: ");
        String telefone = scanner.nextLine();
        System.out.println("Digite o email: ");
        String email = scanner.nextLine();
        System.out.println("Digite a data de nascimento, no modelo (dd/mm/aaaa)");
        LocalDate dataNascimento = lerData();
        if(dataNascimento == null) {
            return;
        }
        Condutor condutor = new Condutor(cpf, nome, telefone, endereco, email, dataNascimento, new ArrayList<Sinistro> ());

        seguradora.getListaSeguros().get(index).autorizarCondutor(condutor);
    }


    public static void cadastrarSeguro(Seguradora seguradora) {
        System.out.println("Digite a data do fim do seguro, no modelo (dd/mm/aaaa)");
        LocalDate dataFim = lerData();
        if(dataFim == null) {
            return;
        }

        System.out.println("Digite se o cliente é pessoa física (PF) ou pessoa jurídica (PJ):");
        String tipoCliente = scanner.nextLine();
        if(tipoCliente.equals("PF")) {
            System.out.println("Digite o CPF do cliente: ");
            String cpf = scanner.nextLine();
            int index = seguradora.encontrarCliente(cpf);
            while(index == -1) {
                System.out.println("O CPF digitado não é valido, digite outro, para sair, digite 'voltar':");
                cpf = scanner.nextLine();
                if(cpf.equals("voltar")) {
                    return;
                }
                index = seguradora.encontrarCliente(cpf);
            }
            Cliente cliente = seguradora.getListaClientes().get(index);
            
            System.out.println("Digite a placa do carro");
            String placaVeiculo = scanner.nextLine();
            System.out.println("Digite a marca do carro: ");
            String marcaVeiculo = scanner.nextLine();
            System.out.println("Digite o modelo do carro: ");
            String modeloVeiculo = scanner.nextLine();
            System.out.println("Digite o ano de fabricação do carro: ");
            int anoFabricacao = Integer.parseInt(scanner.nextLine());
            Veiculo carro = new Veiculo(placaVeiculo, marcaVeiculo, modeloVeiculo, anoFabricacao);
            
            ((ClientePF)cliente).cadastrarVeiculo(carro);
            Seguro seguro = new SeguroPF(LocalDate.now(), dataFim, seguradora, new ArrayList<Sinistro> (), 
                                            new ArrayList<Condutor> (), carro, (ClientePF)cliente);
            seguradora.gerarSeguro(seguro);

        } else if(tipoCliente.equals("PJ")) {
            System.out.println("Digite o CNPJ do cliente: ");
            String cnpj = scanner.nextLine();
            int index = seguradora.encontrarCliente(cnpj);
            while(index == -1) {
                System.out.println("O CNPJ digitado não é valido, digite outro, para sair, digite 'voltar':");
                cnpj = scanner.nextLine();
                if(cnpj.equals("voltar")) {
                    return;
                }
                index = seguradora.encontrarCliente(cnpj);
            }
            Cliente cliente = seguradora.getListaClientes().get(index);

            System.out.println("Digite o número de carros na frota: ");
            int numCarros = Integer.parseInt(scanner.nextLine());
            Frota frota = new Frota(new ArrayList<Veiculo> ());
            for(int i = 0; i < numCarros; i++) {
                System.out.println("Digite a placa do carro");
                String placaVeiculo = scanner.nextLine();
                System.out.println("Digite a marca do carro: ");
                String marcaVeiculo = scanner.nextLine();
                System.out.println("Digite o modelo do carro: ");
                String modeloVeiculo = scanner.nextLine();
                System.out.println("Digite o ano de fabricação do carro: ");
                int anoFabricacao = Integer.parseInt(scanner.nextLine());
                frota.addVeiculo(new Veiculo(placaVeiculo, marcaVeiculo, modeloVeiculo, anoFabricacao));
            }

            ((ClientePJ)cliente).cadastrarFrota(frota);
            Seguro seguro = new SeguroPJ(LocalDate.now(), dataFim, seguradora, new ArrayList<Sinistro> (), 
                                            new ArrayList<Condutor> (), frota, (ClientePJ)cliente);
            seguradora.gerarSeguro(seguro);
        }
    }


    public static void listarClientes(Seguradora seguradora) {
        seguradora.listarClientes("PF");
        seguradora.listarClientes("PJ");
    }


    public static void listarSeguros(Seguradora seguradora) {
        System.out.println("Os seguros associados à seguradora são: ");
        for(Seguro seguro : seguradora.getListaSeguros()) {
            System.out.println(seguro);
        }
    }


    public static void listarSinistrosSeguradora(Seguradora seguradora) {
        System.out.println("Os sinistros associados à seguradora são: ");
        ArrayList<Sinistro> listaSin;
        for(Cliente atual : seguradora.getListaClientes()) {
            listaSin = seguradora.getSinistrosPorCliente(atual);
            for(Sinistro sin : listaSin) {
                System.out.println(sin);
            }
        }
    }


    public static void listarSinistrosSeguro(Seguradora seguradora) {
        System.out.println("Digite o id do seguro");
        int id = Integer.parseInt(scanner.nextLine());
        for(Seguro seguro : seguradora.getListaSeguros()) {
            if(seguro.getId() == id) {
                System.out.println("Os sinistros associados ao seguro são: ");
                for(Sinistro sin : seguro.getListaSinistros()) {
                    System.out.println(sin);
                }
                return;
            }
        }
        System.out.print("O Id digitado não foi encontrado");
    }


    public static void listarSinistrosCliente(Seguradora seguradora) {
        System.out.println("Digite o cpf/cnpj do cliente");
        String identificador = scanner.nextLine();
        ArrayList<Sinistro> listaSin = seguradora.getSinistrosPorCliente(identificador);
        System.out.println("Os sinistros associados ao cliente são: ");
        for(Sinistro sin : listaSin) {
            System.out.println(sin);
        }
    }


    public static void listarSinistrosCondutor(Seguradora seguradora) {
        System.out.println("Digite o id do seguro");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Digite o cpf do condutor");
        String cpf = scanner.nextLine();
        for(Seguro seguro : seguradora.getListaSeguros()) {
            if(seguro.getId() == id) {
                for(Condutor condutor : seguro.getListaCondutores()) {
                    if(condutor.getCpf().equals(cpf)) {
                        System.out.println("Os sinistros associados ao condutor são: ");
                        for(Sinistro sin : seguro.getListaSinistros()) {
                            System.out.println(sin);
                        }
                        return;
                    }
                }
            }
        }
        System.out.print("O condutor buscado não foi encontrado");
    }


    public static void listarVeiculosSeguradora(Seguradora seguradora) {
        System.out.println("Veículos associados a seguradora:");
        ArrayList<Veiculo> listaVeic;
        for(Cliente atual : seguradora.getListaClientes()) {
            if(atual instanceof ClientePF) {
                listaVeic = ((ClientePF)atual).getListaVeiculos();
                for(Veiculo carro : listaVeic) {
                    System.out.println(carro);
                }
            } else if(atual instanceof ClientePJ) {
                for(Frota frota : ((ClientePJ)atual).getListaFrota()) {
                    listaVeic = frota.getListaVeiculo();
                    for(Veiculo carro : listaVeic) {
                        System.out.println(carro);
                    }
                }
            }
        }
    }


    public static void listarVeiculosCliente(Seguradora seguradora) {
        System.out.println("Digite o tipo de cliente (PF) ou (PJ)");
        String tipo = scanner.nextLine();
        ArrayList<Veiculo> listaVeic;
        if(tipo.equals("PF")) {
            System.out.println("Digite o cpf do cliente");
            String cpf = scanner.nextLine();
            for(Cliente cliente : seguradora.getListaClientes()) {
                if(cliente instanceof ClientePF && ((ClientePF)cliente).getCPF().equals(cpf)) {
                    listaVeic = ((ClientePF)cliente).getListaVeiculos();
                    for(Veiculo carro : listaVeic) {
                        System.out.println(carro);
                    }
                    return;
                }
            }
        } else if(tipo.equals("PJ")) {
            System.out.println("Digite o cnpj do cliente");
            String cnpj = scanner.nextLine();
            for(Cliente cliente : seguradora.getListaClientes()) {
                if(cliente instanceof ClientePJ && ((ClientePJ)cliente).getCNPJ().equals(cnpj)) {
                    for(Frota frota : ((ClientePJ)cliente).getListaFrota()) {
                        listaVeic = frota.getListaVeiculo();
                        for(Veiculo carro : listaVeic) {
                            System.out.println(carro);
                        }
                    }
                    return;
                }
            }
        }
    }


    public static void listarCondutoresSeguro(Seguradora seguradora) {
        System.out.println("Digite o id do seguro");
        int id = Integer.parseInt(scanner.nextLine());
        for(Seguro seguro : seguradora.getListaSeguros()) {
            if(seguro.getId() == id) {
                System.out.println("Os condutores associados ao seguro são: ");
                for(Condutor condutor : seguro.getListaCondutores()) {
                    System.out.println(condutor);
                }
                return;
            }
        }
        System.out.print("O Id digitado não foi encontrado");
    }


    public static void listarSeguradoras(ArrayList<Seguradora> listaSeguradoras) {
        System.out.println("Seguradoras cadastradas no sistema:");
        for(Seguradora seguradora : listaSeguradoras) {
            System.out.println(seguradora);
        }
    }


    public static void excluirCliente(Seguradora seguradora) {
        System.out.println("Digite o CPF/CNPJ do cliente:");
        String identificador = scanner.nextLine();
        boolean ans = seguradora.removerCliente(identificador);
        if(ans) {
            System.out.println("Cliete removido com sucesso.");
        } else {
            System.out.println("O Cliente não fazia parte dessa seguradora.");
        }
    }


    public static void excluirVeiculo(Seguradora seguradora) {
        boolean ans = false;

        System.out.println("Digite se o cliente é pessoa física (PF) ou pessoa jurídica (PJ):");
        String tipoCliente = scanner.nextLine();
        if(tipoCliente.equals("PF")) {
            System.out.println("Digite o CPF do cliente: ");
            String cpf = scanner.nextLine();
            int index = seguradora.encontrarCliente(cpf);
            while(index == -1) {
                System.out.println("O CPF digitado não é valido, digite outro, para sair, digite 'voltar':");
                cpf = scanner.nextLine();
                if(cpf.equals("voltar")) {
                    return;
                }
                index = seguradora.encontrarCliente(cpf);
            }
            Cliente cliente = seguradora.getListaClientes().get(index);
            System.out.println("Digite a placa do carro");
            String placaVeiculo = scanner.nextLine();
            ans = ((ClientePF)cliente).removerVeiculo(placaVeiculo);

        } else if(tipoCliente.equals("PJ")) {
            System.out.println("Digite o CNPJ do cliente: ");
            String cnpj = scanner.nextLine();
            int index = seguradora.encontrarCliente(cnpj);
            while(index == -1) {
                System.out.println("O CNPJ digitado não é valido, digite outro, para sair, digite 'voltar':");
                cnpj = scanner.nextLine();
                if(cnpj.equals("voltar")) {
                    return;
                }
                index = seguradora.encontrarCliente(cnpj);
            }
            Cliente cliente = seguradora.getListaClientes().get(index);

            System.out.println("Digite o código da frota: ");
            int codigo = Integer.parseInt(scanner.nextLine());
            boolean flag_n = false;
            while(true) {
                for(Frota atual : ((ClientePJ)cliente).getListaFrota()) {
                    if(atual.getCode() == codigo) {
                        flag_n = true;
                    }
                }
                if(flag_n) {
                    break;
                }
                System.out.println("O código digitado não é valido, digite outro, para sair, digite -1:");
                codigo = Integer.parseInt(scanner.nextLine());
                if(codigo == -1) {
                    return;
                }
            }
            System.out.println("Digite a placa do carro");
            String placaVeiculo = scanner.nextLine();
            ans = ((ClientePJ)cliente).atualizarFrota(codigo, placaVeiculo);
        }
        if(ans) {
            System.out.println("Veículo removido com sucesso.");
        } else {
            System.out.println("O Veícuo não estava cadastrado com esse cliente.");
        }
    }


    public static void excluirSeguradora(ArrayList<Seguradora> listaSeguradoras) {
        System.out.println("Digite o CNPJ da seguradora: ");
        String cnpj = scanner.nextLine();
        for(int i = 0; i < listaSeguradoras.size(); i++) {
            if(listaSeguradoras.get(i).getCNPJ().equals(cnpj)) {
                listaSeguradoras.remove(i);
                System.out.println("A seguradora foi removida com sucesso.");
                return;
            }
        }
        System.out.println("O Código não foi encontrado na lista de seguradoras.");
    }


    public static void excluirFrota(Seguradora seguradora) {
        boolean ans = false;
        System.out.println("Digite o CNPJ do cliente: ");
        String cnpj = scanner.nextLine();
        int index = seguradora.encontrarCliente(cnpj);
        while(index == -1) {
            System.out.println("O CNPJ digitado não está cadastrado, digite um outro. Para sair, digite 'voltar':");
            cnpj = scanner.nextLine();
            if(cnpj.equals("voltar")) {
                return;
            }
            index = seguradora.encontrarCliente(cnpj);
        }
        Cliente cliente = seguradora.getListaClientes().get(index);
        
        System.out.println("Digite o código da frota: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        ans = ((ClientePJ)cliente).atualizarFrota(codigo);
        while(!ans) {
            System.out.println("O código digitado não está cadastrado, digite um outro. Para sair, digite -1:");
            codigo = Integer.parseInt(scanner.nextLine());
            if(codigo == -1) {
                return;
            }
            ans = ((ClientePJ)cliente).atualizarFrota(codigo);
        }
    }


    public static void excluirCondutor(Seguradora seguradora) {
        boolean ans = false;
        System.out.println("Digite o código do seguro: ");
        int codigo = Integer.parseInt(scanner.nextLine());

        int index = seguradora.encontrarSeguro(codigo);
        while(index == -1) {
            System.out.println("O código digitado não está cadastrado, digite um outro. Para sair, digite '-1':");
            codigo = Integer.parseInt(scanner.nextLine());
            if(codigo == -1) {
                return;
            }
            index = seguradora.encontrarSeguro(codigo);
        }
        Seguro seguro = seguradora.getListaSeguros().get(index);
        
        System.out.println("Digite o CPF do condutor: ");
        String cpf = scanner.nextLine();
        ans = seguro.desautorizarCondutor(cpf);
        while(!ans) {
            System.out.println("O CPF digitado não foi encontrado, digite outro, para sair, digite 'voltar':");
            cpf = scanner.nextLine();
            if(cpf.equals("voltar")) {
                return;
            }
            ans = seguro.desautorizarCondutor(cpf);
        }
        if(ans) {
            System.out.println("Condutor removido com sucesso.");
        } else {
            System.out.println("O Condutor não estava cadastrado com esse seguro.");
        }
    }


    public static void excluirSinistro(Seguradora seguradora) {
        System.out.println("Digite o codigo do seguro associado ao sinistro");
        int codigo = Integer.parseInt(scanner.nextLine());
        int index = seguradora.encontrarSeguro(codigo);
        while(index == -1) {
            System.out.println("O codigo digitado não é valido, digite outro, para sair, digite -1:");
            codigo = Integer.parseInt(scanner.nextLine());
            if(codigo == -1) {
                return;
            }
            index = seguradora.encontrarSeguro(codigo);
        }
        Seguro seguro = seguradora.getListaSeguros().get(index);
        System.out.println("Digite o id do sinistro");
        int idSinistro = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < seguro.getListaSinistros().size(); i++) {
            if(seguro.getListaSinistros().get(i).getId() == idSinistro) {
                seguro.getListaSinistros().remove(i);
                System.out.println("O sinistro foi excluído com sucesso");
                return;
            }
        }
        System.out.println("O sinistro não foi encontrado");
    }
}
