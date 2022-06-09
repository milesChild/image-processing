import org.junit.Test;

import model.ImageProcessingModelImpl;

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
