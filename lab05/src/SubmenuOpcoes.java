package lab05.src;

public enum SubmenuOpcoes {
    CADASTRAR_CLIENTE("Cadastrar cliente"),
	CADASTRAR_VEICULO("Cadastrar veiculo"),
	CADASTRAR_SEGURADORA("Cadastrar seguradora"),
	CADASTRAR_FROTA("Cadastrar frota"),
	CADASTRAR_CONDUTOR("Cadastrar condutor"),
	CADASTRAR_SEGURO("Cadastrar seguro"),

	LISTAR_CLIENTES("Listar cliente"),
	LISTAR_SEGUROS("Listar seguros"),
	LISTAR_SINISTROS_SEGURADORA("Listar sinistros da seguradora"),
	LISTAR_SINISTROS_SEGURO("Listar sinistros do seguro"),
	LISTAR_SINISTROS_CLIENTE("Listar sinistros do cliente"),
	LISTAR_SINISTROS_CONDUTOR("Listar sinistros do condutor"),
	LISTAR_VEICULOS_SEGURADORA("Listar veiculos da seguradora"),
	LISTAR_VEICULOS_CLIENTE("Listar veiculos do cliente"),
	LISTAR_CONDUTORES_SEGURO("Listar condutores do seguros"),
	LISTAR_SEGURADORAS("Listar seguradoras"),

	EXCLUIR_CLIENTE("Excluir cliente"),
	EXCLUIR_VEICULO("Excluir veiculo"),
	EXCLUIR_SEGURADORA("Excluir seguradora"),
	EXCLUIR_FROTA("Excluir frota"),
	EXCLUIR_CONDUTOR("Excluir condutor"),
	EXCLUIR_SINISTRO("Excluir sininstro"),
	VOLTAR("Voltar");

    private final String descricao;

    SubmenuOpcoes(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
