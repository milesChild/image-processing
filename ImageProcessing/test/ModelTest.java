import org.junit.Test;

import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import model.PPMImage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the ImageProcessingModel class & its methods.
 */
public class ModelTest {
  PPMImage donkeyTest;
  PPMImage donkeyRedGrayscale = new PPMImage("SmallPPMImages/donkey-red-greyscale.ppm");
  PPMImage donkeyGreenGrayscale = new PPMImage("SmallPPMImages/donkey-green-greyscale.ppm");
  PPMImage donkeyBlueGrayscale = new PPMImage("SmallPPMImages/donkey-blue-greyscale.ppm");
  PPMImage donkeyValueGrayscale = new PPMImage("SmallPPMImages/donkey-value-greyscale.ppm");
  PPMImage donkeyIntensityGrayscale =
          new PPMImage("SmallPPMImages/donkey-intensity-greyscale.ppm");
  PPMImage donkeyLumaGrayscale = new PPMImage("SmallPPMImages/donkey-luma-greyscale.ppm");
  PPMImage donkeyHorizontal = new PPMImage("SmallPPMImages/donkey-horizontal.ppm");
  PPMImage donkeyBrighterBy50 = new PPMImage("SmallPPMImages/donkey-brighten-by-50.ppm");
  PPMImage donkeyDimBy50 = new PPMImage("SmallPPMImages/donkey-dim-by-50.ppm");

  @Test
  public void testModelBrighten() {
    // Create a new model that has no currently loaded images
    ImageProcessingModelImpl model = new ImageProcessingModelImpl();

    // load donkey.ppm, brighten it by 50, and save the image
    model.load("SmallPPMImages/donkey.ppm", "donkey");
    model.brighten(50, "donkey", "donkeyBrighten");
    model.save("TestCreatedImages/donkeyBrighten.ppm", "donkeyBrighten");

    // get the image (deep copy) from the model
    PPMImage testImage = model.getImage("donkeyBrighten");

    // check if the deep copy is the same as the predefined test image
    assertEquals(this.donkeyBrighterBy50, testImage);

    // check if save worked correctly
    model.load("TestCreatedImages/donkeyBrighten.ppm", "copyBrighten");
    assertEquals(testImage, model.getImage("copyBrighten"));
  }

  @Test
  public void testModelDim() {
    // Create a new model that has no currently loaded images
    ImageProcessingModelImpl model = new ImageProcessingModelImpl();

    // load donkey.ppm, dim it by 50, and save the image
    model.load("SmallPPMImages/donkey.ppm", "donkey");
    model.dim(50, "donkey", "donkeyDim");
    model.save("TestCreatedImages/donkeyDim.ppm", "donkeyDim");

    // get the image (deep copy) from the model
    PPMImage testImage = model.getImage("donkeyDim");

    // check if the deep copy is the same as the predefined test image
    assertEquals(this.donkeyDimBy50, testImage);

    // check if save worked correctly
    model.load("TestCreatedImages/donkeyDim.ppm", "copyDim");
    assertEquals(testImage, model.getImage("copyDim"));
  }

  @Test
  public void testModelHorizontal() {
    // Create a new model that has no currently loaded images
    ImageProcessingModelImpl model = new ImageProcessingModelImpl();

    // load donkey.ppm, flip it horizontally, and save the image
    model.load("SmallPPMImages/donkey.ppm", "donkey");
    model.flipHorizontally("donkey", "donkeyHorizontal");
    model.save("TestCreatedImages/donkeyHorizontal.ppm", "donkeyHorizontal");

    // get the image (deep copy) from the model
    PPMImage testImage = model.getImage("donkeyHorizontal");

    // check if the deep copy is the same as the predefined test image
    assertEquals(this.donkeyHorizontal, testImage);

    // check if save worked correctly
    model.load("TestCreatedImages/donkeyHorizontal.ppm", "copyHorizontal");
    assertEquals(testImage, model.getImage("copyHorizontal"));
  }

  @Test
  public void testModelGrayscaleRed() {
    // Create a new model that has no currently loaded images
    ImageProcessingModelImpl model = new ImageProcessingModelImpl();

    // load donkey.ppm, grayscale it to red pixel, and save the image
    model.load("SmallPPMImages/donkey.ppm", "donkey");
    model.grayscale(ImageProcessingModel.GrayscaleTypes.RedGrayscale, "donkey",
            "donkeyRedGrayscale");
    model.save("TestCreatedImages/donkeyRedGrayscale.ppm", "donkeyRedGrayscale");

    // get the image (deep copy) from the model
    PPMImage testImage = model.getImage("donkeyRedGrayscale");

    // check if the deep copy is the same as the predefined test image
    assertEquals(this.donkeyRedGrayscale, testImage);

    // check if save worked correctly
    model.load("TestCreatedImages/donkeyRedGrayscale.ppm", "copyRed");
    assertEquals(testImage, model.getImage("copyRed"));
  }

