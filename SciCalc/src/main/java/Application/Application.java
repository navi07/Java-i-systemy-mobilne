package Application;

import Controlers.Message;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Application extends JFrame {

    public JPanel panel;
    private JList functionList;
    private JButton evalButton;
    private JTextField inputField;
    private JTextArea textArea;
    private JScrollPane scrollContainerPane;

    List<String> cache =  new ArrayList();
    int amount = 0;


    public Application(){
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
                    switch (functionList.getSelectedIndex()) {
                        case 0:
                            inputField.setText("cos()");
                            inputField.requestFocus();
                            inputField.setCaretPosition(inputField.getText().length() - 1);
                            break;
                        case 1:
                            inputField.setText("ctg()");
                            inputField.requestFocus();
                            inputField.setCaretPosition(inputField.getText().length() - 1);
                            break;
                        case 2:
                            inputField.setText("sec()");
                            inputField.requestFocus();
                            inputField.setCaretPosition(inputField.getText().length() - 1);
                            break;
                        case 3:
                            inputField.setText("log( ,)");
                            inputField.requestFocus();
                            inputField.setCaretPosition(inputField.getText().length() - 2);
                            break;
                        case 4:
                            inputField.setText("mod( ,)");
                            inputField.requestFocus();
                            inputField.setCaretPosition(inputField.getText().length() - 2);
                            break;
                        case 5:
                            inputField.setText("Euler( ,)");
                            inputField.requestFocus();
                            inputField.setCaretPosition(inputField.getText().length() - 2);
                            break;
                        case 6:
                            inputField.setText("pi");
                            inputField.requestFocus();
                            inputField.setCaretPosition(inputField.getText().length());
                            break;
                        case 7:
                            inputField.setText("[Ks]");
                            inputField.requestFocus();
                            inputField.setCaretPosition(inputField.getText().length());
                            break;
                        case 8:
                            inputField.setText("[Ll]");
                            inputField.requestFocus();
                            inputField.setCaretPosition(inputField.getText().length());
                            break;
                        case 9:
                            if (!cache.isEmpty()) {
                                textArea.append(new Message().msg(cache.get(cache.size() - amount - 1)));
                            } else {
                                JOptionPane.showMessageDialog(null, "There aren't previous operations",
                                        "No last result",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                            break;
                    }
                }
            }
        });
    }


    public static void main(String[] args) {
        new Application();

    }

}
