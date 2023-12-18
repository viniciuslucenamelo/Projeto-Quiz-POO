package projeto.quiz.service;
import projeto.quiz.Refatorado.Exception.ListaVaziaException;
import projeto.quiz.Refatorado.Exception.RespostaNaoEncontradaException;

import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

import projeto.quiz.domain.Pergunta;
import projeto.quiz.domain.RandomNum;
import projeto.quiz.domain.Alternativa;
import projeto.quiz.repository.PerguntaRepository;

public class PerguntaManager {
    private final PerguntaRepository repository;
    private final Map<String, Integer> placar;  

    public PerguntaManager(PerguntaRepository repository) {
        this.repository = repository;
        this.placar = new HashMap<>();
    }

    public void jogar(String nomeJogador) throws ListaVaziaException {

        List<Pergunta> perguntas = repository.getAll();

        if (perguntas.isEmpty()) {
            System.out.println("Não há perguntas disponíveis para jogar.");
            return; // Retorna ao menu
        }

        Scanner inputScanner = new Scanner(System.in);
        String nomeUsuario;

        do {
            System.out.print("Digite o seu nome (ou digite 0 para voltar ao menu): ");
            nomeUsuario = inputScanner.nextLine();

            if (nomeUsuario.equals("0")) {
                return; // Retorna ao menu
            } else if (nomeUsuario.isEmpty()) {
                System.out.println("Por favor, digite um nome válido.");
            }
        } while (nomeUsuario.isEmpty());
        System.out.println("Iniciando o jogo!");
        System.out.println();
    
        int pontuacao = 0;
    
        for (Pergunta pergunta : perguntas) {
            System.out.println("Pergunta: " + pergunta.getTitulo());
            System.out.println("Área do Conhecimento: " + pergunta.getAreaDoConhecimento());
            System.out.println("Escolha a opção correta:");
    
            List<Alternativa> alternativas = pergunta.getAlternativas();
            for (Alternativa alternativa : alternativas) {
                System.out.println(alternativa.getOpcao() + ". " + alternativa.getAfirmativa());
            }
    
            Scanner scanner = new Scanner(System.in);
            System.out.print("Sua resposta: ");
            String respostaUsuario = scanner.nextLine();
    
            if (pergunta.verificarResposta(respostaUsuario)) {
                System.out.println("Resposta correta! Pontuação +1");
                pontuacao++;
            } else {
                try {
                    System.out.println("Resposta incorreta. A resposta correta era: " + pergunta.obterRespostaCorreta());
                } catch (RespostaNaoEncontradaException ex) {
                    System.out.println("Erro ao obter a resposta correta: " + ex.getMessage());
                }
            }
    
            System.out.println();
        }
    
        System.out.println("Fim do jogo! Pontuação final: " + pontuacao);

        placar.put(nomeJogador, pontuacao);
    }

    public void exibirPlacar() {
        if (placar.isEmpty()) {
            System.out.println("Não há placar registrado.");
        } else {
            System.out.println("Placar dos jogos anteriores:");
            for (Map.Entry<String, Integer> entry : placar.entrySet()) {
                System.out.println("Nome: " + entry.getKey() + ", Pontuação: " + entry.getValue());
            }
        }
    }

    public void adicionarPergunta() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println(); // para formatação
        System.out.println("Insira o título da pergunta (ou 0 para voltar ao menu): ");
        String titulo = scanner.nextLine();
        
        if (titulo.equals("0")) {
            return; // Volta para o menu
        }
        
        while (titulo.trim().isEmpty()) {
            System.out.println("Título não pode estar em branco!");
            System.out.println("Insira o título da pergunta (ou 0 para voltar ao menu): ");
            titulo = scanner.nextLine();
            
            if (titulo.equals("0")) {
                return; // Volta para o menu
            }
        }
    
        System.out.println("Área do conhecimento da pergunta: ");
        String areaDoConhecimento = scanner.nextLine();
    
        while (areaDoConhecimento.trim().isEmpty()) {
            System.out.println("Área do conhecimento não pode estar em branco!");
            System.out.println("Insira a área do conhecimento da pergunta (ou 0 para voltar ao menu): ");
            areaDoConhecimento = scanner.nextLine();
            
            if (areaDoConhecimento.equals("0")) {
                return; // Volta para o menu
            }
        }
    
        Pergunta pergunta = new Pergunta(titulo, areaDoConhecimento);
    
        String opcao, afirmativa;
        boolean opcaoCorreta;
    
