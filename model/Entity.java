package av.model;

public abstract class Entity {
	int x;
	int y;
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
	public String xCharacter(){
		String xCharacter ="";
		if(x == 1){
			xCharacter = "a";
		}
		if(x == 2){
			xCharacter = "b";
		}
		if(x == 3){
			xCharacter = "c";
		}
		if(x == 4){
			xCharacter = "d";
		}
		if(x == 5){
			xCharacter = "e";
		}
		if(x == 6){
			xCharacter = "f";
		}
		if(x == 7){
			xCharacter = "g";
		}
		if(x == 8){
			xCharacter = "h";
		}
		return xCharacter;
	}

}
