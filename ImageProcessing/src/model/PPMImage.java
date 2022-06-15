package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

import javax.imageio.ImageIO;

// TODO:
// IMPLEMENT THE NEW LOAD METHODS (FOR EACH OF THE NEW FILE TYPES)
// IMPLEMENT THE NEW SAVE METHODS (FOR EACH OF THE NEW FILE TYPES)
// CHANGE THE FIELDS TO NOT BE FINAL?
// IMPLEMENT THE LOGIC FOR EACH OF THE NEW OPERATIONS:
//   - BLUR
//   - SHARPEN
//   - GREYSCALE
//   - SEPIA

/**
 * Class to represent a PPM (Portable Pix Map) image, which stores all the pixel values in a 2D
 * array of Pixels of a specified height and width. This program (currently) only supports
 * manipulation of PPM-type images. This class stores all the actual image-manipulation
 * operations that can be conducted by the user; each operation will directly manipulate the 2D
 * array of pixels, {@code pixelGrid}.
 */
public class PPMImage {
  private Pixel[][] pixelGrid;
  private int width;
  private int height;
  private int maxValue;

  /**
   * Constructor for a PPM Image that creates a deep copy of the provided image. The constructed
   * image will have identical parameters to the old one.
   *
   * @param fromImage the image to be copied
   * @throws IllegalArgumentException if the fromImage is null
   */
  public PPMImage(PPMImage fromImage) throws IllegalArgumentException {
    if (fromImage == null) {
      throw new IllegalArgumentException("Given null parameter.");
    }
    // deep copy of pixelGrid
    Pixel[][] copyGrid = new Pixel[fromImage.height][fromImage.width];
    for (int i = 0; i < fromImage.height; i++) {
      Pixel[] tempRow = new Pixel[fromImage.width];

      System.arraycopy(fromImage.pixelGrid[i], 0, tempRow, 0, fromImage.width);
      copyGrid[i] = tempRow;
    }

    this.pixelGrid = copyGrid;
    this.width = fromImage.width;
    this.height = fromImage.height;
    this.maxValue = fromImage.maxValue;
  }

  /**
   * Constructor for a PPM Image that creates an image given a specified computer path for an image.
   * Delegates field initialization to helper methods based on the file type that is provided in
   * the path. Supported file types: .ppm, .jpg, .png, .bmp
   *
   * @param path the computer path for the image that is to be constructed as a PPM image
   * @throws IllegalArgumentException if image is not found from the given path
   */
  public PPMImage(String path) throws IllegalArgumentException {
    String fileType = path.substring(path.length() - 4);
    if (fileType.equals(".ppm")) {
      this.loadPPM(path);
    }
    else if (fileType.equals(".jpg") || fileType.equals(".png") || fileType.equals(".bmp")){
      this.loadCommonImage(path);
    }
    else {
      throw new IllegalArgumentException("Path does not end in a valid file type!");
    }

  }

