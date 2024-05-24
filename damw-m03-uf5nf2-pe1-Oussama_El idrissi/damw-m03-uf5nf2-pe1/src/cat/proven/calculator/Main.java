package cat.proven.calculator;


import cat.proven.calculator.views.MainFrame;
import cat.proven.calculator.model.Calculator;
import javax.swing.SwingUtilities;

/**
 *
 * @author ProvenSoft
 */
public class Main {
    
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            Calculator model = new Calculator();
            MainFrame frame = new MainFrame(model);
            frame.setVisible(true);
        });
       
    }
}
