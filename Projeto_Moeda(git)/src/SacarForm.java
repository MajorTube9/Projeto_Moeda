import javax.swing.*;
import java.awt.*;

public class SacarForm extends JFrame {
    private JPasswordField senhaField;
    private JTextField valorField;
    private JButton confirmarButton, voltarButton;

    public SacarForm() {
        setTitle("Sacar");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new GridLayout(4, 2));

        // Rótulos
        JLabel senhaLabel = new JLabel("Senha:");
        JLabel valorLabel = new JLabel("Valor a Sacar:");

        // Campos de texto
        senhaField = new JPasswordField();
        valorField = new JTextField();

        // Botões
        confirmarButton = new JButton("Confirmar Saque");
        voltarButton = new JButton("Voltar para o Menu");

        // Adicionar componentes ao layout
        add(senhaLabel);
        add(senhaField);
        add(valorLabel);
        add(valorField);
        add(confirmarButton);
        add(voltarButton);

        // Ação do botão Confirmar
        confirmarButton.addActionListener(e -> {
            String senha = new String(senhaField.getPassword()).trim();
            String valorText = valorField.getText().trim();

            if (senha.equals(UserData.getSenha())) {  // Verifica a senha
                try {
                    double valor = Double.parseDouble(valorText);
                    if (valor > 0 && valor <= UserData.getSaldoReais()) {
                        // Atualiza o saldo de Reais
                        double novoSaldo = UserData.getSaldoReais() - valor;
                        UserData.setSaldoReais(novoSaldo);

                        JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
                        dispose();  // Fecha a tela de saque
                        new MenuForm().setVisible(true);  // Volta para o menu
                    } else {
                        JOptionPane.showMessageDialog(null, "Valor inválido para saque ou saldo insuficiente.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um valor válido.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Senha incorreta.");
            }
        });

        // Ação do botão Voltar para o Menu
        voltarButton.addActionListener(e -> {
            dispose();  // Fecha a tela de saque
            new MenuForm().setVisible(true);  // Volta para o menu
        });
    }
}