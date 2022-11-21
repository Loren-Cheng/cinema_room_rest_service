package cinema.exception;

public class ExceptionInMovieRoom {

    public static class RowOrColumnOutOfBoundsException extends RuntimeException {
        private static final String message = "The number of a row or a column is out of bounds!";

        public RowOrColumnOutOfBoundsException() {
            super(message);
        }
    }

    public static class SeatBeenBookedException extends RuntimeException {
        private static final String message = "The ticket has been already purchased!";

        public SeatBeenBookedException() {
            super(message);
        }
    }

    public static class WrongTokenException extends RuntimeException {
        private static final String message = "Wrong token!";

        public WrongTokenException() {
            super(message);
        }
    }

    public static class WrongPasswordException extends RuntimeException{
        private static final String message = "The password is wrong!";
        public WrongPasswordException(){super(message);}
    }

}
