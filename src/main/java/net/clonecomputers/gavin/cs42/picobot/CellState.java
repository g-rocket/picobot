package net.clonecomputers.gavin.cs42.picobot;

public enum CellState {
	/**
	 * wall
	 */
	W {
		@Override
		public boolean matches(CellState state) {
			return state == this;
		}
	},
	/**
	 * open
	 */
	O {
		@Override
		public boolean matches(CellState state) {
			return state == this;
		}
	},
	ANY {
		@Override
		public boolean matches(CellState state) {
			return true;
		}
	};
	
	public abstract boolean matches(CellState state);
}
