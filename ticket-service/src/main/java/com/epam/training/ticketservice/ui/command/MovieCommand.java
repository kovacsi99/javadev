package com.epam.training.ticketservice.ui.command;


import com.epam.training.ticketservice.core.movie.persistance.entity.Movie;
import com.epam.training.ticketservice.core.movie.MovieDto;
import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.ui.security.SecuredCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;
import java.util.StringJoiner;

@ShellComponent
public class MovieCommand extends SecuredCommand {

    private final MovieService movieService;


    @Autowired
    public MovieCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @ShellMethod(key = "create movie", value = "Add the movie to the Database")
    @ShellMethodAvailability("isAdmin")
    public String createMovie(String title, String genre, Long length) {
        MovieDto movieDto = new MovieDto(title, genre, length);
        movieService.saveMovie(movieDto);
        return "Movie saved.";
    }

    @ShellMethod(key = "list movies", value = "List all movies in the Database")
    public String listAllMovies() {
        List<Movie> movieList = movieService.listAllMovies();

        // If the list is empty return early.
        if (movieList.isEmpty()) {
            return "There are no movies at the moment";
        }

        StringJoiner stringJoiner = new StringJoiner("\n");

        for (Movie movie : movieList) {
            stringJoiner.add(movie.toString());
        }

        return stringJoiner.toString();
    }

    @ShellMethod(key = "update movie", value = "Update given movie's properties")
    @ShellMethodAvailability("isAdmin")
    public String updateMovie(String title, String genre, Long length) {
        MovieDto movieDto = new MovieDto(title, genre, length);
        movieService.saveMovie(movieDto);
        return "Movie updated.";
    }

    @ShellMethod(key = "delete movie", value = "Deletes movie from database.")
    @ShellMethodAvailability("isAdmin")
    public String deleteMovie(String title) {
        movieService.deleteExistingMovie(title);
        return "If the movie existed, it was deleted.";
    }
}
