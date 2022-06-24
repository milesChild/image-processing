package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import model.ImageProcessingModel;
import model.ProcessableImage;
import view.ImageProcessingViewGUI;

public class ImageProcessingControllerGUI extends AbstractImageProcessingController implements ActionListener {
  private final ImageProcessingViewGUI view;
  private String currentKey;

  /**
   * Default constructor for the GUI implementation of an image processing controller. Instantiates
   * a new controller using the provided model and GUI view.
   * @param model the model that the controller will transmit information to
   * @param view the view to which information will be transmitted according to the controller's
   *             discretion
   */
  public ImageProcessingControllerGUI(ImageProcessingModel model, ImageProcessingViewGUI view) {
    super(model);
    this.view = view;
    this.view.setListener(this);
    this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.view.setVisible(true);
  }

  /**
   * Invoked when an action occurs.
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()){
      case "load": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showOpenDialog(view);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          String imageFilePath = f.getAbsolutePath();
          this.loadImage(imageFilePath, "initialImage");
          this.currentKey = "initialImage";
        }
        break;
      }
      case "save": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showSaveDialog(view);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          String saveFilePath = f.getAbsolutePath();
          this.saveImage(saveFilePath, this.currentKey);
        }
        break;
      }
      case "vertical": {
        this.model.flipVertically(this.currentKey, "verticalFlip");
        this.currentKey = "verticalFlip";
        break;
      }
      case "horizontal": {
        this.model.flipHorizontally(this.currentKey, "horizontalFlip");
        this.currentKey = "horizontalFlip";
        break;
      }
      case "brighten": {
        int userInput = this.view.getInputValue("brighten");
        this.model.brighten(userInput, this.currentKey, "brighten");
        this.currentKey = "brighten";
        break;
      }
      case "dim": {
        int userInput = this.view.getInputValue("dim");
        this.model.dim(userInput, this.currentKey, "dim");
        this.currentKey = "dim";
        break;
      }
      case "blur": {
        this.model.blur(this.currentKey, "blur");
        this.currentKey = "blur";
        break;
      }
      case "sharpen": {
        this.model.sharpen(this.currentKey, "sharpen");
        this.currentKey = "sharpen";
        break;
      }
      case "sepia": {
        this.model.sepia(this.currentKey, "sepia");
        this.currentKey = "sepia";
        break;
      }
      case "grayscale red": {
        this.model.grayscale(ImageProcessingModel.GrayscaleTypes.RedGrayscale,
                this.currentKey, "red");
        this.currentKey = "red";
        break;
      }
      case "grayscale green": {
        this.model.grayscale(ImageProcessingModel.GrayscaleTypes.GreenGrayscale,
                this.currentKey, "green");
        this.currentKey = "green";
        break;
      }
      case "grayscale blue": {
        this.model.grayscale(ImageProcessingModel.GrayscaleTypes.BlueGrayscale,
                this.currentKey, "blue");
        this.currentKey = "blue";
        break;
      }
      case "grayscale intensity": {
        this.model.grayscale(ImageProcessingModel.GrayscaleTypes.IntensityGrayscale,
                this.currentKey, "intensity");
        this.currentKey = "intensity";
        break;
      }
      case "grayscale value": {
        this.model.grayscale(ImageProcessingModel.GrayscaleTypes.ValueGrayscale,
                this.currentKey, "value");
        this.currentKey = "value";
        break;
      }
      case "grayscale luma": {
        this.model.grayscale(ImageProcessingModel.GrayscaleTypes.LumaGrayscale,
                this.currentKey, "luma");
        this.currentKey = "luma";
        break;
      }
      case "grayscale filter": {
        this.model.grayscale(this.currentKey, "filter");
        this.currentKey = "filter";
        break;
      }
      case "downscale": {
        int userInput = this.view.getInputValue("downscale");
        this.model.downscale(userInput, this.currentKey, "downscale");
        this.currentKey = "downscale";
        break;
      }

    }
    ProcessableImage currentImage = this.model.getImage(this.currentKey);
    this.view.displayLabels(currentImage.createCommonImageContents(), currentImage.drawHistogram());
  }

  /**
   * Displays (in the console) to the user that the program is running.
   */
  @Override
  public void runProgram() {
    try {
      this.view.renderMessage("Program is running in new window.");
    }
    catch (IOException e){
      throw new IllegalArgumentException("Invalid output in console.");
    }

  }
}
