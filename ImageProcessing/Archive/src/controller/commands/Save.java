package controller.commands;

import model.ImageProcessingModel;

/**
 * Command class to represent the save command, which saves a specific image from the imageLibrary
 * and saves it to the specified image-path on the user's device.
 */
public class Save extends AbstractCommand {

  /**
   * Default constructor for a Save command, which saves the specified image to the specified path.
   * @param path the location at which the image will be saved to, in image-path.ppm format (ex:
   *             images/koala.ppm)
   * @param name the name of the image to save
   */
  public Save(String path, String name) {
    super(path, name);
  }


  /**
   * Calls upon the model to execute the save method, giving it the image-path for which to save
   * the specified image name.
   * @param model the model which will be used to actually conduct the save method
   */
  @Override
  public void execute(ImageProcessingModel model) {
    model.save(this.from, this.to);
  }

}
