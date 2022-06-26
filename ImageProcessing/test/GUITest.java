import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;
import java.io.StringReader;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerConsole;
import controller.ImageProcessingControllerGUI;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import model.ProcessableImage;
import view.ImageProcessingView;
import view.ImageProcessingViewConsole;
import view.ImageProcessingViewGUI;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the GUI Implementation.
 */
public class GUITest {
  private ProcessableImage henockRedGrayscale;
  private ProcessableImage henockGreenGrayscale;
  private ProcessableImage henockBlueGrayscale;
  private ProcessableImage henockValueGrayscale;
  private ProcessableImage henockIntensityGrayscale;
  private ProcessableImage henockLumaGrayscale;
  private ProcessableImage henockHorizontal;
  private ProcessableImage henockVertical;
  private ProcessableImage henockSepiaFilter;
  private ProcessableImage henockGrayscaleFilter;
  private ProcessableImage henockBlurFilter;
  private ProcessableImage henockSharpenFilter;

  @Before
  public void init() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader(
            "load res/exampleImages/HenockRedGrayscale.ppm HenockRedGrayscale\n" +
                    "load res/exampleImages/HenockGreenGrayscale.ppm HenockGreenGrayscale\n" +
                    "load res/exampleImages/HenockBlueGrayscale.ppm HenockBlueGrayscale\n" +
                    "load res/exampleImages/HenockValueGrayscale.ppm HenockValueGrayscale\n" +
                    "load res/exampleImages/HenockIntensityGrayscale.ppm " +
                    "HenockIntensityGrayscale\n" +
                    "load res/exampleImages/HenockLumaGrayscale.ppm HenockLumaGrayscale\n" +
                    "load res/exampleImages/HenockHorizontal.ppm HenockHorizontal\n" +
                    "load res/exampleImages/HenockVertical.ppm HenockVertical\n" +
                    "load res/exampleImages/HenockBrighten.ppm HenockBrighten\n" +
                    "load res/exampleImages/HenockDim.ppm HenockDim\n" +
                    "load res/exampleImages/HenockBlur.ppm HenockBlur\n" +
                    "load res/exampleImages/HenockSharpen.ppm HenockSharpen\n" +
                    "load res/exampleImages/HenockTransformationGrayscale.ppm " +
                    "HenockGrayscaleFilter\n" +
                    "load res/exampleImages/HenockSepia.ppm HenockSepia\n");
    ImageProcessingView view = new ImageProcessingViewConsole(appendable);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller =
            new ImageProcessingControllerConsole(model, readable, view);
    controller.runProgram();
    henockRedGrayscale = model.getImage("HenockRedGrayscale");
    henockGreenGrayscale = model.getImage("HenockGreenGrayscale");
    henockBlueGrayscale = model.getImage("HenockBlueGrayscale");
    henockValueGrayscale = model.getImage("HenockValueGrayscale");
    henockIntensityGrayscale = model.getImage("HenockIntensityGrayscale");
    henockLumaGrayscale = model.getImage("HenockLumaGrayscale");
    henockVertical = model.getImage("HenockVertical");
    henockHorizontal = model.getImage("HenockHorizontal");
    henockGrayscaleFilter = model.getImage("HenockGrayscaleFilter");
    henockSepiaFilter = model.getImage("HenockSepia");
    henockBlurFilter = model.getImage("HenockBlur");
    henockSharpenFilter = model.getImage("HenockSharpen");
  }

  @Test
  public void testHorizontalFlipRun() {
    ImageProcessingViewGUI view = new ImageProcessingViewGUI();
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingControllerGUI controller = new ImageProcessingControllerGUI(model, view);
    controller.loadImage("res/exampleImages/Henock.ppm", "initialImage");
    ActionEvent event = new ActionEvent(view, 1, "horizontal");
    controller.actionPerformed(event);

    assertEquals(this.henockHorizontal, model.getImage("horizontalFlip"));
  }

  @Test
  public void testVerticalFlipRun() {
    ImageProcessingViewGUI view = new ImageProcessingViewGUI();
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingControllerGUI controller = new ImageProcessingControllerGUI(model, view);
    controller.loadImage("res/exampleImages/Henock.ppm", "initialImage");
    ActionEvent event = new ActionEvent(view, 1, "vertical");
    controller.actionPerformed(event);

    assertEquals(this.henockVertical, model.getImage("verticalFlip"));
  }

  @Test
  public void testBlurRun() {
    ImageProcessingViewGUI view = new ImageProcessingViewGUI();
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingControllerGUI controller = new ImageProcessingControllerGUI(model, view);
    controller.loadImage("res/exampleImages/Henock.ppm", "initialImage");
    ActionEvent event = new ActionEvent(view, 1, "blur");
    controller.actionPerformed(event);

    assertEquals(this.henockBlurFilter, model.getImage("blur"));
  }

  @Test
  public void testSharpenRun() {
    ImageProcessingViewGUI view = new ImageProcessingViewGUI();
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingControllerGUI controller = new ImageProcessingControllerGUI(model, view);
    controller.loadImage("res/exampleImages/Henock.ppm", "initialImage");
    ActionEvent event = new ActionEvent(view, 1, "sharpen");
    controller.actionPerformed(event);

    assertEquals(this.henockSharpenFilter, model.getImage("sharpen"));
  }

  @Test
  public void testSepiaRun() {
    ImageProcessingViewGUI view = new ImageProcessingViewGUI();
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingControllerGUI controller = new ImageProcessingControllerGUI(model, view);
    controller.loadImage("res/exampleImages/Henock.ppm", "initialImage");
    ActionEvent event = new ActionEvent(view, 1, "sepia");
    controller.actionPerformed(event);

    assertEquals(this.henockSepiaFilter, model.getImage("sepia"));
  }

  @Test
  public void testGrayscaleRedRun() {
    ImageProcessingViewGUI view = new ImageProcessingViewGUI();
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingControllerGUI controller = new ImageProcessingControllerGUI(model, view);
    controller.loadImage("res/exampleImages/Henock.ppm", "initialImage");
    ActionEvent event = new ActionEvent(view, 1, "grayscale red");
    controller.actionPerformed(event);

    assertEquals(this.henockRedGrayscale, model.getImage("red"));
  }

  @Test
  public void testGrayscaleGreenRun() {
    ImageProcessingViewGUI view = new ImageProcessingViewGUI();
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingControllerGUI controller = new ImageProcessingControllerGUI(model, view);
    controller.loadImage("res/exampleImages/Henock.ppm", "initialImage");
    ActionEvent event = new ActionEvent(view, 1, "grayscale green");
    controller.actionPerformed(event);

    assertEquals(this.henockGreenGrayscale, model.getImage("green"));
  }

  @Test
  public void testGrayscaleBlueRun() {
    ImageProcessingViewGUI view = new ImageProcessingViewGUI();
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingControllerGUI controller = new ImageProcessingControllerGUI(model, view);
    controller.loadImage("res/exampleImages/Henock.ppm", "initialImage");
    ActionEvent event = new ActionEvent(view, 1, "grayscale blue");
    controller.actionPerformed(event);

    assertEquals(this.henockBlueGrayscale, model.getImage("blue"));
  }

  @Test
  public void testGrayscaleValueRun() {
    ImageProcessingViewGUI view = new ImageProcessingViewGUI();
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingControllerGUI controller = new ImageProcessingControllerGUI(model, view);
    controller.loadImage("res/exampleImages/Henock.ppm", "initialImage");
    ActionEvent event = new ActionEvent(view, 1, "grayscale value");
    controller.actionPerformed(event);

    assertEquals(this.henockValueGrayscale, model.getImage("value"));
  }

  @Test
  public void testGrayscaleIntensityRun() {
    ImageProcessingViewGUI view = new ImageProcessingViewGUI();
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingControllerGUI controller = new ImageProcessingControllerGUI(model, view);
    controller.loadImage("res/exampleImages/Henock.ppm", "initialImage");
    ActionEvent event = new ActionEvent(view, 1, "grayscale intensity");
    controller.actionPerformed(event);

    assertEquals(this.henockIntensityGrayscale, model.getImage("intensity"));
  }

  @Test
  public void testGrayscaleLumaRun() {
    ImageProcessingViewGUI view = new ImageProcessingViewGUI();
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingControllerGUI controller = new ImageProcessingControllerGUI(model, view);
    controller.loadImage("res/exampleImages/Henock.ppm", "initialImage");
    ActionEvent event = new ActionEvent(view, 1, "grayscale luma");
    controller.actionPerformed(event);

    assertEquals(this.henockLumaGrayscale, model.getImage("luma"));
  }

  @Test
  public void testGrayscaleFilterRun() {
    ImageProcessingViewGUI view = new ImageProcessingViewGUI();
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingControllerGUI controller = new ImageProcessingControllerGUI(model, view);
    controller.loadImage("res/exampleImages/Henock.ppm", "initialImage");
    ActionEvent event = new ActionEvent(view, 1, "grayscale filter");
    controller.actionPerformed(event);

    assertEquals(this.henockGrayscaleFilter, model.getImage("filter"));
  }
}
