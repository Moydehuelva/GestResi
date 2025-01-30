
package Clases;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author moyde
 */
public class Verificaciones {


    public void ComprobarNum(KeyEvent evt) {
// solo numeros
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }


}
