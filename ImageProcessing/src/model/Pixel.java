package model;

public class Pixel {
  protected final int red;
  protected final int green;
  protected final int blue;

  public Pixel(int red, int green, int blue){
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  @Override
  public String toString() {
    return this.red + " " + this.green + " " + this.blue
  }
}
