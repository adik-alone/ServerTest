import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    public static void main(String[] args){
        try(ServerSocket serverSocket = new ServerSocket(5555)){
            while(true) {
                Socket client = serverSocket.accept();;
                System.out.println("Клиент в конекте");

                DataInputStream in = new DataInputStream(client.getInputStream());
                DataOutputStream out = new DataOutputStream(client.getOutputStream());
                System.out.println("Потоки созданы");

                System.out.println("Начинаем общение с клиентом");

                while (!client.isClosed()) {
                    String entery = in.readUTF();
                    System.out.println("Строка полученна --- " + entery);

                    if (entery.equals("exit")) {
                        System.out.println("Завершение работы");
                        out.writeUTF("отключение от сервера, но вот ответ --- " + entery);
                        break;
                    }

                    System.out.println("Ответ от сервера");
                    out.writeUTF("Отвечаю --- " + entery);

                    out.flush();
                }
                in.close();
                out.close();
                client.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
