import org.junit.Test;

import model.ImageProcessingModel;
import model.PPMImage;
import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PPMImageTest {
  PPMImage donkeyTest;
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

  public void init(){
    this.donkeyTest = new PPMImage("SmallPPMImages/donkey.ppm");
    this.donkeyRedGrayscale = new PPMImage("SmallPPMImages/donkey-red-greyscale.ppm");
    this.donkeyGreenGrayscale = new PPMImage("SmallPPMImages/donkey-green-greyscale.ppm");
    this.donkeyBlueGrayscale = new PPMImage("SmallPPMImages/donkey-blue-greyscale.ppm");
    this.donkeyValueGrayscale = new PPMImage("SmallPPMImages/donkey-value-greyscale.ppm");
    this.donkeyIntensityGrayscale =
            new PPMImage("SmallPPMImages/donkey-intensity-greyscale.ppm");
    this.donkeyValueGrayscale = new PPMImage("SmallPPMImages/donkey-value-greyscale.ppm");
    this.donkeyLumaGrayscale = new PPMImage("SmallPPMImages/donkey-luma-greyscale.ppm");
    this.donkeyHorizontal = new PPMImage("SmallPPMImages/donkey-horizontal.ppm");
    this.donkeyVertical = new PPMImage("SmallPPMImages/donkey-vertical.ppm");
    this.donkeyBrighterBy50 = new PPMImage("SmallPPMImages/donkey-brighten-by-50.ppm");
    this.donkeyDimBy50 = new PPMImage("SmallPPMImages/donkey-dim-by-50.ppm");
  }

  @Test
  public void testCopyImage() {
    this.init();
    PPMImage donkeyCopy = new PPMImage(donkeyTest);
    Pixel[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    Pixel[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();

    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {
        assertEquals(donkeyPixelGrid[i][j], donkeyCopyPixelGrid[i][j]);
      }
    }
  }

  @Test
  public void testFlipHorizontal() {
    this.init();
    PPMImage donkeyCopy = new PPMImage("SmallPPMImages/donkey.ppm");
    donkeyCopy.flip(ImageProcessingModel.Orientations.Horizontal);
    Pixel[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    Pixel[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
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
    PPMImage donkeyCopy = new PPMImage("SmallPPMImages/donkey.ppm");
    donkeyCopy.flip(ImageProcessingModel.Orientations.Vertical);
    Pixel[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    Pixel[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {
        assertEquals(donkeyPixelGrid[i][j],
                donkeyCopyPixelGrid[this.donkeyTest.getWidth() - i - 1][j]);
      }
    }
  }

  @Test
  public void testBrighten() {
    this.init();
    PPMImage donkeyCopy = new PPMImage("SmallPPMImages/donkey.ppm");
    donkeyCopy.brighten(50);
    Pixel[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    Pixel[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {

        Pixel p1 = donkeyPixelGrid[i][j];
        assertEquals(donkeyCopyPixelGrid[i][j].getRed(), p1.getRed() + 50);
        assertEquals(donkeyCopyPixelGrid[i][j].getGreen(), p1.getGreen() + 50);
        assertEquals(donkeyCopyPixelGrid[i][j].getBlue(), p1.getBlue() + 50);
      }
    }
  }

  @Test
  public void testBrightenMax() {
    this.init();
    PPMImage donkeyCopy = new PPMImage("SmallPPMImages/donkey.ppm");
    donkeyCopy.brighten(255);
    Pixel[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    Pixel[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {
        assertEquals(donkeyCopyPixelGrid[i][j].getRed(), 255);
        assertEquals(donkeyCopyPixelGrid[i][j].getGreen(), 255);
        assertEquals(donkeyCopyPixelGrid[i][j].getBlue(), 255);
      }
    }
  }

  @Test
  public void testDim() {
    this.init();
    PPMImage donkeyCopy = new PPMImage("SmallPPMImages/donkey.ppm");
    donkeyCopy.brighten(-10);
    Pixel[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    Pixel[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {

        Pixel p1 = donkeyPixelGrid[i][j];
        assertEquals(donkeyCopyPixelGrid[i][j].getRed(), p1.getRed() - 10);
        assertEquals(donkeyCopyPixelGrid[i][j].getGreen(), p1.getGreen() - 10);
        assertEquals(donkeyCopyPixelGrid[i][j].getBlue(), p1.getBlue() - 10);
      }
    }
  }

  @Test
  public void testDimMax() {
    this.init();
    PPMImage donkeyCopy = new PPMImage("SmallPPMImages/donkey.ppm");
    donkeyCopy.brighten(-255);
    Pixel[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    Pixel[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
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
    PPMImage donkeyCopy = new PPMImage("SmallPPMImages/donkey.ppm");
    donkeyCopy.grayscale(ImageProcessingModel.GrayscaleTypes.RedGrayscale);
    Pixel[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    Pixel[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {

        Pixel p1 = donkeyPixelGrid[i][j];
        assertEquals(donkeyCopyPixelGrid[i][j].getRed(), p1.getRed());
        assertEquals(donkeyCopyPixelGrid[i][j].getGreen(), p1.getRed());
        assertEquals(donkeyCopyPixelGrid[i][j].getBlue(), p1.getRed());
      }
    }
  }

  @Test
  public void testGreenGrayscale() {
    this.init();
    PPMImage donkeyCopy = new PPMImage("SmallPPMImages/donkey.ppm");
    donkeyCopy.grayscale(ImageProcessingModel.GrayscaleTypes.GreenGrayscale);
    Pixel[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    Pixel[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {

        Pixel p1 = donkeyPixelGrid[i][j];
        assertEquals(donkeyCopyPixelGrid[i][j].getRed(), p1.getGreen());
        assertEquals(donkeyCopyPixelGrid[i][j].getGreen(), p1.getGreen());
        assertEquals(donkeyCopyPixelGrid[i][j].getBlue(), p1.getGreen());
      }
    }
  }

  @Test
  public void testBlueGrayscale() {
    this.init();
    PPMImage donkeyCopy = new PPMImage("SmallPPMImages/donkey.ppm");
    donkeyCopy.grayscale(ImageProcessingModel.GrayscaleTypes.BlueGrayscale);
    Pixel[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    Pixel[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {

        Pixel p1 = donkeyPixelGrid[i][j];
        assertEquals(donkeyCopyPixelGrid[i][j].getRed(), p1.getBlue());
        assertEquals(donkeyCopyPixelGrid[i][j].getGreen(), p1.getBlue());
        assertEquals(donkeyCopyPixelGrid[i][j].getBlue(), p1.getBlue());
      }
    }
  }

  @Test
  public void testValueGrayscale() {
    this.init();
    PPMImage donkeyCopy = new PPMImage("SmallPPMImages/donkey.ppm");
    donkeyCopy.grayscale(ImageProcessingModel.GrayscaleTypes.ValueGrayscale);
    Pixel[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    Pixel[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {
        Pixel p1 = donkeyPixelGrid[i][j];
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
    PPMImage donkeyCopy = new PPMImage("SmallPPMImages/donkey.ppm");
    donkeyCopy.grayscale(ImageProcessingModel.GrayscaleTypes.IntensityGrayscale);
    Pixel[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    Pixel[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {
        Pixel p1 = donkeyPixelGrid[i][j];
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
    PPMImage donkeyCopy = new PPMImage("SmallPPMImages/donkey.ppm");
    donkeyCopy.grayscale(ImageProcessingModel.GrayscaleTypes.LumaGrayscale);
    Pixel[][] donkeyPixelGrid = this.donkeyTest.getPixelGrid();
    Pixel[][] donkeyCopyPixelGrid = donkeyCopy.getPixelGrid();
    for (int i = 0; i < this.donkeyTest.getHeight(); i++) {
      for (int j = 0; j < this.donkeyTest.getWidth(); j++) {
        Pixel p1 = donkeyPixelGrid[i][j];
        int lumaVal = (int) Math.round(
                (p1.getRed() * 0.2126 + p1.getGreen() * 0.7152 + p1.getBlue() * 0.0722));

        assertEquals(donkeyCopyPixelGrid[i][j].getRed(), lumaVal);
        assertEquals(donkeyCopyPixelGrid[i][j].getGreen(), lumaVal);
        assertEquals(donkeyCopyPixelGrid[i][j].getBlue(), lumaVal);
      }
    }
  }

  @Test
  public void testSaveImage() {
    this.init();
    donkeyTest.brighten(50);
    donkeyTest.saveImage("TestCreatedImages/imageSaveTest.ppm");
    PPMImage testImage = new PPMImage("TestCreatedImages/imageSaveTest.ppm");
    assertTrue(testImage.equals(donkeyBrighterBy50));
  }

  @Test
  public void testCopyImageFullImage() {
    this.init();
    PPMImage donkeyCopy = new PPMImage(donkeyTest);
    donkeyCopy.equals(donkeyTest);
    assertTrue(donkeyTest.equals(donkeyCopy));
  }

  @Test
  public void testFlipHorizontalFullImage() {
    this.init();
    donkeyTest.flip(ImageProcessingModel.Orientations.Horizontal);
    donkeyTest.saveImage("SmallPPMImages/donkey-horizontal.ppm");
    assertTrue(donkeyTest.equals(donkeyHorizontal));
  }

  @Test
  public void testFlipVerticalFullImage() {
    this.init();
    donkeyTest.flip(ImageProcessingModel.Orientations.Vertical);
    assertTrue(donkeyTest.equals(donkeyVertical));
  }

  @Test
  public void testBrightenFullImage() {
    this.init();
    donkeyTest.brighten(50);
    assertTrue(donkeyTest.equals(donkeyBrighterBy50));
  }

  @Test
  public void testDimFullImage() {
    this.init();
    donkeyTest.brighten(-50);
    donkeyTest.equals(donkeyDimBy50);
  }

  @Test
  public void testRedGrayscaleFullImage() {
    this.init();
    donkeyTest.grayscale(ImageProcessingModel.GrayscaleTypes.RedGrayscale);
    assertTrue(donkeyTest.equals(donkeyRedGrayscale));
  }

  @Test
  public void testGreenGrayscaleFullImage() {
    this.init();
    donkeyTest.grayscale(ImageProcessingModel.GrayscaleTypes.GreenGrayscale);
    assertTrue(donkeyTest.equals(donkeyGreenGrayscale));
  }

  @Test
  public void testBlueGrayscaleFullImage() {
    this.init();
    donkeyTest.grayscale(ImageProcessingModel.GrayscaleTypes.BlueGrayscale);
    assertTrue(donkeyTest.equals(donkeyBlueGrayscale));
  }

  @Test
  public void testValueGrayscaleFullImage() {
    this.init();
    donkeyTest.grayscale(ImageProcessingModel.GrayscaleTypes.ValueGrayscale);
    assertTrue(donkeyTest.equals(donkeyValueGrayscale));
  }

  @Test
  public void testIntensityGrayscaleFullImage() {
    this.init();
    donkeyTest.grayscale(ImageProcessingModel.GrayscaleTypes.IntensityGrayscale);
    assertTrue(donkeyTest.equals(donkeyIntensityGrayscale));
  }

  @Test
  public void testLumaGrayscaleFullImage() {
    this.init();
    donkeyTest.grayscale(ImageProcessingModel.GrayscaleTypes.LumaGrayscale);
    assertTrue(donkeyTest.equals(donkeyLumaGrayscale));
  }
}