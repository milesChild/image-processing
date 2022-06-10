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
  PPMImage donkeyRedGrayscale = new PPMImage("SmallPPMImages/donkey-red-greyscale.ppm");
  PPMImage donkeyGreenGrayscale = new PPMImage("SmallPPMImages/donkey-green-greyscale.ppm");
  PPMImage donkeyBlueGrayscale = new PPMImage("SmallPPMImages/donkey-blue-greyscale.ppm");
  PPMImage donkeyValueGrayscale = new PPMImage("SmallPPMImages/donkey-value-greyscale.ppm");
  PPMImage donkeyIntensityGrayscale =
          new PPMImage("SmallPPMImages/donkey-intensity-greyscale.ppm");
  PPMImage donkeyLumaGrayscale = new PPMImage("SmallPPMImages/donkey-luma-greyscale.ppm");
  PPMImage donkeyHorizontal = new PPMImage("SmallPPMImages/donkey-horizontal.ppm");
  PPMImage donkeyVertical = new PPMImage("SmallPPMImages/donkey-vertical.ppm");
  PPMImage donkeyBrighterBy50 = new PPMImage("SmallPPMImages/donkey-brighten-by-50.ppm");
  PPMImage donkeyDimBy50 = new PPMImage("SmallPPMImages/donkey-dim-by-50.ppm");

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
    Readable readable = new StringReader("load SmallPPMimages/donkey.ppm donkey\n" +
            "horizontal-flip donkey donkeyHorizontal\n" +
            "save TestCreatedImages/donkeyHorizontal.ppm donkeyHorizontal\n" +
            "load TestCreatedImages/donkeyHorizontal.ppm copyHorizontal\n q");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, readable, view);
    controller.runProgram();

    assertEquals(this.donkeyHorizontal, model.getImage("donkeyHorizontal"));
    assertEquals(model.getImage("donkeyHorizontal"),
            model.getImage("copyHorizontal"));
    assertEquals(appendable.toString(), "Program Ended.");
  }

  @Test
  public void testVerticalFlipRun(){
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load SmallPPMimages/donkey.ppm donkey\n" +
            "vertical-flip donkey donkeyVertical\n" +
            "save TestCreatedImages/donkeyVertical.ppm donkeyVertical\n" +
            "load TestCreatedImages/donkeyVertical.ppm copyVertical\n q");
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
    Readable readable = new StringReader("load SmallPPMimages/donkey.ppm donkey\n" +
            "brighten 50 donkey donkeyBrighten\n" +
            "save TestCreatedImages/donkeyBrighten.ppm donkeyBrighten\n" +
            "load TestCreatedImages/donkeyBrighten.ppm copyBrighten\n q");
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
    Readable readable = new StringReader("load SmallPPMimages/donkey.ppm donkey\n" +
            "dim 50 donkey donkeyDim\n" +
            "save TestCreatedImages/donkeyDim.ppm donkeyDim\n" +
            "load TestCreatedImages/donkeyDim.ppm copyDim\n q");
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
    Readable readable = new StringReader("load SmallPPMimages/donkey.ppm donkey\n" +
            "grayscale red donkey donkeyGrayscaleRed\n" +
            "save TestCreatedImages/donkeyGrayscaleRed.ppm donkeyGrayscaleRed\n" +
            "load TestCreatedImages/donkeyGrayscaleRed.ppm copyGrayscale\n q");
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
    Readable readable = new StringReader("load SmallPPMimages/donkey.ppm donkey\n" +
            "grayscale green donkey donkeyGrayscaleGreen\n" +
            "save TestCreatedImages/donkeyGrayscaleGreen.ppm donkeyGrayscaleGreen\n" +
            "load TestCreatedImages/donkeyGrayscaleGreen.ppm copyGrayscale\n q");
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
    Readable readable = new StringReader("load SmallPPMimages/donkey.ppm donkey\n" +
            "grayscale blue donkey donkeyGrayscaleBlue\n" +
            "save TestCreatedImages/donkeyGrayscaleBlue.ppm donkeyGrayscaleBlue\n" +
            "load TestCreatedImages/donkeyGrayscaleBlue.ppm copyGrayscale\n q");
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
    Readable readable = new StringReader("load SmallPPMimages/donkey.ppm donkey\n" +
            "grayscale intensity donkey donkeyGrayscaleIntensity\n" +
            "save TestCreatedImages/donkeyGrayscaleIntensity.ppm donkeyGrayscaleIntensity\n" +
            "load TestCreatedImages/donkeyGrayscaleIntensity.ppm copyGrayscale\n q");
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
    Readable readable = new StringReader("load SmallPPMimages/donkey.ppm donkey\n" +
            "grayscale value donkey donkeyGrayscaleValue\n" +
            "save TestCreatedImages/donkeyGrayscaleValue.ppm donkeyGrayscaleValue\n" +
            "load TestCreatedImages/donkeyGrayscaleValue.ppm copyGrayscale\n q");
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
    Readable readable = new StringReader("load SmallPPMimages/donkey.ppm donkey\n" +
            "grayscale luma donkey donkeyGrayscaleLuma\n" +
            "save TestCreatedImages/donkeyGrayscaleLuma.ppm donkeyGrayscaleLuma\n" +
            "load TestCreatedImages/donkeyGrayscaleLuma.ppm copyGrayscale\n q");
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
    Readable readable = new StringReader("loa SmallPPMimages/donkey.ppm donkey\n q");
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
    Readable readable = new StringReader("load SmallPPMimages/donkey.ppm donkey\n pong\n q");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, readable, view);
    controller.runProgram();

    assertEquals(appendable.toString(),
            "Unknown or invalid command. Try Again.\nProgram Ended.");
  }
}
