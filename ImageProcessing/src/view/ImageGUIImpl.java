package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageGUIImpl extends JFrame implements ActionListener, ItemListener{
  private JPanel mainPanel;
  private JScrollPane mainScrollPane;

  private JLabel comboboxDisplay;
  JComboBox<String> comboCommands;

  private JLabel l1;

  public ImageGUIImpl() throws IOException {
    setTitle("Process an Image!");
    setSize(800, 800);

    this.mainPanel = new JPanel();
    this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.PAGE_AXIS));
    this.mainScrollPane = new JScrollPane(this.mainPanel);

    BufferedImage myPicture = ImageIO.read(new File("res/exampleImages/Henock.jpg"));
    JLabel picLabel = new JLabel(new ImageIcon(myPicture));
    this.mainPanel.add(picLabel);

    String[] options = {"Blur",
            "Sharpen",
            "Brighten",
            "Dim",
            "Horizontal Flip",
            "Vertical Flip",
            "Grayscale: Red Component",
            "Grayscale: Green Component",
            "Grayscale: Blue Component",
            "Grayscale: Value",
            "Grayscale: Intensity",
            "Grayscale: Luma",
            "Filter: Grayscale",
            "Filter: Sepia"};
    comboCommands = new JComboBox<>(options);
    comboCommands.addItemListener(this);
    comboCommands.addActionListener(this);
    this.mainPanel.add(comboCommands);

    add(this.mainPanel);
    l1 = new JLabel("Jalpaiguri selected");

    this.mainPanel.add(l1);



  }

  /**
   * Invoked when an item has been selected or deselected by the user.
   * The code written for this method performs the operations
   * that need to occur when an item is selected (or deselected).
   *
   * @param e the event to be processed
   */
  @Override
  public void itemStateChanged(ItemEvent e) {
    // if the state combobox is changed
    if (e.getSource() == this) {

      l1.setText(comboCommands.getSelectedItem() + " selected");
    }
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {


  }
}
