package app.codelabs.forum.helpers;

import android.content.Context;

import com.github.nkzawa.engineio.client.transports.WebSocket;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 17 Apr 2020, 14:36
 */
public class SocketSingleton {
    public static final String CHAT_JOIN_ROOM = "join_room";
    public static final String CHAT_SEND_MESSAGE = "room_message";
    public static final String CHAT_START_CONNECTION = "start_connect";

    public static final String ON_ROOM_INCOMING_MESSAGE = "room_incoming_message";

    public static String PING = "PING";


    private Socket socket;
    private static SocketSingleton instance = null;

    public SocketSingleton() {
        try {
            IO.Options options = new IO.Options();
            options.transports = new String[]{WebSocket.NAME};
            socket = IO.socket(Constant.SOCKET_URL, options);
        } catch (URISyntaxException e) {

        }
    }

    public static SocketSingleton getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new SocketSingleton();
        return instance;
    }

    public static void init() {
        getInstance();
    }

    public Socket getSocket() {
        return socket;
    }
}
