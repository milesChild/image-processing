package model;

public class Pixel {
  protected final int red;
  protected final int green;
  protected final int blue;

  public Pixel(int red, int green, int blue) throws IllegalArgumentException {
    if (!((red >= 0) && (green >= 0) && (blue >= 0))) {
      throw new IllegalArgumentException("Pixels must be integers greater than or equal to 0.");
    }

    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  @Override
  public String toString() {
    return this.red + " " + this.green + " " + this.blue;
  }
}
