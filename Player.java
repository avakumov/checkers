package av;



import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import av.model.Field;
import av.model.MotionAble;

public class Player {
	public String color = null;
	private String action = "";
	
	public String getAction(){
		return action;
	}
	private List<MotionAble> figurs = new LinkedList<MotionAble>();

	public void add(MotionAble figure) {
		figurs.add(figure);
	}
	public void delete(MotionAble figure){
		figurs.remove(figure);
	}
	public List<MotionAble> getFigures(){
		return figurs;
	}
	public MotionAble get(int x, int y){
		for (MotionAble figure : figurs) {
			if (figure.getX() == x && figure.getY() == y) {
				return figure;
			}
		}
		return null;
	}

	public Player(String color) {
		this.color = color;
		

	}
	public int countFigure(){
		int count = 0;
		for(MotionAble figure : figurs){
			if (figure != null){
				count++;
			}
			
		}

		return count;
	}
	
	private MotionAble findFigure(int x, int y) {
		for (MotionAble figure : figurs) {
			if (figure.getX() == x && figure.getY() == y) {
				return figure;
			}
		}
		return null;
	}

	public MotionAble actionFrom() {
		String from1 = action.substring(0, 1); 
		int fromY = Integer.parseInt(action.substring(1, 2));
		int fromX = convertToX(from1);
		return findFigure(fromX, fromY);

	}

	public String actionTo() {
		String to1 = action.substring(3, 4); 
		int toY = Integer.parseInt(action.substring(4, 5));  
		int toX = convertToX(to1);
		String.valueOf(toY);
		return String.valueOf(toX)+String.valueOf(toY);

	}


	public void action(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		do{
			System.out.println(Logic.going +" "+ color + ":");
			action = scanner.nextLine();
		}while(!validInput());
	
	}
	private boolean validInput(){
		if(action.equals("exit")){
			return true;
		}
		String from1, to1;
		int fromX, fromY, toY, toX;
		try{
			from1 = action.substring(0, 1); 
			to1 = action.substring(3, 4);
			fromY = Integer.parseInt(action.substring(1, 2));
			fromX = convertToX(from1);
			toY = Integer.parseInt(action.substring(4, 5));  
			toX = convertToX(to1);
			if(validCoordinate(fromX)&&validCoordinate(fromY)&&
					validCoordinate(toX)&&validCoordinate(toY)){
				return true;
			}else{
				System.out.println(Logic.boundCoordinate);
			}
		}catch( NumberFormatException e){
			System.out.println(Logic.validCharacterCoordinate);
		}catch(StringIndexOutOfBoundsException e){
			System.out.println(Logic.lengthInput);
		}
		return false;
	}
	private static boolean validCoordinate(int coordinate) {
		if (coordinate < 1 || coordinate > 8) {
			return false;
		}
		return true;
	}
	boolean oursFigureOnAction(MotionAble figure,
			Field field) {
		int xFigure = figure.getX();
		int yFigure = figure.getY();
		int xField = field.getX();
		int yField = field.getY();
		int dx, dy;
		if(xFigure < xField){
			dx = 1;
		}else{
			dx = -1;
		}
		if(yFigure < yField){
			dy = 1;
		}else{
			dy = -1;
		}
		while(xFigure + dx != xField){
			if(get(xFigure+dx, yFigure + dy) != null){
				return true;
			}
			xFigure+=dx;
			yFigure+=dy;
		}
		return false;
	}
	private static int convertToX(String from) {
		String s = from.toLowerCase();
		if (s.equals("a"))
			return 1;
		if (s.equals("b"))
			return 2;
		if (s.equals("c"))
			return 3;
		if (s.equals("d"))
			return 4;
		if (s.equals("e"))
			return 5;
		if (s.equals("f"))
			return 6;
		if (s.equals("g"))
			return 7;
		if (s.equals("h"))
			return 8;
		else {
			return 0;
		}

	}

}
