package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;

import control.ClientControl;
import control.PacketDropItem;
import control.PacketUseItem;
import model.Player;




/**
 * The ItemLabel extends JLabel, it is used for each item type received from
 * the players inventory. It sets the items picture and the behavior for each item.
 * @author newtondavi2 (David)
 *
 */
public class ItemLabel extends JLabel {

	private Image itemImage;
	private BufferedImage emptySlot; // The Image for an Empty Slot in the players Inventory (no item)
	private JPopupMenu itemMenu = new JPopupMenu();
	private char asciiCode;
	private InfoPanel inspectPanel;
	private Player player;
	private ClientControl clientControl;
	private boolean sendPacket = false;

	/*
	 * The constructor will take in an items ID so that it can create its inventory imageName for the ItemLabels ImageIcon.
	 */
	public ItemLabel(InfoPanel inspectItem, char c, Player player, ClientControl clientControl){

		this.inspectPanel = inspectItem;
		this.setPreferredSize(new Dimension(50,50));
		this.setIcon(new ImageIcon());
		this.asciiCode = c;
		this.player = player;
		this.clientControl = clientControl;

		this.addMouseListener(new PopupTriggerListener());

		JMenuItem inspect = new JMenuItem("Inspect Item");
		inspect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inspectPanel.getInspect(getLabel().getAscii());
			}
		});
		itemMenu.add(inspect);

		JMenuItem use = new JMenuItem("Use Item");
		use.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//=============USE PACKET CODE HERE===========
				sendPacket = false;
				String data = "";
				if(getLabel().getAscii() == 'Z'){
					data = "6"+player.getClientNum()+"Z";
					sendPacket = true;
				}
				if(sendPacket){
					PacketUseItem p = new PacketUseItem(data.getBytes());
					p.writeData(clientControl);
				}



			}
		});
		itemMenu.add(use);

		JMenuItem drop = new JMenuItem("Drop Item");
		drop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendPacket = false;
				String data = "";
				if(getLabel().getAscii() == 'K'){
					data = "5"+player.getClientNum()+"K";
					sendPacket = true;
				}
				if(getLabel().getAscii() == 'B'){
					data = "5"+player.getClientNum()+"B";
					sendPacket = true;
				}
				if(getLabel().getAscii() == 'C'){
					data = "5"+player.getClientNum()+"C";
					sendPacket = true;
				}
				if(getLabel().getAscii() == 'Z'){
					data = "5"+player.getClientNum()+"Z";
					sendPacket = true;
				}
				if(getLabel().getAscii() == 'X'){
					data = "5"+player.getClientNum()+"X";
					sendPacket = true;
				}
				if(getLabel().getAscii() == 'Q'){
					data = "5"+player.getClientNum()+"Q";
					sendPacket = true;
				}

				if(sendPacket = true){
					PacketDropItem p = new PacketDropItem(data.getBytes());
					p.writeData(clientControl);
				}

			}
		});
		itemMenu.add(drop);


	}

	//rough get of this item label
	public char getAscii(){
		return this.asciiCode;
	}

	//rough return of this label
	public ItemLabel getLabel(){
		return this;
	}

	/**
	 * Inner Class which sets up the right click functionality of each ItemLabel
	 * @author newtondavi2
	 *
	 */
	class PopupTriggerListener extends MouseAdapter {

		Border selectedBorder = BorderFactory.createLineBorder(Color.GREEN, 1);

		public void mousePressed(MouseEvent ev) {
			if (ev.isPopupTrigger()) {
				itemMenu.show(ev.getComponent(), ev.getX(), ev.getY());
				((ItemLabel) ev.getComponent()).setBorder(selectedBorder); //Highlights item that has been clicked on
			}
		}

		public void mouseReleased(MouseEvent ev) {
			if (ev.isPopupTrigger()) {
				itemMenu.show(ev.getComponent(), ev.getX(), ev.getY());

			}
		}

		public void mouseClicked(MouseEvent ev) {
		}
	}

}