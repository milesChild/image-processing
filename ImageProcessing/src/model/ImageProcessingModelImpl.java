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
   *
   * @param images the images that the model will be allowed to operate with.
   */
  public ImageProcessingModelImpl(Map<String, PPMImage> images) throws IllegalArgumentException {
    if (images == null) {
      throw new IllegalArgumentException("Given null parameter.");
    }

    this.knownImages = images;
  }

  /**
   * Default constructor, takes in no currently loaded (known) images and instantiates the
   * knownImages as an empty HashMap. As the user loads in images, knownImages will be populated.
   */
  public ImageProcessingModelImpl() {
    this.knownImages = new HashMap<String, PPMImage>();
  }

//  @Override
//  public void flipHorizontally(String from, String to) {
//    PPMImage newImage = new PPMImage(from);
//    newImage.flipHorizontally();
//    this.knownImages.put(to,newImage);
//  }

  @Override
  public void flipHorizontally(String from, String to) {
    PPMImage fromImage = this.knownImages.get(from);
    PPMImage newImage = new PPMImage(fromImage);
    newImage.flipHorizontally();
    this.knownImages.put(to, newImage);
  }

//  @Override
//  public void flipVertically(String from, String to) {
//    PPMImage newImage = new PPMImage(from);
//    newImage.flipVertically();
//    this.knownImages.put(to,newImage);
//  }

  @Override
  public void flipVertically(String from, String to) {
    PPMImage fromImage = this.knownImages.get(from);
    PPMImage newImage = new PPMImage(fromImage);
    newImage.flipVertically();
    this.knownImages.put(to, newImage);
  }

//  @Override
//  public void brighten(int value, String from, String to) {
//    PPMImage newImage = new PPMImage(from);
//    newImage.editColor(value, value, value);
//    this.knownImages.put(to, newImage);
//  }

  @Override
  public void brighten(int value, String from, String to) {
    PPMImage fromImage = this.knownImages.get(from);
    PPMImage newImage = new PPMImage(fromImage);
    newImage.editColor(value, value, value);
    this.knownImages.put(to, newImage);
  }

  @Override
  public void dim(int value, String from, String to) {
    this.brighten(-value, from, to);
  }

  @Override
  public void save(String path, String name) {
    try {
      this.knownImages.get(name).saveImage(path, name);
    } catch (IOException ignored) {
      throw new IllegalStateException();
    }
  }

  @Override
  public void load(String path, String name) {
    PPMImage loadImage = new PPMImage(path);
    this.knownImages.put(name, loadImage);
  }

}
