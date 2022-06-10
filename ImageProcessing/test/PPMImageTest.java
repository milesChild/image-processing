import org.junit.Test;

import model.ImageProcessingModel;
import model.PPMImage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PPMImageTest {
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
    this.koalaIntensityGrayscale = new PPMImage("PPMimages/koala-intensity-greyscale.ppm");
    this.koalaLumaGrayscale = new PPMImage("PPMimages/koala-luma-greyscale.ppm");
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

    this.init();
    this.koala.grayscale(ImageProcessingModel.GrayscaleTypes.IntensityGrayscale);
    assertTrue(this.koala.equals(koalaIntensityGrayscale));

    this.init();
    this.koala.grayscale(ImageProcessingModel.GrayscaleTypes.ValueGrayscale);
    assertTrue(this.koala.equals(koalaValueGrayscale));

    this.init();
    this.koala.grayscale(ImageProcessingModel.GrayscaleTypes.LumaGrayscale);
    assertTrue(this.koala.equals(koalaLumaGrayscale));
  }

  @Test
  public void testFlipHorizontal() {
    this.init();
  }


}