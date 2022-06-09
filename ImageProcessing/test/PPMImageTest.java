import org.junit.Test;

import model.PPMImage;

import static org.junit.Assert.assertEquals;

public class PPMImageTest {
  PPMImage henockImage;
  PPMImage henockImageCopy;

  public void init() {
    henockImage = new PPMImage("Henock.ppm");
    henockImageCopy = new PPMImage(henockImage);
  }

  @Test
  public void testCopy() {
    this.init();
    assertEquals(henockImage, henockImageCopy);
    assertEquals(henockImageCopy, henockImage);
  }

  @Test
  public void testBrighten(){
    henockImage.brighten(20);
  }


}