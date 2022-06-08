package model;

public interface ImageProcessingModel {
  void flipVertically(String from, String to);

  void flipHorizontally(String from, String to);

  void dim(String from, String to);

  void brighten(String from, String to);
}
