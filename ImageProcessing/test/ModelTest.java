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
  PPMImage henockImage;
  PPMImage henockImageCopy;
  PPMImage koala;
  PPMImage koalaRedGrayscale;
  PPMImage koalaGreenGrayscale;
  PPMImage koalaBlueGrayscale;
  PPMImage koalaIntensityGrayscale;
  PPMImage koalaLumaGrayscale;
  PPMImage koalaValueGrayscale;
  PPMImage koalaHorizontal;
  PPMImage koalaVertical;
  PPMImage koalaVerticalHorizontal;
  PPMImage koalaHorizontalVertical;
  PPMImage koalaBrighterBy50;

  public void init() {
    this.henockImage = new PPMImage("PPMimages/Henock.ppm");
    this.henockImageCopy = new PPMImage(henockImage);

    this.koala = new PPMImage("PPMimages/Koala.ppm");
    this.koalaRedGrayscale = new PPMImage("PPMimages/koala-red-greyscale.ppm");
    this.koalaGreenGrayscale = new PPMImage("PPMimages/koala-green-greyscale.ppm");
    this.koalaBlueGrayscale = new PPMImage("PPMimages/koala-blue-greyscale.ppm");
    //this.koalaIntensityGrayscale = new PPMImage("PPMimages/koala-intensity-greyscale.ppm");
    //this.koalaLumaGrayscale = new PPMImage("PPMimages/koala-luma-greyscale.ppm");
    this.koalaValueGrayscale = new PPMImage("PPMimages/koala-value-greyscale.ppm");
    this.koalaHorizontal = new PPMImage("PPMimages/koala-horizontal.ppm");
    this.koalaVertical = new PPMImage("PPMimages/koala-horizontal.ppm");
    this.koalaHorizontalVertical = new PPMImage("PPMimages/koala-horizontal-vertical.ppm");
    this.koalaVerticalHorizontal = new PPMImage("PPMimages/koala-vertical-horizontal.ppm");
    this.koalaBrighterBy50 = new PPMImage("PPMimages/koala-brighter-by-50.ppm");
  }

  @Test
  public void testCopy() {
    this.init();
    assertEquals(henockImage, henockImageCopy);
    assertEquals(henockImageCopy, henockImage);
  }

  @Test
  public void testBrighten(){
    this.init();
    this.koala.brighten(50);
    assertTrue(this.koala.equals(koalaBrighterBy50));
  }

  @Test
  public void testGrayscale(){
    this.init();
    this.koala.grayscale(ImageProcessingModel.GrayscaleTypes.RedGrayscale);
    assertTrue(this.koala.equals(koalaRedGrayscale));

    this.init();
    this.koala.grayscale(ImageProcessingModel.GrayscaleTypes.GreenGrayscale);
    assertTrue(this.koala.equals(koalaGreenGrayscale));

    this.init();
    this.koala.grayscale(ImageProcessingModel.GrayscaleTypes.BlueGrayscale);
    assertTrue(this.koala.equals(koalaBlueGrayscale));

//    this.init();
//    this.koala.grayscale(ImageProcessingModel.GrayscaleTypes.IntensityGrayscale);
//    assertTrue(this.koala.equals(koalaIntensityGrayscale));

    this.init();
    this.koala.grayscale(ImageProcessingModel.GrayscaleTypes.ValueGrayscale);
    assertTrue(this.koala.equals(koalaValueGrayscale));

//    this.init();
//    this.koala.grayscale(ImageProcessingModel.GrayscaleTypes.LumaGrayscale);
//    assertTrue(this.koala.equals(koalaLumaGrayscale));
  }

  @Test
  public void testFlipHorizontal() {
    this.init();
    this.koala.flip(ImageProcessingModel.Orientations.Horizontal);
    assertTrue(this.koala.equals(koalaHorizontal));
  }

  @Test
  public void testFlipVertical() {
    this.init();
    this.koala.flip(ImageProcessingModel.Orientations.Vertical);
    assertTrue(this.koala.equals(koalaVertical));
  }

  @Test
  public void testFlipHorizontalVertical() {
    this.init();
    this.koala.flip(ImageProcessingModel.Orientations.Horizontal);
    this.koala.flip(ImageProcessingModel.Orientations.Vertical);
    assertTrue(this.koala.equals(koalaHorizontalVertical));
  }

  @Test
  public void testFlipVerticalHorizontal() {
    this.init();
    this.koala.flip(ImageProcessingModel.Orientations.Vertical);
    this.koala.flip(ImageProcessingModel.Orientations.Horizontal);
    assertTrue(this.koala.equals(koalaVerticalHorizontal));
  }
  @Test
  public void testModelHorizontal() {
    this.init();
    // Create a new model that has no currently loaded images
    ImageProcessingModelImpl model = new ImageProcessingModelImpl();

    // load Koala.ppm, flip it horizontally, and save the image
    model.load("PPMimages/Koala.ppm", "koala");
    model.flipHorizontally("koala", "horizontalKoalaTest");
    model.save("TestOutputFiles/horizontalKoalaTest.ppm", "horizontalKoalaTest");

    // get the image (deep copy) from the model
    PPMImage testImage = model.getImage("horizontalKoalaTest");

    // check if the deep copy is the same as the predefined test image
    assertTrue(this.koalaHorizontal.equals(testImage));
  }

  @Test
  public void testModelVertical(){
    this.init();
    // Create a new model that has no currently loaded images
    ImageProcessingModelImpl model = new ImageProcessingModelImpl();

    // load Koala.ppm, flip it vertically, and save the image
    model.load("PPMimages/Koala.ppm", "koala");
    model.flipVertically("koala", "verticalKoalaTest");
    model.save("TestOutputFiles/verticalKoalaTest.ppm", "verticalKoalaTest");

    // get the image (deep copy) from the model
    PPMImage testImage = model.getImage("verticalKoalaTest");

    // check if the deep copy is the same as the predefined test image
    assertTrue(this.koalaVertical.equals(testImage));
  }

  @Test
  public void testModelHorizontalVertical(){
    this.init();
    // Create a new model that has no currently loaded images
    ImageProcessingModelImpl model = new ImageProcessingModelImpl();

    // load Koala.ppm, flip it vertically, and save the image
    model.load("PPMimages/Koala.ppm", "koala");
    model.flipVertically("koala", "verticalKoalaTest");
    model.flipHorizontally("verticalKoalaTest", "horizontalVerticalKoalaTest");
    model.save("TestOutputFiles/horizontalVerticalKoalaTest.ppm",
            "horizontalVerticalKoalaTest");

    // get the image (deep copy) from the model
    PPMImage testImage = model.getImage("horizontalVerticalKoalaTest");

    // check if the deep copy is the same as the predefined test image
    assertTrue(this.koalaHorizontalVertical.equals(testImage));
  }

  @Test
  public void testModelVerticalHorizontal(){
    this.init();
    // Create a new model that has no currently loaded images
    ImageProcessingModelImpl model = new ImageProcessingModelImpl();

    // load Koala.ppm, flip it vertically, and save the image
    model.load("PPMimages/Koala.ppm", "koala");
    model.flipHorizontally("koala", "horizontalKoalaTest");
    model.flipVertically("horizontalKoalaTest", "verticalHorizontalKoalaTest");
    model.save("TestOutputFiles/verticalHorizontalKoalaTest.ppm",
            "verticalHorizontalKoalaTest");

    // get the image (deep copy) from the model
    PPMImage testImage = model.getImage("verticalHorizontalKoalaTest");

    // check if the deep copy is the same as the predefined test image
    assertTrue(this.koalaVerticalHorizontal.equals(testImage));
  }



}
