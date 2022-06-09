package controller.commands;

import model.ImageProcessingModel;

public class HorizontalFlip extends AbstractCommand implements ImageProcessingCommand {

  /**
   * The default constructor for a horizontal flipping command, which calls upon the model to take
   * a current image and flip it horizontally as a new image.
   * @param from the name of the origin image
   * @param to the new name for the flipped image
   * @throws IllegalArgumentException if either of the provided parameters are null
   */
  public HorizontalFlip(String from, String to) throws IllegalArgumentException {
    super(from, to);
  }

  /**
   * Calls upon the model to horizontally flip the image, giving it the origin image name and the
   * name of the new image.
   * @param model the model which will be used to actually conduct the horizontal-flipping
   *              operation.
   */
  public void execute(ImageProcessingModel model) {
    model.flipHorizontally(this.from, this.to);
  }

}
