package graph.main;

import java.awt.*;

/**
 * An interface for the vertices of a graph.
 */
public interface VertexITF {

	/**
	 * Returns the tag of the vertex
	 */
	public String getTag();

	/**
	 * Returns the id of the vertex
	 */
	public String getId();

	/**
	 * Returns the color of the vertex
	 */	
	public Color getColor();
	
	/**
	 * Sets the color of the vertex to 'color'
	 */		
	public void setColor(Color color);

}
