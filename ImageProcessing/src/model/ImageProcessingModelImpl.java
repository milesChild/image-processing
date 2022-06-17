package model;

import java.util.HashMap;
import java.util.Map;

/**
 * The core implementation of the ImageProcessingModel interface. This model stores a hashmap of
 * images - the imageLibrary - which holds all the images that users can conduct operations on.
 * Images can be added to the imageLibrary via use of the {@code load(String path, String name)}
 * method. The model also stores various methods for operations that can be conducted on the
 * images. Each of these methods delegates actual manipulation to the ProcessableImageImpl class.
 */
public class ImageProcessingModelImpl implements ImageProcessingModel {
  private final Map<String, ProcessableImage> imageLibrary;

  /**
   * Constructor which allows the user to load in a pre-existing database (map) of images.
   *
   * @param images the images that the model will be allowed to operate with.
   */
  public ImageProcessingModelImpl(Map<String, ProcessableImage> images)
          throws IllegalArgumentException {
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

  public void addImage(String name, ProcessableImage image) {
    this.imageLibrary.put(name, image);
  }

  // image manipulation methods

  @Override
  public void flipHorizontally(String from, String to) {
    ProcessableImage fromImage =
            this.imageLibrary.getOrDefault(from, null);
    if (fromImage == null) {
      throw new IllegalArgumentException("Image does not exist in library."
              + " Try again.");
    }
    ProcessableImageImpl newProcessableImageImpl =
            new ProcessableImageImpl(fromImage);
    newProcessableImageImpl.flip(Orientations.Horizontal);
    this.imageLibrary.put(to, newProcessableImageImpl);
  }

  @Override
  public void flipVertically(String from, String to) {
    ProcessableImage fromImage =
            this.imageLibrary.getOrDefault(from, null);
    if (fromImage == null) {
      throw new IllegalArgumentException("Image does not exist in library."
              + " Try again.");
    }
    ProcessableImageImpl newProcessableImageImpl =
            new ProcessableImageImpl(fromImage);
    newProcessableImageImpl.flip(Orientations.Vertical);
    this.imageLibrary.put(to, newProcessableImageImpl);
  }

  @Override
  public void brighten(int value, String from, String to) {
    ProcessableImage fromImage =
            this.imageLibrary.getOrDefault(from, null);
    if (fromImage == null) {
      throw new IllegalArgumentException("Image does not exist in library."
              + " Try again.");
    }
    ProcessableImageImpl newProcessableImageImpl =
            new ProcessableImageImpl(fromImage);
    newProcessableImageImpl.brighten(value);
    this.imageLibrary.put(to, newProcessableImageImpl);
  }

  @Override
  public void grayscale(GrayscaleTypes choice, String from, String to) {
    ProcessableImage fromImage =
            this.imageLibrary.getOrDefault(from, null);
    if (fromImage == null) {
      throw new IllegalArgumentException("Image does not exist in library."
              + " Try again.");
    }
    ProcessableImageImpl newProcessableImageImpl =
            new ProcessableImageImpl(fromImage);
    newProcessableImageImpl.grayscale(choice);
    this.imageLibrary.put(to, newProcessableImageImpl);
  }

  @Override
  public void dim(int value, String from, String to) {
    this.brighten(-value, from, to);
  }

  @Override
  public void blur(String from, String to) throws IllegalArgumentException {
    ProcessableImage fromImage =
            this.imageLibrary.getOrDefault(from, null);
    if (fromImage == null) {
      throw new IllegalArgumentException("Image does not exist in library."
              + " Try again.");
    }
    ProcessableImageImpl newProcessableImageImpl =
            new ProcessableImageImpl(fromImage);
    newProcessableImageImpl.blur();
    this.imageLibrary.put(to, newProcessableImageImpl);
  }

  @Override
  public void sharpen(String from, String to) throws IllegalArgumentException {
    ProcessableImage fromImage =
            this.imageLibrary.getOrDefault(from, null);
    if (fromImage == null) {
      throw new IllegalArgumentException("Image does not exist in library."
              + " Try again.");
    }
    ProcessableImageImpl newProcessableImageImpl =
            new ProcessableImageImpl(fromImage);
    newProcessableImageImpl.sharpen();
    this.imageLibrary.put(to, newProcessableImageImpl);
  }

  @Override
  public void sepia(String from, String to) throws IllegalArgumentException {
    ProcessableImage fromImage =
            this.imageLibrary.getOrDefault(from, null);
    if (fromImage == null) {
      throw new IllegalArgumentException("Image does not exist in library."
              + " Try again.");
    }
    ProcessableImageImpl newProcessableImageImpl =
            new ProcessableImageImpl(fromImage);
    newProcessableImageImpl.sepia();
    this.imageLibrary.put(to, newProcessableImageImpl);
  }

  @Override
  public void grayscale(String from, String to) throws IllegalArgumentException {
    ProcessableImage fromImage =
            this.imageLibrary.getOrDefault(from, null);
    if (fromImage == null) {
      throw new IllegalArgumentException("Image does not exist in library."
              + " Try again.");
    }
    ProcessableImageImpl newProcessableImageImpl =
            new ProcessableImageImpl(fromImage);
    newProcessableImageImpl.grayscale();
    this.imageLibrary.put(to, newProcessableImageImpl);
  }

  @Override
  public ProcessableImage getImage(String name) throws IllegalArgumentException {
    ProcessableImage image =
            this.imageLibrary.getOrDefault(name, null);
    if (image == null) {
      throw new IllegalArgumentException("Image does not exist in library."
              + " Try again.");
    }
    return new ProcessableImageImpl(this.imageLibrary.get(name));
  }

}
