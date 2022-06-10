package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Class to represent a PPM (Portable Pix Map) image, which stores all the pixel values in a 2D
 * array of Pixels of a specified height and width. This program (currently) only supports
 * manipulation of PPM-type images. This class stores all the actual image-manipulation
 * operations that can be conducted by the user; each operation will directly manipulate the 2D
 * array of pixels, {@code pixelGrid}.
 */
public class PPMImage {
  private final Pixel[][] pixelGrid;
  private final int width;
  private final int height;
  private final int maxValue;

  /**
   * Constructor for a PPM Image that creates a deep copy of the provided image. The constructed
   * image will have identical parameters to the old one.
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

  public PPMImage(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
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

    Pixel[][] pixelGrid = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        pixelGrid[i][j] = new Pixel(r, g, b, this.maxValue);
      }
    }

    this.pixelGrid = pixelGrid;
  }

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

  public void flip(ImageProcessingModelImpl.Orientations orientation){
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

  public void saveImage(String path) throws IOException {
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
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  public boolean equals(PPMImage other){
    if (width != other.width) return false;
    if (height != other.height) return false;
    if (maxValue != other.maxValue) return false;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (!(this.pixelGrid[i][j].toString().equals(other.pixelGrid[i][j].toString()))){
          return false;
        }
      }
    }
    return true;
  }
}