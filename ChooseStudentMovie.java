package lab9;

import java.util.*;

public class ChooseStudentMovie {
	TreeMap<String, String[]> studentMovies = new TreeMap<String, String[]>();
	
	public class RankedMov implements Comparable<RankedMov> {
		public String name;
		public int rank;
		
		public RankedMov(String name) {
			this.name = name;
			rank = 0;
		}
		public void tick() {
			rank++;
		}
		public void reset() {
			rank = 0;
		}
		public int compareTo(RankedMov mov) {
			return this.rank - mov.rank;
		}
	}
	
	public ChooseStudentMovie() {
		
	}
	
	public void read(String stuMov) {
		
	}
	
	public String chooseMovie(String[] students) {
		
	}
	
	public static void main(String[] args) {
		
	}
}
