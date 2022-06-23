import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import model.PixelImpl;
import model.ProcessableImage;
import model.ProcessableImageImpl;
import view.ImageProcessingView;
import view.ImageTextViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the ProcessableImageImpl class and ImageProcessingModelImpl class.
 */
public class ModelTest {
  ProcessableImage donkeyTest;
  ProcessableImage donkeyRedGrayscale;
  ProcessableImage donkeyGreenGrayscale;
  ProcessableImage donkeyBlueGrayscale;
  ProcessableImage donkeyValueGrayscale;
  ProcessableImage donkeyIntensityGrayscale;
  ProcessableImage donkeyLumaGrayscale;
  ProcessableImage donkeyHorizontal;
  ProcessableImage donkeyVertical;
  ProcessableImage donkeyBrighterBy50;
  ProcessableImage donkeyDimBy50;
  ProcessableImage donkeySepiaFilter;
  ProcessableImage donkeyGrayscaleFilter;
  ProcessableImage donkeyBlurFilter;
  ProcessableImage donkeySharpenFilter;

  @Before
  public void init() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader(
            "load res/predefinedImages/donkey-red-greyscale.ppm donkeyRedGrayscale\n" +
                    "load res/predefinedImages/donkey.ppm donkeyTest\n" +
                    "load res/predefinedImages/donkey-green-greyscale.ppm donkeyGreenGrayscale\n" +
                    "load res/predefinedImages/donkey-blue-greyscale.ppm donkeyBlueGrayscale\n" +
                    "load res/predefinedImages/donkey-value-greyscale.ppm donkeyValueGrayscale\n" +
                    "load res/predefinedImages/donkey-intensity-greyscale.ppm " +
                    "donkeyIntensityGrayscale\n" +
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
    donkeyTest = model.getImage("donkeyTest");
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

  @Test
  public void testCopyImage() {
    this.init();
    ProcessableImageImpl donkeyCopy = new ProcessableImageImpl(donkeyTest);
    PixelImpl[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    PixelImpl[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();

    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {
        assertEquals(donkeyPixelGrid[i][j], donkeyCopyPixelGrid[i][j]);
      }
    }
  }

  @Test
  public void testFlipHorizontal() {
    this.init();
    ProcessableImageImpl donkeyCopy = new ProcessableImageImpl(donkeyTest);
    donkeyCopy.flip(ImageProcessingModel.Orientations.Horizontal);
    PixelImpl[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    PixelImpl[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {
        assertEquals(donkeyPixelGrid[i][j],
                donkeyCopyPixelGrid[i][this.donkeyTest.getWidth() - j - 1]);
      }
    }
  }

  @Test
  public void testFlipVertical() {
    this.init();
    ProcessableImageImpl donkeyCopy = new ProcessableImageImpl(donkeyTest);
    donkeyCopy.flip(ImageProcessingModel.Orientations.Vertical);
    PixelImpl[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    PixelImpl[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {
        assertEquals(donkeyPixelGrid[i][j],
                donkeyCopyPixelGrid[this.donkeyTest.getWidth() - i - 1][j]);
      }
    }
  }

  @Test
  public void testBrightenMax() {
    this.init();
    ProcessableImageImpl donkeyCopy = new ProcessableImageImpl(donkeyTest);
    donkeyCopy.brighten(255);
    PixelImpl[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    PixelImpl[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {
        assertEquals(donkeyCopyPixelGrid[i][j].getRed(), 255);
        assertEquals(donkeyCopyPixelGrid[i][j].getGreen(), 255);
        assertEquals(donkeyCopyPixelGrid[i][j].getBlue(), 255);
      }
    }
  }

  @Test
  public void testDimMax() {
    this.init();
    ProcessableImageImpl donkeyCopy = new ProcessableImageImpl(donkeyTest);
    donkeyCopy.brighten(-255);
    PixelImpl[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    PixelImpl[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {
        assertEquals(donkeyCopyPixelGrid[i][j].getRed(), 0);
        assertEquals(donkeyCopyPixelGrid[i][j].getGreen(), 0);
        assertEquals(donkeyCopyPixelGrid[i][j].getBlue(), 0);
      }
    }
  }

  @Test
  public void testRedGrayscale() {
    this.init();
    ProcessableImageImpl donkeyCopy = new ProcessableImageImpl(donkeyTest);
    donkeyCopy.grayscale(ImageProcessingModel.GrayscaleTypes.RedGrayscale);
    PixelImpl[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    PixelImpl[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {

        PixelImpl p1 = donkeyPixelGrid[i][j];
        assertEquals(donkeyCopyPixelGrid[i][j].getRed(), p1.getRed());
        assertEquals(donkeyCopyPixelGrid[i][j].getGreen(), p1.getRed());
        assertEquals(donkeyCopyPixelGrid[i][j].getBlue(), p1.getRed());
      }
    }
  }

  @Test
  public void testGreenGrayscale() {
    this.init();
    ProcessableImageImpl donkeyCopy = new ProcessableImageImpl(donkeyTest);
    donkeyCopy.grayscale(ImageProcessingModel.GrayscaleTypes.GreenGrayscale);
    PixelImpl[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    PixelImpl[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {

        PixelImpl p1 = donkeyPixelGrid[i][j];
        assertEquals(donkeyCopyPixelGrid[i][j].getRed(), p1.getGreen());
        assertEquals(donkeyCopyPixelGrid[i][j].getGreen(), p1.getGreen());
        assertEquals(donkeyCopyPixelGrid[i][j].getBlue(), p1.getGreen());
      }
    }
  }

  @Test
  public void testBlueGrayscale() {
    this.init();
    ProcessableImageImpl donkeyCopy = new ProcessableImageImpl(donkeyTest);
    donkeyCopy.grayscale(ImageProcessingModel.GrayscaleTypes.BlueGrayscale);
    PixelImpl[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    PixelImpl[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {

        PixelImpl p1 = donkeyPixelGrid[i][j];
        assertEquals(donkeyCopyPixelGrid[i][j].getRed(), p1.getBlue());
        assertEquals(donkeyCopyPixelGrid[i][j].getGreen(), p1.getBlue());
        assertEquals(donkeyCopyPixelGrid[i][j].getBlue(), p1.getBlue());
      }
    }
  }

  @Test
  public void testValueGrayscale() {
    this.init();
    ProcessableImageImpl donkeyCopy = new ProcessableImageImpl(donkeyTest);
    donkeyCopy.grayscale(ImageProcessingModel.GrayscaleTypes.ValueGrayscale);
    PixelImpl[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    PixelImpl[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {
        PixelImpl p1 = donkeyPixelGrid[i][j];
        int tempMax = Math.max(p1.getRed(), p1.getBlue());
        int finalMax = Math.max(tempMax, p1.getGreen());

        assertEquals(donkeyCopyPixelGrid[i][j].getRed(), finalMax);
        assertEquals(donkeyCopyPixelGrid[i][j].getGreen(), finalMax);
        assertEquals(donkeyCopyPixelGrid[i][j].getBlue(), finalMax);
      }
    }
  }

  @Test
  public void testIntensityGrayscale() {
    this.init();
    ProcessableImageImpl donkeyCopy = new ProcessableImageImpl(donkeyTest);
    donkeyCopy.grayscale(ImageProcessingModel.GrayscaleTypes.IntensityGrayscale);
    PixelImpl[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    PixelImpl[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {
        PixelImpl p1 = donkeyPixelGrid[i][j];
        int intensityVal = (int) Math.round((p1.getRed() + p1.getGreen() + p1.getBlue()) / 3.0);

        assertEquals(donkeyCopyPixelGrid[i][j].getRed(), intensityVal);
        assertEquals(donkeyCopyPixelGrid[i][j].getGreen(), intensityVal);
        assertEquals(donkeyCopyPixelGrid[i][j].getBlue(), intensityVal);
      }
    }
  }

  @Test
  public void testLumaGrayscale() {
    this.init();
    ProcessableImageImpl donkeyCopy = new ProcessableImageImpl(donkeyTest);
    donkeyCopy.grayscale(ImageProcessingModel.GrayscaleTypes.LumaGrayscale);
    PixelImpl[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    PixelImpl[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {
        PixelImpl p1 = donkeyPixelGrid[i][j];
        int lumaVal = (int) Math.round(
                (p1.getRed() * 0.2126 + p1.getGreen() * 0.7152 + p1.getBlue() * 0.0722));

        assertEquals(donkeyCopyPixelGrid[i][j].getRed(), lumaVal);
        assertEquals(donkeyCopyPixelGrid[i][j].getGreen(), lumaVal);
        assertEquals(donkeyCopyPixelGrid[i][j].getBlue(), lumaVal);
      }
    }
  }

  @Test
  public void testCopyImageFullImage() {
    this.init();
    ProcessableImage donkeyCopy = new ProcessableImageImpl(donkeyTest);
    assertEquals(donkeyCopy, donkeyTest);
    assertEquals(donkeyTest, donkeyCopy);
  }

  @Test
  public void testFlipHorizontalFullImage() {
    this.init();
    donkeyTest.flip(ImageProcessingModel.Orientations.Horizontal);
    assertEquals(donkeyTest, donkeyHorizontal);
  }

  @Test
  public void testFlipVerticalFullImage() {
    this.init();
    donkeyTest.flip(ImageProcessingModel.Orientations.Vertical);
    assertEquals(donkeyTest, donkeyVertical);
  }

  @Test
  public void testBrightenFullImage() {
    this.init();
    donkeyTest.brighten(50);
    assertEquals(donkeyTest, donkeyBrighterBy50);
  }

  @Test
  public void testDimFullImage() {
    this.init();
    donkeyTest.brighten(-50);
    assertEquals(donkeyTest, donkeyDimBy50);
  }

  @Test
  public void testRedGrayscaleFullImage() {
    this.init();
    donkeyTest.grayscale(ImageProcessingModel.GrayscaleTypes.RedGrayscale);
    assertEquals(donkeyTest, donkeyRedGrayscale);
  }

  @Test
  public void testGreenGrayscaleFullImage() {
    this.init();
    donkeyTest.grayscale(ImageProcessingModel.GrayscaleTypes.GreenGrayscale);
    assertEquals(donkeyTest, donkeyGreenGrayscale);
  }

  @Test
  public void testBlueGrayscaleFullImage() {
    this.init();
    donkeyTest.grayscale(ImageProcessingModel.GrayscaleTypes.BlueGrayscale);
    assertEquals(donkeyTest, donkeyBlueGrayscale);
  }

  @Test
  public void testValueGrayscaleFullImage() {
    this.init();
    donkeyTest.grayscale(ImageProcessingModel.GrayscaleTypes.ValueGrayscale);
    assertEquals(donkeyTest, donkeyValueGrayscale);
  }

  @Test
  public void testIntensityGrayscaleFullImage() {
    this.init();
    donkeyTest.grayscale(ImageProcessingModel.GrayscaleTypes.IntensityGrayscale);
    assertEquals(donkeyTest, donkeyIntensityGrayscale);
  }

  @Test
  public void testLumaGrayscaleFullImage() {
    this.init();
    donkeyTest.grayscale(ImageProcessingModel.GrayscaleTypes.LumaGrayscale);
    assertEquals(donkeyTest, donkeyLumaGrayscale);
  }

  @Test
  public void testSepiaFullImage() {
    this.init();
    donkeyTest.sepia();
    assertEquals(donkeyTest, donkeySepiaFilter);
  }

  @Test
  public void testBlurFullImage() {
    this.init();
    donkeyTest.blur();
    assertEquals(donkeyTest, donkeyBlurFilter);
  }

  @Test
  public void testSharpenFullImage() {
    this.init();
    donkeyTest.sharpen();
    assertEquals(donkeyTest, donkeySharpenFilter);
  }

//  @Test
//  public void testGenerateHistogram() {
//    int[][] test = this.donkeyGrayscaleFilter.generateHistogram();
//    assertEquals(1, test[0][89]);
//    assertEquals(1, test[1][89]);
//    assertEquals(1, test[2][89]);
//    assertEquals(1, test[3][89]);
//  }

}