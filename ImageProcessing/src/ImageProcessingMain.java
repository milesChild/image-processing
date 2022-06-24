import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerConsole;
import controller.ImageProcessingControllerGUI;
import model.ImageProcessingModelImpl;
import view.ImageProcessingView;
import view.ImageProcessingViewConsole;
import view.ImageProcessingViewGUI;

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
  public static void main(String[] args) throws IOException {
    ImageProcessingModelImpl model = new ImageProcessingModelImpl();
    ImageProcessingController controller;

    if (args.length > 0) {
      Readable in = null;
      ImageProcessingView view = new ImageProcessingViewConsole(System.out);

      if (args.length == 1 && args[0].equals("-text")) {
        in = new InputStreamReader(System.in);
      }
      else if (args.length == 2 && args[0].equals("-file")) {
        String filePath = args[1];
        FileReader fileReader = null;
        if (filePath.endsWith(".txt")) {
          File file = new File(filePath);
          try {
            fileReader = new FileReader(file);
          } catch (IOException e) {
            throw new IllegalArgumentException("File not found from given path!");
          }
        }
        in = fileReader;
      }
      controller = new ImageProcessingControllerConsole(model, in, view);
      controller.runProgram();
    }
    else {
      ImageProcessingViewGUI view = new ImageProcessingViewGUI();
      controller = new ImageProcessingControllerGUI(model, view);
    }
  }
}
