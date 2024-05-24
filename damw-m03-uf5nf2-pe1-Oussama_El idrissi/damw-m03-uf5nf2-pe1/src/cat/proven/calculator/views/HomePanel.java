package cat.proven.calculator.views;

import cat.proven.calculator.model.Calculator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ProvenSoft
 */
public class HomePanel extends JPanel implements ActionListener {

    private Calculator modelCalcul;
    private JTextField txtNumero1;
    private JTextField txtNumero2;
    private JTextField txtNumero3;

    private JButton btnSumar;
    private JButton btnRestar;
    private JButton btnMultiplicar;
    private JButton btnDividir;

    public HomePanel() {
        initComponents();
        modelCalcul = new Calculator();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        JLabel lbHeader = new JLabel("Calculadora");
        add(lbHeader, BorderLayout.NORTH);
        JPanel form = createCalculatorForm();
        add(form, BorderLayout.CENTER);
    }

    private JPanel createCalculatorForm() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        panel.add(new JLabel("First Operand:"));
        txtNumero1 = new JTextField(20);
        panel.add(txtNumero1);

        panel.add(new JLabel("Second Operand:"));
        txtNumero2 = new JTextField(20);
        panel.add(txtNumero2);

        panel.add(new JLabel("Result:"));
        txtNumero3 = new JTextField(20);
        txtNumero3.setBackground(Color.gray);
        panel.add(txtNumero3);

        btnSumar = new JButton("+");
        btnSumar.setActionCommand("sumar");
        btnSumar.addActionListener(this);
        btnSumar.setSize(20, 20);

        btnRestar = new JButton("-");
        btnRestar.setActionCommand("restar");
        btnRestar.addActionListener(this);
        btnRestar.setSize(20, 20);

        btnMultiplicar = new JButton("*");
        btnMultiplicar.setActionCommand("multiplicar");
        btnMultiplicar.addActionListener(this);
        btnMultiplicar.setSize(20, 20);

        btnDividir = new JButton("/");
        btnDividir.setActionCommand("dividir");
        btnDividir.addActionListener(this);
        btnDividir.setSize(20, 20);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
//ADDBYUT
        panelBotones.add(btnSumar);
        panelBotones.add(btnRestar);
        panelBotones.add(btnMultiplicar);

        panelBotones.add(btnDividir);

        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        try {
            double num1 = Double.parseDouble(txtNumero1.getText());
            double num2 = Double.parseDouble(txtNumero2.getText());
            double result = 0;

            switch (command) {
                case "sumar":
                    result = modelCalcul.Suma(num1, num2);
                    txtNumero3.setText(String.valueOf(result));

                    break;
                case "restar":
                    result = modelCalcul.Resta(num1, num2);
                    txtNumero3.setText(String.valueOf(result));

                    break;
                case "multiplicar":
                    result = modelCalcul.Multiplicacion(num1, num2);
                    txtNumero3.setText(String.valueOf(result));

                    break;
                case "dividir":
                    if (num2 != 0) {
                        result = modelCalcul.Division(num1, num2);
                    txtNumero3.setText(String.valueOf(result));

                    } else {
                        JOptionPane.showMessageDialog(this, "No se puede dividir por cero.");
                        return;
                    }
                    break;
            }

        }catch (NumberFormatException x) {
            JOptionPane.showMessageDialog(this, "Invalid data", "Validation", JOptionPane.ERROR_MESSAGE);
        }

    }
}
