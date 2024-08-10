package org.example;


enum ConnectionState {
    ACTIVE, CLOSED
}

public class Connection {
    private static int idCounter = 1;
    private ConnectionState state;
    private int id;


    Connection() {
        this.id = idCounter++;
        this.state = ConnectionState.ACTIVE;
    }

    public static void setIdCounter(int idCounter) {
        Connection.idCounter = idCounter;
    }

    public ConnectionState getState() {
        return state;
    }

    public void setState(ConnectionState state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
