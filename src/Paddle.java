import java.awt.Color;

import org.lwjgl.opengl.GL11;


public class Paddle extends QuadEntity {
	private Game game;
	
	public Paddle(Game game) {
		super(100.0f, 10.0f, Color.LIGHT_GRAY);
		this.game = game;
	}
	
	public void moveLeft() {
		this.positionVector.x -= 10.0f;
		
		if(this.positionVector.x < 0) {
			this.positionVector.x = 0;
		}
	}
	
	public void moveRight() {
		this.positionVector.x += 10.0f;

		if(this.positionVector.x > (game.getGameWindow().width() - this.width)) {
			this.positionVector.x = game.getGameWindow().width() - this.width;
		}
	}
	
	public Vertex absoluteCenter() {
		return new Vertex(this.positionVector.x + (this.width / 2.0f), this.positionVector.y, 0.0f);
	}
}
