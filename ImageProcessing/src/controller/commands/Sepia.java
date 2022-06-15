package controller.commands;

import model.ImageProcessingModel;

/**
 * Command class to represent the sepia command, which modifies the appearance of an image to give
 * a sepia effect (reddish-brown color filter) and saves it as a new image.
 */
public class Sepia extends AbstractCommand {

  /**
   * The default constructor for a sepia command, which calls upon the model to take a current
   * image and convert it to a new image with a sepia filter.
   * @param from the name of the origin image
   * @param to the new name for the filtered, sepia image
   * @throws IllegalArgumentException if either of the provided parameters are null
   */
  public Sepia(String from, String to) throws IllegalArgumentException {
    super(from, to);
  }

  /**
   * Calls upon the model to apply the sepia filter to the image, giving it the origin image name
   * and the name of the new image.
   * @param model the model which will be used to actually apply the sepia filter
   */
  public void execute(ImageProcessingModel model) {
    model.sepia(this.from, this.to);
  }

}

