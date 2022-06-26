package controller.commands;

import model.ImageProcessingModel;

/**
 * Command class to represnt the partial image manipulation command. Edits an image partially given
 * a mask for the image and the command that will edit over the mask.
 */
public class PartialManipulation extends AbstractCommand {
  private final String partialCommand;
  private final int commandVal;

  /**
   * Default constructor for partial image manipulation.
   * @param from the from image
   * @param mask the mask image
   * @param to the to image.
   * @param command the command that will be applied to the from image.
   * @param commandVal the user input for brighten/dim commands
   * @throws IllegalArgumentException if any of the from, mask, or to images are null
   */
  public PartialManipulation(String from, String mask, String to, String command, int commandVal)
          throws IllegalArgumentException {
    super(from, mask, to);
    this.partialCommand = command;
    this.commandVal = commandVal;
  }

  /**
   * Calls upon the model to execute the specified function (decided based on the actual
   * implementation of the abstract command), giving it the origin image
   * name and the destination for the new image.
   *
   * @param model the model which will be used to actually conduct the specified operation
   */
  @Override
  public void execute(ImageProcessingModel model) {
    model.partialManipulation(from, mask, to, partialCommand, commandVal);
  }
}
