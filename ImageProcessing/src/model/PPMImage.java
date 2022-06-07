package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PPMImage {
  private final Pixel[][] pixelGrid;
  private int width;
  private int height;
  private int maxValue;

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

        pixelGrid[i][j] = new Pixel(r,g,b);
      }
    }

    this.pixelGrid = pixelGrid;
  }

  public void saveImage (String path) throws IOException {
    File imageFile = new File(path);
    imageFile.createNewFile();
    PrintWriter fileWriter = new PrintWriter(path);

    fileWriter.write("P3");
    fileWriter.write(this.width);
    fileWriter.write(this.height);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        fileWriter.write();
      }
    }

  }
}
