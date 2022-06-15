package controller.commands;

import model.ImageProcessingModel;

/**
 * Command class to represent the sharpen-filter command, which sharpens the pixels in a specific
 * image and saves it as a new sharpened image.
 */
public class Sharpen extends AbstractCommand {

  /**
   * The default constructor for a sharpening filter command, which calls upon the model to take a
   * current image, apply a sharpening filter, and save it as a new image.
   * @param from the name of the origin image
   * @param to the new name for the sharpened image
   * @throws IllegalArgumentException if either of the provided parameters are null
   */
  public Sharpen(String from, String to) throws IllegalArgumentException {
    super(from, to);
  }

  /**
   * Calls upon the model to sharpen the image, giving it the origin image name and the
   * name of the new image.
   * @param model the model which will be used to actually apply the sharpening filter
   */
  public void execute(ImageProcessingModel model) {
    model.sharpen(this.from, this.to);
  }

}

