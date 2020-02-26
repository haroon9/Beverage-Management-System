package de.uniba.dsg.dsam.persistence.exceptions;

public class PersistenceExceptions extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public PersistenceExceptions() {
        // TODO Auto-generated constructor stub
    }

    public PersistenceExceptions(String message) {
        super(message);
    }

    public PersistenceExceptions(Throwable cause) {
        super(cause);
    }

    public PersistenceExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
