package lab9;

import java.util.*;

public class ChooseStudentMovie {
	public TreeMap<String, String[]> studentMovies = new TreeMap<String, String[]>();
	public RankedMov[] allMovies;
	
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
		String input = "Liban: The Godfather, Pulp Fiction, Forrest Gump, The Shining, Joker\r\n"
				+ "Jonathan: Pulp Fiction, Gladiator, Joker, A Separation, Inception\r\n"
				+ "Megan: Fight Club, Forrest Gump, Joker, Inception, M\r\n"
				+ "Benjamin: Pulp Fiction, Fight Club, Scarface, A Separation, Full Metal Jacket\r\n"
				+ "Anusha: The Godfather, Scarface, A Separation, Taxi Driver, Die Hard\r\n"
				+ "Jimmy: Forrest Gump, The Shining, Braveheart, Inception, Jaws\r\n"
				+ "Nikki: Gladiator, Scarface, Shutter Island, M, The Terminator\r\n"
				+ "Abbas: Pulp Fiction, Braveheart, A Separation, The Exorcist, Room\r\n"
				+ "Kyrin: Forrest Gump, The Shining, Shutter Island, Room, Die Hard\r\n"
				+ "William: The Godfather, Braveheart, Inception, Die Hard, Jaws\r\n"
				+ "Ahmad: Gladiator, The Shining, Shutter Island, Joker, M\r\n"
				+ "Esther: Pulp Fiction, The Shining, M, Die Hard, Jaws\r\n"
				+ "Dylan: Pulp Fiction, Fight Club, The Shining, The Terminator, Room\r\n"
				+ "Thomas: Forrest Gump, Braveheart, Shutter Island, Inception, The Exorcist\r\n"
				+ "Thad: Fight Club, The Shining, A Separation, Die Hard, The Terminator\r\n"
				+ "Seth: The Godfather, Braveheart, Shutter Island, Inception, M";
		Scanner scnr = new Scanner(input);
		TreeMap<String, RankedMov> movieMap = new TreeMap<String, RankedMov>();
		while(scnr.hasNext()) {
			String line = scnr.nextLine();
			String [] result = line.split("[,:]+");
			String student = result[0];
			RankedMov[] movies = new RankedMov[result.length -1];
			for (int i = 1; i < result.length; i++) {
				if(!movieMap.containsKey(result[i].substring(1))){
					movies[i-1] = new RankedMov(result[i].substring(1));
					movieMap.put(result[i].substring(1), movies[i-1]);
				}else {
					movies[i-1] = movieMap.get(result[i].substring(1));
				}
				movies[i-1].tick();//counts how many times each movie is seen
			}
			studentMovies.put(student, movies);
		}
		scnr.close();
		
		Set<String> keys = movieMap.keySet();
		Iterator<String> keyItr = keys.iterator();
		allMovies = new RankedMov[movieMap.size()];
		//adds the movies from the map to the public array of movies
		for(int x = 0; x < movieMap.size(); x++) {
			allMovies[x] = movieMap.get(keyItr.next());
		}
		//sorts the array of movies
		Arrays.sort(allMovies, new Comparator<RankedMov>() {
			@Override
			public int compare(RankedMov o1, RankedMov o2) {
				return o2.rank - o1.rank;
			}	
    	});
		//testing for the count of each movie
		for(int i = 0; i < allMovies.length; i++) {
			System.out.println(allMovies[i].name + " occured " + allMovies[i].rank + " times.");
		}
		
	}
	
	public String chooseMovie(String[] students) {
		
	}
	
	public static void main(String[] args) {
		ChooseStudentMovie csm = new ChooseStudentMovie();
	}
}
