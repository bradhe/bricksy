import java.awt.Color;

import org.lwjgl.opengl.GL11;


public class QuadEntity extends Entity implements Collidable {
	protected Color color;
	protected float width;
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	protected float height;
	
	public QuadEntity(float width, float height, Color color) {
		this.color = color;
		this.width = width;
		this.height = height;
	}
	
	@Override
	protected void renderVertices() {
		GL11.glColor4f(color.getRed()/255.0f, color.getGreen()/255.0f, color.getBlue()/255.0f, color.getAlpha()/255.0f);

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(0, 0, 0.0f);
		GL11.glVertex3f(this.width, 0, 0.0f);
		GL11.glVertex3f(this.width, this.height, 0.0f);
		GL11.glVertex3f(0, this.height, 0.0f);
		GL11.glEnd();
	}

	@Override
	public boolean collision(QuadEntity entity) {
		Vertex entityPosition = entity.getPosition();
		
		float[] u = { positionVector.y, positionVector.y + this.height, positionVector.x, positionVector.x + this.width };
		float[] v = { entityPosition.y, entityPosition.y + entity.getHeight(), entityPosition.x, positionVector.x + entity.getWidth() };
		
		// Does this quad exist within the other quad?
		if(u[1] < v[0]) { return false; }
		if(u[0] > v[1]) { return false; }
		if(u[3] < v[2]) { return false; }
		if(u[2] > v[3]) { return false; }
		
		return true;
	}
	
	public Vertex computeDeflection(QuadEntity entity, Vertex existingVelocity) {
		Vertex deflection = new Vertex(existingVelocity);
		deflection.y *= -1.0f;
		
		// Compute some X value for this thing. Perhaps distance from center as a percentage.
		float x = entity.getPosition().x + (entity.getWidth() / 2.0f);
		float halfWidth = (this.width / 2.0f);
		float distanceFromCenter = x - (positionVector.x + halfWidth);
		deflection.x = distanceFromCenter > 0.0f ? halfWidth / distanceFromCenter : 0.0f;
		System.out.println("Deflection: " + deflection + " Half width: " + halfWidth + " Dstance: " + distanceFromCenter);
		return deflection;
	}

}
