package projeto.quiz.commands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import projeto.quiz.commands.AdicionarPergunta;
import projeto.quiz.domain.Alternativa;
import projeto.quiz.domain.Pergunta;
import projeto.quiz.repository.PerguntaRepository;
import projeto.quiz.service.PerguntaService;
import projeto.quiz.validators.NonEmptyValidator;
import projeto.quiz.validators.ValidatorContext;

public class AdicionarPerguntaGUI implements Commands{
    private JFrame frame;
    private JTextField tituloField;
    private JTextField areaDeConhecimentoField;
    private JTextField alternativaAField; 
    private JTextField alternativaBField; 
    private JTextField alternativaCField; 
    private JTextField alternativaDField; 
    private JCheckBox alternativaABox;
    private JCheckBox alternativaBBox;
    private JCheckBox alternativaCBox;
    private JCheckBox alternativaDBox;
    private final PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());


    public AdicionarPerguntaGUI(JFrame frame, JTextField tituloField, JTextField areaDeConhecimentoField,
            JTextField alternativaAField, JTextField alternativaBField, JTextField alternativaCField,
            JTextField alternativaDField, JCheckBox alternativaABox, JCheckBox alternativaBBox,
            JCheckBox alternativaCBox, JCheckBox alternativaDBox) {
        this.frame = frame;
        this.tituloField = tituloField;
        this.areaDeConhecimentoField = areaDeConhecimentoField;
        this.alternativaAField = alternativaAField;
        this.alternativaBField = alternativaBField;
        this.alternativaCField = alternativaCField;
        this.alternativaDField = alternativaDField;
        this.alternativaABox = alternativaABox;
        this.alternativaBBox = alternativaBBox;
        this.alternativaCBox = alternativaCBox;
        this.alternativaDBox = alternativaDBox;
    }


    @Override
    public void execute() {
        String tituloString = this.tituloField.getText();
        String areaDeConhecimento = this.areaDeConhecimentoField.getText();
        String alternativaAString = this.alternativaAField.getText();
        String alternativaBString = this.alternativaBField.getText();
        String alternativaCString = this.alternativaCField.getText();
        String alternativaDString = this.alternativaDField.getText();
        boolean alternativaACheck = this.alternativaABox.isSelected();
        boolean alternativaBCheck = this.alternativaBBox.isSelected();
        boolean alternativaCCheck = this.alternativaCBox.isSelected();
        boolean alternativaDCheck = this.alternativaDBox.isSelected();
    
    
        ValidatorContext<String> tituloValidatorContext = new ValidatorContext<>(new NonEmptyValidator());
        ValidatorContext<String> areaDeConhecimentoValidatorContext = new ValidatorContext<>(new NonEmptyValidator());
        ValidatorContext<String> alternativaAValidatorContext = new ValidatorContext<>(new NonEmptyValidator());
        ValidatorContext<String> alternativaBValidatorContext = new ValidatorContext<>(new NonEmptyValidator());
        ValidatorContext<String> alternativaCValidatorContext = new ValidatorContext<>(new NonEmptyValidator());
        ValidatorContext<String> alternativaDValidatorContext = new ValidatorContext<>(new NonEmptyValidator());
    
        boolean tituloValido = tituloValidatorContext.validate(tituloString);
        boolean areaValida = areaDeConhecimentoValidatorContext.validate(areaDeConhecimento);
        boolean alternativaAValida = alternativaAValidatorContext.validate(alternativaAString);
        boolean alternativaBValida = alternativaBValidatorContext.validate(alternativaBString);
        boolean alternativaCValida = alternativaCValidatorContext.validate(alternativaCString);
        boolean alternativaDValida = alternativaDValidatorContext.validate(alternativaDString);
    
        if (tituloValido && areaValida && alternativaAValida && alternativaBValida && alternativaCValida && alternativaDValida) {
            Pergunta pergunta = new Pergunta(tituloString, areaDeConhecimento);
        
            pergunta.getAlternativas().add(new Alternativa("A", alternativaAString, alternativaACheck));
            pergunta.getAlternativas().add(new Alternativa("B", alternativaBString, alternativaBCheck));
            pergunta.getAlternativas().add(new Alternativa("C", alternativaCString, alternativaCCheck));
            pergunta.getAlternativas().add(new Alternativa("D", alternativaDString, alternativaDCheck));
        
            JOptionPane.showMessageDialog(tituloField.getParent(), "Pergunta criada com sucesso!");
            System.out.println("Alternativa A: " + alternativaACheck);
            System.out.println("Alternativa B: " + alternativaBCheck);
            System.out.println("Alternativa C: " + alternativaCCheck);
            System.out.println("Alternativa D: " + alternativaDCheck);
        } else {
            JOptionPane.showMessageDialog(tituloField.getParent(), "Preencha todas as informações corretamente!");
        }
    }
}