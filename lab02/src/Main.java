package lab02.src;

public class Main {
    public static void main(String[] args){

        // Testes com os clientes
        Cliente ale_seixas = new Cliente("Ale Seixas", "464.177.428-57", "15/08/2003", 19, "rua do limao");
        Cliente giovani_falso = new Cliente("Giovani Boralli", "111.111.111-11", "03/03/2003", 20, "rua do bruno");

        System.out.println(ale_seixas);
        if (ale_seixas.validarCPF()) {
            System.out.println("o CPF eh real\n");
        } else {
            System.out.println("o CPF eh falso\n");
        }

        System.out.println(giovani_falso);
        if (giovani_falso.validarCPF()) {
            System.out.println("o CPF eh real\n");
        } else {
            System.out.println("o CPF eh falso\n");
        }


        // Testes com os sinistros
        Sinistro sinistro1 = new Sinistro("23/03/2023", "rua do limao 2");
        Sinistro sinistro2 = new Sinistro("03/03/2003", "rua do limao 3");

        System.out.println(sinistro1);
        System.out.println(sinistro2);

        // As ids sao geradas aleatoriamente
        System.out.println(sinistro1.getId() == sinistro2.getId());
        System.out.println();


        // Testes com as seguradoras
        Seguradora seguradora1 = new Seguradora("seguradora legal", "21 98888-1111", "seguradora@gmail.com", "rua do limao 4");
        System.out.println(seguradora1);

        seguradora1.setEndereco("rua da laranja");
        System.out.println(seguradora1.getEndereco());
        System.out.println(seguradora1);


        // Testes com os veivulos
        Veiculo carro1 = new Veiculo("1234 5678", "Fiat", "Gol");
        System.out.println(carro1);
        

    }
}