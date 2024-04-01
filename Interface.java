import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Interface implements WindowListener, ActionListener {
    private JFrame minhaTela;
    private JButton botaoIniciar;
    private JButton[] botoesResposta;
    private JLabel labelPergunta;
    private int pontuacaoJogador = 0;
    private String[] perguntas = {
        "Qual é a capital do Brasil?",
        "Quem escreveu Dom Quixote?",
        "Qual é o maior planeta do sistema solar?"
    };
    private String[][] respostas = {
        {"Rio de Janeiro", "Brasília", "São Paulo", "Salvador"},
        {"Machado de Assis", "Miguel de Cervantes", "Carlos Drummond de Andrade", "José de Alencar"},
        {"Júpiter", "Terra", "Saturno", "Netuno"}
    };
    private int[] respostasCorretas = {1, 2, 0}; // indice da resposta correta para cada pergunta
    private int perguntaAtual = 0;

    public Interface() {
        this.minhaTela = new JFrame("Quiz");
        this.minhaTela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel painelJogo = new JPanel();
        JPanel painelBotoes = new JPanel();
        this.botaoIniciar = new JButton("Iniciar");
        this.botaoIniciar.setBackground(Color.BLUE); // Cor de fundo do botão Iniciar
        this.botaoIniciar.setForeground(Color.WHITE); // Cor do texto do botão Iniciar
        this.botaoIniciar.addActionListener(this);
        this.labelPergunta = new JLabel();
        painelJogo.add(labelPergunta);
        this.botoesResposta = new JButton[4];
        for (int i = 0; i < 4; i++) {
            botoesResposta[i] = new JButton();
            botoesResposta[i].setBackground(Color.GRAY); // Cor de fundo dos botões de resposta
            botoesResposta[i].setForeground(Color.WHITE); // Cor do texto dos botões de resposta
            botoesResposta[i].addActionListener(this);
            painelBotoes.add(botoesResposta[i]);
        }
        painelBotoes.add(botaoIniciar);
        Container container = minhaTela.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(painelJogo, BorderLayout.CENTER);
        container.add(painelBotoes, BorderLayout.SOUTH);
        this.minhaTela.setSize(400, 200);
        this.minhaTela.addWindowListener(this);
    }

    public void run() {
        this.minhaTela.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == botaoIniciar) {
            exibirPergunta(perguntaAtual);
            botaoIniciar.setEnabled(false);
        } else {
            JButton botaoClicado = (JButton) evento.getSource();
            int respostaSelecionada = -1;
            for (int i = 0; i < 4; i++) {
                if (botaoClicado == botoesResposta[i]) {
                    respostaSelecionada = i;
                    break;
                }
            }
            if (respostaSelecionada == respostasCorretas[perguntaAtual]) {
                pontuacaoJogador++;
            }
            perguntaAtual++;
            if (perguntaAtual < perguntas.length) {
                exibirPergunta(perguntaAtual);
            } else {
                exibirResultado();
            }
        }
    }

    private void exibirPergunta(int index) {
        labelPergunta.setText(perguntas[index]);
        for (int i = 0; i < 4; i++) {
            botoesResposta[i].setText(respostas[index][i]);
        }
    }

    private void exibirResultado() {
        JDialog dialog = new JDialog(minhaTela, "Resultado", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        JLabel labelResultado = new JLabel("Pontuação final: " + pontuacaoJogador + "/" + perguntas.length);
        panel.add(labelResultado);
        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setVisible(true);
        minhaTela.dispose();
    }

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowOpened(WindowEvent e) {}

    public static void main(String[] args) {
        Interface quiz = new Interface();
        quiz.run();
    }
}