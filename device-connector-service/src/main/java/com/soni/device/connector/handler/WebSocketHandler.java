package com.soni.device.connector.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;

@Component
public class WebSocketHandler extends  AbstractWebSocketHandler {

    @Autowired
    UserSessionUtil userSessionUtil;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
     //   String parameters[] = session.getUri().getQuery().split("=");

        UserSessionUtil.addSessionToPool("abcd", session);

       /* if (parameters.length == 2 && parameters[0].equals("accessToken")) {
            String accessToken = parameters[1];

            Long senderUserId = 0L;
            String senderId = cacheRepository.getUserIdByAccessToken(accessToken);

            if (senderId == null) {
                User sender = userRepository.findByToken(accessToken);
                if (sender != null) {
                    senderUserId = sender.getUserId();
                }
            } else {
                senderUserId = Long.valueOf(senderId);
            }
            if (senderUserId == 0L) {
                return;
            }

            UserSessionUtil.addSessionToPool(senderUserId, session);
        }
        else {
            session.close();
        }
*/
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    //    String parameters[] = session.getUri().getQuery().split("=");

        UserSessionUtil.removeFromSessionToPool("abcd", session);

      /*  if (parameters.length == 2 && parameters[0].equals("accessToken")) {
            String accessToken = parameters[1];

            Long senderUserId = 0L;
            String senderId = cacheRepository.getUserIdByAccessToken(accessToken);

            if (senderId == null) {
                User sender = userRepository.findByToken(accessToken);
                if (sender != null) {
                    senderUserId = sender.getUserId();
                }
            } else {
                senderUserId = Long.valueOf(senderId);
            }
            if (senderUserId == 0L) {
                return;
            }

            UserSessionUtil.removeFromSessionToPool(senderUserId, session);
        }*/

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        try{
            UserSessionUtil.sendTextMessage("abcd", message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        try {
            //do something....
            UserSessionUtil.sendBinaryMessage("abcd", message);
            System.out.println("New Binary Message Received");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
