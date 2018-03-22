package Application;

import Controlers.Message;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Application extends JFrame {

    public JPanel panel;
    private JList<Function> functionList;
    private JButton evalButton;
    private JTextField inputField;
    private JTextArea textArea;
    private JScrollPane scrollContainerPane;

    DefaultListModel<Function> listModel = new DefaultListModel<>();
    List<String> cache =  new ArrayList();
    int amount = 0;


    public Application(){
        functionList.setModel(listModel);
        makeFunctions();

        JFrame frame = new JFrame("Scientific Calculator");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 600);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu options = new JMenu("Options");
        menuBar.add(options);

        JMenuItem reset = new JMenuItem("Reset");
        JMenuItem exit = new JMenuItem("Exit");
        options.add(reset);
        options.add(exit);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                inputField.setText("");
                cache.clear();
            }
        });

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cache.add(inputField.getText());
                amount = 0;
                textArea.append(new Message().msg(inputField.getText()));
                inputField.setText(null);
            }
        });

        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode() == KeyEvent.VK_UP){
                    if(!cache.isEmpty()){
                        inputField.setText(cache.get(cache.size() - amount - 1));

                        if(amount < cache.size() - 1){
                            amount++;
                        }
                    }
                }
            }
        });

        evalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cache.add(inputField.getText());
                amount = 0;
                textArea.append(new Message().msg(inputField.getText()));
                inputField.setText(null);
            }
        });


        functionList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int position = functionList.getSelectedIndex();
                    inputField.setText(listModel.getElementAt(position).getEquivalent());
                    inputField.requestFocus();

                    if(inputField.getText().contains("()")) {
                        inputField.setCaretPosition(inputField.getText().length() - 1);
                    }
                    else if (inputField.getText().contains(",")) {
                        inputField.setCaretPosition(inputField.getText().length() - 2);
                    }
                    else if (inputField.getText().equals("")) {
                        if (!cache.isEmpty()) {
                            inputField.setText(new Message().msg2(cache.get(cache.size() - amount - 1)));

                        } else {
                            JOptionPane.showMessageDialog(null, "There aren't previous operations",
                                    "No last result",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else {
                        inputField.setCaretPosition(inputField.getText().length());
                    }

                }
            }
        });
    }

    public void makeFunctions(){
        Function cos = new Function("Trigonometric cosine function", "cos()");
        listModel.addElement(cos);
        Function ctg = new Function("Trigonometric cotangent function", "ctg()");
        listModel.addElement(ctg);
        Function sec = new Function("Trigonometric secant function", "sec()");
        listModel.addElement(sec);
        Function log = new Function("Logarithm function", "log( ,)");
        listModel.addElement(log);
        Function mod = new Function("Modulo function", "mod( ,)");
        listModel.addElement(mod);
        Function binomial = new Function("Binomial coefficient function", "C( ,)");
        listModel.addElement(binomial);
        Function pi = new Function("PI number", "pi");
        listModel.addElement(pi);
        Function ks = new Function("Sierpinski's constant", "[Ks]");
        listModel.addElement(ks);
        Function ll = new Function("Laplace limit", "[Ll]");
        listModel.addElement(ll);
        Function lastResult = new Function("Last result", "");
        listModel.addElement(lastResult);
    }

    public static void main(String[] args) {
        new Application();
    }

}
