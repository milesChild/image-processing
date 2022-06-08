package model;

import java.awt.*;
import java.util.Map;

public class ImageProcessingModelImpl implements ImageProcessingModel {

  Map<String, PPMImage> knownImages;

  /**
   * Constructor which allows the user to load in a pre-existing database (map) of images.
   * @param images the images that the model will be allows to operate with.
   */
  public ImageProcessingModelImpl(Map<String, PPMImage> images) throws IllegalArgumentException {
    if (images == null) {
      throw new IllegalArgumentException("Given null parameter.");
    }

    this.knownImages = images;
  }

  /**
   * Default constructor ...
   */
  public ImageProcessingModelImpl() {

  }

  @Override
  public void flipHorizontally(String from, String to) {

  }

  @Override
  public void flipVertically(String from, String to) {

  }

  @Override
  public void brighten(String from, String to) {

  }

  @Override
  public void dim(String from, String to) {

  }

}