  @Test
  public void testModelGrayscaleGreen() {
    // Create a new model that has no currently loaded images
    ImageProcessingModelImpl model = new ImageProcessingModelImpl();

    // load donkey.ppm, grayscale it to red pixel, and save the image
    model.load("SmallPPMImages/donkey.ppm", "donkey");
    model.grayscale(ImageProcessingModel.GrayscaleTypes.GreenGrayscale, "donkey",
            "donkeyGreenGrayscale");
    model.save("TestCreatedImages/donkeyGreenGrayscale.ppm", "donkeyGreenGrayscale");

    // get the image (deep copy) from the model
    PPMImage testImage = model.getImage("donkeyGreenGrayscale");

    // check if the deep copy is the same as the predefined test image
    assertEquals(this.donkeyGreenGrayscale, testImage);

    // check if save worked correctly
    model.load("TestCreatedImages/donkeyGreenGrayscale.ppm", "copyGreen");
    assertEquals(testImage, model.getImage("copyGreen"));
  }

  @Test
  public void testModelGrayscaleBlue() {
    // Create a new model that has no currently loaded images
    ImageProcessingModelImpl model = new ImageProcessingModelImpl();

    // load donkey.ppm, grayscale it to red pixel, and save the image
    model.load("SmallPPMImages/donkey.ppm", "donkey");
    model.grayscale(ImageProcessingModel.GrayscaleTypes.BlueGrayscale, "donkey",
            "donkeyBlueGrayscale");
    model.save("TestCreatedImages/donkeyBlueGrayscale.ppm", "donkeyBlueGrayscale");

    // get the image (deep copy) from the model
    PPMImage testImage = model.getImage("donkeyBlueGrayscale");

    // check if the deep copy is the same as the predefined test image
    assertEquals(this.donkeyBlueGrayscale, testImage);

    // check if save worked correctly
    model.load("TestCreatedImages/donkeyBlueGrayscale.ppm", "copyBlue");
    assertEquals(testImage, model.getImage("copyBlue"));
  }

  @Test
  public void testModelGrayscaleIntensity() {
    // Create a new model that has no currently loaded images
    ImageProcessingModelImpl model = new ImageProcessingModelImpl();

    // load donkey.ppm, grayscale it to red pixel, and save the image
    model.load("SmallPPMImages/donkey.ppm", "donkey");
    model.grayscale(ImageProcessingModel.GrayscaleTypes.IntensityGrayscale, "donkey",
            "donkeyIntensityGrayscale");
    model.save("TestCreatedImages/donkeyIntensityGrayscale.ppm", "donkeyIntensityGrayscale");

    // get the image (deep copy) from the model
    PPMImage testImage = model.getImage("donkeyIntensityGrayscale");

    // check if the deep copy is the same as the predefined test image
    assertEquals(this.donkeyIntensityGrayscale, testImage);

    // check if save worked correctly
    model.load("TestCreatedImages/donkeyIntensityGrayscale.ppm", "copyIntensity");
    assertEquals(testImage, model.getImage("copyIntensity"));
  }

  @Test
  public void testModelGrayscaleValue() {
    // Create a new model that has no currently loaded images
    ImageProcessingModelImpl model = new ImageProcessingModelImpl();

    // load donkey.ppm, grayscale it to red pixel, and save the image
    model.load("SmallPPMImages/donkey.ppm", "donkey");
    model.grayscale(ImageProcessingModel.GrayscaleTypes.ValueGrayscale, "donkey",
            "donkeyValueGrayscale");
    model.save("TestCreatedImages/donkeyValueGrayscale.ppm", "donkeyValueGrayscale");

    // get the image (deep copy) from the model
    PPMImage testImage = model.getImage("donkeyValueGrayscale");

    // check if the deep copy is the same as the predefined test image
    assertEquals(this.donkeyValueGrayscale, testImage);

    // check if save worked correctly
    model.load("TestCreatedImages/donkeyValueGrayscale.ppm", "copyValue");
    assertEquals(testImage, model.getImage("copyValue"));
  }

  @Test
  public void testModelGrayscaleLuma() {
    // Create a new model that has no currently loaded images
    ImageProcessingModelImpl model = new ImageProcessingModelImpl();

    // load donkey.ppm, grayscale it to red pixel, and save the image
    model.load("SmallPPMImages/donkey.ppm", "donkey");
    model.grayscale(ImageProcessingModel.GrayscaleTypes.LumaGrayscale, "donkey",
            "donkeyLumaGrayscale");
    model.save("TestCreatedImages/donkeyLumaGrayscale.ppm", "donkeyLumaGrayscale");

    // get the image (deep copy) from the model
    PPMImage testImage = model.getImage("donkeyLumaGrayscale");

    // check if the deep copy is the same as the predefined test image
    assertEquals(this.donkeyLumaGrayscale, testImage);

    // check if save worked correctly
    model.load("TestCreatedImages/donkeyLumaGrayscale.ppm", "copyLuma");
    assertEquals(testImage, model.getImage("copyLuma"));
  }
}
