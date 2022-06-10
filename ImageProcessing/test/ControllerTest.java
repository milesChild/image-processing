import org.junit.Test;

import java.io.StringReader;

import controller.ImageProcessingControllerImpl;
import controller.ImageProcessingController;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import model.PPMImage;
import view.ImageProcessingView;
import view.ImageTextViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the ImageProcessingController class & its methods.
 */
public class ControllerTest {
  PPMImage donkeyRedGrayscale =
          new PPMImage("res/predefinedImages/donkey-red-greyscale.ppm");
  PPMImage donkeyGreenGrayscale =
          new PPMImage("res/predefinedImages/donkey-green-greyscale.ppm");
  PPMImage donkeyBlueGrayscale =
          new PPMImage("res/predefinedImages/donkey-blue-greyscale.ppm");
  PPMImage donkeyValueGrayscale =
          new PPMImage("res/predefinedImages/donkey-value-greyscale.ppm");
  PPMImage donkeyIntensityGrayscale =
          new PPMImage("res/predefinedImages/donkey-intensity-greyscale.ppm");
  PPMImage donkeyLumaGrayscale =
          new PPMImage("res/predefinedImages/donkey-luma-greyscale.ppm");
  PPMImage donkeyHorizontal =
          new PPMImage("res/predefinedImages/donkey-horizontal.ppm");
  PPMImage donkeyVertical =
          new PPMImage("res/predefinedImages/donkey-vertical.ppm");
  PPMImage donkeyBrighterBy50 =
          new PPMImage("res/predefinedImages/donkey-brighten-by-50.ppm");
  PPMImage donkeyDimBy50 =
          new PPMImage("res/predefinedImages/donkey-dim-by-50.ppm");

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionNullModel() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("q");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingController controller =
            new ImageProcessingControllerImpl(null, readable, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionNullReadable() {
    Appendable appendable = new StringBuilder();
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller =
            new ImageProcessingControllerImpl(model, null, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionNullView() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("q");
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller =
            new ImageProcessingControllerImpl(model, readable, null);
  }

  // testing runProgram
  @Test
  public void testHorizontalFlipRun(){
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "horizontal-flip donkey donkeyHorizontal\n" +
            "save res/donkeyHorizontal.ppm donkeyHorizontal\n" +
            "load res/donkeyHorizontal.ppm copyHorizontal\n q");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, readable, view);
    controller.runProgram();

    assertEquals(this.donkeyHorizontal, model.getImage("donkeyHorizontal"));
    assertEquals(model.getImage("donkeyHorizontal"),
            model.getImage("copyHorizontal"));
  }

