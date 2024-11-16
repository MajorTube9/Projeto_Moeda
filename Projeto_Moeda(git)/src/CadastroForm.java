import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class CadastroForm extends BaseForm {
    private JTextField nomeField, sobrenomeField, cpfField, senhaField;
    private JButton cadastrarButton, voltarButton;

    public CadastroForm() {
        super("Cadastro");

        setLayout(new GridLayout(5, 2));

        // Campos de entrada
        nomeField = new JTextField();
        sobrenomeField = new JTextField();
        cpfField = new JTextField();
        senhaField = new JPasswordField();

        // Botões
        cadastrarButton = new JButton("Cadastrar");
        voltarButton = new JButton("Voltar");

        add(new JLabel("Nome:"));
        add(nomeField);
        add(new JLabel("Sobrenome:"));
        add(sobrenomeField);
        add(new JLabel("CPF:"));
        add(cpfField);
        add(new JLabel("Senha:"));
        add(senhaField);
        add(cadastrarButton);
        add(voltarButton);

        // Ação para cadastrar
        cadastrarButton.addActionListener(e -> {
            String nome = nomeField.getText().trim();
            String sobrenome = sobrenomeField.getText().trim();
            String cpf = cpfField.getText().trim();
            String senha = senhaField.getText().trim();

            if (nome.isEmpty() || sobrenome.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
                return;
            }

            try {
                Database.criarUsuario(nome, sobrenome, cpf, senha);
                JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
                dispose();
                new LoginForm().setVisible(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage());
            }
        });

        // Ação para voltar ao login
        voltarButton.addActionListener(e -> {
            dispose();
            new LoginForm().setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CadastroForm().setVisible(true));
    }
}
