import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AtualizarCotacaoForm extends BaseForm {
    private JButton atualizarButton, voltarButton;

    public AtualizarCotacaoForm() {
        super("Atualizar Cotações");

        setLayout(new GridLayout(3, 1));

        atualizarButton = new JButton("Atualizar Cotações");
        voltarButton = new JButton("Voltar");

        add(atualizarButton);
        add(voltarButton);

        // Ação para atualizar as cotações
        atualizarButton.addActionListener(e -> {
            UserData.atualizarCotacao();
            JOptionPane.showMessageDialog(this, "Cotações atualizadas com sucesso!\n"
                    + "Bitcoin: R$ " + String.format("%.2f", UserData.getCotacaoBitcoin()) + "\n"
                    + "Ethereum: R$ " + String.format("%.2f", UserData.getCotacaoEthereum()) + "\n"
                    + "Ripple: R$ " + String.format("%.2f", UserData.getCotacaoRipple()));
        });

        voltarButton.addActionListener(e -> dispose());
    }
}
