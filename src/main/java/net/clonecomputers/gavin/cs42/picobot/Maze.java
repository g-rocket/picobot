package net.clonecomputers.gavin.cs42.picobot;

import static net.clonecomputers.gavin.cs42.picobot.CellState.*;

import java.util.*;

public class Maze {
	private static final CellState[][] map = {
			{ W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W },
			{ W,O,O,W,O,W,O,O,O,W,O,O,O,W,O,O,O,W,O,O,O,W,W,O,W },
			{ W,W,O,W,O,W,O,W,O,W,O,W,O,W,O,W,O,O,O,W,O,O,O,O,W },
			{ W,W,O,W,O,W,W,W,O,W,O,W,O,W,O,W,W,W,W,W,W,W,W,O,W },
			{ W,O,O,W,O,O,O,O,O,W,O,W,O,W,O,O,O,W,O,O,O,O,W,O,W },
			{ W,O,W,W,W,O,W,W,W,W,O,W,O,W,O,W,O,W,O,W,W,O,O,O,W },
			{ W,O,O,O,O,O,O,O,O,O,O,W,O,W,O,W,O,W,O,W,W,W,W,W,W },
			{ W,W,W,W,W,W,W,W,W,W,W,W,O,W,W,W,O,W,O,O,W,O,O,O,W },
			{ W,O,O,W,O,O,O,W,O,O,O,W,O,O,O,W,O,W,W,O,W,O,W,W,W },
			{ W,W,O,W,O,W,O,O,O,W,O,W,O,W,W,W,O,O,W,O,W,O,O,O,W },
			{ W,W,O,W,O,W,W,W,W,W,O,W,O,W,O,W,O,W,W,O,W,O,W,O,W },
			{ W,W,O,W,O,O,O,O,O,W,O,O,O,W,O,O,O,O,W,O,W,W,W,O,W },
			{ W,W,O,W,W,W,W,W,O,W,W,O,W,W,W,W,W,O,W,O,O,O,O,O,W },
			{ W,O,O,O,O,O,O,W,O,W,W,O,O,O,O,O,W,O,W,W,W,O,W,O,W },
			{ W,O,W,W,W,W,W,W,O,W,O,O,W,W,W,O,W,O,O,O,W,O,W,O,W },
			{ W,O,O,O,O,O,O,O,O,W,O,W,W,O,W,O,W,W,W,O,W,O,W,O,W },
			{ W,W,W,W,W,W,W,W,W,W,O,W,O,O,W,O,O,O,W,O,W,O,W,O,W },
			{ W,O,O,O,O,W,O,O,O,W,W,W,O,W,W,W,W,O,W,O,W,O,W,O,W },
			{ W,O,W,W,O,W,O,W,O,O,O,O,O,O,O,O,W,O,W,O,W,W,W,O,W },
			{ W,O,O,W,O,W,O,W,W,W,W,W,O,W,W,O,W,O,W,O,W,O,W,O,W },
			{ W,O,W,W,O,W,O,W,O,W,O,O,O,W,O,O,W,O,O,O,W,O,O,O,W },
			{ W,O,W,O,O,W,O,W,O,O,O,W,W,W,O,W,W,W,W,W,W,W,O,W,W },
			{ W,O,W,W,W,W,O,W,O,W,O,O,O,W,O,W,O,O,O,O,O,W,O,W,W },
			{ W,O,O,O,O,O,O,W,O,W,W,W,O,W,O,O,O,W,W,W,O,O,O,W,W },
			{ W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W },
	};
	
	private boolean[][] doneCells = new boolean[map.length][map[0].length];
	private int cellsLeft = 280;
	
	private int x, y;
	private Direction d;
	
	public Maze() {
		x = 11;
		y = 12;
		d = Direction.N;
		cellsLeft--;
	}
	
	public boolean isSolved() {
		return cellsLeft == 0;
	}
	
	public boolean applyRules(Rule r1, Rule r2) {
		Map<Direction, CellState> neighborhood = getNeighborhood(x, y);
		if(r1.isValid(d, neighborhood)) {
			if(r2.isValid(d, neighborhood)) {
				return false;
			} else {
				return applyRule(r1);
			}
		} else {
			if(r2.isValid(d, neighborhood)) {
				return applyRule(r2);
			} else {
				return false;
			}
		}
	}
	
	public boolean applyRule(Rule r) {
		x += d.offset(r.moveOffset).dx;
		y += d.offset(r.moveOffset).dy;
		d = d.offset(r.stateOffset);
		if(map[y][x] == W) return false;
		if(!doneCells[y][x]) {
			doneCells[y][x] = true;
			cellsLeft--;
		}
		return true;
	}
	
	public Map<Direction, CellState> getNeighborhood(int x, int y) {
		Map<Direction, CellState> m = new HashMap<>();
		for(Direction d: Direction.directions()) {
			m.put(d, map[y+d.dy][x+d.dx]);
		}
		return m;
	}
}
