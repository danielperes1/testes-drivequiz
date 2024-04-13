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

public class App implements WindowListener, ActionListener {
    private JFrame minhaTela;
    private JButton botaoIniciar;
    private JButton[] botoesResposta;
    private JLabel labelPergunta;
    private int pontuacaoJogador = 0;
    private String[] perguntas = {
        "Qual e a velocidade maxima permitida em uma via urbana, onde nao ha sinalizacao indicando outra velocidade?",
        "O que significa uma luz amarela piscando em um semaforo?'?",
        "Qual a documentaçao necessaria para conduzir um veiculo?",
        "O que e uma faixa de pedestres?",
        "Qual e a penalidade para dirigir sob a influencia de alcool?",
        "O que significa uma placa de transito com a inscriçao Pare?",
        "Qual e a função do cinto de segurança em um veiculo?",
        "O que significa uma placa de transito com a inscriçao De a preferencia?"


    };
    private String[][] respostas = {
        {"30 km/h", "50 km/h", "60 km/h", "80 km/h"},
        {"Pode passar com cuidado", "Reduzir a velocidade e ficar preparado para parar", "Acelerar para passar antes que fique vermelho", "Parar imediatamente"},
        {"Carteira Nacional de Habilitação (CNH) e documentos do veiculo", "Apenas a identidade", "Apenas a Carteira Nacional de Habilitaçao (CNH)", "Apenas o Certificado de Registro e Licenciamento do Veiculo (CRLV)"},
        {"Area destinada a ultrapassagem de veiculos", "Local para estacionamento de veiculos", "Pista de corrida para pedestres", "Area demarcada na via destinada a travessia de pedestres"},
        {"Multa leve", "Advertencia por escrito", "Multa e suspensao do direito de dirigir", "Nenhuma penalidade"},
        {"Reduzir a velocidade", "Parar o veiculo", "Acelerar o veiculo", "Proceder com atençao"},
        {"Prevenir roubos", "Manter o veiculo aquecido", "Evitar multas de transito", "Proteger os ocupantes em caso de colisao"},
        {"Proceder com cuidado e dar passagem aos outros veiculos", "Obedecer as leis de transito", "Acelerar o veiculo", "Ceder passagem aos veiculos a direita"},
    };
    private int[] respostasCorretas = {1, 1, 0, 3, 2, 1, 3, 0 }; 
    private int perguntaAtual = 0;

    public App() {
        this.minhaTela = new JFrame("Quiz");
        this.minhaTela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel painelJogo = new JPanel();
        JPanel painelBotoes = new JPanel();
        this.botaoIniciar = new JButton("Iniciar");
        this.botaoIniciar.setBackground(Color.BLUE); 
        this.botaoIniciar.setForeground(Color.WHITE); 
        this.botaoIniciar.addActionListener(this);
        this.labelPergunta = new JLabel();
        painelJogo.add(labelPergunta);
        this.botoesResposta = new JButton[4];
        for (int i = 0; i < 4; i++) {
            botoesResposta[i] = new JButton();
            botoesResposta[i].setBackground(Color.GRAY); 
            botoesResposta[i].setForeground(Color.WHITE); 
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
        App quiz = new App();
        quiz.run();
    }
}
