package com.epam.training.ticketservice.util;

import com.epam.training.ticketservice.core.config.AppConfig;
import com.epam.training.ticketservice.core.movie.persistance.entity.Movie;
import com.epam.training.ticketservice.core.screening.persistance.entity.Screening;

import java.time.format.DateTimeFormatter;

public class FormatUtil {
    public static String formatScreeningAndMovie(Screening screening, Movie movie) {
        DateTimeFormatter formatter = getAppDateTimeFormatter();

        String formattedDateTime = screening.getScreeningDate().format(formatter);
        String formatString = "%s (%s, %s minutes), screened in room %s, at %s";
        return String.format(formatString,
                screening.getMovieTitle(),
                movie.getGenre(),
                movie.getLength(),
                screening.getRoomName(),
                formattedDateTime);
    }

    public static DateTimeFormatter getAppDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(AppConfig.getInstance().getProperties().getProperty("dateTimeFormat"));
    }
}
