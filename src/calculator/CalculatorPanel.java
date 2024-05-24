/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculator;

/**
 *
 * @author ouel1362
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorPanel extends JPanel implements ActionListener {

    private JTextField txtNumero1;
    private JTextField txtNumero2;
    private JButton btnSumar;
    private JButton btnRestar;
    private JButton btnMultiplicar;
    private JButton btnDividir;

    public CalculatorPanel() {
        initComponents();
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
        
        panel.add(new JLabel("Número 1:"));
        txtNumero1 = new JTextField(20);
        panel.add(txtNumero1);
        
        panel.add(new JLabel("Número 2:"));
        txtNumero2 = new JTextField(20);
        panel.add(txtNumero2);
        
        btnSumar = new JButton("Sumar");
        btnSumar.setActionCommand("sumar");
        btnSumar.addActionListener(this);
        panel.add(btnSumar);

        btnRestar = new JButton("Restar");
        btnRestar.setActionCommand("restar");
        btnRestar.addActionListener(this);
        panel.add(btnRestar);

        btnMultiplicar = new JButton("Multiplicar");
        btnMultiplicar.setActionCommand("multiplicar");
        btnMultiplicar.addActionListener(this);
        panel.add(btnMultiplicar);

        btnDividir = new JButton("Dividir");
        btnDividir.setActionCommand("dividir");
        btnDividir.addActionListener(this);
        panel.add(btnDividir);

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
                    result = num1 + num2;
                    break;
                case "restar":
                    result = num1 - num2;
                    break;
                case "multiplicar":
                    result = num1 * num2;
                    break;
                case "dividir":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        JOptionPane.showMessageDialog(this, "No se puede dividir por cero.");
                        return;
                    }
                    break;
            }
            JOptionPane.showMessageDialog(this, "El resultado es: " + result);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa números válidos.");
        }
    }
}
