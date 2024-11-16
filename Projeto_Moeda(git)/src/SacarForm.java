import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class SacarForm extends BaseForm {
    private JTextField valorField;
    private JButton sacarButton, voltarButton;

    public SacarForm(String cpf) {
        super("Sacar");

        setLayout(new GridLayout(3, 2));

        // Campos de entrada
        valorField = new JTextField();
        sacarButton = new JButton("Sacar");
        voltarButton = new JButton("Voltar");

        add(new JLabel("Valor a Sacar:"));
        add(valorField);
        add(sacarButton);
        add(voltarButton);

        sacarButton.addActionListener(e -> {
            try {
                double valor = Double.parseDouble(valorField.getText().trim());
                if (valor <= 0) {
                    JOptionPane.showMessageDialog(this, "O valor deve ser positivo.");
                    return;
                }

                Usuario usuario = Database.obterUsuario(cpf);
                double saldoReais = usuario.getSaldoReais();

                if (saldoReais >= valor) {
                    Database.atualizarSaldo(cpf, saldoReais - valor, usuario.getSaldoBitcoin(), usuario.getSaldoEthereum(), usuario.getSaldoRipple());
                    JOptionPane.showMessageDialog(this, "Saque realizado com sucesso!");
                    dispose();
                    MenuForm.getInstance(cpf).setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(this, "Saldo insuficiente para o saque.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um valor válido.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar saldo: " + ex.getMessage());
            }
        });

        voltarButton.addActionListener(e -> {
            dispose();
            MenuForm.getInstance(cpf).setVisible(true);

        });
    }
}
