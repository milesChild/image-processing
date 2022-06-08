package view;

import java.io.IOException;

public interface ImageProcessingView {

  // TODO: Make IOException more elaborate description
  /**
   * Method that accepts a string and displays it on the command line to the client.
   * @param message the message to be displayed
   * @throws IOException when an IOException occurs
   */
  void renderMessage(String message) throws IOException;
}
