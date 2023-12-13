package projeto.quiz.commands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import projeto.quiz.commands.EditarPergunta;

public class EditarPerguntaGUI extends JFrame {

    public EditarPerguntaGUI() {
        setTitle("Editar Pergunta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 3));

        JTextField escolhaField = new JTextField();
        JTextField tituloField = new JTextField();
        JTextField areaField = new JTextField();

        add(new JLabel("Escolha o número da pergunta a ser editada:"));
        add(escolhaField);
        add(new JLabel()); // rótulo vazio para ocupar espaço na segunda e terceira colunas

        add(new JLabel("Novo Título da Pergunta:"));
        add(tituloField);
        add(new JLabel()); // rótulo vazio para ocupar espaço na segunda e terceira colunas

        add(new JLabel("Nova Área do Conhecimento:"));
        add(areaField);
        add(new JLabel()); // rótulo vazio para ocupar espaço na segunda e terceira colunas

        for (int i = 1; i <= 4; i++) {
            JTextField opcaoField = new JTextField();
            JTextField afirmativaField = new JTextField();
            JTextField corretaField = new JTextField();

            add(new JLabel("Nova Alternativa " + i + ":"));
            add(opcaoField);
            add(new JLabel("Nova Afirmativa:"));
            add(afirmativaField);
            add(new JLabel("É a nova opção correta? (true/false):"));
            add(corretaField);
        }

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adicione lógica para fechar esta janela sem editar pergunta
                dispose();
            }
        });

        JButton editarButton = new JButton("Editar Pergunta");
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Criar a lógica para obter dados da GUI e chamar EditarPergunta
                EditarPergunta editarPergunta = new EditarPergunta();
                editarPergunta.execute();
                // Adicione lógica para fechar esta janela após editar pergunta
                dispose();
            }
        });

        add(voltarButton);
        add(editarButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
