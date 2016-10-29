/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author catherinexiong
 */
public class ReservationConflictException extends Exception {

    /**
     * Creates a new instance of <code>PropertyConflictException</code> without
     * detail message.
     */
    public ReservationConflictException() {
    }

    /**
     * Constructs an instance of <code>PropertyConflictException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ReservationConflictException(String msg) {
        super(msg);
    }
}
