package av.model;

public interface MotionAble {
	public boolean motion(int x, int y);
	public int getX();
	public int getY();
	public void setX(int x);
	public void setY(int y);
}
