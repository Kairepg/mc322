public class Main {
    public static void main(String[] args){
        Cliente ale_seixas = new Cliente("Alê Seixas", "464.177.428-57", "15/08/2003", 19, "rua do limão");
        Sinistro bizonho = new Sinistro("23/03/2023", "rua do limao 2");

        System.out.println("Nossa historia comeca com uma pessoa");
        System.out.println(ale_seixas);
        if (ale_seixas.validarCPF(ale_seixas.getCPF())) {
            System.out.println("ale seixas eh real");
        } else {
            System.out.println("ale seixas eh falso");
        }

        System.out.println("ah nao, um acidente, eis o sinistro:");
        System.out.println(bizonho);

        System.out.println(String.format("O %s ficou com medo da rua por causa do acidente", ale_seixas.getNome()));
        System.out.println(String.format("Ele morava perto, olha: %s", ale_seixas.getEndereco()));
        ale_seixas.setEndereco("rua do Ale seixas");
        System.out.println(String.format("Ele mudou pra uma rua nova: %s", ale_seixas.getEndereco()));

    }
}