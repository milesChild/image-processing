package view;

import java.io.InputStreamReader;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageProcessingModelImpl;
import view.ImageProcessingView;
import view.ImageTextViewImpl;

/**
 * The main class which allows the client to run the program, providing instructions via the
 * console. See the README file for instructions on how to properly interact with the console.
 */
public class ImageProcessingMain {

  /**
   * The main method which allows the client to run the program, providing instructions via the
   * console. See the README file for instructions on how to properly interact with the console.
   * Command-line arguments are not supported in this program, the user will have to interact
   * directly with the console by passing valid commands that can be handled by the controller.
   */
  public static void main(String[] args) {
    ImageProcessingModelImpl model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageTextViewImpl(System.out);
    Readable in = new InputStreamReader(System.in);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model,in,view);
    controller.runProgram();
  }
}
