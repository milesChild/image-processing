package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class PPMImage {
  private final Pixel[][] pixelGrid;
  private final int width;
  private final int height;
  private final int maxValue;

  public PPMImage(String filename){
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File "+filename+ " not found!");
    }

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0)!='#') {
        builder.append(s+System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }

    this.width = sc.nextInt();
    this.height = sc.nextInt();
    this.maxValue = sc.nextInt();

    Pixel[][] pixelGrid = new Pixel[height][width];
    for (int i=0;i<height;i++) {
      for (int j=0;j<width;j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        pixelGrid[i][j] = new Pixel(r,g,b, this.maxValue);
      }
    }

    this.pixelGrid = pixelGrid;
  }

  public void editColor(int redOffset, int greenOffset, int blueOffset) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixelGrid[i][j].offsetPixel(redOffset, greenOffset, blueOffset);
      }
    }
  }

  public void editOrientation() {
  }

  public void flipVertically(){
    Collections.reverse(Arrays.asList(this.pixelGrid));
  }

  public void flipHorizontally(){
    for (int i = 0; i < width; i++) {
      Collections.reverse(Arrays.asList(this.pixelGrid[i]));
    }
  }

  public void saveImage (String path, String filename) throws IOException {
    File imageFile = new File(path);
    imageFile.createNewFile();
    FileWriter writer = new FileWriter(filename);

    writer.write("P3\n");
    writer.write("# " + filename + "\n");
    writer.write(this.width);
    writer.write(" ");
    writer.write(this.height);
    writer.write("\n");
    writer.write(this.maxValue);
    writer.write("\n");

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixelGrid[i][j].writePixel(writer);
      }
    }
    writer.close();

  }
}