  private void loadPPM(String path) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + path + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    // read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3.");
    }

    this.width = sc.nextInt();
    this.height = sc.nextInt();
    this.maxValue = sc.nextInt();

    Pixel[][] pixelGrid = new Pixel[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        pixelGrid[i][j] = new Pixel(r, g, b, this.maxValue);
      }
    }

    this.pixelGrid = pixelGrid;
  }

  private void loadCommonImage(String path) {
    BufferedImage bufferedImage;
    try {
      bufferedImage = ImageIO.read(new File(path));
    }
    catch (IOException e){
      throw new IllegalArgumentException("File " + path + " not found!");
    }

    this.height = bufferedImage.getHeight();
    this.width = bufferedImage.getWidth();
    this.maxValue = 255;

    Pixel[][] pixelGrid = new Pixel[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        Color color = new Color(bufferedImage.getRGB(j, i));
        pixelGrid[i][j] = new Pixel(
                color.getRed(),
                color.getGreen(),
                color.getBlue(),
                this.maxValue);

      }
    }
    this.pixelGrid = pixelGrid;
  }

  /**
   * Brightens all the pixels in this PPM image's pixel grid given a specified value.
   *
   * @param brightenValue the value to brighten each pixel by
   */
  public void brighten(int brightenValue) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixelGrid[i][j].brighten(brightenValue);
      }
    }
  }

  /**
   * Converts all the pixels in this PPM image's pixel grid given a specified grayscale choice.
   *
   * @param grayscaleChoice the type of grayscale the image will be converted to
   */
  public void grayscale(ImageProcessingModel.GrayscaleTypes grayscaleChoice) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixelGrid[i][j].grayscale(grayscaleChoice);
      }
    }
  }

  /**
   * Flips the pixel grid in this PPM image horizontally or vertically,
   * given the specified flip choice.
   *
   * @param orientation the type of flip that will be performed on this PPM image
   */
  public void flip(ImageProcessingModelImpl.Orientations orientation) {
    switch (orientation) {
      case Vertical:
        Collections.reverse(Arrays.asList(this.pixelGrid));
        break;
      case Horizontal:
        for (int i = 0; i < height; i++) {
          Collections.reverse(Arrays.asList(this.pixelGrid[i]));
        }
        break;
      default:
    }
  }

  public void blur() {
    double[][] kernel = new double[][]{
            new double[]{0.0625, 0.125, 0.0625},
            new double[]{0.125, 0.25, 0.125},
            new double[]{0.0625, 0.125, 0.0625}
    };
    this.applyFilter(kernel);
  }

  public void sharpen() {
    double[][] kernel = new double[][]{
            new double[]{-0.125, -0.125, -0.125, -0.125, -0.125},
            new double[]{-0.125, 0.25, 0.25, 0.25, -0.125},
            new double[]{-0.125, 0.25, 1, 0.25, -0.125},
            new double[]{-0.125, 0.25, 0.25, 0.25, -0.125},
            new double[]{-0.125, -0.125, -0.125, -0.125, -0.125}
    };
    this.applyFilter(kernel);
  }

  public void greyscale() {

  }

  public void sepia() {

  }

  private void applyFilter(double[][] kernel) {
    // the new, filtered pixel array
    Pixel[][] copyGrid = new Pixel[this.height][this.width];

    // for every pixel in each row, change the red, green, and blue values (setComponents) based on
    // a multiplication of every cell in the kernel array by the R, G, or B value in the pixel array
    for(int i = 0; i < this.height; i++) {
      Pixel[] tempRow = new Pixel[this.width];
      for (int j = 0; j < this.width; j++) {
        // populate the tempRow with the new pixels
        int filteredRed = this.applyKernel(kernel, i, j, "r");
        int filteredGreen = this.applyKernel(kernel, i, j, "g");
        int filteredBlue = this.applyKernel(kernel, i, j, "b");
        Pixel filteredPixel = new Pixel(filteredRed, filteredGreen, filteredBlue, maxValue);
        tempRow[j] = filteredPixel;
      }
      copyGrid[i] = tempRow;
    }

    this.pixelGrid = copyGrid;
  }

  private int applyKernel(double[][] kernel, int row, int col, String color) {
    int centerSlot = (kernel.length - 1) / 2;
    int newPixelVal = 0;

    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel.length; j++) {
        // ensure cell exists in pixelGrid. if it does, continue
        if(!(row - centerSlot + i < 0 || row - centerSlot + i >= this.height)
                && !(col - centerSlot + j < 0 || col - centerSlot + j >= this.width)) {
          double kernelVal = kernel[i][j];
          double pixelGridVal = 0;

          if (color.equals("r")) {
            pixelGridVal += pixelGrid[row - centerSlot + i][col - centerSlot + j].getRed();
          }
          if (color.equals("g")) {
            pixelGridVal += pixelGrid[row - centerSlot + i][col - centerSlot + j].getGreen();
          }
          if (color.equals("b")) {
            pixelGridVal += pixelGrid[row - centerSlot + i][col - centerSlot + j].getBlue();
          }
          newPixelVal += pixelGridVal * kernelVal;
        }
      }
    }

    return newPixelVal;
  }

  /**
   * Saves this PPM image to a specified path on the device.
   *
   * @param path the device path the PPM image will be saved to
   */
  public void saveImage(String path) {
    String fileType = path.substring(path.length() - 4);
    if (fileType.equals(".ppm")) {
      this.saveAsPPM(path);
    }
    else if (fileType.equals(".jpg") || fileType.equals(".bpm") || fileType.equals(".png")) {
      this.saveAsCommonImage(path);
    }
    else {
      throw new IllegalArgumentException("Illegal file type in path!");
    }
  }

  private void saveAsPPM(String path) {
    File newFile = new File(path);

    StringBuilder newFileContents = new StringBuilder();
    newFileContents.append("P3" + " ");
    newFileContents.append(this.width).append(" ");
    newFileContents.append(this.height).append(" ");
    newFileContents.append(this.maxValue).append(" ");


    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel p1 = this.pixelGrid[i][j];
        newFileContents.append(p1.toString());
      }
    }

    try {
      FileWriter writer = new FileWriter(newFile);
      newFile.createNewFile();
      writer.write(newFileContents.toString());
      writer.close();
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  private void saveAsCommonImage(String path) {
    File newFile = new File(path);
    BufferedImage bufferedImage = new BufferedImage(
            this.width, this.height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        Color color = new Color(this.pixelGrid[i][j].getRed(), this.pixelGrid[i][j].getGreen(),
                this.pixelGrid[i][j].getBlue());
        bufferedImage.setRGB(j, i, color.getRGB());
      }
    }

    try {
      ImageIO.write(bufferedImage, path, newFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  /**
   * Overriden equals method. A PPMImage equals another image if they have the same width, height,
   * max value, and the pixels in each grid are equal.
   *
   * @param o the object that is being checked if it is equal against this PPMImage
   * @return true if the object is equal, false if the object is not equal
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PPMImage ppmImage = (PPMImage) o;

    if (width != ppmImage.width) {
      return false;
    }

    if (height != ppmImage.height) {
      return false;
    }

    if (maxValue != ppmImage.maxValue) {
      return false;
    }

    return Arrays.deepEquals(pixelGrid, ppmImage.pixelGrid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.height, this.maxValue, this.width);
  }

  /**
   * Gets the height of this PPMImage.
   *
   * @return the height of this image
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Gets the width of this PPMImage.
   *
   * @return the width of this image
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Gets a deep copy of this PPMImage's pixel grid.
   *
   * @return a copy of this image's pixel grid
   */
  public Pixel[][] getPixelGrid() {
    Pixel[][] gridCopy = new Pixel[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      System.arraycopy(this.pixelGrid[i], 0, gridCopy[i], 0, this.width);
    }
    return gridCopy;
  }
}