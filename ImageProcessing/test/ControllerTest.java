import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import controller.ImageProcessingControllerImpl;
import controller.ImageProcessingController;
import model.ProcessableImageImpl;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageProcessingView;
import view.ImageTextViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the ImageProcessingController class & its methods.
 */
public class ControllerTest {
  ProcessableImageImpl donkeyRedGrayscale;
  ProcessableImageImpl donkeyGreenGrayscale;
  ProcessableImageImpl donkeyBlueGrayscale;
  ProcessableImageImpl donkeyValueGrayscale;
  ProcessableImageImpl donkeyIntensityGrayscale;
  ProcessableImageImpl donkeyLumaGrayscale;
  ProcessableImageImpl donkeyHorizontal;
  ProcessableImageImpl donkeyVertical;
  ProcessableImageImpl donkeyBrighterBy50;
  ProcessableImageImpl donkeyDimBy50;
  ProcessableImageImpl donkeySepiaFilter;
  ProcessableImageImpl donkeyGrayscaleFilter;
  ProcessableImageImpl donkeyBlurFilter;
  ProcessableImageImpl donkeySharpenFilter;

  @Before
  public void init() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader(
            "load res/predefinedImages/donkey-red-greyscale.ppm donkeyRedGrayscale\n" +
            "load res/predefinedImages/donkey-green-greyscale.ppm donkeyGreenGrayscale\n" +
            "load res/predefinedImages/donkey-blue-greyscale.ppm donkeyBlueGrayscale\n" +
            "load res/predefinedImages/donkey-value-greyscale.ppm donkeyValueGrayscale\n" +
            "load res/predefinedImages/donkey-intensity-greyscale.ppm donkeyIntensityGrayscale\n" +
            "load res/predefinedImages/donkey-luma-greyscale.ppm donkeyLumaGrayscale\n" +
            "load res/predefinedImages/donkey-horizontal.ppm donkeyHorizontal\n" +
            "load res/predefinedImages/donkey-vertical.ppm donkeyVertical\n" +
            "load res/predefinedImages/donkey-brighten-by-50.ppm donkeyBrighten\n" +
            "load res/predefinedImages/donkey-dim-by-50.ppm donkeyDim\n" +
            "load res/predefinedImages/donkeyBlur.ppm donkeyBlur\n" +
            "load res/predefinedImages/donkeySharpen.ppm donkeySharpen\n" +
            "load res/predefinedImages/donkeyGrayscaleFilter.ppm donkeyGrayscaleFilter\n" +
            "load res/predefinedImages/donkeySepia.ppm donkeySepia\n");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, readable, view);
    controller.runProgram();
    donkeyRedGrayscale = model.getImage("donkeyRedGrayscale");
    donkeyGreenGrayscale = model.getImage("donkeyGreenGrayscale");
    donkeyBlueGrayscale = model.getImage("donkeyBlueGrayscale");
    donkeyValueGrayscale = model.getImage("donkeyValueGrayscale");
    donkeyIntensityGrayscale = model.getImage("donkeyIntensityGrayscale");
    donkeyLumaGrayscale = model.getImage("donkeyLumaGrayscale");
    donkeyVertical = model.getImage("donkeyVertical");
    donkeyHorizontal = model.getImage("donkeyHorizontal");
    donkeyBrighterBy50 = model.getImage("donkeyBrighten");
    donkeyDimBy50 = model.getImage("donkeyDim");
    donkeyGrayscaleFilter = model.getImage("donkeyGrayscaleFilter");
    donkeySepiaFilter = model.getImage("donkeySepia");
    donkeyBlurFilter = model.getImage("donkeyBlur");
    donkeySharpenFilter = model.getImage("donkeySharpen");

  }

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
  public void testHorizontalFlipRun() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "horizontal-flip donkey donkeyHorizontal\n" +
            "save res/testImages/donkeyHorizontal.ppm donkeyHorizontal\n" +
            "load res/testImages/donkeyHorizontal.ppm copyHorizontal\n q");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, readable, view);
    controller.runProgram();

    assertEquals(this.donkeyHorizontal, model.getImage("donkeyHorizontal"));
    assertEquals(model.getImage("donkeyHorizontal"),
            model.getImage("copyHorizontal"));
  }

  @Test
  public void testVerticalFlipRun() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "vertical-flip donkey donkeyVertical\n" +
            "save res/testImages/donkeyVertical.ppm donkeyVertical\n" +
            "load res/testImages/donkeyVertical.ppm copyVertical\n q");
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
  public void testBrightenRun() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "brighten 50 donkey donkeyBrighten\n" +
            "save res/testImages/donkeyBrighten.ppm donkeyBrighten\n" +
            "load res/testImages/donkeyBrighten.ppm copyBrighten\n q");
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
  public void testDimRun() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "dim 50 donkey donkeyDim\n" +
            "save res/testImages/donkeyDim.ppm donkeyDim\n" +
            "load res/testImages/donkeyDim.ppm copyDim\n q");
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
  public void testGrayscaleRedRun() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "grayscale red donkey donkeyGrayscaleRed\n" +
            "save res/testImages/donkeyRedGrayscale.ppm donkeyGrayscaleRed\n" +
            "load res/testImages/donkeyRedGrayscale.ppm copyGrayscale\n q");
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
  public void testGrayscaleGreenRun() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "grayscale green donkey donkeyGrayscaleGreen\n" +
            "save res/testImages/donkeyGreenGrayscale.ppm donkeyGrayscaleGreen\n" +
            "load res/testImages/donkeyGreenGrayscale.ppm copyGrayscale\n q");
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
  public void testGrayscaleBlueRun() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "grayscale blue donkey donkeyGrayscaleBlue\n" +
            "save res/testImages/donkeyBlueGrayscale.ppm donkeyGrayscaleBlue\n" +
            "load res/testImages/donkeyBlueGrayscale.ppm copyGrayscale\n q");
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
  public void testGrayscaleIntensityRun() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "grayscale intensity donkey donkeyGrayscaleIntensity\n" +
            "save res/testImages/donkeyIntensityGrayscale.ppm donkeyGrayscaleIntensity\n" +
            "load res/testImages/donkeyIntensityGrayscale.ppm copyGrayscale\n q");
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
  public void testGrayscaleValueRun() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "grayscale value donkey donkeyGrayscaleValue\n" +
            "save res/testImages/donkeyValueGrayscale.ppm donkeyGrayscaleValue\n" +
            "load res/testImages/donkeyValueGrayscale.ppm copyGrayscale\n q");
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
  public void testGrayscaleLumaRun() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "grayscale luma donkey donkeyGrayscaleLuma\n" +
            "save res/testImages/donkeyLumaGrayscale.ppm donkeyGrayscaleLuma\n" +
            "load res/testImages/donkeyLumaGrayscale.ppm copyGrayscale\n q");
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
  public void testGrayscaleFilterRun() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "grayscaleFilter donkey donkeyGrayscaleFilter\n" +
            "save res/testImages/donkeyFilterGrayscale.ppm donkeyGrayscaleFilter\n" +
            "load res/testImages/donkeyFilterGrayscale.ppm copyGrayscale\n q");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, readable, view);
    controller.runProgram();

    assertEquals(this.donkeyGrayscaleFilter, model.getImage("donkeyGrayscaleFilter"));
    assertEquals(model.getImage("donkeyGrayscaleFilter"),
            model.getImage("copyGrayscale"));
    assertEquals(appendable.toString(), "Program Ended.");
  }

  @Test
  public void testSepiaFilterRun() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "sepia donkey donkeySepiaFilter\n" +
            "save res/testImages/donkeyFilterSepia.ppm donkeySepiaFilter\n" +
            "load res/testImages/donkeyFilterSepia.ppm copySepia\n q");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, readable, view);
    controller.runProgram();

    assertEquals(this.donkeySepiaFilter, model.getImage("donkeySepiaFilter"));
    assertEquals(model.getImage("donkeySepiaFilter"),
            model.getImage("copySepia"));
    assertEquals(appendable.toString(), "Program Ended.");
  }

  @Test
  public void testSharpenFilterRun() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "sharpen donkey donkeySharpenFilter\n" +
            "save res/testImages/donkeyFilterSharpen.ppm donkeySharpenFilter\n" +
            "load res/testImages/donkeyFilterSharpen.ppm copySharpen\n q");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, readable, view);
    controller.runProgram();

    assertEquals(this.donkeySharpenFilter, model.getImage("donkeySharpenFilter"));
    assertEquals(model.getImage("donkeySharpenFilter"),
            model.getImage("copySharpen"));
    assertEquals(appendable.toString(), "Program Ended.");
  }

  @Test
  public void testBlurFilterRun() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n" +
            "blur donkey donkeyBlurFilter\n" +
            "save res/testImages/donkeyFilterBlur.ppm donkeyBlurFilter\n" +
            "load res/testImages/donkeyFilterBlur.ppm copyBlur\n q");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, readable, view);
    controller.runProgram();

    assertEquals(this.donkeyBlurFilter, model.getImage("donkeyBlurFilter"));
    assertEquals(model.getImage("donkeyBlurFilter"),
            model.getImage("copyBlur"));
    assertEquals(appendable.toString(), "Program Ended.");
  }


  @Test
  public void testInvalidInput1() {
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
  public void testInvalidInputAfterValidCommand() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/predefinedImages/donkey.ppm donkey\n " +
            "pong\n q");
    ImageProcessingView view = new ImageTextViewImpl(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, readable, view);
    controller.runProgram();

    assertEquals(appendable.toString(),
            "Unknown or invalid command. Try Again.\nProgram Ended.");
  }
}
