package model;

/**
 * Interface for an ImageProcessingModel which contains the operations that can be conducted on
 * images. Implementations will have a HashMap of known images that can be operated on, and the
 * operations which change the appearance of images will call upon a particular PPM image that is
 * stored in the model's knownImages map.
 */
public interface ImageProcessingModel {

  /**
   * Enumeration to represent the two different orientation types an image can have: vertical or
   * horizontal. These orientation types are referenced during flipping operations.
   */
  enum Orientations {
    Vertical,
    Horizontal
  }

  /**
   * Enumeration to represent the different grayscale conversion types, either red, green, blue,
   * value, intensity, or luma. These types are used in the model when making grayscale image
   * conversions.
   */
  enum GrayscaleTypes {
    RedGrayscale,
    GreenGrayscale,
    BlueGrayscale,
    ValueGrayscale,
    IntensityGrayscale,
    LumaGrayscale,
    TransformationGrayscale
  }

  void addImage(String name, ProcessableImage processableImage);

  /**
   * Method that calls on the ProcessableImageImpl to flip an image vertically, saving it as a new
   * image by the provided name. Grabs the image to flip by getting the image in imageLibrary under
   * the given name, specified by the user.
   *
   * @param from the name of the origin image
   * @param to   the new name for the flipped image
   * @throws IllegalArgumentException if there is no image of the given name in the imageLibrary
   */
  void flipVertically(String from, String to) throws IllegalArgumentException;

  /**
   * Method that calls on the ProcessableImageImpl to flip an image horizontally, saving it as a new
   * image by the provided name. Grabs the image to flip by getting the image in imageLibrary under
   * the given name, specified by the user.
   *
   * @param from the name of the origin image
   * @param to   the new name for the flipped image
   * @throws IllegalArgumentException if there is no image of the given name in the imageLibrary
   */
  void flipHorizontally(String from, String to) throws IllegalArgumentException;

  /**
   * Method that calls on the ProcessableImageImpl to dim an image by a certain value, saving it as
   * a new image by the provided name. Grabs the image to dim by getting the image in imageLibrary
   * under the given name, specified by the user.
   *
   * @param value the amount to dim the image by
   * @param from  the name of the image to dim
   * @param to    the name of the new, dimmed image that will be added to the imageLibrary
   * @throws IllegalArgumentException if there is no image of the given name in the imageLibrary
   */
  void dim(int value, String from, String to) throws IllegalArgumentException;

  /**
   * Method that calls on the ProcessableImageImpl to brighten an image by a certain value, saving
   * it as a new image by the provided name. Grabs the image to brighten by getting the image in
   * imageLibrary under the given name, specified by the user.
   *
   * @param value the amount to brighten the image by
   * @param from  the name of the image to brighten
   * @param to    the name of the new, brightened image that will be added to the imageLibrary
   * @throws IllegalArgumentException if there is no image of the given name in the imageLibrary
   */
  void brighten(int value, String from, String to) throws IllegalArgumentException;

  /**
   * Method that calls on the ProcessableImageImpl to convert an image to a grayscale type, saving
   * it as a new image by the provided name. Grabs the image to convert by getting the image in
   * imageLibrary under the given name, specified by the user.
   *
   * @param choice the type of grayscale to convert to
   * @param from   the path of the image to convert to grayscale
   * @param to     the name of the new, grayscaled image that will be added to the imageLibrary
   * @throws IllegalArgumentException if there is no image of the given name in the imageLibrary
   */
  void grayscale(ImageProcessingModelImpl.GrayscaleTypes choice, String from, String to)
          throws IllegalArgumentException;

  /**
   * Method that calls on the ProcessableImageImpl to apply a luma grayscale filter to an image,
   * saving it as a new image by the provided name. Grabs the image to grayscale by getting the
   * image in imageLibrary under the given name, specified by the user.
   *
   * @param from the name of the origin image
   * @param to   the new name for the greyscaled image
   * @throws IllegalArgumentException if there is no image of the given name in the imageLibrary
   */
  void grayscale(String from, String to) throws IllegalArgumentException;

  /**
   * Returns a copy of the desired PPM image in the imageLibrary.
   *
   * @param name the name of the image to copy
   * @return a copy of the ProcessableImageImpl
   * @throws IllegalArgumentException if there is no image of the given name in the imageLibrary
   */
  ProcessableImage getImage(String name) throws IllegalArgumentException;

  // Assignment 5 methods

  /**
   * Method that calls on the ProcessableImageImpl to apply a blurring filter to an image, saving
   * it as a new image by the provided name. Grabs the image to blur by getting the image in
   * imageLibrary under the given name, specified by the user.
   *
   * @param from the name of the origin image
   * @param to   the new name for the blurred image
   * @throws IllegalArgumentException if there is no image of the given name in the imageLibrary
   */
  void blur(String from, String to) throws IllegalArgumentException;

  /**
   * Method that calls on the ProcessableImageImpl to apply a sharpening filter to an image, saving
   * it as a new image by the provided name. Grabs the image to sharpen by getting the image in
   * imageLibrary under the given name, specified by the user.
   *
   * @param from the name of the origin image
   * @param to   the new name for the sharpened image
   * @throws IllegalArgumentException if there is no image of the given name in the imageLibrary
   */
  void sharpen(String from, String to) throws IllegalArgumentException;

  /**
   * Method that calls on the ProcessableImageImpl to apply a sepia filter to an image, saving it
   * as a new image by the provided name. Grabs the image to add the sepia filter to by getting the
   * image in imageLibrary under the given name, specified by the user.
   *
   * @param from the name of the origin image
   * @param to   the new name for the sepia-filtered image
   * @throws IllegalArgumentException if there is no image of the given name in the imageLibrary
   */
  void sepia(String from, String to) throws IllegalArgumentException;

  void downscale(int percent, String from, String to) throws IllegalArgumentException;

}
