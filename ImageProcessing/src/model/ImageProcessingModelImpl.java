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

  @Override
  public void flipHorizontally(String from, String to) {
    PPMImage fromImage = this.imageLibrary.get(from);
    PPMImage newImage = new PPMImage(fromImage);
    newImage.flip(Orientations.Horizontal);
    this.imageLibrary.put(to, newImage);
  }

  @Override
  public void flipVertically(String from, String to) {
    PPMImage fromImage = this.imageLibrary.get(from);
    PPMImage newImage = new PPMImage(fromImage);
    newImage.flip(Orientations.Vertical);
    this.imageLibrary.put(to, newImage);
  }

  @Override
  public void brighten(int value, String from, String to) {
    PPMImage fromImage = this.imageLibrary.get(from);
    PPMImage newImage = new PPMImage(fromImage);
    newImage.brighten(value);
    this.imageLibrary.put(to, newImage);
  }

  public void grayscale(GrayscaleTypes choice, String from, String to){
    PPMImage fromImage = this.imageLibrary.get(from);
    PPMImage newImage = new PPMImage(fromImage);
    newImage.grayscale(choice);
    this.imageLibrary.put(to, newImage);
  }

  @Override
  public void dim(int value, String from, String to) {
    this.brighten(-value, from, to);
  }

  @Override
  public void save(String path, String name) {
    this.imageLibrary.get(name).saveImage(path);
  }

  @Override
  public void load(String path, String name) {
    PPMImage loadImage = new PPMImage(path);
    this.imageLibrary.put(name, loadImage);
  }

  @Override
  public PPMImage getImage(String name) throws IllegalArgumentException {
    PPMImage libImage = this.imageLibrary.getOrDefault(name, null);
    if (libImage == null) {
      throw new IllegalArgumentException("Image does not exist in library. Try again.");
    }

      PPMImage copiedImage = new PPMImage(this.imageLibrary.get(name));
      return copiedImage;
  }

}
