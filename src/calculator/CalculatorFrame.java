/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculator;

/**
 *
 * @author ouel1362
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class CalculatorFrame extends JFrame implements ActionListener {

    private CalculatorFrame convertPanel;
    private CalculatorPanel calculatorPanel;

    private ActionListener listener;

    public CalculatorFrame() {
        listener = this;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUpMenu();
        displayCalculatorPanel();
        setSize(400, 300);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    private void setUpMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu;
        JMenuItem menuItem;

        menu = new JMenu("File");
        menuItem = new JMenuItem("Exit");
        menuItem.setActionCommand("exit");
        menuItem.addActionListener(listener);
        menu.add(menuItem);
        menuBar.add(menu);

        menuItem = new JMenuItem("Calculadora");
        menuItem.setActionCommand("calculator");
        menuItem.addActionListener(listener);
        menu.add(menuItem);
        menuBar.add(menu);

        menu = new JMenu("Help");
        menuItem = new JMenuItem("About");
        menuItem.setActionCommand("about");
        menuItem.addActionListener(listener);
        menu.add(menuItem);
        menuBar.add(menu);

        setJMenuBar(menuBar);
    }

    private void displayCalculatorPanel() {
        if (calculatorPanel == null) {
            calculatorPanel = new CalculatorPanel();
        }
        setContentPane(calculatorPanel);
        
        
        validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equalsIgnoreCase("exit")) {
            doExit();
        } else if (actionCommand.equalsIgnoreCase("about")) {
            displayAboutDialog();

        } else if (actionCommand.equalsIgnoreCase("calculator")) {
            displayCalculatorPanel();
        }
    }

    private void doExit() {
        int answer = JOptionPane.showConfirmDialog(this, "Are you sure?", "Exit application", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (answer == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    private String aboutMessage = "Calculadora de Oussama";

    private void displayAboutDialog() {
        JOptionPane.showMessageDialog(this, aboutMessage, "About", JOptionPane.INFORMATION_MESSAGE);
    }
}
