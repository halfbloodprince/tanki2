package model;

public interface Grid {
	/**
	 * Check if given tile is occupied
	 * @param x X position of tile
	 * @param y Y position of tile
	 * @return true, if this tile is occupied
	 */
	public boolean occupied(int x, int y);
	
	/**
	 * Set given tile as occupied
	 * @param x X position
	 * @param y Y position
	 */
	public void setTile(int x, int y);
	
	public int getWidth();
	public int getHeight();

	/**
	 * Get height of the dirt pile at given point
	 * @param x X position
	 * @return Dirt amount at given position
	 */
	public int getSurfaceHeight(int x);
}
