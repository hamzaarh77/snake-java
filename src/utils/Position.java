package utils;

public class Position {

	private int x;
	private int y;


	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	@Override
	public boolean equals(Object obj) {
		// Vérifie si l'objet est lui-même
		if (this == obj) {
			return true;
		}
		// Vérifie si l'objet est null ou d'une classe différente
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		// Effectue le cast en Position
		Position p = (Position) obj;
		// Compare les coordonnées
		return this.getX() == p.getX() && this.getY() == p.getY();
	}

	public String toString(){
		return this.x+";"+this.y+"\n";
	}


}
