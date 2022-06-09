package controller.commands;

import model.ImageProcessingModel;

/**
 *
 */
public abstract class AbstractCommand implements ImageProcessingCommand {

  protected final String from; // image alias at origin
  protected final String to; // name for new image

  /**
   * The abstract implementation for a command to be executed on an image.
   * @param from the name of the origin image
   * @param to the name of the new image
   * @throws IllegalArgumentException if either of the provided parameters are null
   */
  public AbstractCommand(String from, String to) throws IllegalArgumentException {
    if (from == null || to == null) {
      throw new IllegalArgumentException("Given null parameter");
    }
    this.from = from;
    this.to = to;
  }

  /**
   * Calls upon the model to execute the specified function (decided based on the actual
   * implementation of the abstract command), giving it the origin image
   * name and the destination for the new image.
   * @param model the model which will be used to actually conduct the specified operation
   */
  abstract public void execute(ImageProcessingModel model);
}
