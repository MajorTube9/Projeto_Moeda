import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class VenderCriptoForm extends BaseForm {
    private JTextField quantidadeField;
    private JButton venderButton, voltarButton;
    private JComboBox<String> criptoComboBox;
    private JLabel precoLabel;

    public VenderCriptoForm(String cpf) {
        super("Vender Criptomoeda");

        setLayout(new GridLayout(5, 2));

        // Rótulos
        JLabel criptoLabel = new JLabel("Selecione a Criptomoeda:");
        JLabel quantidadeLabel = new JLabel("Quantidade:");

        // Campo de texto para quantidade
        quantidadeField = new JTextField();

        // ComboBox para escolher criptomoeda
        criptoComboBox = new JComboBox<>(new String[]{"Bitcoin", "Ethereum", "Ripple"});

        // Rótulo de preço
        precoLabel = new JLabel("Preço: R$ " + String.format("%.2f", UserData.getCotacaoBitcoin()));  // Valor inicial do preço

        // Botões
        venderButton = new JButton("Vender");
        voltarButton = new JButton("Voltar");

        // Adicionar os componentes ao layout
        add(criptoLabel);
        add(criptoComboBox);
        add(quantidadeLabel);
        add(quantidadeField);
        add(precoLabel);
        add(venderButton);
        add(voltarButton);

        // Ação para atualizar preço ao escolher criptomoeda
        criptoComboBox.addActionListener(e -> {
            if (criptoComboBox.getSelectedItem().equals("Bitcoin")) {
                precoLabel.setText("Preço: R$ " + String.format("%.2f", UserData.getCotacaoBitcoin()));
            } else if (criptoComboBox.getSelectedItem().equals("Ethereum")) {
                precoLabel.setText("Preço: R$ " + String.format("%.2f", UserData.getCotacaoEthereum()));
            } else {
                precoLabel.setText("Preço: R$ " + String.format("%.2f", UserData.getCotacaoRipple()));
            }
        });

        // Ação do botão de venda
        venderButton.addActionListener(e -> {
            try {
                String criptomoeda = criptoComboBox.getSelectedItem().toString();
                double quantidade = Double.parseDouble(quantidadeField.getText().trim());
                double preco = 0.0;
                double saldoCripto = 0.0;
                double saldoReais = UserData.getSaldoReais();

                if (criptomoeda.equals("Bitcoin")) {
                    preco = UserData.getCotacaoBitcoin();
                    saldoCripto = UserData.getSaldoBitcoin();
                } else if (criptomoeda.equals("Ethereum")) {
                    preco = UserData.getCotacaoEthereum();
                    saldoCripto = UserData.getSaldoEthereum();
                } else {
                    preco = UserData.getCotacaoRipple();
                    saldoCripto = UserData.getSaldoRipple();
                }

                if (saldoCripto >= quantidade) {
                    // Atualiza os saldos
                    UserData.setSaldoReais(saldoReais + preco * quantidade);
                    if (criptomoeda.equals("Bitcoin")) {
                        UserData.setSaldoBitcoin(saldoCripto - quantidade);
                    } else if (criptomoeda.equals("Ethereum")) {
                        UserData.setSaldoEthereum(saldoCripto - quantidade);
                    } else {
                        UserData.setSaldoRipple(saldoCripto - quantidade);
                    }

                    // Atualiza o saldo no banco de dados
                    Database.atualizarSaldo(UserData.getCpf(), UserData.getSaldoReais(), UserData.getSaldoBitcoin(),
                            UserData.getSaldoEthereum(), UserData.getSaldoRipple());

                    JOptionPane.showMessageDialog(this, "Venda realizada com sucesso!\nValor recebido: R$ " + String.format("%.2f", preco * quantidade));
                    dispose();
                    MenuForm.getInstance(cpf).setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(this, "Saldo insuficiente para a venda.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite uma quantidade válida.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar o saldo: " + ex.getMessage());
            }
        });

        // Ação do botão voltar
        voltarButton.addActionListener(e -> {
            dispose();
            MenuForm.getInstance(cpf).setVisible(true);

        });
    }
}
