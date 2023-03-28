package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    Map<String, Movie> movieDb;
    Map<String, Director> directorDb;
    Map<String, LinkedHashSet<String>> moviesDirectorDb;

    public MovieRepository(){

        movieDb = new HashMap<>();
        directorDb = new HashMap<>();
        moviesDirectorDb = new HashMap<>();

    }
    public void addMovie(Movie movie){

        movieDb.put(movie.getName(), movie);


    }
    public void addDirector(Director director){

        directorDb.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movie, String director){

        LinkedHashSet<String> movies = moviesDirectorDb.getOrDefault(director, new LinkedHashSet<>());
        movies.add(movie);
        moviesDirectorDb.put(director, movies);

    }

    public Movie getMovieByName(String name){
        return movieDb.get(name);
    }

    public Director getDirectorByName(String name){

        return directorDb.get(name);

    }

    public List<String> getMoviesByDirectorName(String director){

        return new ArrayList<>(moviesDirectorDb.get(director));

    }

    public List<String> findAllMovies(){

        return new ArrayList<>(movieDb.keySet());

    }

    public void deleteDirectorByName(String director){

        Set<String> listOfMovies = moviesDirectorDb.get(director);

        for(String movie: listOfMovies){
            movieDb.remove(movie);
        }

        moviesDirectorDb.remove(director);
    }

    public void deleteAllDirectors(){

        for(Set<String> movies: moviesDirectorDb.values()){

            for(String movie: movies){

                movieDb.remove(movie);

            }
        }

        directorDb.clear();
        moviesDirectorDb.clear();

    }

}

