package lab05.src;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    public static void exibirMenuExterno() {
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

    public static MenuOpcoes lerOpcaoMenuExterno() {
		int opUsuario;
		MenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = Integer.parseInt(scanner.nextLine());
		}while(opUsuario < 0 || opUsuario > MenuOpcoes.values().length - 1);
		opUsuarioConst = MenuOpcoes.values()[opUsuario];
        
		return opUsuarioConst;
	}
	
	private static SubmenuOpcoes lerOpcaoSubmenu(MenuOpcoes op) {
		int opUsuario;
		SubmenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = Integer.parseInt(scanner.nextLine());
		}while(opUsuario < 0 || opUsuario > op.getSubmenu().length - 1);
		opUsuarioConst = op.getSubmenu()[opUsuario];
        
		return opUsuarioConst;
	}
	
	public static void executarOpcaoMenuExterno(ArrayList<Seguradora> listaSeguradoras, MenuOpcoes op) {
        Seguradora seguradora;
		switch(op) {
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				executarSubmenu(listaSeguradoras, op);
				break;

			case GERAR_SINISTRO:
                seguradora = MenuAuxiliar.conferirSeguradora(listaSeguradoras);
                if(seguradora == null) {
                    System.out.println("A seguradora digitada não existe");
                    break;
                }
                MenuAuxiliar.gerarSinistro(seguradora);
				break;


			case CALCULAR_RECEITA:
                seguradora = MenuAuxiliar.conferirSeguradora(listaSeguradoras);
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
        Seguradora seguradora;

		switch(opSubmenu) {
		case CADASTRAR_CLIENTE:
            seguradora = MenuAuxiliar.conferirSeguradora(listaSeguradoras);
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            MenuAuxiliar.cadastrarCliente(seguradora);
			break;
            
		case CADASTRAR_VEICULO:
            seguradora = MenuAuxiliar.conferirSeguradora(listaSeguradoras);
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            MenuAuxiliar.cadastrarVeiculo(seguradora);
			break;
          

		case CADASTRAR_SEGURADORA:
            MenuAuxiliar.cadastrarSeguradora(listaSeguradoras);
			break;

        case CADASTRAR_FROTA:
            seguradora = MenuAuxiliar.conferirSeguradora(listaSeguradoras);
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            MenuAuxiliar.cadastrarFrota(seguradora);
            break;

        case CADASTRAR_CONDUTOR:
            seguradora = MenuAuxiliar.conferirSeguradora(listaSeguradoras);
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            MenuAuxiliar.cadastrarCondutor(seguradora);
            break;

        case CADASTRAR_SEGURO:
            seguradora = MenuAuxiliar.conferirSeguradora(listaSeguradoras);
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            MenuAuxiliar.cadastrarSeguro(seguradora);
            break;
            
        
		case LISTAR_CLIENTES:
            seguradora = MenuAuxiliar.conferirSeguradora(listaSeguradoras);
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            MenuAuxiliar.listarClientes(seguradora);
			break;

        case LISTAR_SEGUROS:
            seguradora = MenuAuxiliar.conferirSeguradora(listaSeguradoras);
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            MenuAuxiliar.listarSeguros(seguradora);
			break;

		case LISTAR_SINISTROS_SEGURADORA:
            seguradora = MenuAuxiliar.conferirSeguradora(listaSeguradoras);
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            MenuAuxiliar.listarSinistrosSeguradora(seguradora);
            break;

        case LISTAR_SINISTROS_SEGURO:
            seguradora = MenuAuxiliar.conferirSeguradora(listaSeguradoras);
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            MenuAuxiliar.listarSinistrosSeguro(seguradora);
            break;

        case LISTAR_SINISTROS_CLIENTE:
            seguradora = MenuAuxiliar.conferirSeguradora(listaSeguradoras);
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            MenuAuxiliar.listarSinistrosCliente(seguradora);
            break;

        case LISTAR_SINISTROS_CONDUTOR:
            seguradora = MenuAuxiliar.conferirSeguradora(listaSeguradoras);
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            MenuAuxiliar.listarSinistrosCondutor(seguradora);
            break;

		case LISTAR_VEICULOS_SEGURADORA:
            seguradora = MenuAuxiliar.conferirSeguradora(listaSeguradoras);
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            MenuAuxiliar.listarVeiculosSeguradora(seguradora);
            break;

        case LISTAR_VEICULOS_CLIENTE:
            seguradora = MenuAuxiliar.conferirSeguradora(listaSeguradoras);
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            MenuAuxiliar.listarVeiculosCliente(seguradora);
            break;

        case LISTAR_CONDUTORES_SEGURO:
            seguradora = MenuAuxiliar.conferirSeguradora(listaSeguradoras);
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            MenuAuxiliar.listarCondutoresSeguro(seguradora);
            break;

        case LISTAR_SEGURADORAS:
            MenuAuxiliar.listarSeguradoras(listaSeguradoras);
            break;

		case EXCLUIR_CLIENTE:
            seguradora = MenuAuxiliar.conferirSeguradora(listaSeguradoras);
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            MenuAuxiliar.excluirCliente(seguradora);
            break;

		case EXCLUIR_VEICULO:
            seguradora = MenuAuxiliar.conferirSeguradora(listaSeguradoras);
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            MenuAuxiliar.excluirVeiculo(seguradora);
            break;

        case EXCLUIR_SEGURADORA:
            MenuAuxiliar.excluirSeguradora(listaSeguradoras);
            break;

        case EXCLUIR_FROTA:
            seguradora = MenuAuxiliar.conferirSeguradora(listaSeguradoras);
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            MenuAuxiliar.excluirFrota(seguradora);
            break;

        case EXCLUIR_CONDUTOR:
            seguradora = MenuAuxiliar.conferirSeguradora(listaSeguradoras);
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            MenuAuxiliar.excluirCondutor(seguradora);
            break;
            
		case EXCLUIR_SINISTRO:
            seguradora = MenuAuxiliar.conferirSeguradora(listaSeguradoras);
            if(seguradora == null) {
                System.out.println("A seguradora digitada não existe");
                break;
            }
            MenuAuxiliar.excluirSinistro(seguradora);
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
}