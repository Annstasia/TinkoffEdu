package edu.project3;

import java.time.LocalDateTime;

public record LogRecord(String remoteAddr, String remoteUser,
                        LocalDateTime dateTime, String command, String resource,
                        String protocol,
                        int status, long bodyBytesSent,
                        String httpReferer, String userAgent) {
}
