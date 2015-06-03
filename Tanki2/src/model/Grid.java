package model;

public interface Grid {
	/**
	 * Check if given tile is occupied
	 * @param x X position of tile
	 * @param y Y position of tile
	 * @return true, if this tile is occupied
	 */
	public boolean occupied(int x, int y);
}
