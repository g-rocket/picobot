package net.clonecomputers.gavin.cs42.picobot;

public enum Direction {
	N( 1,  0,  0),
	E( 0,  1,  1),
	S(-1,  0,  2),
	W( 0, -1,  3),
	X( 0,  0, -1);
	
	public final int dx, dy, order;
	
	private Direction(int dx, int dy, int order) {
		this.dx = dx;
		this.dy = dy;
		this.order = order;
	}
	
	public static Direction byOrder(int order) {
		switch(order) {
		case 0: return N;
		case 1: return E;
		case 2: return S;
		case 3: return W;
		default: return X;
		}
	}
	
	public static Direction[] directions() {
		return new Direction[]{N, E, S, W};
	}
	
	public static Direction[] moves() {
		return new Direction[]{N, E, S, W, X};
	}
	
	public Direction offset(Direction offset) {
		if(this == X || offset == X) return X;
		return byOrder((order + offset.order) % 4);
	}

	public Direction inverseOffset(Direction offset) {
		if(this == X || offset == X) return X;
		return byOrder((order - offset.order + 4) % 4);
	}
	
	public boolean isDirection() {
		return this != X;
	}
}
