package controller.commands;

import model.ImageProcessingModel;

/**
 * Command class to represent the greyscale command, which applies the greyscale filter to an image
 * and saves it as a new, greyscaled image.
 */
public class Greyscale extends AbstractCommand {

  /**
   * The default constructor for a greyscale filter command, which calls upon the model to take a
   * current image, apply a greyscale filter, and save it as a new image.
   * @param from the name of the origin image
   * @param to the new name for the greyscale-filtered image
   * @throws IllegalArgumentException if either of the provided parameters are null
   */
  public Greyscale(String from, String to)
          throws IllegalArgumentException {
    super(from, to);
  }

  /**
   * Calls upon the model to apply the greyscale filter to the image, giving it the origin image
   * name and the name of the new image.
   * @param model the model which will be used to actually apply the greyscale filter
   */
  @Override
  public void execute(ImageProcessingModel model) {
    model.greyscale(from, to);
  }
}
