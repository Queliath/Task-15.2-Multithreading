package by.epam.task15_2.dao.exception;

/**
 * Created by Владислав on 05.07.2016.
 */
public class DAOException extends Exception{
    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
