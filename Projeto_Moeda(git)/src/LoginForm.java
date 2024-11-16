import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginForm extends JFrame {
    private JTextField cpfField;
    private JPasswordField senhaField;
    private JButton loginButton, cadastroButton;

    public LoginForm() {
        setTitle("Tela de Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new GridLayout(3, 2));

        // Rótulos
        JLabel cpfLabel = new JLabel("CPF:");
        JLabel senhaLabel = new JLabel("Senha:");

        // Campos de texto
        cpfField = new JTextField();
        senhaField = new JPasswordField();

        // Botões
        loginButton = new JButton("Login");
        cadastroButton = new JButton("Cadastrar");

        // Adicionar componentes ao layout
        add(cpfLabel);
        add(cpfField);
        add(senhaLabel);
        add(senhaField);
        add(loginButton);
        add(cadastroButton);

        // Ação do botão de login
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfField.getText().trim();
                String senha = new String(senhaField.getPassword()).trim();

                // Verifica se o CPF e a senha estão corretos
                if (cpf.equals(UserData.getCpf()) && senha.equals(UserData.getSenha())) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                    dispose(); // Fecha a tela de login
                    new MenuForm().setVisible(true); // Abre o menu principal
                } else {
                    JOptionPane.showMessageDialog(null, "CPF ou senha incorretos.");
                }
            }
        });

        // Ação do botão de cadastro
        cadastroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela de login
                new CadastroForm().setVisible(true); // Abre a tela de cadastro
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }
}