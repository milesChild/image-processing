import java.io.IOException;

import javax.swing.*;

import controller.ImageProcessingControllerImplGUI;
import view.ImageGUIImpl;

public class SwingTest {
  public static void main(String[] args) throws IOException {
    ImageProcessingControllerImplGUI s = new ImageProcessingControllerImplGUI();


    try {
      // Set cross-platform Java L&F (also called "Metal")
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

    } catch (UnsupportedLookAndFeelException e) {
      // handle exception
    } catch (ClassNotFoundException e) {
      // handle exception
    } catch (InstantiationException e) {
      // handle exception
    } catch (IllegalAccessException e) {
      // handle exception
    } catch (Exception e) {
    }
  }
}
