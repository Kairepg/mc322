package lab04.src;
import java.time.LocalDate;
import java.util.ArrayList;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private static ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
    private static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

    // Construtor
    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;    
    }

        // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }


    @Override
    public String toString() {
        String text = "";
        text += String.format("Nome da Seguradora: %s\n", nome);
        text += String.format("Telefone: %s\n", telefone);
        text += String.format("Email: %s\n", email);
        text += String.format("Endereco: %s\n", endereco);
        text += "Lista de Clientes: \n";
        for(int i = 0; i < listaClientes.size(); i++) {
            text += String.format("Nome do Cliente: %s, ", (listaClientes.get(i)).getNome());
            if(listaClientes.get(i) instanceof ClientePF) {
                text += String.format("CPF: %s ", ((ClientePF) listaClientes.get(i)).getCPF());
            } else if(listaClientes.get(i) instanceof ClientePJ) {
                text += String.format("CNPJ: %s ", ((ClientePJ) listaClientes.get(i)).getCNPJ());
            }
            text += "\n";
        }
        text += "Lista de Sinistros: \n";
        for(int i = 0; i < listaSinistros.size(); i++) {
            text += String.format("ID: %s\n", (listaSinistros.get(i)).getId());
        }

        return text;
    }


    public int encontrarCliente(String identificador) {
        for(int i = 0; i < listaClientes.size(); i++) {
            if(listaClientes.get(i) instanceof ClientePF) {
                if(((ClientePF) listaClientes.get(i)).getCPF().equals(identificador)) {
                    return i;
                }
            }
            else if(listaClientes.get(i) instanceof ClientePJ) {
                if(((ClientePJ) listaClientes.get(i)).getCNPJ().equals(identificador)) {
                    return i;
                }
            }
        }
        return -1;
    }


    public boolean cadastrarCliente(Cliente cliente) {
        if(listaClientes.contains(cliente)){
            return true;
        }
        if(!Validacao.validarNome(cliente.getNome())) {
            return false;
        }

        if(cliente instanceof ClientePF) {
            if(Validacao.validarCPF(((ClientePF) cliente).getCPF())) {
                listaClientes.add(cliente);
                return true;
            }
        } else if(cliente instanceof ClientePJ) {
            if(Validacao.validarCNPJ(((ClientePJ)cliente).getCNPJ())) {
                listaClientes.add(cliente);
                return true;
            }
        }
        return false;
    }

    
    public boolean removerCliente(String identificador) {
        boolean returnFlag = false;
        for(int i = 0; i < listaClientes.size(); i++) {
            if(listaClientes.get(i) instanceof ClientePF) {
                if(((ClientePF) listaClientes.get(i)).getCPF().equals(identificador)) {
                    listaClientes.remove(i);
                    returnFlag = true;
                    break;
                }
            }
            else if(listaClientes.get(i) instanceof ClientePJ) {
                if(((ClientePJ) listaClientes.get(i)).getCNPJ().equals(identificador)) {
                    listaClientes.remove(i);
                    returnFlag = true;
                    break;
                }
            }
        }
        if(returnFlag) {
            int i = 0;
            while(i < listaSinistros.size()) {
                if(listaSinistros.get(i).getCliente() instanceof ClientePF){
                    if(((ClientePF)listaSinistros.get(i).getCliente()).getCPF().equals(identificador)){
                        listaSinistros.remove(i);
                        i--;
                    }
                } else if(listaSinistros.get(i).getCliente() instanceof ClientePJ){
                    if(((ClientePJ)listaSinistros.get(i).getCliente()).getCNPJ().equals(identificador)){
                        listaSinistros.remove(i);
                        i--;
                    }
                }
                i++;
            }
        }
        return returnFlag;
    }


    public void listarClientes(String tipoCliente) {
        if(tipoCliente == "PF") {
            System.out.println("Clientes Físicos: ");
            for(int i = 0; i < listaClientes.size(); i++) {
                if(listaClientes.get(i) instanceof ClientePF) {
                    System.out.println(listaClientes.get(i));
                }
            }
        } else if(tipoCliente == "PJ") {
            System.out.println("Clientes Jurídicos: ");
            for(int i = 0; i < listaClientes.size(); i++) {
                if(listaClientes.get(i) instanceof ClientePJ) {
                    System.out.println(listaClientes.get(i));
                }
            }
        }
    }

    
    public boolean gerarSinistro(LocalDate data, String endereco, Veiculo veiculo, 
                                    Cliente cliente) {
        if(listaClientes.contains(cliente)) {
            Sinistro sinistro = new Sinistro(data, endereco, this, veiculo, cliente);
            listaSinistros.add(sinistro);
            return true;
        } else {
            return false;
        }
    }


    public boolean visualizarSinistro(String identificador) {
        boolean found = false;
        System.out.println(String.format("Sinistros Associados ao Cliente de Identificador %s:", identificador));
        for(int i = 0; i < listaSinistros.size(); i++) {
            if((listaSinistros.get(i)).getCliente() instanceof ClientePF) {
                if(((ClientePF) (listaSinistros.get(i)).getCliente()).getCPF().equals(identificador)) {
                    System.out.println(listaSinistros.get(i));
                    found = true;
                }
            }
            else if((listaSinistros.get(i)).getCliente() instanceof ClientePJ) {
                if(((ClientePJ) (listaSinistros.get(i)).getCliente()).getCNPJ().equals(identificador)) {
                    System.out.println(listaSinistros.get(i));
                    found = true;
                    
                }
            }
        }
        return found;
    }


    public void listarSinistros() {
        System.out.println("Sinistros: ");
        for(int i = 0; i < listaSinistros.size(); i++) {
            System.out.println(listaSinistros.get(i));
        }
    }


    private int calculaNumeroSinistros(String identificador) {
        int num = 0;
        for(int i = 0; i < listaSinistros.size(); i++) {
            if((listaSinistros.get(i)).getCliente() instanceof ClientePF) {
                if(((ClientePF) (listaSinistros.get(i)).getCliente()).getCPF().equals(identificador)) {
                    num++;
                }
            }
            else if((listaSinistros.get(i)).getCliente() instanceof ClientePJ) {
                if(((ClientePJ) (listaSinistros.get(i)).getCliente()).getCNPJ().equals(identificador)) {
                    num++;
                }
            }
        }
        return num;
    }


    public void calcularPrecoSeguroCliente() {
        double score;
        for(int i = 0; i < listaClientes.size(); i++) {
            score = listaClientes.get(i).calculaScore();
            listaClientes.get(i).setValorSeguro(score * (1 + calculaNumeroSinistros(listaClientes.get(i).getNome())));
        }
    }


    public void calcularPrecoSeguroCliente(Cliente cliente) {
        double score = cliente.calculaScore();
        cliente.setValorSeguro(score * (1 + calculaNumeroSinistros(cliente.getNome())));
    }


    public void calcularReceita() {
        double valorTotal = 0;
        for(int i = 0; i < listaClientes.size(); i++) {
            valorTotal += listaClientes.get(i).getValorSeguro();
        }
        System.out.println(String.format("Receita total da Seguradora: %f", valorTotal));
    }
}