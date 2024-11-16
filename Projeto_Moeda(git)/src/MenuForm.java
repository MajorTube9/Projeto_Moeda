import javax.swing.*;
import java.awt.*;

public class MenuForm extends BaseForm {
    private JButton consultarSaldoButton, comprarCriptoButton, venderCriptoButton, atualizarCotacaoButton, sairButton, depositarButton, sacarButton;

    public MenuForm() {
        super("Menu Principal");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 1));  // Ajustado para incluir mais botões

        consultarSaldoButton = new JButton("Consultar Saldo");
        comprarCriptoButton = new JButton("Comprar Criptomoedas");
        venderCriptoButton = new JButton("Vender Criptomoedas");
        atualizarCotacaoButton = new JButton("Atualizar Cotação");
        depositarButton = new JButton("Depositar");
        sacarButton = new JButton("Sacar");
        sairButton = new JButton("Sair do Programa");

        add(consultarSaldoButton);
        add(comprarCriptoButton);
        add(venderCriptoButton);
        add(atualizarCotacaoButton);
        add(depositarButton); // Adicionando o botão de depositar
        add(sacarButton); // Adicionando o botão de sacar
        add(sairButton);

        consultarSaldoButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Saldo em Reais: R$ " + String.format("%.2f", UserData.getSaldoReais()) +
                "\nBitcoin: " + String.format("%.2f", UserData.getSaldoBitcoin()) +
                "\nEthereum: " + String.format("%.2f", UserData.getSaldoEthereum()) +
                "\nRipple: " + String.format("%.2f", UserData.getSaldoRipple()));
        });

        comprarCriptoButton.addActionListener(e -> {
            setVisible(false);
            new ComprarCriptoForm(this).setVisible(true);
        });

        venderCriptoButton.addActionListener(e -> {
            setVisible(false);
            new VenderCriptoForm(this).setVisible(true);
        });

        atualizarCotacaoButton.addActionListener(e -> {
            UserData.atualizarCotacoes();
            JOptionPane.showMessageDialog(this, 
                "Cotações Atualizadas:\n" +
                "Bitcoin: R$ " + String.format("%.2f", UserData.getCotacaoBitcoin()) +
                "\nEthereum: R$ " + String.format("%.2f", UserData.getCotacaoEthereum()) +
                "\nRipple: R$ " + String.format("%.2f", UserData.getCotacaoRipple()));
        });

        depositarButton.addActionListener(e -> {
            setVisible(false);
            new DepositarForm().setVisible(true);  // Acessa a tela de depósito
        });

        sacarButton.addActionListener(e -> {
            setVisible(false);
            new SacarForm().setVisible(true);  // Acessa a tela de saque
        });

        sairButton.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuForm().setVisible(true));
    }
}