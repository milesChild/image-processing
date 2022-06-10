package controller.commands;

import model.ImageProcessingModel;

/**
 * Abstract class for a command that includes fields and constructor that are common for most
 * commands (this reduces code repetition). Implementations represent a specific operation that can
 * be executed on an image. Specific commands will extend this class and be called by the
 * controller when manipulating model information.
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
