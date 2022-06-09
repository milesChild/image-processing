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
    //model.dim(50,"Koala.ppm", "penis2");
    //model.brighten(30,"Koala.ppm", "penis");
    //model.flipVertically("Koala.ppm", "penis3");
    model.flipHorizontally("koala", "horizontalKoala");
    model.save("src/horizontalKoala.ppm", "horizontalKoala");
  }

}
