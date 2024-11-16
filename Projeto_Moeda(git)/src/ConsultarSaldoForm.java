import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConsultarSaldoForm extends JFrame {
    private JPasswordField senhaField;
    private JButton consultarButton, voltarButton;

    public ConsultarSaldoForm(MenuForm menu) {
        setTitle("Consultar Saldo");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField();
        consultarButton = new JButton("Consultar Saldo");
        voltarButton = new JButton("Voltar para o Menu");

        add(senhaLabel);
        add(senhaField);
        add(consultarButton);
        add(voltarButton);

        // Ação do botão Consultar Saldo
        consultarButton.addActionListener(e -> {
            String senha = new String(senhaField.getPassword()).trim();
            if (senha.equals(UserData.getSenha())) {
                JOptionPane.showMessageDialog(this, "Saldo em Reais: R$ " + String.format("%.2f", UserData.getSaldoReais()));
            } else {
                JOptionPane.showMessageDialog(this, "Senha incorreta.");
            }
        });

        // Ação do botão Voltar
        voltarButton.addActionListener(e -> {
            setVisible(false);
            menu.setVisible(true);
        });
    }
}