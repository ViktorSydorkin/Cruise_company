package DAO.Execptions;

public class DAOException extends RuntimeException {
    /**
     * DAO exception. Prints a given message
     *
     * @param message - message to be printed
     */
    public DAOException(String message) {
        super(message);
    }
}