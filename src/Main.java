/* import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class Main {
  public static void main(String[] args) throws Exception {
    JTextField field = new JTextField(30);

    ((AbstractDocument) (field.getDocument())).setDocumentFilter(new DocumentFilter() {
      public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
          throws BadLocationException {
        System.out.println("insert");
        fb.insertString(offset, string.toUpperCase(), attr);
      }

      public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr)
          throws BadLocationException {
        System.out.println("replace");
        fb.replace(offset, length, string.toUpperCase(), attr);
      }
    });

    JFrame frame = new JFrame("User Information");
    frame.getContentPane().add(field);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
} */