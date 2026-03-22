import model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    // arrays do sistema
    static Clube[] clubes = new Clube[20];
    static int qtdClubes = 0;

    static Campeonato[] campeonatos = new Campeonato[5];
    static int qtdCampeonatos = 0;

    static Grupo[] grupos = new Grupo[5];
    static int qtdGrupos = 0;

    static Participante[] participantes = new Participante[25];
    static int qtdParticipantes = 0;

    static Administrador admin = new Administrador("Admin", "admin@sistema.com", "admin123");

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  SISTEMA DE APOSTAS DE FUTEBOL");
        System.out.println("========================================");

        carregarDadosTeste();

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n1 - Login");
            System.out.println("2 - Cadastrar participante");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");
            opcao = lerInt();

            if (opcao == 1) {
                fazerLogin();
            } else if (opcao == 2) {
                cadastrarParticipante();
            } else if (opcao == 0) {
                System.out.println("Saindo...");
            } else {
                System.out.println("Opcao invalida!");
            }
        }
    }

    static void fazerLogin() {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        // verifica se e admin
        if (admin.autenticar(email, senha)) {
            menuAdmin();
            return;
        }

        // procura participante
        Participante p = null;
        for (int i = 0; i < qtdParticipantes; i++) {
            if (participantes[i].autenticar(email, senha)) {
                p = participantes[i];
                break;
            }
        }

        if (p != null) {
            menuParticipante(p);
        } else {
            System.out.println("Email ou senha incorretos.");
        }
    }

    // ============ MENU ADMIN ============

    static void menuAdmin() {
        System.out.println("Bem-vindo, " + admin.getNome() + "!");
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU ADMINISTRADOR ---");
            System.out.println("1 - Gerenciar clubes");
            System.out.println("2 - Gerenciar campeonatos");
            System.out.println("3 - Gerenciar partidas");
            System.out.println("4 - Ver grupos e classificacoes");
            System.out.println("5 - Listar participantes");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");
            opcao = lerInt();

            if (opcao == 1) {
                menuClubes();
            } else if (opcao == 2) {
                menuCampeonatos();
            } else if (opcao == 3) {
                menuPartidas();
            } else if (opcao == 4) {
                verGrupos();
            } else if (opcao == 5) {
                listarParticipantes();
            } else if (opcao == 0) {
                // sai
            } else {
                System.out.println("Opcao invalida!");
            }
        }
    }

    static void menuClubes() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- CLUBES ---");
            System.out.println("1 - Cadastrar clube");
            System.out.println("2 - Listar clubes");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            opcao = lerInt();

            if (opcao == 1) {
                System.out.print("Nome do clube: ");
                String nome = sc.nextLine();
                System.out.print("Sigla: ");
                String sigla = sc.nextLine();
                clubes[qtdClubes] = new Clube(nome, sigla);
                qtdClubes++;
                System.out.println("Clube cadastrado!");
            } else if (opcao == 2) {
                if (qtdClubes == 0) {
                    System.out.println("Nenhum clube cadastrado.");
                } else {
                    for (int i = 0; i < qtdClubes; i++) {
                        System.out.println((i + 1) + " - " + clubes[i]);
                    }
                }
            } else if (opcao != 0) {
                System.out.println("Opcao invalida!");
            }
        }
    }

    static void menuCampeonatos() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- CAMPEONATOS ---");
            System.out.println("1 - Criar campeonato");
            System.out.println("2 - Adicionar clube ao campeonato");
            System.out.println("3 - Listar campeonatos");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            opcao = lerInt();

            if (opcao == 1) {
                if (qtdCampeonatos >= 5) {
                    System.out.println("Limite de campeonatos atingido.");
                } else {
                    System.out.print("Nome do campeonato: ");
                    String nome = sc.nextLine();
                    campeonatos[qtdCampeonatos] = new Campeonato(nome);
                    qtdCampeonatos++;
                    System.out.println("Campeonato criado!");
                }
            } else if (opcao == 2) {
                if (qtdCampeonatos == 0) {
                    System.out.println("Nenhum campeonato cadastrado.");
                } else if (qtdClubes == 0) {
                    System.out.println("Nenhum clube cadastrado.");
                } else {
                    // escolhe campeonato
                    for (int i = 0; i < qtdCampeonatos; i++) {
                        System.out.println((i + 1) + " - " + campeonatos[i].getNome());
                    }
                    System.out.print("Numero do campeonato: ");
                    int c = lerInt() - 1;
                    if (c < 0 || c >= qtdCampeonatos) {
                        System.out.println("Invalido.");
                    } else {
                        // escolhe clube
                        for (int i = 0; i < qtdClubes; i++) {
                            System.out.println((i + 1) + " - " + clubes[i]);
                        }
                        System.out.print("Numero do clube: ");
                        int cl = lerInt() - 1;
                        if (cl < 0 || cl >= qtdClubes) {
                            System.out.println("Invalido.");
                        } else {
                            boolean ok = campeonatos[c].adicionarClube(clubes[cl]);
                            if (ok) {
                                System.out.println("Clube adicionado!");
                            } else {
                                System.out.println("Nao foi possivel adicionar. Campeonato cheio (max 8) ou clube ja cadastrado.");
                            }
                        }
                    }
                }
            } else if (opcao == 3) {
                if (qtdCampeonatos == 0) {
                    System.out.println("Nenhum campeonato cadastrado.");
                } else {
                    for (int i = 0; i < qtdCampeonatos; i++) {
                        System.out.println((i + 1) + " - " + campeonatos[i]);
                        Clube[] cs = campeonatos[i].getClubes();
                        for (int j = 0; j < cs.length; j++) {
                            System.out.println("   -> " + cs[j]);
                        }
                    }
                }
            } else if (opcao != 0) {
                System.out.println("Opcao invalida!");
            }
        }
    }

    static void menuPartidas() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- PARTIDAS ---");
            System.out.println("1 - Cadastrar partida");
            System.out.println("2 - Listar partidas");
            System.out.println("3 - Registrar resultado");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            opcao = lerInt();

            if (opcao == 1) {
                cadastrarPartida();
            } else if (opcao == 2) {
                listarPartidas();
            } else if (opcao == 3) {
                registrarResultado();
            } else if (opcao != 0) {
                System.out.println("Opcao invalida!");
            }
        }
    }

    static void cadastrarPartida() {
        if (qtdCampeonatos == 0) {
            System.out.println("Nenhum campeonato cadastrado.");
            return;
        }

        // seleciona campeonato
        for (int i = 0; i < qtdCampeonatos; i++) {
            System.out.println((i + 1) + " - " + campeonatos[i].getNome());
        }
        System.out.print("Numero do campeonato: ");
        int c = lerInt() - 1;
        if (c < 0 || c >= qtdCampeonatos) {
            System.out.println("Invalido.");
            return;
        }

        Clube[] cs = campeonatos[c].getClubes();
        if (cs.length < 2) {
            System.out.println("O campeonato precisa ter pelo menos 2 clubes.");
            return;
        }

        for (int i = 0; i < cs.length; i++) {
            System.out.println((i + 1) + " - " + cs[i]);
        }
        System.out.print("Clube mandante: ");
        int m = lerInt() - 1;
        System.out.print("Clube visitante: ");
        int v = lerInt() - 1;

        if (m < 0 || m >= cs.length || v < 0 || v >= cs.length || m == v) {
            System.out.println("Selecao invalida.");
            return;
        }

        System.out.print("Data e hora (dd/MM/yyyy HH:mm): ");
        String dataStr = sc.nextLine();
        LocalDateTime dt = null;
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            dt = LocalDateTime.parse(dataStr, fmt);
        } catch (DateTimeParseException e) {
            System.out.println("Data invalida. Use o formato dd/MM/yyyy HH:mm");
            return;
        }

        Partida partida = new Partida(cs[m], cs[v], dt);
        campeonatos[c].adicionarPartida(partida);
        System.out.println("Partida cadastrada: " + partida);
    }

    static void listarPartidas() {
        boolean temPartida = false;
        for (int c = 0; c < qtdCampeonatos; c++) {
            Partida[] ps = campeonatos[c].getPartidas();
            if (ps.length > 0) {
                temPartida = true;
                System.out.println("\nPartidas de " + campeonatos[c].getNome() + ":");
                for (int i = 0; i < ps.length; i++) {
                    System.out.println("  " + (i + 1) + " - " + ps[i]);
                }
            }
        }
        if (!temPartida) {
            System.out.println("Nenhuma partida cadastrada.");
        }
    }

    static void registrarResultado() {
        if (qtdCampeonatos == 0) {
            System.out.println("Nenhum campeonato cadastrado.");
            return;
        }

        for (int i = 0; i < qtdCampeonatos; i++) {
            System.out.println((i + 1) + " - " + campeonatos[i].getNome());
        }
        System.out.print("Numero do campeonato: ");
        int c = lerInt() - 1;
        if (c < 0 || c >= qtdCampeonatos) {
            System.out.println("Invalido.");
            return;
        }

        Partida[] ps = campeonatos[c].getPartidas();
        if (ps.length == 0) {
            System.out.println("Nenhuma partida neste campeonato.");
            return;
        }

        for (int i = 0; i < ps.length; i++) {
            if (!ps[i].isEncerrada()) {
                System.out.println((i + 1) + " - " + ps[i]);
            }
        }
        System.out.print("Numero da partida: ");
        int idx = lerInt() - 1;
        if (idx < 0 || idx >= ps.length) {
            System.out.println("Invalido.");
            return;
        }
        if (ps[idx].isEncerrada()) {
            System.out.println("Partida ja encerrada.");
            return;
        }

        System.out.print("Gols " + ps[idx].getMandante().getSigla() + ": ");
        int gm = lerInt();
        System.out.print("Gols " + ps[idx].getVisitante().getSigla() + ": ");
        int gv = lerInt();

        Placar placar = new Placar(gm, gv);
        ps[idx].registrarResultado(placar);
        System.out.println("\nResultado registrado: " + ps[idx].getMandante().getSigla() + " " + gm + " x " + gv + " " + ps[idx].getVisitante().getSigla());
        System.out.println("Resultado: " + placar.getResultado());

        // verifica apostas de todos os participantes nessa partida
        System.out.println("\n--- Resultado das apostas ---");
        boolean tevAposta = false;
        for (int i = 0; i < qtdParticipantes; i++) {
            Aposta[] apostas = participantes[i].getApostas();
            for (int j = 0; j < apostas.length; j++) {
                if (apostas[j].getPartida() == ps[idx]) {
                    tevAposta = true;
                    int pts = apostas[j].calcularPontos();
                    String descricao = apostas[j].getDescricaoPalpite();
                    if (pts == 10) {
                        System.out.println("  " + participantes[i].getNome() + " -> " + descricao + " | ACERTOU O PLACAR EXATO! +10 pts");
                    } else if (pts == 5) {
                        System.out.println("  " + participantes[i].getNome() + " -> " + descricao + " | Acertou o resultado! +5 pts");
                    } else {
                        System.out.println("  " + participantes[i].getNome() + " -> " + descricao + " | Nao acertou. 0 pts");
                    }
                }
            }
        }
        if (!tevAposta) {
            System.out.println("  Nenhum participante apostou nessa partida.");
        }
    }

    static void verGrupos() {
        if (qtdGrupos == 0) {
            System.out.println("Nenhum grupo criado.");
            return;
        }
        for (int i = 0; i < qtdGrupos; i++) {
            System.out.println((i + 1) + " - " + grupos[i]);
        }
        System.out.print("Ver classificacao de qual grupo (0 pra voltar): ");
        int idx = lerInt() - 1;
        if (idx >= 0 && idx < qtdGrupos) {
            grupos[idx].exibirClassificacao();
        }
    }

    static void listarParticipantes() {
        if (qtdParticipantes == 0) {
            System.out.println("Nenhum participante.");
            return;
        }
        for (int i = 0; i < qtdParticipantes; i++) {
            System.out.println((i + 1) + " - " + participantes[i].getInfo());
        }
    }

    // ============ MENU PARTICIPANTE ============

    static void menuParticipante(Participante p) {
        System.out.println("Bem-vindo, " + p.getNome() + "!");
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU PARTICIPANTE ---");
            System.out.println("1 - Criar grupo");
            System.out.println("2 - Entrar em grupo");
            System.out.println("3 - Fazer aposta");
            System.out.println("4 - Ver minhas apostas");
            System.out.println("5 - Ver classificacao do grupo");
            System.out.println("6 - Meu perfil");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");
            opcao = lerInt();

            if (opcao == 1) {
                criarGrupo(p);
            } else if (opcao == 2) {
                entrarGrupo(p);
            } else if (opcao == 3) {
                fazerAposta(p);
            } else if (opcao == 4) {
                verApostas(p);
            } else if (opcao == 5) {
                verClassificacao(p);
            } else if (opcao == 6) {
                System.out.println(p.getInfo());
            } else if (opcao == 0) {
                // sai
            } else {
                System.out.println("Opcao invalida!");
            }
        }
    }

    static void criarGrupo(Participante p) {
        // verifica se o participante ja esta em algum grupo
        for (int i = 0; i < qtdGrupos; i++) {
            Participante[] mbs = grupos[i].getParticipantes();
            for (int j = 0; j < mbs.length; j++) {
                if (mbs[j] == p) {
                    System.out.println("Voce ja esta em um grupo!");
                    return;
                }
            }
        }

        if (qtdGrupos >= 5) {
            System.out.println("Limite de grupos atingido (maximo 5).");
            return;
        }
        if (qtdCampeonatos == 0) {
            System.out.println("Nenhum campeonato disponivel.");
            return;
        }

        System.out.print("Nome do grupo: ");
        String nome = sc.nextLine();

        for (int i = 0; i < qtdCampeonatos; i++) {
            System.out.println((i + 1) + " - " + campeonatos[i].getNome());
        }
        System.out.print("Numero do campeonato: ");
        int c = lerInt() - 1;
        if (c < 0 || c >= qtdCampeonatos) {
            System.out.println("Invalido.");
            return;
        }

        Grupo g = new Grupo(nome, campeonatos[c]);
        g.adicionarParticipante(p);
        grupos[qtdGrupos] = g;
        qtdGrupos++;
        System.out.println("Grupo '" + nome + "' criado!");
    }

    static void entrarGrupo(Participante p) {
        // verifica se ja esta em algum grupo
        for (int i = 0; i < qtdGrupos; i++) {
            Participante[] mbs = grupos[i].getParticipantes();
            for (int j = 0; j < mbs.length; j++) {
                if (mbs[j] == p) {
                    System.out.println("Voce ja esta no grupo '" + grupos[i].getNome() + "'.");
                    return;
                }
            }
        }

        if (qtdGrupos == 0) {
            System.out.println("Nenhum grupo disponivel.");
            return;
        }

        for (int i = 0; i < qtdGrupos; i++) {
            System.out.println((i + 1) + " - " + grupos[i]);
        }
        System.out.print("Numero do grupo (0 pra cancelar): ");
        int idx = lerInt() - 1;
        if (idx < 0 || idx >= qtdGrupos) {
            return;
        }

        boolean ok = grupos[idx].adicionarParticipante(p);
        if (ok) {
            System.out.println("Voce entrou no grupo '" + grupos[idx].getNome() + "'!");
        } else {
            System.out.println("Nao foi possivel entrar. Grupo cheio (max 5).");
        }
    }

    static void fazerAposta(Participante p) {
        if (qtdCampeonatos == 0) {
            System.out.println("Nenhum campeonato disponivel.");
            return;
        }

        for (int i = 0; i < qtdCampeonatos; i++) {
            System.out.println((i + 1) + " - " + campeonatos[i].getNome());
        }
        System.out.print("Numero do campeonato: ");
        int c = lerInt() - 1;
        if (c < 0 || c >= qtdCampeonatos) {
            System.out.println("Invalido.");
            return;
        }

        Partida[] ps = campeonatos[c].getPartidas();
        if (ps.length == 0) {
            System.out.println("Nenhuma partida neste campeonato.");
            return;
        }

        // mostra so as partidas disponiveis para aposta
        System.out.println("Partidas disponiveis:");
        int cont = 0;
        for (int i = 0; i < ps.length; i++) {
            if (!ps[i].isEncerrada() && ps[i].apostasAbertas() && !p.jaApostouPartida(ps[i])) {
                System.out.println((i + 1) + " - " + ps[i]);
                cont++;
            }
        }
        if (cont == 0) {
            System.out.println("Nao ha partidas disponiveis para aposta.");
            return;
        }

        System.out.print("Numero da partida (0 pra cancelar): ");
        int idx = lerInt() - 1;
        if (idx < 0 || idx >= ps.length) {
            return;
        }

        Partida partida = ps[idx];
        if (partida.isEncerrada() || !partida.apostasAbertas()) {
            System.out.println("Apostas encerradas para essa partida.");
            return;
        }
        if (p.jaApostouPartida(partida)) {
            System.out.println("Voce ja apostou nessa partida.");
            return;
        }

        System.out.println("Tipo de aposta:");
        System.out.println("1 - Apenas resultado (5 pts)");
        System.out.println("2 - Placar exato (10 pts / 5 pts)");
        System.out.print("Opcao: ");
        int tipo = lerInt();

        // escolhe o resultado
        System.out.println("Resultado previsto:");
        System.out.println("1 - " + partida.getMandante().getNome() + " vence");
        System.out.println("2 - Empate");
        System.out.println("3 - " + partida.getVisitante().getNome() + " vence");
        System.out.print("Opcao: ");
        int resOpc = lerInt();

        Resultado resultado;
        if (resOpc == 1) {
            resultado = Resultado.MANDANTE;
        } else if (resOpc == 2) {
            resultado = Resultado.EMPATE;
        } else if (resOpc == 3) {
            resultado = Resultado.VISITANTE;
        } else {
            System.out.println("Opcao invalida.");
            return;
        }

        if (tipo == 1) {
            ApostaResultado aposta = new ApostaResultado(p, partida, resultado);
            p.adicionarAposta(aposta);
            System.out.println("Aposta registrada: " + aposta);
        } else if (tipo == 2) {
            System.out.print("Gols " + partida.getMandante().getSigla() + ": ");
            int gm = lerInt();
            System.out.print("Gols " + partida.getVisitante().getSigla() + ": ");
            int gv = lerInt();
            Placar placar = new Placar(gm, gv);
            ApostaPlacar aposta = new ApostaPlacar(p, partida, placar);
            p.adicionarAposta(aposta);
            System.out.println("Aposta registrada: " + aposta);
        } else {
            System.out.println("Tipo invalido.");
        }
    }

    static void verApostas(Participante p) {
        Aposta[] apostas = p.getApostas();
        if (apostas.length == 0) {
            System.out.println("Nenhuma aposta registrada.");
            return;
        }
        System.out.println("\nApostas de " + p.getNome() + ":");
        for (int i = 0; i < apostas.length; i++) {
            System.out.println("  " + (i + 1) + " - " + apostas[i]);
        }
        System.out.println("Total de pontos: " + p.getTotalPontos());
    }

    static void verClassificacao(Participante p) {
        boolean achou = false;
        for (int i = 0; i < qtdGrupos; i++) {
            Participante[] mbs = grupos[i].getParticipantes();
            for (int j = 0; j < mbs.length; j++) {
                if (mbs[j] == p) {
                    grupos[i].exibirClassificacao();
                    achou = true;
                }
            }
        }
        if (!achou) {
            System.out.println("Voce nao esta em nenhum grupo.");
        }
    }

    // ============ CADASTRO PARTICIPANTE ============

    static void cadastrarParticipante() {
        if (qtdParticipantes >= 25) {
            System.out.println("Limite de participantes atingido.");
            return;
        }
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        // verifica email duplicado
        for (int i = 0; i < qtdParticipantes; i++) {
            if (participantes[i].getEmail().equals(email)) {
                System.out.println("Email ja cadastrado.");
                return;
            }
        }

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        participantes[qtdParticipantes] = new Participante(nome, email, senha);
        qtdParticipantes++;
        System.out.println("Participante cadastrado com sucesso!");
    }

    // ============ UTILITARIOS ============

    static int lerInt() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // carrega dados de exemplo para facilitar o teste
    static void carregarDadosTeste() {
        clubes[qtdClubes++] = new Clube("Flamengo",   "FLA");
        clubes[qtdClubes++] = new Clube("Palmeiras",  "PAL");
        clubes[qtdClubes++] = new Clube("Gremio",     "GRE");
        clubes[qtdClubes++] = new Clube("Corinthians","COR");

        Campeonato camp = new Campeonato("Brasileirao 2025");
        camp.adicionarClube(clubes[0]);
        camp.adicionarClube(clubes[1]);
        camp.adicionarClube(clubes[2]);
        camp.adicionarClube(clubes[3]);
        campeonatos[qtdCampeonatos++] = camp;

        // partida ja encerrada
        Partida p3 = new Partida(clubes[0], clubes[2], LocalDateTime.now().minusDays(2));
        p3.registrarResultado(new Placar(2, 1));

        // partidas futuras
        Partida p1 = new Partida(clubes[0], clubes[1], LocalDateTime.now().plusDays(3));
        Partida p2 = new Partida(clubes[2], clubes[3], LocalDateTime.now().plusDays(5));

        camp.adicionarPartida(p3);
        camp.adicionarPartida(p1);
        camp.adicionarPartida(p2);

        // participantes de teste
        Participante lorena = new Participante("Lorena Coleto", "lorena@email.com", "123");
        Participante arthur = new Participante("Arthur Lopes",  "arthur@email.com", "123");
        participantes[qtdParticipantes++] = lorena;
        participantes[qtdParticipantes++] = arthur;

        // apostas na partida ja encerrada
        lorena.adicionarAposta(new ApostaPlacar(lorena, p3, new Placar(2, 1)));         // 10 pts
        arthur.adicionarAposta(new ApostaResultado(arthur, p3, Resultado.MANDANTE));    // 5 pts

        // grupo de exemplo
        Grupo g = new Grupo("Turma A", camp);
        g.adicionarParticipante(lorena);
        g.adicionarParticipante(arthur);
        grupos[qtdGrupos++] = g;

        System.out.println("Dados de teste carregados.");
        System.out.println("  admin  -> admin@sistema.com / admin123");
        System.out.println("  lorena -> lorena@email.com / 123");
        System.out.println("  arthur -> arthur@email.com / 123");
    }
}
