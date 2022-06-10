package controller.commands;

import model.ImageProcessingModel;

/**
 * Command class to represent the load command, which loads an image from the specified image-path
 * and saves it as a new image in the model's library.
 */
public class Load extends AbstractCommand {

  /**
   * Default constructor for a Load command, which loads the specified image from the specified
   * image-path.
   * @param path the location at which the image will be loaded from, in image-path.ppm format (ex:
   *             images/koala.ppm)
   * @param name the name of the image to load
   */
  public Load(String path, String name) {
    super(path, name);
  }


  /**
   * Calls upon the model to execute the Load method, giving it the image-path from which to pull
   * the specified image name.
   * @param model the model which will be used to actually conduct the Load method
   */
  @Override
  public void execute(ImageProcessingModel model) {
    model.load(this.from, this.to);
  }

}
