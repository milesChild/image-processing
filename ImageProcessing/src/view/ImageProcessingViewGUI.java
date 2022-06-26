package view;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 * A GUI implementation for the image processing application. Allows the user to load and save an
 * image system's file selector and press buttons to manipulate the image. Shows the user the image
 * manipulations and shows the image's histogram.
 */
public class ImageProcessingViewGUI extends JFrame implements ImageProcessingView {
  private final JLabel imageLabel;
  private final JLabel histogramLabel;
  private final JButton loadButton;
  private final JButton saveButton;
  private final JButton brightenButton;
  private final JButton dimButton;
  private final JButton blurButton;
  private final JButton sharpenButton;
  private final JButton horizontalFlipButton;
  private final JButton verticalFlipButton;
  private final JButton sepiaButton;
  private final JButton grayscaleRedButton;
  private final JButton grayscaleGreenButton;
  private final JButton grayscaleBlueButton;
  private final JButton grayscaleValueButton;
  private final JButton grayscaleIntensityButton;
  private final JButton grayscaleLumaButton;
  private final JButton grayscaleFilterButton;
  private final JButton downscaleButton;

  /**
   * Default constructor for the GUI. Instantiates all the panels and buttons needed for the view.
   */
  public ImageProcessingViewGUI() {
    super();
    setTitle("Process an Image!");
    setSize(1100, 600);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new FlowLayout());
    this.add(mainPanel);

    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Selected Image:"));

    JPanel histogramPanel = new JPanel();
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Image Histogram:"));

    this.imageLabel = new JLabel();
    JScrollPane imageScrollable = new JScrollPane(this.imageLabel);
    imageScrollable.setPreferredSize(new Dimension(400,400));
    imagePanel.add(imageScrollable);
    mainPanel.add(imagePanel);

    this.histogramLabel = new JLabel();
    JScrollPane histogramScrollable = new JScrollPane(this.histogramLabel);
    histogramScrollable.setPreferredSize(new Dimension(275,400));
    histogramPanel.add(histogramScrollable);
    mainPanel.add(histogramPanel);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(17, 1));
    buttonPanel.setBorder(BorderFactory.createTitledBorder("Image Functions:"));

    this.loadButton = new JButton("Load Image");
    this.loadButton.setActionCommand("load");
    buttonPanel.add(loadButton);

    this.saveButton = new JButton("Save Image");
    this.saveButton.setActionCommand("save");
    buttonPanel.add(saveButton);

    this.brightenButton = new JButton("Brighten");
    this.brightenButton.setActionCommand("brighten");
    buttonPanel.add(brightenButton);

    this.dimButton = new JButton("Dim");
    this.dimButton.setActionCommand("dim");
    buttonPanel.add(dimButton);

    this.horizontalFlipButton = new JButton("Horizontal Flip");
    this.horizontalFlipButton.setActionCommand("horizontal");
    buttonPanel.add(horizontalFlipButton);

    this.verticalFlipButton = new JButton("Vertical Flip");
    this.verticalFlipButton.setActionCommand("vertical");
    buttonPanel.add(verticalFlipButton);

    this.blurButton = new JButton("Blur");
    this.blurButton.setActionCommand("blur");
    buttonPanel.add(blurButton);

    this.sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("sharpen");
    buttonPanel.add(sharpenButton);

    this.sepiaButton = new JButton("Sepia");
    this.sepiaButton.setActionCommand("sepia");
    buttonPanel.add(sepiaButton);

    this.grayscaleFilterButton = new JButton("Grayscale Filter");
    this.grayscaleFilterButton.setActionCommand("grayscale filter");
    buttonPanel.add(grayscaleFilterButton);

    this.grayscaleRedButton = new JButton("Grayscale Red");
    this.grayscaleRedButton.setActionCommand("grayscale red");
    buttonPanel.add(grayscaleRedButton);

    this.grayscaleGreenButton = new JButton("Grayscale Green");
    this.grayscaleGreenButton.setActionCommand("grayscale green");
    buttonPanel.add(grayscaleGreenButton);

    this.grayscaleBlueButton = new JButton("Grayscale Blue");
    this.grayscaleBlueButton.setActionCommand("grayscale blue");
    buttonPanel.add(grayscaleBlueButton);

    this.grayscaleValueButton = new JButton("Grayscale Value");
    this.grayscaleValueButton.setActionCommand("grayscale value");
    buttonPanel.add(grayscaleValueButton);

    this.grayscaleIntensityButton = new JButton("Grayscale Intensity");
    this.grayscaleIntensityButton.setActionCommand("grayscale intensity");
    buttonPanel.add(grayscaleIntensityButton);

    this.grayscaleLumaButton = new JButton("Grayscale Luma");
    this.grayscaleLumaButton.setActionCommand("grayscale luma");
    buttonPanel.add(grayscaleLumaButton);

    this.downscaleButton = new JButton("Downscale");
    this.downscaleButton.setActionCommand("downscale");
    buttonPanel.add(downscaleButton);

    mainPanel.add(buttonPanel);
  }

  /**
   * Displays the given image and the given image's histogram to the user.
   * @param image the given image
   * @param histogram the given image's histogram
   */
  public void displayLabels(BufferedImage image, BufferedImage histogram) {
    ImageIcon displayImage = new ImageIcon(image);
    this.imageLabel.setIcon(displayImage);

    ImageIcon displayHistogram = new ImageIcon(histogram);
    this.histogramLabel.setIcon(displayHistogram);
  }

  /**
   * Sets all the action listeners for the GUI's buttons.
   * @param listener where the button click will be processed
   */
  public void setListener(ActionListener listener) {
    this.loadButton.addActionListener(listener);
    this.saveButton.addActionListener(listener);
    this.brightenButton.addActionListener(listener);
    this.dimButton.addActionListener(listener);
    this.blurButton.addActionListener(listener);
    this.sharpenButton.addActionListener(listener);
    this.grayscaleRedButton.addActionListener(listener);
    this.grayscaleGreenButton.addActionListener(listener);
    this.grayscaleBlueButton.addActionListener(listener);
    this.grayscaleValueButton.addActionListener(listener);
    this.grayscaleIntensityButton.addActionListener(listener);
    this.grayscaleLumaButton.addActionListener(listener);
    this.grayscaleFilterButton.addActionListener(listener);
    this.horizontalFlipButton.addActionListener(listener);
    this.verticalFlipButton.addActionListener(listener);
    this.sepiaButton.addActionListener(listener);
    this.downscaleButton.addActionListener(listener);
  }

  /**
   * Receives an input value from the user by displaying a pop-up dialog screen.
   * @param command the message sent to the user in the pop-up screen.
   * @return the value the user entered, as an int
   */
  public int getInputValue(String command) {
    String value = JOptionPane.showInputDialog("Enter " + command + " value:");
    return Integer.parseInt(value);
  }

  /**
   * Method that accepts a string and displays it on the command line to the client.
   *
   * @param message the message to be displayed
   * @throws IOException if transmission to the provided data destination fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    System.out.println(message);
  }

}
