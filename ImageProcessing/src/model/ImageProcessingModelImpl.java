package model;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import commands.HorizontalFlip;
import commands.ImageProcessingCommand;

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
    this.knownImages = new HashMap<String, PPMImage>();
  }

  @Override
  public void flipHorizontally(String from, String to) {
    PPMImage newImage = new PPMImage(from);
    newImage.flipHorizontally();
    this.knownImages.put(to,newImage);
  }

  @Override
  public void flipVertically(String from, String to) {
    PPMImage newImage = new PPMImage(from);
    newImage.flipVertically();
    this.knownImages.put(to,newImage);
  }

  @Override
  public void brighten(int value, String from, String to) {
    PPMImage newImage = new PPMImage(from);
    newImage.editColor(value,value,value);
    this.knownImages.put(to,newImage);
  }

  @Override
  public void save(String path, String name) {
    try {
      this.knownImages.get(name).saveImage(path, name);
    }
    catch (IOException ignored) {

    }
  }

  @Override
  public void load(String path, String name) {
    PPMImage loadImage = new PPMImage(path);
    this.knownImages.put(name,loadImage);
  }

  @Override
  public void dim(int value, String from, String to) {
    this.brighten(-value, from, to);
  }

}