        for (int i = 1; i <= 4; i++) {
            System.out.println(); // para formatação
            System.out.println("Insira a opção da pergunta " + i + ": ");
            opcao = scanner.nextLine();
            
            while (opcao.trim().isEmpty()) {
                System.out.println("Opção não pode estar em branco!");
                System.out.println("Insira a opção da pergunta " + i + ": ");
                opcao = scanner.nextLine();
            }
    
            System.out.println("Insira a afirmativa para a opção " + i + ": ");
            afirmativa = scanner.nextLine();
            
            while (afirmativa.trim().isEmpty()) {
                System.out.println("Afirmativa não pode estar em branco!");
                System.out.println("Insira a afirmativa para a opção " + i + ": ");
                afirmativa = scanner.nextLine();
            }
    
            System.out.print("Essa é a opção correta? (true/false): "); 
            opcaoCorreta = Boolean.parseBoolean(scanner.nextLine());
    
            // criando instância de alternativa
            Alternativa alternativa = new Alternativa(opcao, afirmativa, opcaoCorreta);
    
            // adicionando a alternativa à pergunta
            pergunta.adicionarAlternativa(alternativa);
        }
    
        // Adiciona a pergunta ao repositório
        repository.add(pergunta);
    
        System.out.println("Pergunta adicionada com sucesso!");
    }
    

        public void listarPerguntas() {
            List<Pergunta> perguntas = repository.getAll();

            if (perguntas.isEmpty()) {
                System.out.println("Não há perguntas disponíveis.");
            } else {
                System.out.println("Lista de Perguntas:");
                for (int i = 0; i < perguntas.size(); i++) {
                    Pergunta pergunta = perguntas.get(i);
                    System.out.println(i + ". Título: " + pergunta.getTitulo());
                    System.out.println("   Área do Conhecimento: " + pergunta.getAreaDoConhecimento());
                }
            }
    }

    public void removerPergunta() {
        listarPerguntas();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o número da pergunta que deseja remover: ");
        int escolha = scanner.nextInt();

        List<Pergunta> perguntas = repository.getAll();

        if (escolha >= 0 && escolha < perguntas.size()) {
            Pergunta perguntaRemovida = perguntas.get(escolha);
            repository.remove(perguntaRemovida);
            System.out.println("Pergunta removida com sucesso!");
        } else {
            System.out.println("Escolha inválida. A pergunta não foi removida.");
        }
    }

    public void editarPergunta() throws ListaVaziaException {

    PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());
    List<Pergunta> perguntas = perguntaService.getPerguntas();

    if (perguntas.isEmpty()) {
        throw new ListaVaziaException("Não há perguntas para editar.");
    }

    Scanner sc = new Scanner(System.in);

    System.out.println(); // ajudar formatação

    // Listar os títulos das perguntas disponíveis
    for (int i = 0; i < perguntas.size(); i++) {
        System.out.println(i + ". Título: " + perguntas.get(i).getTitulo());
    }

    System.out.println(); // ajudar formatação
    System.out.print("Digite o número da pergunta que deseja editar: ");
    int escolhaEdit = sc.nextInt();
    sc.nextLine();

    // Verificar se a escolha do usuário está dentro dos limites
    if (escolhaEdit >= 0 && escolhaEdit < perguntas.size()) {
        System.out.println(); // ajudar formatação
        System.out.print("Digite o novo título (ou Enter para manter o atual): ");
        String novoTitulo = sc.nextLine();

        if (!novoTitulo.trim().isEmpty()) {
            perguntas.get(escolhaEdit).setTitulo(novoTitulo);
        }

        System.out.println(); // ajudar formatação
        System.out.print("Digite a nova área do conhecimento (ou Enter para manter a atual): ");
        String novaAreaDoConhecimento = sc.nextLine();

        if (!novaAreaDoConhecimento.trim().isEmpty()) {
            perguntas.get(escolhaEdit).setAreaDoConhecimento(novaAreaDoConhecimento);
        }

        // Editar as alternativas
        List<Alternativa> alternativas = perguntas.get(escolhaEdit).getAlternativas();

        for (int i = 0; i < alternativas.size(); i++) {
            System.out.println(); // ajudar formatação
            Alternativa alternativa = alternativas.get(i);
            System.out.println("Editar Alternativa " + (i + 1));

            // Editar a opção
            System.out.print("Nova opção (ou Enter para manter a atual): ");
            String novaOpcao = sc.nextLine();
            if (!novaOpcao.trim().isEmpty()) {
                alternativa.setOpcao(novaOpcao);
            }

            // Editar a afirmativa
            System.out.print("Nova afirmativa (ou Enter para manter a atual): ");
            String novaAfirmativa = sc.nextLine();
            if (!novaAfirmativa.trim().isEmpty()) {
                alternativa.setAfirmativa(novaAfirmativa);
            }

            // Editar se é a opção correta
            System.out.print("É a opção correta? (true/false) (ou Enter para manter a atual): ");
            String respostaCorreta = sc.nextLine();
            if (!respostaCorreta.trim().isEmpty()) {
                boolean novaOpcaoCorreta = Boolean.parseBoolean(respostaCorreta);
                alternativa.setOpcaoCorreta(novaOpcaoCorreta);
            }
        }

        System.out.println("Pergunta editada com sucesso.");
    } else {
        System.out.println("Escolha inválida. A pergunta não foi editada.");
    }
}}

