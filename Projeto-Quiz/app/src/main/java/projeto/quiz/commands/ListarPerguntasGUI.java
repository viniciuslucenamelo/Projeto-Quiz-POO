package projeto.quiz.commands;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import projeto.quiz.Refatorado.Exception.ListaVaziaException;
import projeto.quiz.domain.Alternativa;
import projeto.quiz.domain.Pergunta;
import projeto.quiz.service.PerguntaService;
import projeto.quiz.repository.PerguntaRepository;

public class ListarPerguntasGUI extends JFrame {

    public ListarPerguntasGUI() {
        setTitle("Lista de Perguntas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane);

        try {
            listarPerguntasSemRespostas(textArea);
        } catch (ListaVaziaException e) {
            textArea.append(e.getMessage());
        }

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void listarPerguntasSemRespostas(JTextArea textArea) throws ListaVaziaException {
        PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());
        List<Pergunta> perguntas = perguntaService.getPerguntas();

        if (perguntas.isEmpty()) {
            throw new ListaVaziaException("Não há perguntas para listar.");
        }

        textArea.append("Lista de Perguntas:\n");

        for (Pergunta pergunta : perguntas) {
            textArea.append("Título: " + pergunta.getTitulo() + "\n");
            textArea.append("Área do Conhecimento: " + pergunta.getAreaDoConhecimento() + "\n");
            textArea.append("Alternativas:\n");

            for (Alternativa alternativa : pergunta.getAlternativas()) {
                textArea.append("Opção: " + alternativa.getOpcao() + "\n");
                textArea.append("Afirmativa: " + alternativa.getAfirmativa() + "\n");
            }

            textArea.append("\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ListarPerguntasGUI());
    }
}
