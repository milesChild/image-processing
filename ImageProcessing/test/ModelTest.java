import static org.junit.Assert.assertEquals;

/**
 * Tests for the ImageProcessingModel class & its methods.
 */
public class ModelTest {
//  ProcessableImageImpl donkeyRedGrayscale =
//          new ProcessableImageImpl("res/predefinedImages/donkey-red-grayscale.ppm");
//  ProcessableImageImpl donkeyGreenGrayscale =
//          new ProcessableImageImpl("res/predefinedImages/donkey-green-grayscale.ppm");
//  ProcessableImageImpl donkeyBlueGrayscale =
//          new ProcessableImageImpl("res/predefinedImages/donkey-blue-grayscale.ppm");
//  ProcessableImageImpl donkeyValueGrayscale =
//          new ProcessableImageImpl("res/predefinedImages/donkey-value-grayscale.ppm");
//  ProcessableImageImpl donkeyIntensityGrayscale =
//          new ProcessableImageImpl("res/predefinedImages/donkey-intensity-grayscale.ppm");
//  ProcessableImageImpl donkeyLumaGrayscale =
//          new ProcessableImageImpl("res/predefinedImages/donkey-luma-grayscale.ppm");
//  ProcessableImageImpl donkeyHorizontal =
//          new ProcessableImageImpl("res/predefinedImages/donkey-horizontal.ppm");
//  ProcessableImageImpl donkeyBrighterBy50 =
//          new ProcessableImageImpl("res/predefinedImages/donkey-brighten-by-50.ppm");
//  ProcessableImageImpl donkeyDimBy50 =
//          new ProcessableImageImpl("res/predefinedImages/donkey-dim-by-50.ppm");
//
//  @Test (expected = IllegalArgumentException.class)
//  public void testNullModel() {
//    ImageProcessingModel model = new ImageProcessingModelImpl(null);
//  }
//
//  @Test
//  public void testModelBrighten() {
//    // Create a new model that has no currently loaded images
//    ImageProcessingModel model = new ImageProcessingModelImpl();
//
//    // load donkey.ppm, brighten it by 50, and save the image
//    model.load("res/predefinedImages/donkey.ppm", "donkey");
//    model.brighten(50, "donkey", "donkeyBrighten");
//    model.save("res/testImages/donkeyBrighten.ppm", "donkeyBrighten");
//
//    // get the image (deep copy) from the model
//    ProcessableImageImpl testImage = model.getImage("donkeyBrighten");
//
//    // check if the deep copy is the same as the predefined test image
//    assertEquals(this.donkeyBrighterBy50, testImage);
//
//    // check if save worked correctly
//    model.load("res/testImages/donkeyBrighten.ppm", "copyBrighten");
//    assertEquals(testImage, model.getImage("copyBrighten"));
//  }
//
//  @Test
//  public void testModelDim() {
//    // Create a new model that has no currently loaded images
//    ImageProcessingModel model = new ImageProcessingModelImpl();
//
//    // load donkey.ppm, dim it by 50, and save the image
//    model.load("res/predefinedImages/donkey.ppm", "donkey");
//    model.dim(50, "donkey", "donkeyDim");
//    model.save("res/testImages/donkeyDim.ppm", "donkeyDim");
//
//    // get the image (deep copy) from the model
//    ProcessableImageImpl testImage = model.getImage("donkeyDim");
//
//    // check if the deep copy is the same as the predefined test image
//    assertEquals(this.donkeyDimBy50, testImage);
//
//    // check if save worked correctly
//    model.load("res/testImages/donkeyDim.ppm", "copyDim");
//    assertEquals(testImage, model.getImage("copyDim"));
//  }
//
//  @Test
//  public void testModelHorizontal() {
//    // Create a new model that has no currently loaded images
//    ImageProcessingModel model = new ImageProcessingModelImpl();
//
//    // load donkey.ppm, flip it horizontally, and save the image
//    model.load("res/predefinedImages/donkey.ppm", "donkey");
//    model.flipHorizontally("donkey", "donkeyHorizontal");
//    model.save("res/testImages/donkeyHorizontal.ppm", "donkeyHorizontal");
//
//    // get the image (deep copy) from the model
//    ProcessableImageImpl testImage = model.getImage("donkeyHorizontal");
//
//    // check if the deep copy is the same as the predefined test image
//    assertEquals(this.donkeyHorizontal, testImage);
//
//    // check if save worked correctly
//    model.load("res/testImages/donkeyHorizontal.ppm", "copyHorizontal");
//    assertEquals(testImage, model.getImage("copyHorizontal"));
//  }
//
//  @Test
//  public void testModelGrayscaleRed() {
//    // Create a new model that has no currently loaded images
//    ImageProcessingModel model = new ImageProcessingModelImpl();
//
//    // load donkey.ppm, grayscale it to red pixel, and save the image
//    model.load("res/predefinedImages/donkey.ppm", "donkey");
//    model.grayscale(ImageProcessingModel.GrayscaleTypes.RedGrayscale, "donkey",
//            "donkeyRedGrayscale");
//    model.save("res/testImages/donkeyRedGrayscale.ppm", "donkeyRedGrayscale");
//
//    // get the image (deep copy) from the model
//    ProcessableImageImpl testImage = model.getImage("donkeyRedGrayscale");
//
//    // check if the deep copy is the same as the predefined test image
//    assertEquals(this.donkeyRedGrayscale, testImage);
//
//    // check if save worked correctly
//    model.load("res/testImages/donkeyRedGrayscale.ppm", "copyRed");
//    assertEquals(testImage, model.getImage("copyRed"));
//  }
//
//  @Test
//  public void testModelGrayscaleGreen() {
//    // Create a new model that has no currently loaded images
//    ImageProcessingModel model = new ImageProcessingModelImpl();
//
//    // load donkey.ppm, grayscale it to green pixel, and save the image
//    model.load("res/predefinedImages/donkey.ppm", "donkey");
//    model.grayscale(ImageProcessingModel.GrayscaleTypes.GreenGrayscale, "donkey",
//            "donkeyGreenGrayscale");
//    model.save("res/testImages/donkeyGreenGrayscale.ppm", "donkeyGreenGrayscale");
//
//    // get the image (deep copy) from the model
//    ProcessableImageImpl testImage = model.getImage("donkeyGreenGrayscale");
//
//    // check if the deep copy is the same as the predefined test image
//    assertEquals(this.donkeyGreenGrayscale, testImage);
//
//    // check if save worked correctly
//    model.load("res/testImages/donkeyGreenGrayscale.ppm", "copyGreen");
//    assertEquals(testImage, model.getImage("copyGreen"));
//  }
//
//  @Test
//  public void testModelGrayscaleBlue() {
//    // Create a new model that has no currently loaded images
//    ImageProcessingModel model = new ImageProcessingModelImpl();
//
//    // load donkey.ppm, grayscale it to blue pixel, and save the image
//    model.load("res/predefinedImages/donkey.ppm", "donkey");
//    model.grayscale(ImageProcessingModel.GrayscaleTypes.BlueGrayscale, "donkey",
//            "donkeyBlueGrayscale");
//    model.save("res/testImages/donkeyBlueGrayscale.ppm", "donkeyBlueGrayscale");
//
//    // get the image (deep copy) from the model
//    ProcessableImageImpl testImage = model.getImage("donkeyBlueGrayscale");
//
//    // check if the deep copy is the same as the predefined test image
//    assertEquals(this.donkeyBlueGrayscale, testImage);
//
//    // check if save worked correctly
//    model.load("res/testImages/donkeyBlueGrayscale.ppm", "copyBlue");
//    assertEquals(testImage, model.getImage("copyBlue"));
//  }
//
//  @Test
//  public void testModelGrayscaleIntensity() {
//    // Create a new model that has no currently loaded images
//    ImageProcessingModel model = new ImageProcessingModelImpl();
//
//    // load donkey.ppm, grayscale it to intensity, and save the image
//    model.load("res/predefinedImages/donkey.ppm", "donkey");
//    model.grayscale(ImageProcessingModel.GrayscaleTypes.IntensityGrayscale, "donkey",
//            "donkeyIntensityGrayscale");
//    model.save("res/testImages/donkeyIntensityGrayscale.ppm", "donkeyIntensityGrayscale");
//
//    // get the image (deep copy) from the model
//    ProcessableImageImpl testImage = model.getImage("donkeyIntensityGrayscale");
//
//    // check if the deep copy is the same as the predefined test image
//    assertEquals(this.donkeyIntensityGrayscale, testImage);
//
//    // check if save worked correctly
//    model.load("res/testImages/donkeyIntensityGrayscale.ppm", "copyIntensity");
//    assertEquals(testImage, model.getImage("copyIntensity"));
//  }
//
//  @Test
//  public void testModelGrayscaleValue() {
//    // Create a new model that has no currently loaded images
//    ImageProcessingModel model = new ImageProcessingModelImpl();
//
//    // load donkey.ppm, grayscale it to value, and save the image
//    model.load("res/predefinedImages/donkey.ppm", "donkey");
//    model.grayscale(ImageProcessingModel.GrayscaleTypes.ValueGrayscale, "donkey",
//            "donkeyValueGrayscale");
//    model.save("res/testImages/donkeyValueGrayscale.ppm", "donkeyValueGrayscale");
//
//    // get the image (deep copy) from the model
//    ProcessableImageImpl testImage = model.getImage("donkeyValueGrayscale");
//
//    // check if the deep copy is the same as the predefined test image
//    assertEquals(this.donkeyValueGrayscale, testImage);
//
//    // check if save worked correctly
//    model.load("res/testImages/donkeyValueGrayscale.ppm", "copyValue");
//    assertEquals(testImage, model.getImage("copyValue"));
//  }
//
//  @Test
//  public void testModelGrayscaleLuma() {
//    // Create a new model that has no currently loaded images
//    ImageProcessingModel model = new ImageProcessingModelImpl();
//
//    // load donkey.ppm, grayscale it to luma, and save the image
//    model.load("res/predefinedImages/donkey.ppm", "donkey");
//    model.grayscale(ImageProcessingModel.GrayscaleTypes.LumaGrayscale, "donkey",
//            "donkeyLumaGrayscale");
//    model.save("res/testImages/donkeyLumaGrayscale.ppm", "donkeyLumaGrayscale");
//
//    // get the image (deep copy) from the model
//    ProcessableImageImpl testImage = model.getImage("donkeyLumaGrayscale");
//
//    // check if the deep copy is the same as the predefined test image
//    assertEquals(this.donkeyLumaGrayscale, testImage);
//
//    // check if save worked correctly
//    model.load("res/testImages/donkeyLumaGrayscale.ppm", "copyLuma");
//    assertEquals(testImage, model.getImage("copyLuma"));
//  }
}
