package model;

/**
 * Interface for an ImageProcessingModel which contains the operations that can be conducted on
 * images. Implementations will have a HashMap of known images that can be operated on, and the
 * operations which change the appearance of images will call upon a particular PPM image that is
 * stored in the model's knownImages map.
 */
public interface ImageProcessingModel {
  enum Orientations { Vertical, Horizontal }
  enum GrayscaleTypes {
    RedGrayscale,
    GreenGrayscale,
    BlueGrayscale,
    ValueGrayscale,
    IntensityGrayscale,
    LumaGrayscale
  }

  /**
   * Method that calls on the PPMImage to flip an image vertically, saving it as a new image by
   * the provided name. Grabs the image to flip by getting the image in imageLibrary under the given
   * name, specified by the user.
   * @param from the name of the origin image
   * @param to the new name for the flipped image
   */
  void flipVertically(String from, String to);

  /**
   * Method that calls on the PPMImage to flip an image horizontally, saving it as a new image by
   * the provided name. Grabs the image to flip by getting the image in imageLibrary under the given
   * name, specified by the user.
   * @param from the name of the origin image
   * @param to the new name for the flipped image
   */
  void flipHorizontally(String from, String to);

  /**
   * Method that calls on the PPMImage to dim an image by a certain value, saving it as a new
   * image by the provided name. Grabs the image to dim by getting the image in imageLibrary
   * under the given name, specified by the user.
   * @param value the amount to dim the image by
   * @param from the name of the image to dim
   * @param to the name of the new, dimmed image that will be added to the imageLibrary
   */
  void dim(int value, String from, String to);

  /**
   * Method that calls on the PPMImage to brighten an image by a certain value, saving it as a new
   * image by the provided name. Grabs the image to brighten by getting the image in imageLibrary
   * under the given name, specified by the user.
   * @param value the amount to brighten the image by
   * @param from the name of the image to brighten
   * @param to the name of the new, brightened image that will be added to the imageLibrary
   */
  void brighten(int value, String from, String to);

  /**
   * Method that pulls a PPM image from the client's device and adds it to the imageLibrary.
   * @param path the path on the client's device to pull the image from
   * @param name the name to save the image as
   */
  void save(String path, String name);

  /**
   * Method that saves a PPM image from the imageLibrary (under the specified name) to client's
   * device under the specified path.
   * @param path the path on the client's device to pull the image from
   * @param name the name to save the image as
   */
  void load(String path, String name);

  /**
   * Method that calls on the PPMImage to convert an image to a grayscale type, saving it as a new
   * image by the provided name. Grabs the image to convert by getting the image in imageLibrary
   * under the given name, specified by the user.
   * @param choice the type of grayscale to convert to
   * @param from the path of the image to convert to grayscale
   * @param to the name of the new, grayscaled image that will be added to the imageLibrary
   */
  void grayscale(ImageProcessingModelImpl.GrayscaleTypes choice, String from, String to);

  /**
   * Returns a copy of the desired PPM image in the imageLibrary.
   * @param name the name of the image to copy
   * @return a copy of the PPMImage
   * @throws IllegalArgumentException if the name of the image does not correspond to an actual
   *                                  image in the library
   */
  PPMImage getImage(String name) throws IllegalArgumentException;
}
