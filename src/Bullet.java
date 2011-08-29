import java.awt.Color;


public class Bullet extends QuadEntity {
	private Game game;
	private Vertex velocity;
	
	public Bullet(Game game) {
		super(15.0f, 15.0f, Color.WHITE);
		this.game = game;
		
		velocity = new Vertex(0.0f, 5.0f, 0.0f);
	}

	public void updatePosition() {
		if(this.positionVector.x < 0 || (this.positionVector.x + this.width) > game.getGameWindow().width()) {
			velocity.x *= -1.0f;
		}
		else if(this.positionVector.y < 1 || (this.positionVector.y + this.height) > game.getGameWindow().height()) {
			velocity.y *= -1.0f;
		}
		
		// See if this contacted any other renderables. If it did then turn around.
		for(Collidable c : game.getCollidables()) {
			if(c.collision(this)) {
				velocity = c.computeDeflection(this, velocity);
				break;
			}
		}
		
		this.positionVector = this.positionVector.add(velocity);
	}
}
