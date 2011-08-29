
public class Vertex {
	public static final Vertex ZERO = new Vertex(0.0f, 0.0f, 0.0f);
	
	public float x;
	public float y;
	public float z;
	
	public float nx;
	public float ny;
	public float nz;
	
	public Vertex() {
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
		nx = 0.0f;
		ny = 0.0f;
		nz = 0.0f;
	}
	
	public Vertex(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		nx = 0.0f;
		ny = 0.0f;
		nz = 0.0f;
	}
	
	public Vertex(Vertex copy) {
		this.x = copy.x;
		this.y = copy.y;
		this.z = copy.z;
	}
	
	public String toString() {
		return "("+x+", "+y+", "+z+")";
	}
	
	public Vertex cross(Vertex v) {
		Vertex u = this; // convenience
		return new Vertex((u.y * v.z) - (u.z * v.y), (u.z * v.x) - (u.x * v.z), (u.x * v.y) - (u.y * v.x));
	}
	
	public Vertex cross(Vertex v1, Vertex v2) {
		// Consider this vector the base
		Vertex u = subtract(v1);
		Vertex v = subtract(v2);
		return u.cross(v);
	}
	
	public Vertex subtract(Vertex v1) {
		return new Vertex(v1.x - x, v1.y - y, v1.z - z);
	}
	
	public Vertex add(Vertex v) {
		return new Vertex((x+v.x), (y+v.y), (z+v.z));
	}
	
	public Vertex average(Vertex v) {
		return new Vertex((x*v.x)/2.0f, (y*v.y)/2.0f, (z*v.z)/2.0f);
	}
	
	public void normalize() {
		float len = (float)length();
		//System.out.println("Len ("+x+"): " +" (" + (x * x) + ") " + Math.sqrt((x * x) + (y * y) + (z * z)));
		x = x / len;
		y = y / len;
		z = z / len;
	}
	
	public double length() {
		return Math.sqrt((x * x) + (y * y) + (z * z));
	}
	
	public boolean equals(Object obj) {
		if(obj.getClass().equals(Vertex.class)) {
			Vertex v = (Vertex)obj;
			return (v.x == x) && (v.y == y) && (v.z == z); 
		}
		else {
			return super.equals(obj);
		}
	}
	
	public static Vertex average(Vertex[] vectors) {
		float x = 0.0f;
		float y = 0.0f;
		float z = 0.0f;
		
		for(Vertex v : vectors) {
			x += v.x;
			y += v.y;
			z += v.z;
		}
		
		x /= vectors.length;
		y /= vectors.length;
		z /= vectors.length;
		
		return new Vertex(x, y, z);
	}
}