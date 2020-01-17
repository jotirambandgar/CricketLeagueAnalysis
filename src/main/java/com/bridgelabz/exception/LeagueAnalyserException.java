package com.bridgelabz.exception;

public class LeagueAnalyserException extends RuntimeException {

    public enum ExceptionType {
                CENSUS_FILE_PROBLEM,
                INVALID_DATA,
                NO_CSV_FILE,
    }
    public ExceptionType type;

    public LeagueAnalyserException(String message,ExceptionType type) {
        super(message);
        this.type = type;
    }
}
