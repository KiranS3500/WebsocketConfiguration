package com.soni.device.connector.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.Principal;

@Data
@Slf4j
@Service
public class ConnectionService {

    public void sendToAdmin(String topic, Message<byte[]> message) {
        //save in database
        try {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            bOut.write(message.getPayload());
            bOut.flush();
            byte[] imageInByte = bOut.toByteArray();
            bOut.close();

            InputStream in = new ByteArrayInputStream(imageInByte);
            BufferedImage bImageFromConvert = ImageIO.read(in);

            ImageIO.write(bImageFromConvert, "jpeg", new File("E:/agent-screen.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendToAgent(String topic, Message<byte[]> message) {
        //save in database
        try {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            bOut.write(message.getPayload());
            bOut.flush();
            byte[] imageInByte = bOut.toByteArray();
            bOut.close();

            InputStream in = new ByteArrayInputStream(imageInByte);
            BufferedImage bImageFromConvert = ImageIO.read(in);

            ImageIO.write(bImageFromConvert, "jpeg", new File("E:/agent-screen.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
