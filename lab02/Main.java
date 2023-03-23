public class Main {
    public static void main(String[] args){
        Cliente ale_seixas = new Cliente("Alê Seixas", "464.177.428-57", "15/08/2003", 19, "rua do limão");
        if (ale_seixas.validarCPF(ale_seixas.getCPF())) {
            System.out.println("ale seixas eh real");
        } else {
            System.out.println("ale seixas eh falso");
        }
        System.out.println(ale_seixas);

        //ale seixas mudou de endereco
        System.out.println(ale_seixas.getEndereco());
        ale_seixas.setEndereco("rua do Ale seixas");
        System.out.println(ale_seixas.getEndereco());
    }
}