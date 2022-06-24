package controller.commands;

import model.ImageProcessingModel;

public class Downscale extends AbstractCommand {
  private final int value; // the percentage the image will be downscaled by

  /**
   * The default constructor for a Downscale command, which downscales a specific image by the given
   * percentage and saves it as a new image.
   * @param value the percentage the image should be downscaled by
   * @param from the name of the origin image
   * @param to the name of the new image
   * @throws IllegalArgumentException if either of the provided parameters are null
   */
  public Downscale(int value, String from, String to) {
    super(from, to);
    this.value = value;
  }
  /**
   * Calls upon the model to execute the downscale function, giving it the origin image
   * name and the destination for the new image.
   *
   * @param model the model which will be used to actually conduct the specified operation
   */
  @Override
  public void execute(ImageProcessingModel model) {
    model.downscale(this.value, from, to);
  }
}
