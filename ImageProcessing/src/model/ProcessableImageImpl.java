package model;

import java.awt.Color;
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
  private PixelImpl[][] pixelGrid;
  private final int width;
  private final int height;
  private final int maxValue;

  /**
   * Constructor for a ProcessableImageImpl that creates a deep copy of the provided image.
   * The constructed image will have identical parameters to the old one.
   *
   * @param image the image to be copied
   * @throws IllegalArgumentException if the image is null
   */
  public ProcessableImageImpl(ProcessableImage image)
          throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Given null parameter.");
    }

    this.pixelGrid = image.getPixelGrid();
    this.width = image.getWidth();
    this.height = image.getHeight();
    this.maxValue = image.getMaxValue();
  }

  /**
   * Constructor for a ProcessableImageImpl that accepts all parameters that are necessary for
   * making a new ProcessableImageImpl individually.
   *
   * @param pixelGrid the 2D array of pixels that represents the actual image
   * @param width     the width of the rows in the image
   * @param height    the height of the columns in the image
   * @param maxValue  the maximum int value that any color channel in any given pixel can have
   */
  public ProcessableImageImpl(PixelImpl[][] pixelGrid, int width, int height, int maxValue) {
    this.pixelGrid = pixelGrid;
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
  }

  // image-manipulation methods

  @Override
  public void brighten(int brightenValue) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixelGrid[i][j].brighten(brightenValue);
      }
    }
  }

  @Override
  public void grayscale(ImageProcessingModel.GrayscaleTypes grayscaleChoice) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixelGrid[i][j].grayscale(grayscaleChoice);
      }
    }
  }

  @Override
  public void grayscale() {
    double[][] matrix = new double[][]{new double[]{0.2126, 0.7152, 0.0722},
      new double[]{0.2126, 0.7152, 0.0722},
      new double[]{0.2126, 0.7152, 0.0722}
    };
    this.applyColorTransformation(matrix);

  }

  @Override
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

  @Override
  public void blur() {
    double[][] kernel = new double[][]{new double[]{0.0625, 0.125, 0.0625},
      new double[]{0.125, 0.25, 0.125},
      new double[]{0.0625, 0.125, 0.0625}
    };
    this.applyFilter(kernel);
  }

  @Override
  public void sharpen() {
    double[][] kernel = new double[][]{new double[]{-0.125, -0.125, -0.125, -0.125, -0.125},
      new double[]{-0.125, 0.25, 0.25, 0.25, -0.125},
      new double[]{-0.125, 0.25, 1, 0.25, -0.125},
      new double[]{-0.125, 0.25, 0.25, 0.25, -0.125},
      new double[]{-0.125, -0.125, -0.125, -0.125, -0.125}
    };
    this.applyFilter(kernel);
  }

  @Override
  public void sepia() {
    double[][] matrix = new double[][]{new double[]{0.393, 0.769, 0.189},
      new double[]{0.349, 0.686, 0.168},
      new double[]{0.272, 0.534, 0.131}
    };
    this.applyColorTransformation(matrix);
  }

  // helpers for the blur and sharpen methods

  /**
   * Helper method that takes a kernel (given as a square matrix) and applies that kernel to every
   * pixel in this processable image.
   *
   * @param kernel the kernel to be applied to each pixel in this image
   */
  private void applyFilter(double[][] kernel) {
    // the new, filtered pixel array
    PixelImpl[][] copyGrid = new PixelImpl[this.height][this.width];

    // for every pixel in each row, change the red, green, and blue values (setComponents) based on
    // a multiplication of every cell in the kernel array by the R, G, or B value in the pixel array
    for (int i = 0; i < this.height; i++) {
      PixelImpl[] tempRow = new PixelImpl[this.width];
      for (int j = 0; j < this.width; j++) {
        // populate the tempRow with the new pixels
        int filteredRed = this.applyKernel(kernel, i, j, "r");
        int filteredGreen = this.applyKernel(kernel, i, j, "g");
        int filteredBlue = this.applyKernel(kernel, i, j, "b");
        PixelImpl filteredPixel = new PixelImpl(filteredRed, filteredGreen, filteredBlue, maxValue);
        tempRow[j] = filteredPixel;
      }
      copyGrid[i] = tempRow;
    }

    this.pixelGrid = copyGrid;
  }

  /**
   * Applies a given kernel to a given pixel's color component. Accesses the neighbors of the pixel
   * to complete the kernel calculation.
   *
   * @param kernel the kernel to be applied to the targeted pixel
   * @param row    the row of the targeted pixel
   * @param col    the column of the targeted pixel
   * @param color  the color component of the targeted pixel that the kernel will be applied to
   * @return the new color component value of the pixel
   */
  private int applyKernel(double[][] kernel, int row, int col, String color) {
    int centerSlot = (kernel.length - 1) / 2;
    int newPixelVal = 0;

    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel.length; j++) {
        // ensure cell exists in pixelGrid. if it does, continue
        if (!(row - centerSlot + i < 0 || row - centerSlot + i >= this.height)
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
   *
   * @param matrix the 2D array of double values that will be used to compute the new color
   *               values of the new image
   */
  private void applyColorTransformation(double[][] matrix) {
    // the new, filtered pixel array
    PixelImpl[][] copyGrid = new PixelImpl[this.height][this.width];

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        PixelImpl oldPixel = this.pixelGrid[i][j];
        int newRed = (int) (matrix[0][0] * oldPixel.getRed() + matrix[0][1] * oldPixel.getGreen()
                + matrix[0][2] * oldPixel.getBlue());
        int newGreen = (int) (matrix[1][0] * oldPixel.getRed() + matrix[1][1] * oldPixel.getGreen()
                + matrix[1][2] * oldPixel.getBlue());
        int newBlue = (int) (matrix[2][0] * oldPixel.getRed() + matrix[2][1] * oldPixel.getGreen()
                + matrix[2][2] * oldPixel.getBlue());
        PixelImpl newPixel = new PixelImpl(newRed, newGreen, newBlue, this.maxValue);
        copyGrid[i][j] = newPixel;
      }
    }
    this.pixelGrid = copyGrid;
  }

  @Override
  public String createPPMContents() {
    StringBuilder newFileContents = new StringBuilder();
    newFileContents.append("P3" + " ");
    newFileContents.append(this.width).append(" ");
    newFileContents.append(this.height).append(" ");
    newFileContents.append(this.maxValue).append(" ");


    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        PixelImpl p1 = this.pixelGrid[i][j];
        newFileContents.append(p1.toString());
      }
    }

    return newFileContents.toString();
  }

  @Override
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

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getMaxValue() {
    return this.maxValue;
  }

  @Override
  public PixelImpl[][] getPixelGrid() {
    PixelImpl[][] gridCopy = new PixelImpl[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        PixelImpl oldPixel = this.pixelGrid[i][j];
        PixelImpl newPixel = new PixelImpl(
                oldPixel.getRed(),
                oldPixel.getGreen(),
                oldPixel.getBlue(),
                this.maxValue);
        gridCopy[i][j] = newPixel;
      }
    }
    return gridCopy;
  }

  // assignment 6 methods

  /**
   * Generates the frequency values to be used when plotting a histogram for this processable image.
   * @return a list of four array lists, each of which are of size equal to the {@code maxValue} for
   *         any of the given pixels in this processableImage. Each list stores the frequencies of
   *         a given value for all values 0 -> {@code maxValue}
   */
  public int[][] generateHistogram() {
    int[] red = new int[this.maxValue];
    int[] green = new int[this.maxValue];
    int[] blue = new int[this.maxValue];
    int[] intensity = new int[this.maxValue]; // the average of the above three arrays

    for (int i = 0; i < this.pixelGrid.length; i++) {
      for (int j = 0; j < this.pixelGrid[0].length; j++) {
        Pixel curPixel = pixelGrid[i][j];
        red[curPixel.getRed()]++;
        green[curPixel.getGreen()]++;
        blue[curPixel.getBlue()]++;
        intensity[(curPixel.getRed() + curPixel.getGreen() + curPixel.getBlue()) / 3]++;
      }
    }

    return new int[][] {red, green, blue, intensity};
  }

//  public ProcessableImage drawHistogram() {
//    int[][] frequencies = this.generateHistogram(); // refreshes before drawing
//    PixelImpl[][] histogram = new PixelImpl[3][this.maxValue];
//
//
//    return new ProcessableImageImpl(histogram, 500, 100, this.maxValue);
//  }

}