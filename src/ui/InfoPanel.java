package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * The GoldPanel will display the currently selected Coin Bags gold value.
 * @author newtondavi2 (David)
 *
 */
public class InfoPanel extends JPanel {

	private Image backgroundImage;
	private Image inspectedImage;

	public InfoPanel() {
		this.setPreferredSize(new Dimension(200,150));

		this.createBorder();
		try {
			backgroundImage = ImageIO.read(new File("GUI_InfoPanel"
					+ ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(backgroundImage!= null){
			g.drawImage(backgroundImage, 0, 0, null);

		}
		if(inspectedImage!= null){
			g.drawImage(inspectedImage, 0, 0, null);

		}
	}

	/**
	 * Loads the image to display information on the item inspected.
	 * @param c
	 */
	public void getInspect(char c){

		if(c == 'K'){
			try {
				// load the imageName
				inspectedImage = ImageIO.read(new File("KeyDescription.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(c == 'B'){
			try {
				// load the imageName
				inspectedImage = ImageIO.read(new File("BagDescription.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(c == 'C'){
			try {
				// load the imageName
				inspectedImage = ImageIO.read(new File("CoinDescription.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(c == 'Z'){
			try {
				// load the imageName
				inspectedImage = ImageIO.read(new File("Candy_Description.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(c == 'X'){
			try {
				// load the imageName
				inspectedImage = ImageIO.read(new File("SKey_Description.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(c == 'Q'){
			try {
				// load the imageName
				inspectedImage = ImageIO.read(new File("Treasure_Description.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		this.revalidate();
		this.repaint();

	}

	/**
	 * Creates a Compound beveled border.
	 */
	private void createBorder() {
		//Compound borders
		Border compound;

		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		//This creates a nice frame.
		compound = BorderFactory.createCompoundBorder(
		                          raisedbevel, loweredbevel);

		this.setBorder(compound);
	}

}