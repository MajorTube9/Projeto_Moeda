import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ComprarCriptoForm extends BaseForm {
    private JTextField quantidadeField;
    private JComboBox<String> criptoComboBox;
    private JButton comprarButton, voltarButton;

    public ComprarCriptoForm(MenuForm menu) {
        super("Comprar Criptomoedas");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        JLabel criptoLabel = new JLabel("Escolha a criptomoeda:");
        JLabel quantidadeLabel = new JLabel("Quantidade:");

        criptoComboBox = new JComboBox<>(new String[]{"Bitcoin", "Ethereum", "Ripple"});
        quantidadeField = new JTextField();

        comprarButton = new JButton("Comprar");
        voltarButton = new JButton("Voltar para o Menu");

        add(criptoLabel);
        add(criptoComboBox);
        add(quantidadeLabel);
        add(quantidadeField);
        add(comprarButton);
        add(voltarButton);

        comprarButton.addActionListener(e -> {
            try {
                double quantidade = Double.parseDouble(quantidadeField.getText().trim());
                if (quantidade <= 0) throw new NumberFormatException();
                double custo = switch (criptoComboBox.getSelectedItem().toString()) {
                    case "Bitcoin" -> quantidade * UserData.getCotacaoBitcoin() * 1.02;
                    case "Ethereum" -> quantidade * UserData.getCotacaoEthereum() * 1.01;
                    case "Ripple" -> quantidade * UserData.getCotacaoRipple() * 1.01;
                    default -> 0;
                };

                if (UserData.getSaldoReais() >= custo) {
                    UserData.setSaldoReais(UserData.getSaldoReais() - custo);
                    switch (criptoComboBox.getSelectedItem().toString()) {
                        case "Bitcoin" -> UserData.setSaldoBitcoin(UserData.getSaldoBitcoin() + quantidade);
                        case "Ethereum" -> UserData.setSaldoEthereum(UserData.getSaldoEthereum() + quantidade);
                        case "Ripple" -> UserData.setSaldoRipple(UserData.getSaldoRipple() + quantidade);
                    }
                    JOptionPane.showMessageDialog(this, "Compra realizada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Saldo insuficiente!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite uma quantidade válida.");
            }
        });

        voltarButton.addActionListener(e -> {
            setVisible(false);
            menu.setVisible(true);
        });
    }
}