package commands;

import model.ImageProcessingModel;

public abstract class AbstractCommand implements ImageProcessingCommand {

  String from; // image alias at origin
  String to; // destination for new image

  public AbstractCommand(String from, String to) throws IllegalArgumentException {
    if (from == null || to == null) {
      throw new IllegalArgumentException("Given null parameter");
    }
    this.from = from;
    this.to = to;
  }

  /**
   * Calls upon the model to execute the specified funtion (decided based on the actual
   * implementation of the abstract command) flip the image, giving it the origin image
   * name and the destination for the new image.
   * @param model the model which will be used to actually conduct the specified operation
   */
  abstract public void execute(ImageProcessingModel model);
}
