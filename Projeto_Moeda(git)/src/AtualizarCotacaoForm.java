import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class AtualizarCotacaoForm extends JFrame {
    private JButton atualizarButton, voltarButton;

    public AtualizarCotacaoForm(MenuForm menu) {
        setTitle("Atualizar Cotação");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        JLabel infoLabel = new JLabel("Clique para atualizar cotações:", SwingConstants.CENTER);
        add(infoLabel);

        atualizarButton = new JButton("Atualizar Cotações");
        add(atualizarButton);

        voltarButton = new JButton("Voltar para o Menu");
        add(voltarButton);

        atualizarButton.addActionListener(e -> {
            atualizarCotacoes();
            JOptionPane.showMessageDialog(this, "Cotações atualizadas com sucesso!");
        });

        voltarButton.addActionListener(e -> {
            setVisible(false);
            menu.setVisible(true);
        });
    }

    private void atualizarCotacoes() {
        Random random = new Random();
        UserData.setCotacaoBitcoin(UserData.getCotacaoBitcoin() * (1 + (random.nextDouble() * 0.1 - 0.05)));
        UserData.setCotacaoEthereum(UserData.getCotacaoEthereum() * (1 + (random.nextDouble() * 0.1 - 0.05)));
        UserData.setCotacaoRipple(UserData.getCotacaoRipple() * (1 + (random.nextDouble() * 0.1 - 0.05)));
    }
}