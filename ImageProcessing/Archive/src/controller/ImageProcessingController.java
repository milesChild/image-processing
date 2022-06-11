package controller;

/**
 * Interface that will be implemented by all the controller implementations (currently,
 * only one supported) and will hold all the methods that must be common amongst all
 * implementations. Includes a runProgram() method that enables the client to pass in commands
 * that will be transmitted to the model for image-manipulation, loading, and saving.
 */
public interface ImageProcessingController {

  /**
   * Allows the user to run the program and pass in various commands for loading, saving, and
   * manipulating images. User can quit by typing "q" at any time.
   */
  void runProgram();

}
