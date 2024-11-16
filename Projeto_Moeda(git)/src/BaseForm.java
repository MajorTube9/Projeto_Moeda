import javax.swing.*;

public abstract class BaseForm extends JFrame {
    public BaseForm(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}