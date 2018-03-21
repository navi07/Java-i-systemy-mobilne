package Controlers;

import org.mariuszgromada.math.mxparser.Expression;
import java.text.MessageFormat;

public class Message extends MessageFormat{
        public Message() {
            super("");
        }

        public String msg(String formula){

            Expression expression = new Expression(formula);
            Double result = expression.calculate() ;

            try {
                if(Double.toString(result) == "NaN") {
                    throw new MyException();
            }
            else {
                    Object[] objects = {formula, result};
                    MessageFormat messageFormat = new MessageFormat("{0}\n\t = {1} \n-----\n");
                    return messageFormat.format(objects);
                }
        } catch(MyException a) {}
          return "";
        }
}
