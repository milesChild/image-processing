package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageProcessingModelImpl implements ImageProcessingModel {
  private final Map<String, PPMImage> imageLibrary;
  enum Orientations {Vertical, Horizontal}
  public enum GrayscaleTypes {
    RedGrayscale,
    GreenGrayscale,
    BlueGrayscale,
    ValueGrayscale,
    IntensityGrayscale,
    LumaGrayscale
  }

  /**
   * Constructor which allows the user to load in a pre-existing database (map) of images.
   *
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
    try {
      this.imageLibrary.get(name).saveImage(path);
    } catch (IOException ignored) {
      throw new IllegalStateException();
    }
  }

  @Override
  public void load(String path, String name) {
    PPMImage loadImage = new PPMImage(path);
    this.imageLibrary.put(name, loadImage);
  }

}
