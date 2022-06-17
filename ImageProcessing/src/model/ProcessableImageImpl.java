package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/**
 * Class to represent an image, which stores all the pixel values in a 2D array of Pixels of a
 * specified height and width.
 * This class stores all the actual image-manipulation operations that can be conducted by the user;
 * each operation will directly manipulate the 2D array of pixels, {@code pixelGrid}.
 */
public class ProcessableImageImpl implements ProcessableImage {
  private Pixel[][] pixelGrid;
  private final int width;
  private final int height;
  private final int maxValue;

  /**
   * Constructor for a ProcessableImageImpl that creates a deep copy of the provided image.
   * The constructed image will have identical parameters to the old one.
   * @param image the image to be copied
   * @throws IllegalArgumentException if the image is null
   */
  public ProcessableImageImpl(ProcessableImageImpl image)
          throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Given null parameter.");
    }
    // deep copy of pixelGrid
    Pixel[][] copyGrid = new Pixel[image.height][image.width];
    for (int i = 0; i < image.height; i++) {
      Pixel[] tempRow = new Pixel[image.width];

      System.arraycopy(image.pixelGrid[i], 0, tempRow, 0, image.width);
      copyGrid[i] = tempRow;
    }

    this.pixelGrid = copyGrid;
    this.width = image.width;
    this.height = image.height;
    this.maxValue = image.maxValue;
  }

  /**
   * Constructor for a ProcessableImageImpl that accepts all parameters that are necessary for
   * making a new ProcessableImageImpl individually.
   * @param pixelGrid the 2D array of pixels that represents the actual image
   * @param width the width of the rows in the image
   * @param height the height of the columns in the image
   * @param maxValue the maximum int value that any color channel in any given pixel can have
   */
  public ProcessableImageImpl(Pixel[][] pixelGrid, int width, int height, int maxValue) {
    this.pixelGrid = pixelGrid;
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
  }

  // image-manipulation methods

  public void brighten(int brightenValue) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixelGrid[i][j].brighten(brightenValue);
      }
    }
  }

  public void grayscale(ImageProcessingModel.GrayscaleTypes grayscaleChoice) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixelGrid[i][j].grayscale(grayscaleChoice);
      }
    }
  }

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

  public void grayscale() {
    double[][] matrix = new double[][]{
            new double[]{0.2126, 0.7152, 0.0722},
            new double[]{0.2126, 0.7152, 0.0722},
            new double[]{0.2126, 0.7152, 0.0722}
    };
    this.applyColorTransformation(matrix);

  }

  public void sepia() {
    double[][] matrix = new double[][]{
            new double[]{0.393, 0.769, 0.189},
            new double[]{0.349, 0.686, 0.168},
            new double[]{0.272, 0.534, 0.131}
    };
    this.applyColorTransformation(matrix);
  }

  // helpers for the blur and sharpen methods

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

  // helper for the grayscale and sepia methods

  /**
   * Helper method that takes a matrix and uses it to transform the colors in each of the color
   * channels for each pixel in the {@code pixelGrid}.
   * @param matrix the 2D array of double values that will be used to compute the new color
   *               values of the new image
   */
  private void applyColorTransformation(double[][] matrix){
    // the new, filtered pixel array
    Pixel[][] copyGrid = new Pixel[this.height][this.width];

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        Pixel oldPixel = this.pixelGrid[i][j];
        int newRed = (int) (matrix[0][0] * oldPixel.getRed() + matrix[0][1] * oldPixel.getGreen()
                + matrix[0][2] * oldPixel.getBlue());
        int newGreen = (int) (matrix[1][0] * oldPixel.getRed() + matrix[1][1] * oldPixel.getGreen()
                + matrix[1][2] * oldPixel.getBlue());
        int newBlue = (int) (matrix[2][0] * oldPixel.getRed() + matrix[2][1] * oldPixel.getGreen()
                + matrix[2][2] * oldPixel.getBlue());
        Pixel newPixel = new Pixel(newRed, newGreen, newBlue, this.maxValue);
        copyGrid[i][j] = newPixel;
      }
    }
    this.pixelGrid = copyGrid;
  }

  public String createPPMContents() {
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

    return newFileContents.toString();
  }

  public BufferedImage createCommonImageContents() {
    BufferedImage bufferedImage = new BufferedImage(
            this.width, this.height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        Color color = new Color(this.pixelGrid[i][j].getRed(), this.pixelGrid[i][j].getGreen(),
                this.pixelGrid[i][j].getBlue());
        bufferedImage.setRGB(j, i, color.getRGB());
      }
    }
    return bufferedImage;
  }

  /**
   * Overriden equals method. A ProcessableImageImpl equals another image if they have the same
   * width, height, max value, and the pixels in each grid are equal.
   *
   * @param o the object that is being checked if it is equal against this ProcessableImageImpl
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

    ProcessableImageImpl processableImageImpl = (ProcessableImageImpl) o;

    if (width != processableImageImpl.width) {
      return false;
    }

    if (height != processableImageImpl.height) {
      return false;
    }

    if (maxValue != processableImageImpl.maxValue) {
      return false;
    }

    return Arrays.deepEquals(pixelGrid, processableImageImpl.pixelGrid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.height, this.maxValue, this.width);
  }

  public int getHeight() {
    return this.height;
  }

  public int getWidth() {
    return this.width;
  }

  // TODO: Remove?
  public Pixel[][] getPixelGrid() {
    Pixel[][] gridCopy = new Pixel[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      System.arraycopy(this.pixelGrid[i], 0, gridCopy[i], 0, this.width);
    }
    return gridCopy;
  }
}