  @Test
  public void testVerticalFlipRun(){
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "vertical-flip donkey donkeyVertical\n" +
            "save res/donkeyVertical.ppm donkeyVertical\n" +
            "load res/donkeyVertical.ppm copyVertical\n q");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, readable, view);
    controller.runProgram();

    assertEquals(this.donkeyVertical, model.getImage("donkeyVertical"));
    assertEquals(model.getImage("donkeyVertical"),
            model.getImage("copyVertical"));
    assertEquals(appendable.toString(), "Program Ended.");
  }

  @Test
  public void testBrightenRun(){
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "brighten 50 donkey donkeyBrighten\n" +
            "save res/donkeyBrighten.ppm donkeyBrighten\n" +
            "load res/donkeyBrighten.ppm copyBrighten\n q");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, readable, view);
    controller.runProgram();

    assertEquals(this.donkeyBrighterBy50, model.getImage("donkeyBrighten"));
    assertEquals(model.getImage("donkeyBrighten"),
            model.getImage("copyBrighten"));
    assertEquals(appendable.toString(), "Program Ended.");
  }

  @Test
  public void testDimRun(){
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "dim 50 donkey donkeyDim\n" +
            "save res/donkeyDim.ppm donkeyDim\n" +
            "load res/donkeyDim.ppm copyDim\n q");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, readable, view);
    controller.runProgram();

    assertEquals(this.donkeyDimBy50, model.getImage("donkeyDim"));
    assertEquals(model.getImage("donkeyDim"),
            model.getImage("copyDim"));
    assertEquals(appendable.toString(), "Program Ended.");
  }

  @Test
  public void testGrayscaleRedRun(){
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "grayscale red donkey donkeyGrayscaleRed\n" +
            "save res/donkeyGrayscaleRed.ppm donkeyGrayscaleRed\n" +
            "load res/donkeyGrayscaleRed.ppm copyGrayscale\n q");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, readable, view);
    controller.runProgram();

    assertEquals(this.donkeyRedGrayscale, model.getImage("donkeyGrayscaleRed"));
    assertEquals(model.getImage("donkeyGrayscaleRed"),
            model.getImage("copyGrayscale"));
    assertEquals(appendable.toString(), "Program Ended.");
  }

  @Test
  public void testGrayscaleGreenRun(){
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "grayscale green donkey donkeyGrayscaleGreen\n" +
            "save res/donkeyGrayscaleGreen.ppm donkeyGrayscaleGreen\n" +
            "load res/donkeyGrayscaleGreen.ppm copyGrayscale\n q");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, readable, view);
    controller.runProgram();

    assertEquals(this.donkeyGreenGrayscale, model.getImage("donkeyGrayscaleGreen"));
    assertEquals(model.getImage("donkeyGrayscaleGreen"),
            model.getImage("copyGrayscale"));
    assertEquals(appendable.toString(), "Program Ended.");
  }

  @Test
  public void testGrayscaleBlueRun(){
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "grayscale blue donkey donkeyGrayscaleBlue\n" +
            "save res/donkeyGrayscaleBlue.ppm donkeyGrayscaleBlue\n" +
            "load res/donkeyGrayscaleBlue.ppm copyGrayscale\n q");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, readable, view);
    controller.runProgram();

    assertEquals(this.donkeyBlueGrayscale, model.getImage("donkeyGrayscaleBlue"));
    assertEquals(model.getImage("donkeyGrayscaleBlue"),
            model.getImage("copyGrayscale"));
    assertEquals(appendable.toString(), "Program Ended.");
  }

  @Test
  public void testGrayscaleIntensityRun(){
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "grayscale intensity donkey donkeyGrayscaleIntensity\n" +
            "save res/donkeyGrayscaleIntensity.ppm donkeyGrayscaleIntensity\n" +
            "load res/donkeyGrayscaleIntensity.ppm copyGrayscale\n q");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, readable, view);
    controller.runProgram();

    assertEquals(this.donkeyIntensityGrayscale, model.getImage("donkeyGrayscaleIntensity"));
    assertEquals(model.getImage("donkeyGrayscaleIntensity"),
            model.getImage("copyGrayscale"));
    assertEquals(appendable.toString(), "Program Ended.");
  }

  @Test
  public void testGrayscaleValueRun(){
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "grayscale value donkey donkeyGrayscaleValue\n" +
            "save res/donkeyGrayscaleValue.ppm donkeyGrayscaleValue\n" +
            "load res/donkeyGrayscaleValue.ppm copyGrayscale\n q");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, readable, view);
    controller.runProgram();

    assertEquals(this.donkeyValueGrayscale, model.getImage("donkeyGrayscaleValue"));
    assertEquals(model.getImage("donkeyGrayscaleValue"),
            model.getImage("copyGrayscale"));
    assertEquals(appendable.toString(), "Program Ended.");
  }

  @Test
  public void testGrayscaleLumaRun(){
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "grayscale luma donkey donkeyGrayscaleLuma\n" +
            "save res/donkeyGrayscaleLuma.ppm donkeyGrayscaleLuma\n" +
            "load res/donkeyGrayscaleLuma.ppm copyGrayscale\n q");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, readable, view);
    controller.runProgram();

    assertEquals(this.donkeyLumaGrayscale, model.getImage("donkeyGrayscaleLuma"));
    assertEquals(model.getImage("donkeyGrayscaleLuma"),
            model.getImage("copyGrayscale"));
    assertEquals(appendable.toString(), "Program Ended.");
  }

  @Test
  public void testInvalidInput1(){
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("loa res/predefinedImages/donkey.ppm donkey\n q");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, readable, view);
    controller.runProgram();

    assertEquals(appendable.toString(),
            "Unknown or invalid command. Try Again.\nProgram Ended.");
  }

  @Test
  public void testInvalidInputAfterValidCommand(){
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n pong\n q");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, readable, view);
    controller.runProgram();

    assertEquals(appendable.toString(),
            "Unknown or invalid command. Try Again.\nProgram Ended.");
  }
}
