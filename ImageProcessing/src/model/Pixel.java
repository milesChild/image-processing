package model;

public class Pixel {
  private int red;
  private int green;
  private int blue;
  private final int maxValue;

  public Pixel(int red, int green, int blue, int maxValue) throws IllegalArgumentException {
    if (!((red >= 0) && (green >= 0) && (blue >= 0))) {
      throw new IllegalArgumentException("Pixels must be integers greater than or equal to 0.");
    }
    this.setComponents(red, green, blue);
    this.maxValue = maxValue;
  }

  public void brighten (int brightenValue) {
    int newRed = Math.min(this.red + brightenValue, this.maxValue);
    int newGreen = Math.min(this.green + brightenValue, this.maxValue);
    int newBlue = Math.min(this.blue + brightenValue, this.maxValue);
    this.setComponents(newRed, newGreen, newBlue);
  }

  private int findMaxComponent(){
    if (this.red >= this.green && this.red >= this.blue){
      return this.red;
    } else if (this.green >= this.red && this.green >= this.blue) {
      return this.green;
    } else {
      return this.blue;
    }
  }

  public void grayscale (ImageProcessingModelImpl.GrayscaleTypes grayscale){
    switch (grayscale) {
      case redGrayscale:
        this.setComponents(this.red, this.red, this.red);
        break;
      case greenGrayscale:
        this.setComponents(this.green, this.green, this.green);
        break;
      case blueGrayscale:
        this.setComponents(this.blue, this.blue, this.blue);
        break;
      case valueGrayscale:
        int maxComponent = this.findMaxComponent();
        this.setComponents(maxComponent, maxComponent, maxComponent);
        break;
      case intensityGrayscale:
        int intensity = (int) Math.round((this.red + this.green + this.blue) / 3.0);
        this.setComponents(intensity, intensity, intensity);
        break;
      case lumaGrayscale:
        int luma = (int) Math.round(this.red * 0.2126 + this.green * 0.7152 + this.blue * 0.0722);
        this.setComponents(luma, luma, luma);
        break;
      default:
        this.setComponents(this.red, this.green, this.blue);
        break;
    }
  }

  @Override
  public String toString() {
    return " " + this.red + " " + this.green + " " + this.blue;
  }

  public void setComponents(int red, int green, int blue){
    this.red = red;
    this.green = green;
    this.blue = blue;
  }
}
