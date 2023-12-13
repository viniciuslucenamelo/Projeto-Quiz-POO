package projeto.quiz.commands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import projeto.quiz.commands.AdicionarPergunta;

public class AdicionarPerguntaGUI extends JFrame {

    public AdicionarPerguntaGUI() {
        setTitle("Adicionar Pergunta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(720, 576);
        setLayout(new GridLayout(7, 4));

        JTextField tituloField = new JTextField();
        JTextField areaField = new JTextField();

        add(new JLabel("Título da Pergunta:"));
        add(tituloField);
        add(new JLabel("Área do Conhecimento:"));
        add(areaField);

        for (int i = 1; i <= 4; i++) {
            JTextField opcaoField = new JTextField();
            JTextField afirmativaField = new JTextField();
            JTextField corretaField = new JTextField();

            // Organiza as alternativas em duas colunas
            int column = (i % 2 == 0) ? 2 : 1;

            add(new JLabel("Alternativa " + i + ":"));
            add(opcaoField);
            add(new JLabel("Afirmativa:"));
            add(afirmativaField);
            add(new JLabel("É a opção correta? (true/false):"));
            add(corretaField);
        }

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adicione lógica para fechar esta janela sem adicionar pergunta
                dispose();
            }
        });

        JButton adicionarButton = new JButton("Adicionar Pergunta");
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Criar a lógica para obter dados da GUI e chamar AdicionarPergunta
                AdicionarPergunta adicionarPergunta = new AdicionarPergunta();
                adicionarPergunta.execute();
                // Adicione lógica para fechar esta janela após adicionar pergunta
                dispose();
            }
        });

        add(new JLabel()); // rótulo vazio para ajustar o layout
        add(voltarButton);
        add(new JLabel()); // rótulo vazio para ajustar o layout
        add(adicionarButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
