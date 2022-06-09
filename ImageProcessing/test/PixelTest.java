import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.File;

import model.ImageProcessingModel;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests that ensure proper functioning of the pixel class, which represents a single pixel in an
 * image.
 */
public class PixelTest {
  Pixel red;
  Pixel blue;
  Pixel green;
  Pixel black;
  Pixel white;

  @Before
  public void init() {
    this.red = new Pixel(255, 0, 0, 255);
    this.blue = new Pixel(0, 0, 255, 255);
    this.green = new Pixel(0, 255, 0, 255);
    this.black = new Pixel(0, 0, 0, 255);
    this.white = new Pixel(255, 255, 255, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitNullParameter() {
    Pixel test = new Pixel(-2, 100, 100, 255);
  }

  @Test
  public void testToString() {
    this.init();
    assertEquals(" 255 0 0", this.red.toString());
    assertEquals(" 0 0 255", this.blue.toString());
    assertEquals(" 0 255 0", this.green.toString());
    assertEquals(" 0 0 0", this.black.toString());
    assertEquals(" 255 255 255", this.white.toString());
  }

  @Test
  public void testSetComponents() {
    this.init();
    this.red.setComponents(30, 20, 10);
    assertEquals(" 30 20 10", this.red.toString());

    this.green.setComponents(40, 40, 40);
    assertEquals(" 40 40 40", this.green.toString());

    this.blue.setComponents(20, 30, 50);
    assertEquals(" 20 30 50", this.blue.toString());
  }

  @Test
  public void testBrighten() {
    this.init();
    this.red.brighten(30);
    assertEquals(" 255 30 30", this.red.toString());

    this.green.brighten(-40);
    assertEquals(" 0 215 0", this.green.toString());

    this.blue.brighten(0);
    assertEquals(" 0 0 255", this.blue.toString());

    this.black.brighten(100);
    assertEquals(" 100 100 100", this.black.toString());

    this.white.brighten(56);
    assertEquals(" 255 255 255", this.white.toString());
  }

  @Test
  public void testGrayscale(){
    this.init();
    this.red.grayscale(ImageProcessingModel.GrayscaleTypes.RedGrayscale);
    assertEquals(" 255 255 255", this.red.toString());

    this.init();
    this.red.grayscale(ImageProcessingModel.GrayscaleTypes.GreenGrayscale);
    assertEquals(" 0 0 0", this.red.toString());

    this.init();
    this.red.grayscale(ImageProcessingModel.GrayscaleTypes.BlueGrayscale);
    assertEquals(" 0 0 0", this.red.toString());

    this.init();
    this.red.grayscale(ImageProcessingModel.GrayscaleTypes.ValueGrayscale);
    assertEquals(" 255 255 255", this.red.toString());

    this.init();
    this.red.grayscale(ImageProcessingModel.GrayscaleTypes.IntensityGrayscale);
    assertEquals(" 85 85 85", this.red.toString());

    this.init();
    this.red.grayscale(ImageProcessingModel.GrayscaleTypes.LumaGrayscale);
    assertEquals(" 54 54 54", this.red.toString());

  }
}
