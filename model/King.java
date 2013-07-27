package av.model;

import av.Logic;

public class King extends Figure implements MotionAble {
	
	public King(int x, int y){
		this.x = x;
		this.y = y;
	}
	public String toString(){
		
		return Logic.kingOf +" "+ xCharacter() + y;
	}
	@Override
	public boolean motion(int toX, int toY) {
		int dx = toX-x;
		int dy = toY-y;
		
		if( Math.abs(dx) == Math.abs(dy)){
			return true;
		}		
		return false;
	}
	@Override
	public int getX() {
		
		return x;
	}
	@Override
	public int getY() {
		
		return y;
	}
	@Override
	public void setX(int x) {
		this.x = x;
	}
	@Override
	public void setY(int y) {
		this.y = y;
		
	}

}
