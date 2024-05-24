package cat.proven.calculator.views;

import cat.proven.calculator.model.Calculator;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author ProvenSoft
 */
public class MainFrame extends JFrame implements ActionListener {  //TODO

    private Calculator model;
    private HomePanel homePanel;

    //TODO add attributes
    private ActionListener listener;

    public MainFrame(Calculator model) {

        listener = this;

        initComponents();
    }

    private void initComponents() {
        //TODO create components
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                doExit();
            }
        });
        setUpMenu();
        JLabel welcome = new JLabel("Welcome to calculator (c)ProvenSoft 2024");

        setSize(500, 500);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setTitle("Calculator Program");
        add(welcome, BorderLayout.CENTER);

    }



    private void setUpMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu;
        JMenuItem menuItem;

        menu = new JMenu("File");
        menuItem = new JMenuItem("Home");
        menuItem.setActionCommand("home");
        menuItem.addActionListener(listener);
        menu.add(menuItem);
        menuBar.add(menu);

        menuItem = new JMenuItem("Exit");
        menuItem.setActionCommand("exit");
        menuItem.addActionListener(listener);
        menu.add(menuItem);
        menuBar.add(menu);

        menu = new JMenu("Panels");
        menuItem = new JMenuItem("Calculation Panel");
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
        if (homePanel == null) {
            homePanel = new HomePanel();
        }
        setContentPane(homePanel);
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
        } else if (actionCommand.equalsIgnoreCase("home")) {
            
        }
    }

    private void doExit() {
        int answer = JOptionPane.showConfirmDialog(this, "Are you sure?", "Exit application", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (answer == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }
    private String aboutMessage = "Calculator  ProvenSoft "
            + "Made by Oussama";

    private void displayAboutDialog() {
        JOptionPane.showMessageDialog(this, aboutMessage, "About", JOptionPane.INFORMATION_MESSAGE);
    }
    


}
