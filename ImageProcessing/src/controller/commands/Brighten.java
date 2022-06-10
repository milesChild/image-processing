package controller.commands;

import model.ImageProcessingModel;

/**
 * Command class to represent the brighten command, which brightens the pixels in a specific image
 * and saves it as a new image.
 */
public class Brighten extends AbstractCommand {

  private final int value; // the amount the pixels will be brightened by

  /**
   * The default constructor for a Brighten command, which brightens the pixels in a specific image
   * and saves it as a new image.
   * @param value the amount you brighten the pixels by in the image
   * @param from the name of the origin image
   * @param to the name of the new image
   * @throws IllegalArgumentException if either of the provided parameters are null
   */
  public Brighten(int value, String from, String to) {
    super(from, to);
    this.value = value;
  }

  /**
   * Calls upon the model to execute the brighten function, giving it the origin image name and the
   * name for the new image.
   * @param model the model which will be used to actually conduct the brighten operation
   */
  @Override
  public void execute(ImageProcessingModel model) {

    model.brighten(this.value, this.from, this.to);
  }

}
