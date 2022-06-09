package model;

import java.io.FileWriter;
import java.io.IOException;

public class Pixel {
  protected int red;
  protected int green;
  protected int blue;
  protected int maxValue;

  public Pixel(int red, int green, int blue, int maxValue) throws IllegalArgumentException {
    if (!((red >= 0) && (green >= 0) && (blue >= 0))) {
      throw new IllegalArgumentException("Pixels must be integers greater than or equal to 0.");
    }

    this.red = red;
    this.green = green;
    this.blue = blue;
    this.maxValue = maxValue;
  }

  public void offsetPixel(int redOffset, int greenOffset, int blueOffset) {
    if (this.red + redOffset > maxValue) {
      this.red = maxValue;
    }
    else {
      this.red += redOffset;
    }

    if (this.green + greenOffset > maxValue) {
      this.green = maxValue;
    }
    else {
      this.green += greenOffset;
    }

    if (this.blue + blueOffset > maxValue) {
      this.blue = maxValue;
    }
    else {
      this.blue += blueOffset;
    }

  }

  public void writePixel(FileWriter writer) throws IOException {
    writer.write(red);
    writer.write( " ");
    writer.write(green);
    writer.write( " ");
    writer.write(blue);
    writer.write( " ");
  }

}
