package projeto.quiz.commands;

import javax.swing.*;
import projeto.quiz.service.PerguntaService;
import projeto.quiz.repository.PerguntaRepository;
import projeto.quiz.Refatorado.Exception.ListaVaziaException;
import projeto.quiz.domain.Pergunta;

import java.util.List;

public class RemoverPerguntaGUI implements Commands {

    private JFrame frame;

    public RemoverPerguntaGUI(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void execute() {
        try {
            removerPergunta();
        } catch (ListaVaziaException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage());
        }
    }

    private void removerPergunta() throws ListaVaziaException {
        PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());
        List<Pergunta> perguntas = perguntaService.getPerguntas();

        if (perguntas.isEmpty()) {
            throw new ListaVaziaException("Não há perguntas para apagar.");
        }

        String[] opcoes = new String[perguntas.size()];

        // Preencher opcoes com os títulos das perguntas disponíveis
        for (int i = 0; i < perguntas.size(); i++) {
            opcoes[i] = perguntas.get(i).getTitulo();
        }

        // Mostrar diálogo de seleção de pergunta
        String escolha = (String) JOptionPane.showInputDialog(frame,
                "Escolha a pergunta para remover:", "Remover Pergunta",
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        // Verificar se o usuário fez uma escolha válida
        if (escolha != null) {
            for (Pergunta pergunta : perguntas) {
                if (pergunta.getTitulo().equals(escolha)) {
                    // Remover pergunta usando o serviço
                    perguntaService.remover(pergunta);

                    JOptionPane.showMessageDialog(frame, "Pergunta removida com sucesso.");
                    return;
                }
            }
        }

        // Se chegou aqui, o usuário cancelou ou ocorreu algum erro
        JOptionPane.showMessageDialog(frame, "Remoção de pergunta cancelada ou ocorreu um erro.");
    }
}
