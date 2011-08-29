import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;


public class Renderer {
	private Game game;
	private List<Entity> entities;
	
	public Renderer(Game game) {
		this.game = game;
		this.entities = new ArrayList<Entity>();
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	
	public void init() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, game.getGameWindow().width(), game.getGameWindow().height(), 0, -1.0f, 1.0f);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		Color[] colors = { Color.red, Color.blue, Color.green, Color.yellow, Color.orange, Color.cyan };
		
		int[][] blockPositions = {
			{ 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 },
			{ 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
		};
		
		float topOffset = 15.0f;
		for(int i = 0; i < blockPositions.length; i++) {
			for(int j = 0; j < blockPositions[i].length; j++) {
				Block block = new Block(game, (game.getGameWindow().width() / (float)blockPositions[i].length), 25.0f, colors[blockPositions[i][j]]);
				block.changePosition(new Vertex((block.getWidth() * j), (block.getHeight() * i) + topOffset, 0.0f));
				
				game.getCollidables().add(block);
				this.entities.add(block);
			}
		}
		
		Paddle paddle = createPaddle();
		game.setPaddle(paddle);
		game.getCollidables().add(paddle);
		this.entities.add(paddle);
		
		Bullet bullet = createBullet();
		game.setBullet(bullet);
		this.entities.add(bullet);
	}
	
	private Paddle createPaddle() {
		Paddle paddle = new Paddle(game);
		
		// Put the paddle in the middle of the bottom of the screen.
		float x = (game.getGameWindow().width() / 2.0f) - (paddle.getWidth() / 2);
		float y = (game.getGameWindow().height() - paddle.getHeight() - 20.0f);
		paddle.changePosition(new Vertex(x, y, 0.0f));
		
		return paddle;
	}
	
	private Bullet createBullet() {
		Bullet bullet = new Bullet(game);
		
		// Put the paddle in the middle of the bottom of the screen.
		float x = (game.getGameWindow().width() / 2.0f) - (bullet.getWidth() / 2);
		float y = (game.getGameWindow().height() - bullet.getHeight() - 60.0f);
		bullet.changePosition(new Vertex(x, y, 0.0f));
		return bullet;
	}
	
	public void renderAll() {
		game.updateAnimations();
		
		GL11.glClearColor(0.4f, 0.4f, 0.4f, 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glLoadIdentity();
		
		for(Entity entity : entities) {
			entity.render();
		}
	}
}
