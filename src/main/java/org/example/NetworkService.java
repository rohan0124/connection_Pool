package org.example;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Library for managing connection pools which can be initialized with a pool size
//Threads to simulate Clients, where each client  will getConnection() ; sleep(x) ; closeConnection(id)
// now cleaned and formatted the code after the interview state
public class NetworkService {
    private static NetworkService service;
    volatile Map<Integer, Connection> connectionList = new ConcurrentHashMap<>();
    private volatile int currentActiveConnections;
    private int size;

    private NetworkService(int size) {
        this.size = size;
        this.currentActiveConnections = 0;
    }

    public static synchronized NetworkService getInstance(int size) {
        if (service == null) {
            service = new NetworkService(size);
        }
        return service;
    }

    synchronized Connection getConnection() {
        while (connectionList.size() == size) {
            try {
                System.out.println("Waiting for connection  " + Thread.currentThread().getName());
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted while waiting for a connection.");
            }
        }

        Connection connection = new Connection();
        connectionList.put(connection.getId(), connection);
        System.out.println("Thread " + Thread.currentThread().getName() + " got connection with  UUID " + connection.getId());
        return connection;
    }

    synchronized void closeConnection(int connectionId) {
        Connection connection = connectionList.get(connectionId);
        if (connection == null) {
            System.out.println("Invalid Connection Id");
            throw new RuntimeException("Invalid Connection Id");
        }
        if (connection.getState().equals(ConnectionState.ACTIVE)) {
            connection.setState(ConnectionState.CLOSED);
            connectionList.remove(connectionId);
            System.out.println("Thread " + Thread.currentThread().getName() + " closes connection with UUID " + connection.getId());
            this.notifyAll();
        }
    }

}
