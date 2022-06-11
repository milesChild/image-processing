package model;

/**
 * This class represents a Pixel, which is referenced throughout the entire rest of the project.
 * PPM images are made up of pixels, each of which has a Red, Green, and Blue component. RGB values
 * are stored in the format 0-255, 0-255, 0-255, 255 being the maximum value for any particular
 * color component. Users can also specify an alternate {@code maxValue} for the color component.
 */
public class Pixel {
  private int red;
  private int green;
  private int blue;
  private final int maxValue;

  /**
   * Default constructor for a pixel which takes in a red, green, blue, and max value that any of
   * the pixels can hold. This information will be used to construct a pixel in RGB format, where
   * (r, g, b) are the color components and r, g, b >= maxValue.
   *
   * @param red      red pixel component of RGB
   * @param green    green pixel component of RGB
   * @param blue     blue pixel component of RGB
   * @param maxValue the maximum value that any pixel can have
   * @throws IllegalArgumentException if any of the RGB values are set above the max value or
   *                                  are not positive
   */
  public Pixel(int red, int green, int blue, int maxValue) throws IllegalArgumentException {
    if (maxValue < 0) {
      throw new IllegalArgumentException("Max value of a pixel must be greater than 0.");
    }
    this.maxValue = maxValue;
    this.setComponents(red, green, blue); // throws exception when R, G, or B > maxvalue
  }

  public void brighten(int brightenValue) {
    int newRed = this.red;
    int newGreen = this.green;
    int newBlue = this.blue;
    if (brightenValue > 0) {
      newRed = Math.min(this.red + brightenValue, this.maxValue);
      newGreen = Math.min(this.green + brightenValue, this.maxValue);
      newBlue = Math.min(this.blue + brightenValue, this.maxValue);
    }
    if (brightenValue < 0) {
      newRed = Math.max(this.red + brightenValue, 0);
      newGreen = Math.max(this.green + brightenValue, 0);
      newBlue = Math.max(this.blue + brightenValue, 0);
    }

    this.setComponents(newRed, newGreen, newBlue);
  }

  private int findMaxComponent() {
    if (this.red >= this.green && this.red >= this.blue) {
      return this.red;
    } else if (this.green >= this.red && this.green >= this.blue) {
      return this.green;
    } else {
      return this.blue;
    }
  }

  /**
   * Converts a pixel to a certain grayscale type, specified by the user. If the user passes an
   * invalid grayscale type, the pixels simply will not change. Our design already notifies the user
   * that they have passed in an invalid command if they specify an invalid grayscale type.
   * We ensure that the method will never take in an invalid enumeration.
   *
   * @param grayscale the grayscale type to convert the pixels to.
   * @throws IllegalArgumentException if the provided grayscale type is null
   */
  public void grayscale(ImageProcessingModel.GrayscaleTypes grayscale)
          throws IllegalArgumentException {
    if (grayscale == null) {
      throw new IllegalArgumentException("Given null parameter.");
    }

    switch (grayscale) {
      case RedGrayscale:
        this.setComponents(this.red, this.red, this.red);
        break;
      case GreenGrayscale:
        this.setComponents(this.green, this.green, this.green);
        break;
      case BlueGrayscale:
        this.setComponents(this.blue, this.blue, this.blue);
        break;
      case ValueGrayscale:
        int maxComponent = this.findMaxComponent();
        this.setComponents(maxComponent, maxComponent, maxComponent);
        break;
      case IntensityGrayscale:
        int intensity = (int) Math.round((this.red + this.green + this.blue) / 3.0);
        this.setComponents(intensity, intensity, intensity);
        break;
      case LumaGrayscale:
        int luma = (int) Math.round((this.red * 0.2126 + this.green * 0.7152 + this.blue * 0.0722));
        this.setComponents(luma, luma, luma);
        break;
      default:
        this.setComponents(this.red, this.green, this.blue);
        break;
    }
  }

  @Override
  public String toString() {
    return this.red + " " + this.green + " " + this.blue + " ";
  }

  public int getRed() {
    return this.red;
  }

  public int getGreen() {
    return this.green;
  }

  public int getBlue() {
    return this.blue;
  }

  public void setComponents(int redVal, int greenVal, int blueVal) throws IllegalArgumentException {
    if (redVal < 0 || redVal > this.maxValue) {
      throw new IllegalArgumentException("The red component must be an integer greater than 0 " +
              "or less than the max value.");
    }
    if (greenVal < 0 || greenVal > this.maxValue) {
      throw new IllegalArgumentException("The green component must be an integer greater than 0 " +
              "or less than the max value.");
    }
    if (blueVal < 0 || blueVal > this.maxValue) {
      throw new IllegalArgumentException("The blue component must be an integer greater than 0 " +
              "or less than the max value.");
    }
    this.red = redVal;
    this.green = greenVal;
    this.blue = blueVal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Pixel pixel = (Pixel) o;

    if (red != pixel.red) return false;
    if (green != pixel.green) return false;
    return blue == pixel.blue;
  }
}
