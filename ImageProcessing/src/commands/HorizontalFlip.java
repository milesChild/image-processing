package commands;

import model.ImageProcessingModel;

public class HorizontalFlip extends AbstractCommand implements ImageProcessingCommand {

  public HorizontalFlip(String from, String to) throws IllegalArgumentException {
    super(from, to);
  }

  /**
   * Calls upon the model to horizontally flip the image, giving it the origin image name and the
   * destination for the new image.
   * @param model the model which will be used to actually conduct the horizontal-flipping
   *              operation.
   */
  public void execute(ImageProcessingModel model) {
    model.flipHorizontally(this.from, this.to);
  }

}
