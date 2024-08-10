package org.example;

public class NetworkServiceTest {
    public NetworkServiceTest() {

    }

    void testWith_pooLSize_6_and_threadSize_20() {
        NetworkService networkService = NetworkService.getInstance(6);

        for (int i = 1; i <= 20; i++) {
            int id = i;
            Thread thread = new Thread(() -> {
                try {
                    Connection connection = networkService.getConnection();
                    Thread.sleep(1000);
                    networkService.closeConnection(connection.getId());
                } catch (Exception e) {
                    System.out.println("Error for Thread " + id + e.getMessage());
                }
            }, "Thread " + i);
            thread.start();

        }
    }
    void testWith_pooLSize_10_and_threadSize_5() {
        NetworkService networkService = NetworkService.getInstance(10);

        for (int i = 1; i <= 5; i++) {
            int id = i;
            Thread thread = new Thread(() -> {
                try {
                    Connection connection = networkService.getConnection();
                    Thread.sleep(1000);
                    networkService.closeConnection(connection.getId());
                } catch (Exception e) {
                    System.out.println("Error for Thread " + id + e.getMessage());
                }
            }, "Thread " + i);
            thread.start();

        }
    }
}
