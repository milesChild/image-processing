package view;

import java.io.IOException;

public interface ImageProcessingView {

  /**
   * Method that accepts a string and displays it on the command line to the client.
   * @param message the message to be displayed
   * @throws IOException if transmission to the provided data destination fails
   */
  void renderMessage(String message) throws IOException;
}
