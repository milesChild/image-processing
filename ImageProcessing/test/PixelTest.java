import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.File;

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
    this.black = new Pixel(0, 0, 0, 225);
    this.white = new Pixel(255, 255, 255, 225);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitNullParameter() {
    Pixel test = new Pixel(-2, 100, 100, 255);
  }

  @Test
  public void testWritePixel() {
    // create fake file
    // call file.write(this.red)
    // test that the file contents equal "255, 0, 0"
  }

}
