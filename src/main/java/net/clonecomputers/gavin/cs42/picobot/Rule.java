package net.clonecomputers.gavin.cs42.picobot;

import java.util.*;

import static net.clonecomputers.gavin.cs42.picobot.Direction.*;

public class Rule {
	public final Direction stateOffset;
	public final Direction moveOffset;
	public final Map<Direction, CellState> states;
	
	public Rule(int stateOffset, int moveOffset, Map<Direction, CellState> states) {
		this.stateOffset = Direction.byOrder(stateOffset);
		this.moveOffset = Direction.byOrder(moveOffset);
		this.states = Collections.unmodifiableMap(states);
	}
	
	public boolean isValid(Direction state, Map<Direction, CellState> neighborhood) {
		for(Direction d: Direction.directions()) {
			if(!states.get(d).matches(neighborhood.get(d.offset(state)))) {
				return false;
			}
		}
		return true;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Direction state: Direction.directions()) {
			sb.append(state.order);
			sb.append(" ");
			for(Direction dir: new Direction[]{N, E, W, S}) {
				CellState validStates = states.get(dir.inverseOffset(state));
				switch(validStates) {
				case W:
					sb.append(dir.toString());
					break;
				case O:
					sb.append("x");
					break;
				case ANY:
					sb.append("*");
					break;
				}
			}
			sb.append(" -> ");
			sb.append(state.offset(moveOffset));
			sb.append(" ");
			sb.append(state.offset(stateOffset).order);
			sb.append("\n");
		}
		return sb.toString();
	}
}
