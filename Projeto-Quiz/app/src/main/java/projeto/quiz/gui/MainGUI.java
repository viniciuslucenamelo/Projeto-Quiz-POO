package projeto.quiz.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import projeto.quiz.Refatorado.Exception.ListaVaziaException;
import projeto.quiz.commands.AdicionarPerguntaGUI;
import projeto.quiz.commands.EditarPerguntaGUI;
import projeto.quiz.commands.ListarPerguntasGUI;
import projeto.quiz.commands.RemoverPerguntaGUI;
import projeto.quiz.repository.PerguntaRepository;
import projeto.quiz.service.PerguntaManager;

public class MainGUI extends JFrame {

    private PerguntaManager perguntaManager = new PerguntaManager(PerguntaRepository.getInstance());

    public MainGUI() {
        setTitle("QUIZ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(0x4682B4));

        setLayout(new GridBagLayout());
        setSize(720, 576);
        setResizable(false);

        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); 


        Dimension buttonSize = new Dimension(200, 40);

        JButton jogarButton = new JButton("Jogar");
        jogarButton.setPreferredSize(buttonSize);
        add(jogarButton, gbc);

        gbc.gridy++;
        JButton adicionarButton = new JButton("Adicionar Pergunta");
        adicionarButton.setPreferredSize(buttonSize);
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdicionarPerguntaGUI();
            }
        });
        add(adicionarButton, gbc);

        gbc.gridy++;
        JButton removerButton = new JButton("Remover Pergunta");
        removerButton.setPreferredSize(buttonSize);
        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemoverPerguntaGUI();
            }
        });
        add(removerButton, gbc);

        gbc.gridy++;
        JButton editarPerguntaButton = new JButton("Editar Pergunta");
        editarPerguntaButton.setPreferredSize(buttonSize);
        editarPerguntaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditarPerguntaGUI().setVisible(true);
            }
        });
        add(editarPerguntaButton, gbc);

        gbc.gridy++;
        JButton listarButton = new JButton("Listar Perguntas");
        listarButton.setPreferredSize(buttonSize);
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarPerguntasGUI listarPerguntasGUI = new ListarPerguntasGUI();
                listarPerguntasGUI.setVisible(true);
            }
        });
        add(listarButton, gbc);

        gbc.gridy++;
        JButton sairButton = new JButton("Sair");
        sairButton.setPreferredSize(buttonSize);
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(sairButton, gbc);


        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }
}
