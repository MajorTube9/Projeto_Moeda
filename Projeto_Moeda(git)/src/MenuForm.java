import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuForm extends BaseForm {
    private JButton consultarSaldoButton, comprarCriptoButton, venderCriptoButton, atualizarCotacaoButton, depositarButton, sacarButton;
    private static MenuForm menuFormInstance = null;

    private MenuForm(String cpf) {
        super("Menu");

        setLayout(new GridLayout(4, 1));

        // Botões do menu
        consultarSaldoButton = new JButton("Consultar Saldo");
        comprarCriptoButton = new JButton("Comprar Criptomoeda");
        venderCriptoButton = new JButton("Vender Criptomoeda");
        atualizarCotacaoButton = new JButton("Atualizar Cotações");
        depositarButton = new JButton("Depositar");
        sacarButton = new JButton("Sacar");

        add(consultarSaldoButton);
        add(comprarCriptoButton);
        add(venderCriptoButton);
        add(atualizarCotacaoButton);
        add(depositarButton);
        add(sacarButton);

        // Ações dos botões
        consultarSaldoButton.addActionListener(e -> new ConsultarSaldoForm(cpf).setVisible(true));
        comprarCriptoButton.addActionListener(e -> new ComprarCriptoForm(cpf).setVisible(true));
        venderCriptoButton.addActionListener(e -> new VenderCriptoForm(cpf).setVisible(true));
        atualizarCotacaoButton.addActionListener(e -> new AtualizarCotacaoForm().setVisible(true));
        depositarButton.addActionListener(e -> new DepositarForm(cpf).setVisible(true));
        sacarButton.addActionListener(e -> new SacarForm(cpf).setVisible(true));
    }

    // Método para garantir que apenas uma instância do MenuForm será criada
    public static MenuForm getInstance(String cpf) {
        if (menuFormInstance == null) {
            menuFormInstance = new MenuForm(cpf);
        }
        return menuFormInstance;
    }
}
