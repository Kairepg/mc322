package lab05.src;

public enum MenuOpcoes {
    CADASTROS("Cadastros", new SubmenuOpcoes[] {
            SubmenuOpcoes.CADASTRAR_CLIENTE,
            SubmenuOpcoes.CADASTRAR_VEICULO,
            SubmenuOpcoes.CADASTRAR_SEGURADORA,
            SubmenuOpcoes.CADASTRAR_FROTA,
            SubmenuOpcoes.CADASTRAR_CONDUTOR,
            SubmenuOpcoes.CADASTRAR_SEGURO,
            SubmenuOpcoes.VOLTAR
    }),
    LISTAR("Listar", new SubmenuOpcoes[] {
            SubmenuOpcoes.LISTAR_CLIENTES,
            SubmenuOpcoes.LISTAR_SEGUROS,
            SubmenuOpcoes.LISTAR_SINISTROS_SEGURADORA,
            SubmenuOpcoes.LISTAR_SINISTROS_SEGURO,
            SubmenuOpcoes.LISTAR_SINISTROS_CLIENTE,
            SubmenuOpcoes.LISTAR_SINISTROS_CONDUTOR,
            SubmenuOpcoes.LISTAR_VEICULOS_SEGURADORA,
            SubmenuOpcoes.LISTAR_VEICULOS_CLIENTE,
            SubmenuOpcoes.LISTAR_CONDUTORES_SEGURO,
            SubmenuOpcoes.LISTAR_SEGURADORAS,
            SubmenuOpcoes.VOLTAR
    }),
    EXCLUIR("Excluir", new SubmenuOpcoes[] {
            SubmenuOpcoes.EXCLUIR_CLIENTE,
            SubmenuOpcoes.EXCLUIR_VEICULO,
            SubmenuOpcoes.EXCLUIR_SEGURADORA,
            SubmenuOpcoes.EXCLUIR_FROTA,
            SubmenuOpcoes.EXCLUIR_CONDUTOR,
            SubmenuOpcoes.EXCLUIR_SINISTRO,
            SubmenuOpcoes.VOLTAR}),
    GERAR_SINISTRO("Gerar Sinistro", new SubmenuOpcoes[] {SubmenuOpcoes.VOLTAR}),
    CALCULAR_RECEITA("Calcular Receita", new SubmenuOpcoes[] {SubmenuOpcoes.VOLTAR}),
    SAIR("Sair", new SubmenuOpcoes[] {});

    private final String descricao;
    private final SubmenuOpcoes[] submenu;

    MenuOpcoes(String descricao, SubmenuOpcoes[] submenu) {
        this.descricao = descricao;
        this.submenu = submenu;
    }

    public String getDescricao() {
        return descricao;
    }

    public SubmenuOpcoes[] getSubmenu() {
        return submenu;
    }
}
