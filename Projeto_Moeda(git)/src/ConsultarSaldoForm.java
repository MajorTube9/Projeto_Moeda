import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class ConsultarSaldoForm extends BaseForm {
    private JButton consultarButton, voltarButton;

    public ConsultarSaldoForm(String cpf) {
        super("Consultar Saldo");

        setLayout(new GridLayout(2, 1));

        consultarButton = new JButton("Consultar Saldo");
        voltarButton = new JButton("Voltar");

        add(consultarButton);
        add(voltarButton);

        consultarButton.addActionListener(e -> {
            try {
                Usuario usuario = Database.obterUsuario(cpf);
                JOptionPane.showMessageDialog(this, "Saldo em Reais: R$ " + usuario.getSaldoReais()
                        + "\nSaldo em Bitcoin: " + usuario.getSaldoBitcoin()
                        + "\nSaldo em Ethereum: " + usuario.getSaldoEthereum()
                        + "\nSaldo em Ripple: " + usuario.getSaldoRipple());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao consultar saldo: " + ex.getMessage());
            }
        });

        voltarButton.addActionListener(e -> {
            dispose();
            MenuForm.getInstance(cpf).setVisible(true);

        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsultarSaldoForm("12345678901").setVisible(true));
    }
}
