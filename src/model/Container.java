package model;

import java.awt.Image;
import java.util.ArrayList;

/**
 * This class provides for GameObjects that represent game items that store
 * other items, such as a backpack holding collectables.
 *
 * @author dalyandr
 *
 */
public class Container extends GameObject {

	final private int maximumItems = 10;
	protected ArrayList<GameObject> items;

	/**
	 *
	 * @param id
	 * @param position
	 * @param image
	 * @param collidable
	 * @param drawable
	 * @param numberOfItemsCanContain
	 */
	public Container(ID id, Position position, Image image, boolean collidable,
			boolean drawable,  int boundingBoxSize) {
		super(id, position, image, collidable, drawable, boundingBoxSize);
		this.items = new ArrayList<GameObject>();
	}

	/**
	 * Alternative constructor with initial item. Primarily for testing
	 * purposes.
	 *
	 * @param id
	 * @param position
	 * @param image
	 * @param collidable
	 * @param drawable
	 * @param maximumItems
	 */
	public Container(ID id, Position position, Image image, boolean collidable,
			boolean drawable, int boundingBoxSize, Collectable... collectables) {
		super(id, position, image, collidable, drawable, boundingBoxSize);
		this.items = new ArrayList<GameObject>();
		for (Collectable collectable : collectables) {
			addItemToContainer(collectable);
		}
	}

	/**
	 * Allows for an item to be added to the list of items.
	 *
	 * @param gameObject
	 */
	public void addItemToContainer(Collectable collectable) {
		// check if it's the kind of thing we can add to the container
		if (collectable == null) {
			return;
		}else if (items.size() < maximumItems) {
			items.add(collectable);
		}
	}

	/**
	 * Allows for an item to be removed to the list of items.
	 *
	 * @param gameObject
	 */
	public void removeItemFromContainer(Collectable collectable) {
		if(collectable==null || numberOfObjectInContainer()==0){
			return;
		}else if (items.contains(collectable)) {
			collectable.setPosition(position); //update position
			items.remove(collectable);
		}

	}

	/**
	 * Returns number of items in this container.
	 *
	 * @param gameObject
	 */
	public int numberOfObjectInContainer() {
		return items.size();
	}


	/**
	 * This method returns entire contents of this Container.
	 *
	 * @returnlist of GameObject objects in container
	 */
	public ArrayList<GameObject> returnContents(){
		return items;
	}





}
