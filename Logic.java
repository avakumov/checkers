package av;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import av.model.CheckerBlack;
import av.model.CheckerWhite;
import av.model.Field;
import av.model.King;
import av.model.MotionAble;

public class Logic {
	
	public static void main(String[] args) {
		initEnglish();
		initRussian();
		System.out.println("Choose language. en - english, ru - russian: ");
		while(true){
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			String language = scanner.nextLine();
			if(setLanguage(language)){
				break;
			}
		}
		
		Logic game = new Logic();
		game.run();

		
		
	}
	
	private static Map<String, String> en = new HashMap<String, String>();
	private static Map<String, String> ru = new HashMap<String, String>();
	
	static String initStart;
	static String initEnd;
	static String exampleStep;
	static String exit;
	static String winsWhite;
	static String winsBlack;
	static String exitPlayer;
	static String validCharacterCoordinate;
	static String boundCoordinate;
	static String lengthInput;
	static String notFoundFigure;
	static String fieldNotEmpty;
	static String going;
	static String notAble;
	static String oursFiguresOnPath;
	static String figureDelete;
	static String jump;
	static String simpleStep;
	static String  mustJump;
	static String countFigures;
	public static String whiteChecker;
	public static String blackCheker;
	public static String kingOf;

	private Player white = null;
	private Player black = null;
	private String action = "white";
	private List<Field> fields = new LinkedList<Field>();

	private static void initEnglish(){
		
		en.put("initStart", "Game is starting...");
		en.put("initEnd", "Initialization complete.");
		en.put("exampleStep", "example step: a3 b4, a3-b4, a3 c5, a3-c5, A3-b4, A3-B4");
		en.put("winsWhite", "White wins. Game is Over.");
		en.put("winsBlack", "Black wins. Game is Over.");
		en.put("exitPlayer", "One of the players out of the game. The game is over");
		en.put("validCharacterCoordinate", "Second coordinate must is character from 1 to 8");
		en.put("boundCoordinate", "First coordinate is spacing A-H, second 1-8");
		en.put("lengthInput", "Input length not less than 5. Example: a3-b4");
		en.put("notFoundFigure", "Figure not found on ");
		en.put("fieldNotEmpty", "Field where you going is not empty or not support");
		en.put("going", "Going");
		en.put("notAble", "can not walk on ");
		en.put("oursFiguresOnPath", "Ours figures on path");
		en.put("figureDelete",  "figure delete: ");
		en.put("jump",  " Step with jump");
		en.put("simpleStep", " simple step");
		en.put("mustJump", "You must step with jump");
		en.put("whiteChecker", "White checker ");
		en.put("blackCheker", "Black checker ");
		en.put("kingOf", "King ");
		en.put(exit, "exit");
		en.put(countFigures, "Count of figure ");
	}
	
	private static void initRussian(){
		ru.put("initStart", "Игра запускается...");
		ru.put("initEnd", "Инициализация завершена.");
		ru.put("exampleStep", "Пример ходов: a3 b4, a3-b4, a3 c5, a3-c5, A3-b4, A3-B4");
		ru.put("winsWhite", "Белые выиграли. Игра окончена.");
		ru.put("winsBlack", "Черные выиграли. Игра окончена.");
		ru.put("exitPlayer", "Один из игроков вышел из игры. Игра окончена.");
		ru.put("validCharacterCoordinate", "Диапазон полей:от A до H и  от 1 до 8, ");
		ru.put("boundCoordinate", "Диапазон полей:от A до H и  от 1 до 8, ");
		ru.put("lengthInput", "Длина вводимого хода должна соствлять не менее пяти символов. Пример: a3-b4");
		ru.put("notFoundFigure", "Фигура не найдена ");
		ru.put("fieldNotEmpty", "Поле на которое вы ходите занято или вы пытаетесь сходить на белое поле ");
		ru.put("going", "Ходит ");
		ru.put("notAble", "Фигура не поддерживает данный ход ");
		ru.put("oursFiguresOnPath", "Вы не можете ходить через свои фигуры ");
		ru.put("figureDelete",  "Фигура удалена: ");
		ru.put("jump",  " Ход с прыжком ");
		ru.put("simpleStep", " Простой ход");
		ru.put("mustJump", "Вы должны рубить противника ");
		ru.put("whiteChecker", "Белая пешка ");
		ru.put("blackCheker", "Черная пешка ");
		ru.put("kingOf", "Дамка ");
		ru.put("exit", "выход");
		ru.put("countFigures", "Количество фигур ");
	}
	private static void loadLanguage(Map<String, String> map){
		
		initStart = map.get("initStart");
		initEnd = map.get("initEnd");
		exampleStep = map.get("exampleStep");
		exit = map.get("exit");
		winsWhite = map.get("winsWhite");
		winsBlack = map.get("winsBlack");
		exitPlayer = map.get("exitPlayer");
		validCharacterCoordinate = map.get("validCharacterCoordinate");
		boundCoordinate = map.get("boundCoordinate");
		lengthInput = map.get("lengthInput");
		notFoundFigure = map.get("notFoundFigure");
		fieldNotEmpty = map.get("fieldNotEmpty");
		going = map.get("going");
		notAble = map.get("notAble");
		oursFiguresOnPath = map.get("oursFiguresOnPath");
		figureDelete = map.get("figureDelete");
		jump = map.get("jump");
		simpleStep = map.get("simpleStep");
		mustJump = map.get("mustJump");
		whiteChecker = map.get("whiteChecker");
		blackCheker = map.get("blackCheker");
		kingOf = map.get("kingOf");
		countFigures = map.get("countFigures");
	}
	private static boolean setLanguage(String language){
		if(language.equals("en")){
			loadLanguage(en);	
			return true;
		}
		if(language.equals("ru")){
			loadLanguage(ru);
			return true;
		}
		return false;
		
	}
	
