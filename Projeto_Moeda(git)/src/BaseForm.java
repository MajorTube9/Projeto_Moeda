import javax.swing.*;

public abstract class BaseForm extends JFrame {
    // Classe base com funcionalidades comuns aos formulários
    public BaseForm(String title) {
        setTitle(title);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
