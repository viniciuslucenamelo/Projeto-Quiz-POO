package projeto.quiz.commands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import projeto.quiz.Refatorado.Exception.ListaVaziaException;
import projeto.quiz.domain.Pergunta;
import projeto.quiz.service.PerguntaService;
import projeto.quiz.repository.PerguntaRepository;

public class RemoverPerguntaGUI extends JFrame {

    private PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());
    private JList<Pergunta> perguntaList;

    public RemoverPerguntaGUI() {
        setTitle("Remover Pergunta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        try {
            // Listar as perguntas disponíveis
            List<Pergunta> perguntas = perguntaService.getPerguntas();

            if (perguntas.isEmpty()) {
                throw new ListaVaziaException("Não há perguntas para remover.");
            }
            

            DefaultListModel<Pergunta> perguntaListModel = new DefaultListModel<>();
            for (Pergunta pergunta : perguntas) {
                perguntaListModel.addElement(pergunta);
            }

            perguntaList = new JList<>(perguntaListModel);
            perguntaList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            JScrollPane scrollPane = new JScrollPane(perguntaList);
            add(scrollPane, BorderLayout.CENTER);

            JButton removerButton = new JButton("Remover Pergunta");
            removerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removerPerguntaSelecionada();
                }
            });

            JButton voltarButton = new JButton("Voltar");
            voltarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(removerButton);
            buttonPanel.add(voltarButton);

            add(buttonPanel, BorderLayout.SOUTH);

        } catch (ListaVaziaException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            dispose();
        }
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void removerPerguntaSelecionada() {
        int selectedIndex = perguntaList.getSelectedIndex();
        if (selectedIndex != -1) {
            Pergunta perguntaSelecionada = perguntaList.getSelectedValue();
            try {
                perguntaService.remover(perguntaSelecionada);
                JOptionPane.showMessageDialog(null, "Pergunta removida com sucesso.");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao remover a pergunta: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma pergunta para remover.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RemoverPerguntaGUI());
    }
}
