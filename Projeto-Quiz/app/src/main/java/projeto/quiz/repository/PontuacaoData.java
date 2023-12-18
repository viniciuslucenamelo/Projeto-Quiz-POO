package main.java.projeto.quiz.repository;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PontuacaoData {
    private final String arquivoPontuacao = "pontuacao.bin";
    private final Map<String, Integer> pontuacoes;

    public PontuacaoData() {
        this.pontuacoes = new HashMap<>();
        carregarPontuacoes(); // Carregar pontuações salvas anteriormente, se existirem
    }

    public void adicionarPontuacao(String nomeJogador, int pontuacao) {
        pontuacoes.put(nomeJogador, pontuacao);
        salvarPontuacoes();
    }

    public Map<String, Integer> obterPontuacoes() {
        return pontuacoes;
    }

    private void salvarPontuacoes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoPontuacao))) {
            oos.writeObject(pontuacoes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarPontuacoes() {
        File arquivo = new File(arquivoPontuacao);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoPontuacao))) {
                pontuacoes.putAll((Map<String, Integer>) ois.readObject());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

