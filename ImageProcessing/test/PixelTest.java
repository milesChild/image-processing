import org.junit.Before;
import org.junit.Test;

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
    this.red = new Pixel(255, 0, 0);
    this.blue = new Pixel(0, 0, 255);
    this.green = new Pixel(0, 255, 0);
    this.black = new Pixel(0, 0, 0);
    this.white = new Pixel(255, 255, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitNullParameter() {
    Pixel test = new Pixel(-2, 100, 100);
  }

  @Test
  public void testToString() {
    assertEquals("255 0 0", this.red.toString());
    assertEquals("255 255 255", this.white.toString());
    assertEquals("0 0 0", this.black.toString());
  }

}
