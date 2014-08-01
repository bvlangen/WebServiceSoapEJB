package com.bvlangen.soap.handler;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogMessageHandler implements SOAPHandler<SOAPMessageContext> {

    public static final Logger LOGGER = Logger.getLogger(LogMessageHandler.class.getName());

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        log(context);
        return true; // return true to continue processing
    }

    private void log(SOAPMessageContext context) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n------------------------------------");
        stringBuilder.append("\n<<LOG MESSAGE>>\n");
        Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outboundProperty) {
            stringBuilder.append("\nDirection: OUTBOUND ");
        } else {
            logRequestHeaders(context);
            stringBuilder.append("\nDirection: INBOUND ");
        }

        SOAPMessage message = context.getMessage();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            message.writeTo(baos);
            stringBuilder.append("\nMessage:\n").append(baos.toString());
        } catch (SOAPException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        stringBuilder.append("\n------------------------------------");
        LOGGER.info(stringBuilder.toString());
    }

    private void logRequestHeaders(SOAPMessageContext context) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n------------------------------------");
        stringBuilder.append("\n<<LOG REQUEST HEADERS>>\n\n");

        Map requestHeaders = (Map) context.get(MessageContext.HTTP_REQUEST_HEADERS);
        addRequestHeadersToLogStr(stringBuilder, requestHeaders, "");

        stringBuilder.append("\n------------------------------------");
        LOGGER.info(stringBuilder.toString());
    }

    private void addRequestHeadersToLogStr(StringBuilder stringBuilder, Map map, String indent) {
        Set keys = map.keySet();
        for (Object key : keys) {
            stringBuilder.append(indent).append(key).append(" : ").append(map.get(key)).append("\n");
            if (map.get(key) instanceof Map)
                addRequestHeadersToLogStr(stringBuilder, (Map) map.get(key), indent += " "); // Recursive!!
        }
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true; // return true to continue processing
    }

    @Override
    public void close(MessageContext context) {

    }
}
