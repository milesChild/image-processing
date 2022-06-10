import org.junit.Test;

import java.io.IOException;

import model.ImageProcessingModel;
import model.PPMImage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PPMImageTest {
  PPMImage donkeyTest;
  PPMImage donkeyRedGrayscale = new PPMImage("SmallPPMImages/donkey-red-greyscale.ppm");
  PPMImage donkeyGreenGrayscale = new PPMImage("SmallPPMImages/donkey-green-greyscale.ppm");
  PPMImage donkeyBlueGrayscale = new PPMImage("SmallPPMImages/donkey-blue-greyscale.ppm");
  PPMImage donkeyValueGrayscale = new PPMImage("SmallPPMImages/donkey-value-greyscale.ppm");
  PPMImage donkeyIntensityGrayscale = new PPMImage("SmallPPMImages/donkey-intensity-greyscale.ppm");
  PPMImage donkeyLumaGrayscale = new PPMImage("SmallPPMImages/donkey-luma-greyscale.ppm");
  PPMImage donkeyHorizontal = new PPMImage("SmallPPMImages/donkey-horizontal.ppm");
    this.donkeyVertical = new PPMImage("SmallPPMImages/donkey-vertical.ppm");
    this.donkeyBrighterBy50 = new PPMImage("SmallPPMImages/donkey-brighten-by-50.ppm");
    this.donkeyDimBy50 = new PPMImage("SmallPPMImages/donkey-dim-by-50.ppm");

  public void init(){
    this.donkeyTest = new PPMImage("SmallPPMImages/donkey.ppm");
    this.donkeyRedGrayscale = new PPMImage("SmallPPMImages/donkey-red-greyscale.ppm");
    this.donkeyGreenGrayscale = new PPMImage("SmallPPMImages/donkey-green-greyscale.ppm");
    this.donkeyBlueGrayscale = new PPMImage("SmallPPMImages/donkey-blue-greyscale.ppm");
    this.donkeyValueGrayscale = new PPMImage("SmallPPMImages/donkey-value-greyscale.ppm");
    this.donkeyIntensityGrayscale = new PPMImage("SmallPPMImages/donkey-intensity-greyscale.ppm");
    this.donkeyValueGrayscale = new PPMImage("SmallPPMImages/donkey-value-greyscale.ppm");
    this.donkeyLumaGrayscale = new PPMImage("SmallPPMImages/donkey-luma-greyscale.ppm");
    this.donkeyHorizontal = new PPMImage("SmallPPMImages/donkey-horizontal.ppm");
    this.donkeyVertical = new PPMImage("SmallPPMImages/donkey-vertical.ppm");
    this.donkeyBrighterBy50 = new PPMImage("SmallPPMImages/donkey-brighten-by-50.ppm");
    this.donkeyDimBy50 = new PPMImage("SmallPPMImages/donkey-dim-by-50.ppm");
  }
//  PPMImage henockImage;
//  PPMImage henockImageCopy;
//  PPMImage koala;
//  PPMImage koalaRedGrayscale;
//  PPMImage koalaGreenGrayscale;
//  PPMImage koalaBlueGrayscale;
//  PPMImage koalaIntensityGrayscale;
//  PPMImage koalaLumaGrayscale;
//  PPMImage koalaValueGrayscale;
//  PPMImage koalaHorizontal;
//  PPMImage koalaVertical;
//  PPMImage koalaVerticalHorizontal;
//  PPMImage koalaHorizontalVertical;
//  PPMImage koalaBrighterBy50;

//  public void init() {
//    this.henockImage = new PPMImage("PPMimages/Henock.ppm");
//    this.henockImageCopy = new PPMImage(henockImage);
//
//    this.koala = new PPMImage("PPMimages/Koala.ppm");
//    this.koalaRedGrayscale = new PPMImage("PPMimages/koala-red-greyscale.ppm");
//    this.koalaGreenGrayscale = new PPMImage("PPMimages/koala-green-greyscale.ppm");
//    this.koalaBlueGrayscale = new PPMImage("PPMimages/koala-blue-greyscale.ppm");
//    //this.koalaIntensityGrayscale = new PPMImage("PPMimages/koala-intensity-greyscale.ppm");
//    //this.koalaLumaGrayscale = new PPMImage("PPMimages/koala-luma-greyscale.ppm");
//    this.koalaValueGrayscale = new PPMImage("PPMimages/koala-value-greyscale.ppm");
//    this.koalaHorizontal = new PPMImage("PPMimages/koala-horizontal.ppm");
//    this.koalaVertical = new PPMImage("PPMimages/koala-horizontal.ppm");
//    this.koalaHorizontalVertical = new PPMImage("PPMimages/koala-horizontal-vertical.ppm");
//    this.koalaVerticalHorizontal = new PPMImage("PPMimages/koala-vertical-horizontal.ppm");
//    this.koalaBrighterBy50 = new PPMImage("PPMimages/koala-brighter-by-50.ppm");
//  }
//
//  @Test
//  public void testCopy() {
//    this.init();
//    assertEquals(henockImage, henockImageCopy);
//    assertEquals(henockImageCopy, henockImage);
//  }
//
//  @Test
//  public void testBrighten(){
//    this.init();
//    this.koala.brighten(50);
//    assertTrue(this.koala.equals(koalaBrighterBy50));
//  }
//
//  @Test
//  public void testGrayscale(){
//    this.init();
//    this.koala.grayscale(ImageProcessingModel.GrayscaleTypes.RedGrayscale);
//    assertTrue(this.koala.equals(koalaRedGrayscale));
//
//    this.init();
//    this.koala.grayscale(ImageProcessingModel.GrayscaleTypes.GreenGrayscale);
//    assertTrue(this.koala.equals(koalaGreenGrayscale));
//
//    this.init();
//    this.koala.grayscale(ImageProcessingModel.GrayscaleTypes.BlueGrayscale);
//    assertTrue(this.koala.equals(koalaBlueGrayscale));
//
////    this.init();
////    this.koala.grayscale(ImageProcessingModel.GrayscaleTypes.IntensityGrayscale);
////    assertTrue(this.koala.equals(koalaIntensityGrayscale));
//
//    this.init();
//    this.koala.grayscale(ImageProcessingModel.GrayscaleTypes.ValueGrayscale);
//    assertTrue(this.koala.equals(koalaValueGrayscale));
//
////    this.init();
////    this.koala.grayscale(ImageProcessingModel.GrayscaleTypes.LumaGrayscale);
////    assertTrue(this.koala.equals(koalaLumaGrayscale));
//  }
//
//  @Test
//  public void testFlipHorizontal() {
//    this.init();
//    this.koala.flip(ImageProcessingModel.Orientations.Horizontal);
//    assertTrue(this.koala.equals(koalaHorizontal));
//  }
//
//  @Test
//  public void testFlipVertical() {
//    this.init();
//    this.koala.flip(ImageProcessingModel.Orientations.Vertical);
//    assertTrue(this.koala.equals(koalaVertical));
//  }
//
//  @Test
//  public void testFlipHorizontalVertical() {
//    this.init();
//    this.koala.flip(ImageProcessingModel.Orientations.Horizontal);
//    this.koala.flip(ImageProcessingModel.Orientations.Vertical);
//    assertTrue(this.koala.equals(koalaHorizontalVertical));
//  }
//
//  @Test
//  public void testFlipVerticalHorizontal() {
//    this.init();
//    this.koala.flip(ImageProcessingModel.Orientations.Vertical);
//    this.koala.flip(ImageProcessingModel.Orientations.Horizontal);
//    assertTrue(this.koala.equals(koalaVerticalHorizontal));
//  }

//  @Test
//  public void testGenerateImages() throws IOException {
////    PPMImage donkey = new PPMImage("SmallPPMImages/donkey.ppm");
////
////    donkey.brighten(10);
////    donkey.toString();
////    //donkey.saveImage("SmallPPMImages/bright-donkey.ppm");
////    PPMImage donkeyBright = new PPMImage(donkey);
////    donkeyBright.toString();
////    PPMImage henock = new PPMImage("SmallPPMImages/donkey.ppm");
////    henock.brighten(10);
////    henock.saveImage("SmallPPMimages/wheenock.ppm");
//
//    //donkeyBright.saveImage("SmallPPMImages/donkey-test.ppm");
////    donkey.brighten(10);
////    //donkey.flip(ImageProcessingModel.Orientations.Vertical);
//////    donkey.saveImage("SmallPPMImages/donkey-vertical.ppm");
////
////    donkeyTest = new PPMImage("SmallPPMImages/donkey.ppm");
////    donkeyTest.flip(ImageProcessingModel.Orientations.Vertical);
////    donkeyTest.saveImage("SmallPPMImages/donkey-horizontal.ppm");
//  }
  @Test
  public void testCopyImage(){
    this.init();
    PPMImage donkeyCopy = new PPMImage(donkeyTest);
    donkeyCopy.equals(donkeyTest);
    donkeyTest.equals(donkeyCopy);
  }

  @Test
  public void testFlipImage() {
    this.init();
    donkeyTest.flip(ImageProcessingModel.Orientations.Horizontal);
    donkeyTest.equals(donkeyHorizontal);

    this.init();
    donkeyTest.flip(ImageProcessingModel.Orientations.Vertical);
    donkeyTest.equals(donkeyVertical);
  }

  public void testBrightenDim(){
    this.init();
    donkeyTest.flip(ImageProcessingModel.Orientations.Horizontal);
    donkeyTest.equals(donkeyHorizontal);

    this.init();
    donkeyTest.flip(ImageProcessingModel.Orientations.Vertical);
    donkeyTest.equals(donkeyVertical);
  }

}