	private Field getField(String xy) {
		int x = Integer.parseInt(xy.substring(0, 1));
		int y = Integer.parseInt(xy.substring(1, 2));

		for (Field field : fields) {
			if (field.getX() == x && field.getY() == y) {
				return field;
			}
		}
		return null;
	}

	private Field getField(int x, int y) {
		for (Field field : fields) {
			if (field.getX() == x && field.getY() == y) {
				return field;
			}
		}
		return null;
	}

	

	private void exchange(MotionAble figure, Field field) {
		int bufX = field.getX();
		int bufY = field.getY();
		field.setX(figure.getX());
		field.setY(figure.getY());
		figure.setX(bufX);
		figure.setY(bufY);
	}

	private void deleteFigure(Player opponent, MotionAble figure, Field field) {
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
			MotionAble figureToDelete = opponent.get(xFigure+dx, yFigure + dy); 
			if( figureToDelete!= null){
				System.out.println(figureDelete + figureToDelete.toString());
				opponent.delete(figureToDelete);
				fields.add(new Field(xFigure+dx, yFigure + dy));				
			}
			xFigure+=dx;
			yFigure+=dy;
		}

	}

	public void run() {
		start();
		System.out.println(exampleStep);
		while (!exit(true)) {

			if (action.equals("white")) {
				System.out.println(countFigures + white.color + " : "
						+ white.countFigure());
				System.out.println(countFigures + black.color + " : "
						+ black.countFigure());
				logic(white, black);
			}
			if (action.equals("black")) {
				System.out.println(countFigures + white.color + " : "
						+ white.countFigure());
				System.out.println(countFigures + black.color + " : "
						+ black.countFigure());
				logic(black, white);
			}

		}

	}

	public boolean exit(boolean print) {
		if (white.getAction().equals(exit)) {
			if (print) {
				System.out.println(exitPlayer);
				return true;
			}
			return true;

		}
		if (black.getAction().equals(exit)) {
			if (print) {
				System.out.println(exitPlayer);
				return true;
			}
			return true;
		}
		if (white.countFigure() == 0) {
			if (print) {
				System.out.println(winsBlack);
				return true;
			}
			return true;

		}
		if (black.countFigure() == 0) {
			if (print) {
				System.out.println(winsWhite);
				return true;
			}
			return true;

		}
		return false;
	}

	public void logic(Player active, Player passive) {
		active.action();
		if (exit(false))
			return;

		MotionAble figure = active.actionFrom();
		String to = active.actionTo();
		Field field = getField(to);
		if (figure == null) {
			System.out.println(notFoundFigure + " "
					+ active.getAction().substring(0, 2));
			return;
		}
		
		if (field == null) {
			System.out.println(fieldNotEmpty);
			return;
		}
		
		if (!figure.motion(field.getX(), field.getY())) {
			System.out.println(notAble + " "
					+ active.getAction().substring(3, 5));
			return;
		}
		
		
		
		if (active.oursFigureOnAction(figure, field)) {
			System.out.println(oursFiguresOnPath);
			return;
		}

		List<Field> requiredMotions = requiredMotion(figure, active, passive);
		List<MotionAble> requiredMotionFigures = requiredMotionFigures(active,
				passive);

		if (requiredMotionFigures.isEmpty()) {
			exchange(figure, field);
			modifyToKing(figure);
			System.out.println(active.color + simpleStep);
			action = passive.color;
			return;
		}

		for (MotionAble requiredMotionFigure : requiredMotionFigures) {
			if (requiredMotionFigure == figure) {
				for (Field requiredField : requiredMotions) {
					if (requiredField == field) {

						deleteFigure(passive, figure, field);
						exchange(figure, field);
						System.out.println(active.color + jump);
						List<Field> req = requiredMotion(figure, active, passive);
						modifyToKing(figure);
						if (req.isEmpty()) {
							action = passive.color;
							return;
						} else {
							return;
						}
					} 
				}
			}

		}
		System.out.println(mustJump);
		return;

	}

	public void start() {
		System.out.println(initStart);
		white = new Player("white");
		white.add(new CheckerWhite(1, 1));
		white.add(new CheckerWhite(1, 3));
		white.add(new CheckerWhite(2, 2));
		white.add(new CheckerWhite(3, 1));
		white.add(new CheckerWhite(3, 3));
		white.add(new CheckerWhite(4, 2));
		white.add(new CheckerWhite(5, 1));
		white.add(new CheckerWhite(5, 3));
		white.add(new CheckerWhite(6, 2));
		white.add(new CheckerWhite(7, 1));
		white.add(new CheckerWhite(7, 3));
		white.add(new CheckerWhite(8, 2));
		
		black = new Player("black");
		black.add(new CheckerBlack(1, 7));
		black.add(new CheckerBlack(2, 6));
		black.add(new CheckerBlack(2, 8));
		black.add(new CheckerBlack(3, 7));
		black.add(new CheckerBlack(4, 6));
		black.add(new CheckerBlack(4, 8));
		black.add(new CheckerBlack(5, 7));
		black.add(new CheckerBlack(6, 6));
		black.add(new CheckerBlack(6, 8));
		black.add(new CheckerBlack(7, 7));
		black.add(new CheckerBlack(8, 6));
		black.add(new CheckerBlack(8, 8));
		
		addFields();
		System.out.println(initEnd);

	}
	private void addFields(){
		for(int x =1; x<9; x+=2){
			for(int y = 1; y<9; y+=2){
				if(white.get(x, y) == null && black.get(x, y) == null){
					fields.add(new Field(x, y));
				}
			}
		}
		for(int x =2; x<9; x+=2){
			for(int y = 2; y<9; y+=2){
				if(white.get(x, y) == null && black.get(x, y) == null){
					fields.add(new Field(x, y));
				}
			}
		}
		
	}
	private void modifyToKing(MotionAble figure) {
		if (figure instanceof CheckerWhite) {
			if (figure.getY() == 8) {
				white.add(new King(figure.getX(), figure.getY()));
				white.delete(figure);
			}
		}
		if (figure instanceof CheckerBlack) {
			if (figure.getY() == 1) {
				black.add(new King(figure.getX(), figure.getY()));
				black.delete(figure);

			}
		}

	}

	private List<MotionAble> requiredMotionFigures(Player action,
			Player opponent) {
		List<MotionAble> figures = new ArrayList<MotionAble>();
		for (MotionAble actionFigure : action.getFigures()) {
			if (!requiredMotion(actionFigure, action, opponent).isEmpty()) {
				figures.add(actionFigure);
			}
		}
		return figures;

	}

	private List<Field> requiredMotion(MotionAble figure, Player active, Player opponent) {
		List<Field> fields = new ArrayList<Field>();
		int x = figure.getX();
		int y = figure.getY();
		if (figure instanceof CheckerWhite || figure instanceof CheckerBlack) {

			if (opponent.get(x + 1, y + 1) != null
					&& getField(x + 2, y + 2) != null) {
				fields.add(getField(x + 2, y + 2));
			}
			if (opponent.get(x + 1, y - 1) != null
					&& getField(x + 2, y - 2) != null) {
				fields.add(getField(x + 2, y - 2));
			}
			if (opponent.get(x - 1, y + 1) != null
					&& getField(x - 2, y + 2) != null) {
				fields.add(getField(x - 2, y + 2));
			}
			if (opponent.get(x - 1, y - 1) != null
					&& getField(x - 2, y - 2) != null) {
				fields.add(getField(x - 2, y - 2));
			}

		}
		if (figure instanceof King) {

			boolean flag = true;
			for (int i = 1; i < 7; i++) {
				if (opponent.get(x + i, y + i) != null && flag) {
					flag = false;
				}
				if (getField(x + i + 1, y + i + 1) != null && !flag) {
					if(!active.oursFigureOnAction(figure, getField(x + i + 1, y + i + 1))){
						fields.add(getField(x + i + 1, y + i + 1));
					}
					

				}
			}
			flag = true;

			for (int i = 1; i < 7; i++) {
				if (opponent.get(x - i, y + i) != null && flag) {
					flag = false;
				}
				if (getField(x - i - 1, y + i + 1) != null && !flag) {
					if(!active.oursFigureOnAction(figure, getField(x - i - 1, y + i + 1))){
						fields.add(getField(x - i - 1, y + i + 1));
					}
					

				}
			}
			flag = true;

			for (int i = 1; i < 7; i++) {
				if (opponent.get(x + i, y - i) != null && flag) {
					flag = false;
				}
				if (getField(x + i + 1, y - i - 1) != null && !flag) {
					if(!active.oursFigureOnAction(figure, getField(x + i + 1, y - i - 1))){
						fields.add(getField(x + i + 1, y - i - 1));
					}
					

				}
			}
			flag = true;

			for (int i = 1; i < 7; i++) {
				if (opponent.get(x - i, y - i) != null && flag) {
					flag = false;
				}
				if (getField(x - i - 1, y - i - 1) != null && !flag) {
					if(!active.oursFigureOnAction(figure, getField(x - i - 1, y - i - 1))){
						fields.add(getField(x - i - 1, y - i - 1));
					}
					

				}
			}

		}
		return fields;
	}



}
