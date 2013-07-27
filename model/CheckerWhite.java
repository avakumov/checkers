package av.model;

import av.Logic;
public class CheckerWhite extends Figure implements MotionAble{
	

	public CheckerWhite(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		
		return Logic.whiteChecker +" "+ xCharacter() + y;
	}
	@Override
	public boolean motion(int toX, int toY) {
		int dx = toX-x;
		int dy = toY-y;
		if( dx == 1 && dy == 1){
			return true;
		}
		if( dx == -1 && dy == 1){
			return true;
		}
		
		if( Math.abs(dx) == 2 && Math.abs(dx) == Math.abs(dy)){
			return true;
		}		
		return false;
	}
	@Override
	public void setX(int x) {
		this.x = x;
		
	}
	@Override
	public void setY(int y) {
		this.y = y;
		
	}
	@Override
	public int getX() {
		
		return x;
	}
	@Override
	public int getY() {
		
		return y;
	}


}
