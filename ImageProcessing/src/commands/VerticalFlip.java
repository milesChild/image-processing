package commands;

import model.ImageProcessingModel;

public class VerticalFlip extends AbstractCommand {

  public VerticalFlip(String from, String to) {
    super(from, to);
  }

  /**
   * Calls upon the model to execute the vertical flip function, giving it the origin image
   * name and the destination for the new image.
   * @param model the model which will be used to actually conduct the vertical-flipping operation
   */
  @Override
  public void execute(ImageProcessingModel model) {
    model.flipVertically(this.from, this.to);
  }

}
