package model;

public interface ImageProcessingModel {
  void flipVertically(String from, String to);

  void flipHorizontally(String from, String to);

  void dim(int value, String from, String to);

  void brighten(int value, String from, String to);
}
