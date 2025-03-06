package org.learning.lld.revision_practice.logger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

enum LogLevel {
    DEBUG, INFO, WARNING, ERROR, FATAL
}

@Getter
@ToString
class LogMessage {
    private final LogLevel level;
    private final String message;
    private final long timestamp;

    public LogMessage(LogLevel level, String message) {
        this.level = level;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }
}

interface LogAppender {
    void append(LogMessage message);
}
class ConsoleLogAppender implements LogAppender {

    @Override
    public void append(LogMessage message) {
        System.out.println(message);
    }
}
@AllArgsConstructor
class DatabaseLogAppender implements LogAppender {
    private final String jdbcUrl;
    private final String username;
    private final String password;

    @Override
    public void append(LogMessage message) {
        System.out.printf("logging to database: %s|%s:%s\n", jdbcUrl, username, password);
        System.out.println(message);
    }
}

@Getter
@Setter
@AllArgsConstructor
class LoggerConfig {
    private LogLevel logLevel;
    private LogAppender logAppender;

}

class Logger {
    private static final Logger logger = new Logger();
    private LoggerConfig config;

    private Logger() {
        config = new LoggerConfig(LogLevel.INFO, new ConsoleLogAppender());
    }

    public static Logger getInstance() { return logger; }
    public void setConfig(LoggerConfig config) {
        this.config = config;
    }

    public void log(LogLevel level, String message) {
        if (level.ordinal() >= config.getLogLevel().ordinal()) {
            LogMessage logMessage = new LogMessage(level, message);
            config.getLogAppender().append(logMessage);
        }
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void fatal(String message) {
        log(LogLevel.FATAL, message);
    }
}

public class Runner {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        logger.info("jel");

        logger.error("hello");
    }
}
