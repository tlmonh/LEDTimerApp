import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import javax.swing.JFrame;


/**
 *
 * @author Timothy
 */
public class PrinterClassGUI  implements Printable {

    private JFrame frameToPrint;

    public PrinterClassGUI(JFrame f){
        this.frameToPrint = f;
    }
    public int print(Graphics g,PageFormat pf, int page) throws PrinterException {
        if(page > 0){
            return NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(),pf.getImageableY());
        this.frameToPrint.printAll(g);

        return PAGE_EXISTS;
    }
}
