package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import commands.Brighten;
import commands.Dim;
import commands.HorizontalFlip;
import commands.ImageProcessingCommand;
import commands.Load;
import commands.VerticalFlip;
import model.ImageProcessingModel;
import view.ImageProcessingView;


public class ImageProcessingControllerImpl implements ImageProcessingController {

  private ImageProcessingModel model;
  private Readable in;
  private ImageProcessingView view;
  Map<String, Function<Scanner, ImageProcessingCommand>> knownCommands;

  /**
   * Default constructor for an image processing controller implementation which instantiates a
   * new controller using the provided model, readable, and view.
   * @param model the model that the controller will transmit information to
   * @param in the readable that the controller will use to accept user input
   * @param view the view to which information will be transmitted according to the controller's
   *             discretion
   * @throws IllegalArgumentException if any of the provided parameters are null
   */
  public ImageProcessingControllerImpl(ImageProcessingModel model, Readable in,
                                       ImageProcessingView view) throws IllegalArgumentException {
    if (model == null || in == null || view == null) {
      throw new IllegalArgumentException("Given null parameter.");
    }
    this.model = model;
    this.in = in;
    this.view = view;
    initCommands();
  }

  // TODO: new constructor

  // Initializes the known commands that can be consulted to operate on the model.
  private void initCommands() {
    knownCommands = new HashMap<>();
    knownCommands.put("horizontal-flip", s -> new HorizontalFlip(s.nextLine(), s.nextLine()));
    knownCommands.put("vertical-flip", s -> new VerticalFlip(s.nextLine(), s.nextLine()));
    knownCommands.put("brighten", s ->
            new Brighten(s.nextInt(), s.nextLine(), s.nextLine()));
    knownCommands.put("dim", s -> new Dim(s.nextInt(), s.nextLine(), s.nextLine()));
    knownCommands.put("load", s -> new Load(s.next(), s.next()));
  }

  public void runProgram() {
    Scanner s = new Scanner(in);
    boolean quit = false;

    while(s.hasNext()) {
      ImageProcessingCommand c;
      String in = s.nextLine();

      // quit when prompted.
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        quit = true;
        break;
      }
      Function<Scanner, ImageProcessingCommand> cmd =
              knownCommands.getOrDefault(in, null);
      if (cmd == null) {
        try {
          this.view.renderMessage("Unknown or invalid command. Try Again.");
        } catch (IOException e) {
          throw new IllegalStateException();
        }
      } else {
        c = cmd.apply(s);
        c.execute(this.model);
      }
    }

    if (quit) this.quitProgram();
  }

  private void quitProgram() {
    try {
      this.view.renderMessage("Program Ended.");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }
}