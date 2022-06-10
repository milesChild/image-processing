package controller.commands;

import model.ImageProcessingModel;

/**
 * Command class to represent the vertical-flip command, which vertically-flips a specific
 * image and saves it as a new image.
 */
public class VerticalFlip extends AbstractCommand {

  /**
   * The default constructor for a vertical flipping command, which calls upon the model to take
   * a current image and flip it vertically as a new image.
   * @param from the name of the origin image
   * @param to the new name for the flipped image
   * @throws IllegalArgumentException if either of the provided parameters are null
   */
  public VerticalFlip(String from, String to) {
    super(from, to);
  }

  /**
   * Calls upon the model to execute the vertical flip function, giving it the origin image
   * name and the name for the new image.
   * @param model the model which will be used to actually conduct the vertical-flipping operation
   */
  @Override
  public void execute(ImageProcessingModel model) {
    model.flipVertically(this.from, this.to);
  }

}
