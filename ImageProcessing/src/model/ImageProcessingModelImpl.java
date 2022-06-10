package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The core implementation of the ImageProcessingModel interface. This model stores a hashmap of
 * images - the imageLibrary - which holds all the images that users can conduct operations on.
 * Images can be added to the imageLibrary via use of the {@code load(String path, String name)}
 * method. The model also stores various methods for operations that can be conducted on the
 * images. Each of these methods delegates actual manipulation to the PPMImage class.
 */
public class ImageProcessingModelImpl implements ImageProcessingModel {
  private final Map<String, PPMImage> imageLibrary;

  /**
   * Constructor which allows the user to load in a pre-existing database (map) of images.
   * @param images the images that the model will be allowed to operate with.
   */
  public ImageProcessingModelImpl(Map<String, PPMImage> images) throws IllegalArgumentException {
    if (images == null) {
      throw new IllegalArgumentException("Given null parameter.");
    }

    this.imageLibrary = images;
  }

  /**
   * Default constructor, takes in no currently loaded (known) images and instantiates the
   * imageLibrary as an empty HashMap. As the user loads in images, imageLibrary will be populated.
   */
  public ImageProcessingModelImpl() {
    this.imageLibrary = new HashMap<>();
  }

  /**
   * Method that calls on the PPMImage to flip an image horizontally, saving it as a new image by
   * the provided name. Grabs the image to flip by getting the image in imageLibrary under the given
   * name, specified by the user.
   * @param from the name of the origin image
   * @param to the new name for the flipped image
   */
  @Override
  public void flipHorizontally(String from, String to) {
    PPMImage fromImage = this.imageLibrary.get(from);
    PPMImage newImage = new PPMImage(fromImage);
    newImage.flip(Orientations.Horizontal);
    this.imageLibrary.put(to, newImage);
  }

  /**
   * Method that calls on the PPMImage to flip an image vertically, saving it as a new image by
   * the provided name. Grabs the image to flip by getting the image in imageLibrary under the given
   * name, specified by the user.
   * @param from the name of the origin image
   * @param to the new name for the flipped image
   */
  @Override
  public void flipVertically(String from, String to) {
    PPMImage fromImage = this.imageLibrary.get(from);
    PPMImage newImage = new PPMImage(fromImage);
    newImage.flip(Orientations.Vertical);
    this.imageLibrary.put(to, newImage);
  }

  /**
   * Method that calls on the PPMImage to brighten an image by a certain value, saving it as a new
   * image by the provided name. Grabs the image to brighten by getting the image in imageLibrary
   * under the given name, specified by the user.
   * @param value the amount to brighten the image by
   * @param from the name of the image to brighten
   * @param to the name of the new, brightened image that will be added to the imageLibrary
   */
  @Override
  public void brighten(int value, String from, String to) {
    PPMImage fromImage = this.imageLibrary.get(from);
    PPMImage newImage = new PPMImage(fromImage);
    newImage.brighten(value);
    this.imageLibrary.put(to, newImage);
  }

  /**
   * Method that calls on the PPMImage to convert an image to a grayscale type, saving it as a new
   * image by the provided name. Grabs the image to convert by getting the image in imageLibrary
   * under the given name, specified by the user.
   * @param choice the type of grayscale to convert to
   * @param from the name of the image to convert to grayscale
   * @param to the name of the new, grayscaled image that will be added to the imageLibrary
   */
  public void grayscale(GrayscaleTypes choice, String from, String to){
    PPMImage fromImage = this.imageLibrary.get(from);
    PPMImage newImage = new PPMImage(fromImage);
    newImage.grayscale(choice);
    this.imageLibrary.put(to, newImage);
  }

  /**
   * Method that calls on the PPMImage to dim an image by a certain value, saving it as a new
   * image by the provided name. Grabs the image to dim by getting the image in imageLibrary
   * under the given name, specified by the user.
   * @param value the amount to dim the image by
   * @param from the name of the image to dim
   * @param to the name of the new, dimmed image that will be added to the imageLibrary
   */
  @Override
  public void dim(int value, String from, String to) {
    this.brighten(-value, from, to);
  }

  /**
   * Method that pulls a PPM image from the client's device and adds it to the imageLibrary.
   * @param path the path on the client's device to pull the image from
   * @param name the name to save the image as
   */
  @Override
  public void save(String path, String name) {
    try {
      this.imageLibrary.get(name).saveImage(path);
    } catch (IOException ignored) {
      throw new IllegalStateException();
    }
  }

  /**
   * Method that saves a PPM image from the imageLibrary (under the specified name) to client's
   * device under the specified path.
   * @param path the path on the client's device to pull the image from
   * @param name the name to save the image as
   */
  @Override
  public void load(String path, String name) {
    PPMImage loadImage = new PPMImage(path);
    this.imageLibrary.put(name, loadImage);
  }

}
