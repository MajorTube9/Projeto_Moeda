import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class LoginForm extends BaseForm {
    private JTextField cpfField;
    private JPasswordField senhaField;
    private JButton loginButton, cadastroButton;

    public LoginForm() {
        super("Login");

        setLayout(new GridLayout(3, 2));

        // Campos de entrada
        cpfField = new JTextField();
        senhaField = new JPasswordField();

        // Botões
        loginButton = new JButton("Login");
        cadastroButton = new JButton("Cadastrar");

        add(new JLabel("CPF:"));
        add(cpfField);
        add(new JLabel("Senha:"));
        add(senhaField);
        add(loginButton);
        add(cadastroButton);

        // Ação do botão de login
        loginButton.addActionListener(e -> {
            String cpf = cpfField.getText().trim();
            String senha = new String(senhaField.getPassword()).trim();

            if (cpf.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
                return;
            }

            try {
                boolean autenticado = Database.autenticarUsuario(cpf, senha);
                if (autenticado) {
                    UserData.setCpf(cpf);
                    MenuForm.getInstance(cpf).setVisible(true);

                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "CPF ou senha inválidos.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao autenticar: " + ex.getMessage());
            }
        });

        // Ação do botão de cadastro
        cadastroButton.addActionListener(e -> {
            dispose();
            new CadastroForm().setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
    }
}
