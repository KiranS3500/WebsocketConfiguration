package com.soni.device.connector.handler;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
public class UserSessionUtil {

    public static Map<String, CopyOnWriteArraySet<WebSocketSession>> websockets = new HashMap<>();

    public static void addSessionToPool(String userId, WebSocketSession session) {

        CopyOnWriteArraySet<WebSocketSession> userSessions = websockets.get(userId);

        if (userSessions != null) {
            userSessions.add(session);
            websockets.put(userId, userSessions);
        } else {
            CopyOnWriteArraySet<WebSocketSession> newUserSessions = new CopyOnWriteArraySet<>();
            newUserSessions.add(session);
            websockets.put(userId, newUserSessions);
        }

    }

    public static void removeFromSessionToPool(String userId, WebSocketSession session) {
        CopyOnWriteArraySet<WebSocketSession> userSessions = websockets.get(userId);
        if (userSessions != null) {
            for (WebSocketSession sessionItem : userSessions) {
                if (sessionItem.equals(session)) {
                    userSessions.remove(session);
                }
            }
        }
        websockets.put(userId, userSessions);
    }

    public static void sendTextMessage(String userId, TextMessage message) throws IOException {
        CopyOnWriteArraySet<WebSocketSession> userSessions = websockets.get(userId);
        if (userSessions == null) {
            return;
        }
        for (WebSocketSession session : userSessions) {
            System.out.println("sent text message to " + userSessions);
            session.sendMessage(message);
        }
    }

    public static void sendBinaryMessage(String userId, BinaryMessage binaryMessage) throws IOException {
        CopyOnWriteArraySet<WebSocketSession> userSessions = websockets.get(userId);
        if (userSessions == null) {
            return;
        }
        for (WebSocketSession session : userSessions) {
            System.out.println("sent Binary message to " + userSessions);
            session.sendMessage(binaryMessage);
        }
    }
}
