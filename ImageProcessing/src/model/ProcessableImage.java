package model;

import java.awt.image.BufferedImage;

/**
 * The interface to represent a processable image, which is the common image type that is used by
 * the program when actual operations are conducted on an image. Regardless of the file type of
 * image loaded into the program, it will automatically be converted (upon the load command) into a
 * {@code ProcessableImageImpl}. This interface supports the ability to load and save various common
 * types of image files and holds the common methods that an Image object will need for image
 * manipulation.
 */
public interface ProcessableImage {

  /**
   * Brightens all the pixels in this PPM image's pixel grid given a specified value.
   *
   * @param brightenValue the value to brighten each pixel by
   */
  void brighten(int brightenValue);

  /**
   * Converts all the pixels in this PPM image's pixel grid given a specified grayscale choice.
   *
   * @param grayscaleChoice the type of grayscale the image will be converted to
   */
  void grayscale(ImageProcessingModel.GrayscaleTypes grayscaleChoice);

  /**
   * Flips the pixel grid in this PPM image horizontally or vertically,
   * given the specified flip choice.
   *
   * @param orientation the type of flip that will be performed on this PPM image
   */
  void flip(ImageProcessingModelImpl.Orientations orientation);

  /**
   * Applies a filter to blur the pixels in a specific image and saves it as a new blurred image.
   * The filter that will be applied is referred to as a "kernel", which is a 2D array of double
   * values that will be used to compute the new int value for each color channel for each of the
   * original pixels in the image.
   */
  void blur();

  /**
   * Applies a filter to sharpen the pixels in a specific image and saves it as a new sharpened
   * image. The filter that will be applied is referred to as a "kernel", which is a 2D array of
   * double values that will be used to compute the new int value for each color channel for each
   * of the original pixels in the image.
   */
  void sharpen();

  /**
   * Applies a filter to grayscale the pixels in a specific image and saves it as a new grayscaled
   * image. The filter that will be applied is referred to as a "matrix", which is a 2D array of
   * double values that will be used to compute the new int value for each color channel for each
   * of the original pixels in the image.
   */
  void grayscale();

  /**
   * Applies a sepia filter to the pixels in a specific image and saves it as a new, filtered image.
   * The filter that will be applied is referred to as a "matrix", which is a 2D array of
   * double values that will be used to compute the new int value for each color channel for each
   * of the original pixels in the image.
   */
  void sepia();

  /**
   * Used for saving processable images as .ppm files. Takes in the parameters of the given image
   * class and uses it to write a new .ppm file.
   * @return a new .ppm file that uses the 2D array of pixels, height, width, and max value of the
   *         current image class
   */
  String createPPMContents();

  /**
   * Used for saving processable images as many popular image file types. Always makes
   * BufferedImages of {@code TYPE_INT_RGB}.
   * @return a new {@code BufferedImage} that is constructed with the pixels, height, and width of
   *         the current image class
   */
  BufferedImage createCommonImageContents();

  /**
   * Gets the height of this ProcessableImageImpl.
   *
   * @return the height of this image
   */
  int getHeight();

  /**
   * Gets the width of this ProcessableImageImpl.
   *
   * @return the width of this image
   */
  int getWidth();

  // TODO: Remove?
  /**
   * Gets a deep copy of the pixel grid, a 2D array of pixels, in the given image class.
   * @return a deep copy of the 2D array of pixels, pixelGrid, as a new, identical 2D array of
   *         pixels
   */
  Pixel[][] getPixelGrid();

  }
