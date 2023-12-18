package projeto.quiz.commands;

import projeto.quiz.Refatorado.Exception.ListaVaziaException;
import projeto.quiz.service.PerguntaService;
import projeto.quiz.repository.PerguntaRepository;
import projeto.quiz.domain.Alternativa;
import projeto.quiz.domain.Pergunta;

import java.util.List;

public class ListarPerguntas implements Commands {

    private List<Pergunta> perguntas;

    @Override
    public void execute() {
        try {
            // Atualiza a lista de perguntas antes de listar
            PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());
            perguntas = perguntaService.getPerguntas();
            listarPerguntasSemRespostas();
        } catch (ListaVaziaException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listarPerguntasSemRespostas() throws ListaVaziaException {
        if (perguntas.isEmpty()) {
            throw new ListaVaziaException("Não há perguntas para listar.");
        }

        System.out.println("Lista de Perguntas:");

        for (Pergunta pergunta : perguntas) {
            System.out.println("Título: " + pergunta.getTitulo());
            System.out.println("Área do Conhecimento: " + pergunta.getAreaDoConhecimento());
            System.out.println("Alternativas:");

            for (Alternativa alternativa : pergunta.getAlternativas()) {
                System.out.println("Opção: " + alternativa.getOpcao());
                System.out.println("Afirmativa: " + alternativa.getAfirmativa());
            }

            System.out.println();
        }
    }

    // Adicione este método para obter a lista de perguntas
    public List<Pergunta> getPerguntas() {
        return perguntas;
    }
}
