
import java.util.*;

public class ChooseStudentMovie {
	public TreeMap<String, RankedMov[]> studentMovies = new TreeMap<String, RankedMov[]>();
	public RankedMov[] allMovies;
	
	public class RankedMov implements Comparable<RankedMov> {
		public String name;
		public int rank;
		
		//Default constructor
		public RankedMov() {
            this.name = "null";
            rank = 0;
        }
		
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
				//movies[i-1].tick();//counts how many times each movie is seen
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
		/*
		//testing for the count of each movie
		for(int i = 0; i < allMovies.length; i++) {
			System.out.println(allMovies[i].name + " occured " + allMovies[i].rank + " times.");
		}
		*/
		
	}
	
	//Ahmad
	public String chooseMovie(String[] students) {
		if(students.length == 0) {
		    System.out.println("No students names in the array found.");
		    return null;
		}
		
		String name;
		RankedMov[] Movies;
		//For loop meant to go through each student and tick the movie
		for(int i = 0; i < students.length; i++) {
		    name = students[i];
		    Movies = studentMovies.get(name);
		    //goes through movie and ticks it.
		    for(RankedMov singleMovie : Movies) {
		        singleMovie.tick();
		    }
		}
		
		//For loop that sees which movie has the highest rank.
		//Uses student names to iterate through studentMovies map.
		//Also sets the rank back to 0 for each movie.
		RankedMov highestRankMovie = new RankedMov();
		for(int i = 0; i < students.length; i++) {
		    name = students[i];
		    Movies = studentMovies.get(name);
		    
            for(RankedMov singleMovie : Movies) {
                if(singleMovie.rank > highestRankMovie.rank) {
                    highestRankMovie.rank = singleMovie.rank;
                    highestRankMovie.name = singleMovie.name;
                    singleMovie.reset();
                }
                else {
                    singleMovie.reset();
                }
            }
		}
		
		
	    return highestRankMovie.name;
	}
	
	public static void main(String[] args) {
		ChooseStudentMovie mov = new ChooseStudentMovie();
		
		String[] array1 = new String[3];
		array1[0] = "Liban";
		array1[1] = "Seth";
		array1[2] = "Kyrin";
		System.out.println("Expect: The Godfather, Forrest Gump, or Shutter Island    Result: " + mov.chooseMovie(array1));
		
		
		String[] array2 = new String[4];
		array2[0] = "Thad";
		array2[1] = "Abbas";
		array2[2] = "Megan";
		array2[3] = "Ahmad";
		System.out.println("Expect: Fight Club, The Shining, A Separation, or M    Result: " + mov.chooseMovie(array2));
		
		String[] array3 = new String[2];
		array3[0] = "Nikki";
		array3[1] = "Jimmy";
		System.out.println("Expect: Forrest Gump, The Shining, Braveheart, Inception, Jaws, " + 
		"Gladiator, Scarface, Shutter Island, M, or The Terminator    Result: " + mov.chooseMovie(array3));
		
	}
}
