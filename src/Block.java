import java.awt.Color;

import org.lwjgl.opengl.GL11;


public class Block extends QuadEntity {
	private float width;
	private float height;
	private Color color;
	private Game game;
	
	public Block(Game game, float width, float height, Color color) {
		super(width, height, color);
		this.game = game;
	}
	
	public Vertex computeDeflection(QuadEntity entity, Vertex velocity) {
		Vertex deflection = super.computeDeflection(entity, velocity);
		
		if(collision(entity)) {
			game.getRenderer().getEntities().remove(this);
			game.getCollidables().remove(this);
		}
		
		return deflection;
	}
}
