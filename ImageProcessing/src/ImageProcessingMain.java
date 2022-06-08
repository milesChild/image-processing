import java.io.InputStreamReader;

import commands.ImageProcessingCommand;
import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageProcessingView;
import view.ImageTextViewImpl;

public class ImageProcessingMain {
  public static void main(String[] args) {
    ImageProcessingModelImpl model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageTextViewImpl(System.out);
    Readable in = new InputStreamReader(System.in);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model,in,view);

  }
}
