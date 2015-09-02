package net.clonecomputers.gavin.cs42.picobot;

import java.util.*;

public class Picobot {

	public static void main(String[] args) {
		recurse(null, new HashMap<Direction, CellState>(), Direction.byOrder(0));
	}

	public static void recurse(Rule r, Map<Direction, CellState> states, Direction dir) {
		for(CellState state: CellState.values()) {
			states.put(dir, state);
			if(dir.order + 1 < 4) {
				recurse(r, states, dir.offset(Direction.byOrder(1)));
			} else {
				checkWithDirections(r, states);
			}
		}
	}
	
	public static void checkWithDirections(Rule r, Map<Direction, CellState> states) {
		for(int stateOffset = 0; stateOffset < 4; stateOffset++) {
			for(int moveOffset = -1; moveOffset < 4; moveOffset++) {
				Rule r2 = new Rule(stateOffset, moveOffset, states);
				if(r == null) {
					recurse(r2, new HashMap<Direction, CellState>(), Direction.byOrder(0));
				} else {
					checkProgram(r, r2);
				}
			}
		}
	}
	
	static int programNumber = 0;
	public static void checkProgram(Rule r1, Rule r2) {
		programNumber++;
		if(programNumber % 100 == 0) System.out.println(programNumber/100);
		Maze maze = new Maze();
		for(int i = 0; i < 10000; i++) {
			if(!maze.applyRules(r1, r2)) return;
			if(maze.isSolved()) {
				System.out.println(r1);
				System.out.println(r2);
				System.out.println();
				return;
			}
		}
	}
	
}
