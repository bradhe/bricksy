
public interface Collidable {
	boolean collision(QuadEntity entity);
	Vertex computeDeflection(QuadEntity entity, Vertex velocity);
}
