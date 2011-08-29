import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;


public class Game {	
	public static void main(String[] args) {
		new Game().play();
	}
	
	public List<Collidable> collidables;
	public List<Collidable> getCollidables() {
		return collidables;
	}
	
	private GameWindow gameWindow;
	private Renderer renderer;
	
	public Game() {
		this.gameWindow = new GameWindow(this);
		this.renderer = new Renderer(this);
		
		this.collidables = new ArrayList<Collidable>();
	}
	
	public GameWindow getGameWindow() {
		return gameWindow;
	}
	
	public void setGameWindow(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
	}
	
	public Renderer getRenderer() {
		return renderer;
	}
	
	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}
	
	public void play() {
		this.gameWindow.start();
	}
	
	public void checkKeyboard() {
		if(paddle != null) {
			if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
				paddle.moveLeft();	
			}
			else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
				paddle.moveRight();
			}
		}
	}
	
	private Paddle paddle;
	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}
	
	private Bullet bullet;
	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}
	
	public void updateAnimations() {
		bullet.updatePosition();
	}
}
