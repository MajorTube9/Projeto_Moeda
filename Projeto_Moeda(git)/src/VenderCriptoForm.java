import javax.swing.*;
import java.awt.*;

public class VenderCriptoForm extends BaseForm {
    private JTextField quantidadeField;
    private JComboBox<String> criptoComboBox;
    private JButton venderButton, voltarButton;

    public VenderCriptoForm(MenuForm menu) {
        super("Vender Criptomoedas");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        JLabel criptoLabel = new JLabel("Escolha a criptomoeda:");
        JLabel quantidadeLabel = new JLabel("Quantidade:");

        criptoComboBox = new JComboBox<>(new String[]{"Bitcoin", "Ethereum", "Ripple"});
        quantidadeField = new JTextField();

        venderButton = new JButton("Vender");
        voltarButton = new JButton("Voltar para o Menu");

        add(criptoLabel);
        add(criptoComboBox);
        add(quantidadeLabel);
        add(quantidadeField);
        add(venderButton);
        add(voltarButton);

        venderButton.addActionListener(e -> {
            try {
                double quantidade = Double.parseDouble(quantidadeField.getText().trim());
                if (quantidade <= 0) throw new NumberFormatException();
                double receita = switch (criptoComboBox.getSelectedItem().toString()) {
                    case "Bitcoin" -> quantidade * UserData.getCotacaoBitcoin() * 0.97;
                    case "Ethereum" -> quantidade * UserData.getCotacaoEthereum() * 0.98;
                    case "Ripple" -> quantidade * UserData.getCotacaoRipple() * 0.99;
                    default -> 0;
                };

                switch (criptoComboBox.getSelectedItem().toString()) {
                    case "Bitcoin" -> {
                        if (UserData.getSaldoBitcoin() >= quantidade) {
                            UserData.setSaldoBitcoin(UserData.getSaldoBitcoin() - quantidade);
                            UserData.setSaldoReais(UserData.getSaldoReais() + receita);
                            JOptionPane.showMessageDialog(this, "Venda realizada com sucesso! Receita: R$ " + String.format("%.2f", receita));
                        } else {
                            JOptionPane.showMessageDialog(this, "Saldo insuficiente de Bitcoin!");
                        }
                    }
                    case "Ethereum" -> {
                        if (UserData.getSaldoEthereum() >= quantidade) {
                            UserData.setSaldoEthereum(UserData.getSaldoEthereum() - quantidade);
                            UserData.setSaldoReais(UserData.getSaldoReais() + receita);
                            JOptionPane.showMessageDialog(this, "Venda realizada com sucesso! Receita: R$ " + String.format("%.2f", receita));
                        } else {
                            JOptionPane.showMessageDialog(this, "Saldo insuficiente de Ethereum!");
                        }
                    }
                    case "Ripple" -> {
                        if (UserData.getSaldoRipple() >= quantidade) {
                            UserData.setSaldoRipple(UserData.getSaldoRipple() - quantidade);
                            UserData.setSaldoReais(UserData.getSaldoReais() + receita);
                            JOptionPane.showMessageDialog(this, "Venda realizada com sucesso! Receita: R$ " + String.format("%.2f", receita));
                        } else {
                            JOptionPane.showMessageDialog(this, "Saldo insuficiente de Ripple!");
                        }
                    }
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