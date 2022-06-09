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

  void flipVertically(String from, String to);

  void flipHorizontally(String from, String to);

  void dim(int value, String from, String to);

  void brighten(int value, String from, String to);

  void save(String path, String name);

  void load(String path, String name);

  void grayscale(ImageProcessingModelImpl.GrayscaleTypes choice, String path, String name);
}
