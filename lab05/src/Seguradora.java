package lab05.src;
import java.util.ArrayList;

public class Seguradora {
    private final String CNPJ;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Seguro> listaSeguros;

    // Construtor
    public Seguradora(String cnpj, String nome, String telefone, String email, 
                        String endereco, ArrayList<Cliente> listaClientes,
                        ArrayList<Seguro> listaSeguros) {
        this.CNPJ = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaClientes = listaClientes;
        this.listaSeguros = listaSeguros;
    }

        // Getters e setters
    public String getCNPJ() {
        return CNPJ;
    }

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

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public ArrayList<Seguro> getListaSeguros() {
        return listaSeguros;
    }

    @Override
    public String toString() {
        String text = "";
        text += String.format("Nome da Seguradora: %s\n", nome);
        text += String.format("CNPJ: %s\n", CNPJ);
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
        text += "Lista de Seguros: \n";
        for(int i = 0; i < listaSeguros.size(); i++) {
            text += String.format("ID: %s\n", (listaSeguros.get(i)).getId());
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

    public int encontrarSeguro(int codigo) {
        for(int i = 0; i < listaSeguros.size(); i++) {
            if(listaSeguros.get(i).getId() == codigo) {
                return i;
            }
        }
        return -1;
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


    public boolean gerarSeguro(Seguro seguro) {
        if(listaSeguros.contains(seguro)) {
            return false;
        }
        listaSeguros.add(seguro);
        return true;
    }


    public boolean cancelarSeguro(int id) {
        for(int i = 0; i < listaSeguros.size(); i++) {
            if(listaSeguros.get(i).getId() == id) {
                listaSeguros.remove(i);
                return true;
            }
        }
        return false;
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
            while(i < listaSeguros.size()) {
                if(listaSeguros.get(i) instanceof SeguroPF){
                    if((((SeguroPF) listaSeguros.get(i)).getCliente()).getCPF().equals(identificador)){
                        listaSeguros.remove(i);
                        i--;
                    }
                } else if(listaSeguros.get(i) instanceof SeguroPJ){
                    if((((SeguroPJ) listaSeguros.get(i)).getCliente()).getCNPJ().equals(identificador)){
                        listaSeguros.remove(i);
                        i--;
                    }
                }
                i++;
            }
        }
        return returnFlag;
    }


    public ArrayList<Seguro> getSegurosPorCliente(String identificador) {
        ArrayList<Seguro> lista = new ArrayList<Seguro>();
        for(int i = 0; i < listaSeguros.size(); i++) {
            if(listaSeguros.get(i) instanceof SeguroPF) {
                if(((SeguroPF) listaSeguros.get(i)).getCliente().getCPF().equals(identificador)) {
                    lista.add(listaSeguros.get(i));
                }
            }
            if(listaSeguros.get(i) instanceof SeguroPJ) {
                if(((SeguroPJ) listaSeguros.get(i)).getCliente().getCNPJ().equals(identificador)) {
                    lista.add(listaSeguros.get(i));
                }
            }
        }
        return lista;
    }


    public ArrayList<Sinistro> getSinistrosPorCliente(String identificador) {
        ArrayList<Seguro> listaSeg = getSegurosPorCliente(identificador);
        ArrayList<Sinistro> lista = new ArrayList<Sinistro>();
        for(int i = 0; i < listaSeg.size(); i++) {
            lista.addAll(listaSeg.get(i).getListaSinistros());
        }
        return lista;
    }


    public ArrayList<Seguro> getSegurosPorCliente(Cliente cliente) {
        ArrayList<Seguro> lista = new ArrayList<Seguro>();
        for(int i = 0; i < listaSeguros.size(); i++) {
            if(listaSeguros.get(i) instanceof SeguroPF) {
                if(((SeguroPF) listaSeguros.get(i)).getCliente().equals(cliente)) {
                    lista.add(listaSeguros.get(i));
                }
            }
            if(listaSeguros.get(i) instanceof SeguroPJ) {
                if(((SeguroPJ) listaSeguros.get(i)).getCliente().equals(cliente)) {
                    lista.add(listaSeguros.get(i));
                }
            }
        }
        return lista;
    }


    public ArrayList<Sinistro> getSinistrosPorCliente(Cliente cliente) {
        ArrayList<Seguro> listaSeg = getSegurosPorCliente(cliente);
        ArrayList<Sinistro> lista = new ArrayList<Sinistro>();
        for(int i = 0; i < listaSeg.size(); i++) {
            lista.addAll(listaSeg.get(i).getListaSinistros());
        }
        return lista;
    }


    public void calcularReceita() {
        double valorTotal = 0;
        for(int i = 0; i < listaSeguros.size(); i++) {
            valorTotal += listaSeguros.get(i).getValorMensal();
        }
        System.out.println(String.format("Receita total da Seguradora: %.2f", valorTotal));
    }
}