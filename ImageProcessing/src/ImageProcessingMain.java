import java.io.InputStreamReader;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageProcessingModelImpl;
import view.ImageProcessingView;
import view.ImageTextViewImpl;

public class ImageProcessingMain {
  public static void main(String[] args) {
    ImageProcessingModelImpl model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageTextViewImpl(System.out);
    Readable in = new InputStreamReader(System.in);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model,in,view);
    controller.runProgram();

  }
}
