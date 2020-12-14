package graph.main;
import java.awt.*;

/**
 * An interface for the edges of a graph.
 */
public interface EdgeITF {
		
	/**
	 * Returns the origin of the edge
	 */
	public VertexITF origin();
	
	/**
	 * Returns the destination of the edge
	 */
	public VertexITF destination();
	
	/**
	 * Returns the weight of the edge
	 */
	public Color color();
}
