import org.junit.Test;

import java.io.InputStreamReader;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageProcessingModelImpl;
import view.ImageProcessingView;
import view.ImageTextViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the ImageProcessingModel class & its methods.
 */
public class ModelTest {
  @Test
  public void testModel() {
    ImageProcessingModelImpl model = new ImageProcessingModelImpl();
    model.load("Koala.ppm", "koala");
    model.flipHorizontally("koala", "horizontalKoala");
    model.save("src/horizontalKoala.ppm", "horizontalKoala");
  }

}
