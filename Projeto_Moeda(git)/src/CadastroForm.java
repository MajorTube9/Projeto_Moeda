import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CadastroForm extends JFrame {
    private JTextField nomeField, sobrenomeField, cpfField;
    private JPasswordField senhaField;
    private JButton cadastrarButton;

    public CadastroForm() {
        setTitle("Tela de Cadastro");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new GridLayout(5, 2));

        // Rótulos
        JLabel nomeLabel = new JLabel("Nome:");
        JLabel sobrenomeLabel = new JLabel("Sobrenome:");
        JLabel cpfLabel = new JLabel("CPF:");
        JLabel senhaLabel = new JLabel("Senha:");

        // Campos de texto
        nomeField = new JTextField();
        sobrenomeField = new JTextField();
        cpfField = new JTextField();
        senhaField = new JPasswordField();

        // Botão de cadastrar
        cadastrarButton = new JButton("Cadastrar");

        // Adicionar componentes ao layout
        add(nomeLabel);
        add(nomeField);
        add(sobrenomeLabel);
        add(sobrenomeField);
        add(cpfLabel);
        add(cpfField);
        add(senhaLabel);
        add(senhaField);
        add(cadastrarButton);

        // Ação do botão Cadastrar
        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText().trim();
                String sobrenome = sobrenomeField.getText().trim();
                String cpf = cpfField.getText().trim();
                String senha = new String(senhaField.getPassword()).trim();

                // Verifica se o CPF e a senha têm o formato correto
                if (cpf.length() == 11 && senha.length() == 6) {
                    // Armazena os dados do usuário na classe UserData
                    UserData.setNome(nome);
                    UserData.setSobrenome(sobrenome);
                    UserData.setCpf(cpf);
                    UserData.setSenha(senha);

                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                    dispose(); // Fecha a tela de cadastro
                    new LoginForm().setVisible(true); // Volta para a tela de login
                } else {
                    JOptionPane.showMessageDialog(null, "CPF deve ter 11 dígitos e senha deve ter 6 dígitos.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CadastroForm().setVisible(true);
            }
        });
    }
}