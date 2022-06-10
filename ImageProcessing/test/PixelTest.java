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
  public void testInvalidInitNegativePixel() {
    Pixel test = new Pixel(-2, 100, 100, 255);
  }

  @Test
  public void testToStringRed() {
    this.init();
    assertEquals("255 0 0 ", this.red.toString());
  }

  @Test
  public void testToStringGreen() {
    this.init();
    assertEquals("0 255 0 ", this.green.toString());
  }

  @Test
  public void testToStringBlue() {
    this.init();
    assertEquals("0 0 255 ", this.blue.toString());
  }

  @Test
  public void testToStringBlack() {
    this.init();
    assertEquals("0 0 0 ", this.black.toString());
  }

  @Test
  public void testToStringWhite() {
    this.init();
    assertEquals("255 255 255 ", this.white.toString());
  }

  @Test
  public void testSetComponentsRed() {
    this.init();
    this.red.setComponents(30, 20, 10);
    assertEquals("30 20 10 ", this.red.toString());
  }

  @Test
  public void testSetComponentsGreen() {
    this.init();
    this.green.setComponents(40, 40, 40);
    assertEquals("40 40 40 ", this.green.toString());
  }

  @Test
  public void testSetComponentsBlue() {
    this.init();
    this.blue.setComponents(20, 30, 50);
    assertEquals("20 30 50 ", this.blue.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidOverMaxSecondsSetComponents() {
    this.init();
    this.green.setComponents(1000, 1000, 1000);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNegativeSetComponents() {
    this.init();
    this.green.setComponents(-10, 255, 255);
  }

  @Test
  public void testBrightenRed() {
    this.init();
    this.red.brighten(30);
    assertEquals("255 30 30 ", this.red.toString());
  }

  @Test
  public void testBrightenGreen() {
    this.init();
    this.green.brighten(-40);
    assertEquals("0 215 0 ", this.green.toString());
  }

  @Test
  public void testBrightenBlue() {
    this.init();
    this.blue.brighten(0);
    assertEquals("0 0 255 ", this.blue.toString());
  }

  @Test
  public void testBrightenBlack() {
    this.init();
    this.black.brighten(100);
    assertEquals("100 100 100 ", this.black.toString());
  }

  @Test
  public void testBrightenWhite() {
    this.init();
    this.white.brighten(56);
    assertEquals("255 255 255 ", this.white.toString());
  }

  @Test
  public void testBrightenTryingToSetAboveMax() {
    this.init();
    this.red.brighten(1000);
    assertEquals("255 255 255 ", this.red.toString());
  }

  @Test
  public void testGrayscaleRed(){
    this.init();
    this.red.grayscale(ImageProcessingModel.GrayscaleTypes.RedGrayscale);
    assertEquals("255 255 255 ", this.red.toString());
  }

  @Test
  public void testGrayscaleGreen(){
    this.init();
    this.red.grayscale(ImageProcessingModel.GrayscaleTypes.GreenGrayscale);
    assertEquals("0 0 0 ", this.red.toString());
  }

  @Test
  public void testGrayscaleBlue(){
    this.init();
    this.red.grayscale(ImageProcessingModel.GrayscaleTypes.BlueGrayscale);
    assertEquals("0 0 0 ", this.red.toString());
  }

  @Test
  public void testGrayscaleValue(){
    this.init();
    this.red.grayscale(ImageProcessingModel.GrayscaleTypes.ValueGrayscale);
    assertEquals("255 255 255 ", this.red.toString());
  }

  @Test
  public void testGrayscaleIntensity(){
    this.init();
    this.red.grayscale(ImageProcessingModel.GrayscaleTypes.IntensityGrayscale);
    assertEquals("85 85 85 ", this.red.toString());
  }

  @Test
  public void testGrayscaleLuma(){
    this.init();
    this.red.grayscale(ImageProcessingModel.GrayscaleTypes.LumaGrayscale);
    assertEquals("54 54 54 ", this.red.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGrayscaleNullParameter() {
    this.init();
    this.green.grayscale(null);
  }

}
