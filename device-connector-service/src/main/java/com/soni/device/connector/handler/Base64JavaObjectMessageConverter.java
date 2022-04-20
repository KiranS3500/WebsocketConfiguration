package com.soni.device.connector.handler;

import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.util.Base64Utils;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.SerializationUtils;

public class Base64JavaObjectMessageConverter extends AbstractMessageConverter {

    public Base64JavaObjectMessageConverter() {
        super(MimeTypeUtils.APPLICATION_OCTET_STREAM);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected Object convertFromInternal(Message<?> message, Class<?> targetClass, @Nullable Object conversionHint) {
        byte[] messageBytes = Base64Utils.decode((byte[]) message.getPayload());
        return SerializationUtils.deserialize(messageBytes);
    }

    @Override
    public Object convertToInternal(Object payload, @Nullable MessageHeaders headers, @Nullable Object conversionHint) {
        byte[] messageBytes = SerializationUtils.serialize(payload);
        return Base64Utils.encode(messageBytes);
    }

}