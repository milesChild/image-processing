package model;

/**
 * Interface representing a pixel of an image. The pixel of an image should be able to mutate itself
 * through the given methods as well as return the values of its components.
 */
public interface Pixel {

  /**
   * Brightens each component of this pixel by the given value.
   * @param brightenValue the value each component will be brightened by
   */
  void brighten(int brightenValue);

  /**
   * Converts a pixel to a certain grayscale type, specified by the user. If the user passes an
   * invalid grayscale type, the pixels simply will not change. Our design already notifies the user
   * that they have passed in an invalid command if they specify an invalid grayscale type.
   * We ensure that the method will never take in an invalid enumeration.
   *
   * @param grayscale the grayscale type to convert the pixels to.
   * @throws IllegalArgumentException if the provided grayscale type is null
   */
  void grayscale(ImageProcessingModel.GrayscaleTypes grayscale)
          throws IllegalArgumentException;

  /**
   * Gets the red component.
   * @return the red component of this pixel.
   */
  int getRed();

  /**
   * Gets the green component.
   * @return the green component of this pixel.
   */
  int getGreen();

  /**
   * Gets the blue component.
   * @return the blue component of this pixel.
   */
  int getBlue();

  /**
   * Sets the components of this pixel according to the given red, green, and blue values.
   * Sets component to 0 by default if a given value is less than 0. Sets component to this pixel's
   * max value by default if the given value is greater than the max value.
   * @param redVal the value that will be set to the red component
   * @param greenVal the value that will be set to the green component
   * @param blueVal the value that will be set to the blue component
   */
  void setComponents(int redVal, int greenVal, int blueVal);



}
