package commands;

import model.ImageProcessingModel;

/**
 * Interface to represent a command that can be executed on an image. Specific commands will
 * implement this interface and be called by the controller when manipulating model information.
 */
public interface ImageProcessingCommand {

  void execute(ImageProcessingModel m);

}
