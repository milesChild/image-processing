package controller.commands;

import model.ImageProcessingModel;

/**
 * Interface to represent a command that can be executed on an image. Specific commands will
 * implement this interface and be called by the controller when manipulating model information.
 */
public interface ImageProcessingCommand {

  /**
   * Calls upon the model to execute the given command.
   * @param model the model which will be used to actually conduct the operation.
   */
  void execute(ImageProcessingModel model);

}
