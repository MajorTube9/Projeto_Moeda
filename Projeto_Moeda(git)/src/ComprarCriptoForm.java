import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class ComprarCriptoForm extends BaseForm {
    private JTextField quantidadeField;
    private JButton comprarButton, voltarButton;
    private JComboBox<String> criptoComboBox;
    private JLabel precoLabel;
    private String cpf;

    public ComprarCriptoForm(String cpf) {
        super("Comprar Criptomoeda");
        this.cpf = cpf;  // Armazena o CPF

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
        comprarButton = new JButton("Comprar");
        voltarButton = new JButton("Voltar");

        // Adicionar os componentes ao layout
        add(criptoLabel);
        add(criptoComboBox);
        add(quantidadeLabel);
        add(quantidadeField);
        add(precoLabel);
        add(comprarButton);
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

        // Ação do botão de compra
        comprarButton.addActionListener(e -> {
            try {
                String criptomoeda = criptoComboBox.getSelectedItem().toString();
                double quantidade = Double.parseDouble(quantidadeField.getText().trim());
                double preco = 0.0;
                double saldoReais = Database.obterUsuario(cpf).getSaldoReais();  // Obtem o saldo de reais do usuário

                if (criptomoeda.equals("Bitcoin")) {
                    preco = UserData.getCotacaoBitcoin();
                } else if (criptomoeda.equals("Ethereum")) {
                    preco = UserData.getCotacaoEthereum();
                } else if (criptomoeda.equals("Ripple")) {
                    preco = UserData.getCotacaoRipple();
                }

                double custoTotal = preco * quantidade;
                if (saldoReais >= custoTotal) {
                    // Atualiza o saldo em reais
                    saldoReais -= custoTotal;  // Deduz o custo total

                    // Atualiza o saldo da criptomoeda
                    if (criptomoeda.equals("Bitcoin")) {
                        UserData.setSaldoBitcoin(UserData.getSaldoBitcoin() + quantidade);
                    } else if (criptomoeda.equals("Ethereum")) {
                        UserData.setSaldoEthereum(UserData.getSaldoEthereum() + quantidade);
                    } else {
                        UserData.setSaldoRipple(UserData.getSaldoRipple() + quantidade);
                    }

                    // Atualiza o saldo no banco de dados
                    Database.atualizarSaldo(cpf, saldoReais, UserData.getSaldoBitcoin(), UserData.getSaldoEthereum(), UserData.getSaldoRipple());

                    JOptionPane.showMessageDialog(this, "Compra realizada com sucesso!\nValor total: R$ " + String.format("%.2f", custoTotal));
                    dispose();
                    MenuForm.getInstance(cpf).setVisible(true);
 // Volta para o menu
                } else {
                    JOptionPane.showMessageDialog(this, "Saldo insuficiente em Reais para a compra.");
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
  // Volta para o menu
        });
    }
}
