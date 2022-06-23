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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;


import model.Pixel;
import model.ProcessableImage;


public class ImageGUIImpl extends JFrame implements ImageProcessingView{
  private final JLabel imageLabel;

  private final JButton loadButton;
  private final JButton saveButton;
  private final JButton brightenButton;
  private final JButton dimButton;
  private final JButton blurButton;
  private final JButton sharpenButton;
  private final JButton horizontalFlipButton;
  private final JButton verticalFlipButton;
  private final JButton grayscaleButton;
  private final JButton sepiaButton;



  public ImageGUIImpl() throws IOException {
    super();
    setTitle("Process an Image!");
    setSize(800, 800);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new FlowLayout());
    this.add(mainPanel);

    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Selected Image:"));
    mainPanel.add(imagePanel);
    imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.PAGE_AXIS));

    this.imageLabel = new JLabel();
    JScrollPane imageScrollable = new JScrollPane(this.imageLabel);
    imageScrollable.setPreferredSize(new Dimension(400,400));
    imagePanel.add(imageScrollable);
    mainPanel.add(imagePanel);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(16, 1));
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

    this.grayscaleButton = new JButton("Grayscale");
    this.grayscaleButton.setActionCommand("grayscale");
    buttonPanel.add(grayscaleButton);

    this.blurButton = new JButton("Blur");
    this.blurButton.setActionCommand("blur");
    buttonPanel.add(blurButton);

    this.sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("sharpen");
    buttonPanel.add(sharpenButton);

    this.sepiaButton = new JButton("Sepia");
    this.sepiaButton.setActionCommand("sepia");
    buttonPanel.add(sepiaButton);

    mainPanel.add(buttonPanel);
  }

  public void displayImage(BufferedImage image) {
//    BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(),
//            BufferedImage.TYPE_INT_RGB);

    ImageIcon displayImage = new ImageIcon(image);
    this.imageLabel.setIcon(displayImage);
  }

  public void setListener(ActionListener listener){
    this.loadButton.addActionListener(listener);
    this.saveButton.addActionListener(listener);
    this.brightenButton.addActionListener(listener);
    this.dimButton.addActionListener(listener);
    this.blurButton.addActionListener(listener);
    this.sharpenButton.addActionListener(listener);
    this.grayscaleButton.addActionListener(listener);
    this.horizontalFlipButton.addActionListener(listener);
    this.verticalFlipButton.addActionListener(listener);
    this.sepiaButton.addActionListener(listener);
  }

  public int getInputValue(String command){
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

  }

//  public String getGrayscaleSelection(){
//
//  }
}
