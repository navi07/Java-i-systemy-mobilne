package Controlers;
import javax.swing.*;

public class MyException extends RuntimeException {
        public MyException() {
            JOptionPane.showMessageDialog(null, "Input correct data !" ,"Exception",
                    JOptionPane.ERROR_MESSAGE);
    }
}