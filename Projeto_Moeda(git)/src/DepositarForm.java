import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class DepositarForm extends BaseForm {
    private JTextField valorField;
    private JButton depositarButton, voltarButton;

    public DepositarForm(String cpf) {
        super("Depositar");

        setLayout(new GridLayout(3, 2));

        // Campos de entrada
        valorField = new JTextField();
        depositarButton = new JButton("Depositar");
        voltarButton = new JButton("Voltar");

        add(new JLabel("Valor a Depositar:"));
        add(valorField);
        add(depositarButton);
        add(voltarButton);

        depositarButton.addActionListener(e -> {
            try {
                double valor = Double.parseDouble(valorField.getText().trim());
                if (valor <= 0) {
                    JOptionPane.showMessageDialog(this, "O valor deve ser positivo.");
                    return;
                }

                Usuario usuario = Database.obterUsuario(cpf);
                double saldoReais = usuario.getSaldoReais();
                Database.atualizarSaldo(cpf, saldoReais + valor, usuario.getSaldoBitcoin(), usuario.getSaldoEthereum(), usuario.getSaldoRipple());
                JOptionPane.showMessageDialog(this, "Depósito realizado com sucesso!");
                dispose();
                MenuForm.getInstance(cpf).setVisible(true);

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
