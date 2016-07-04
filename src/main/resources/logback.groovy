// We highly recommended that you always add a status listener just
// after the last import statement and before all other statements
statusListener(OnConsoleStatusListener)

scan()

appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d [%8.8thread] [%-5level] [%logger{0}] %msg%n"
    }
}
logger("org.aop", DEBUG)

root(INFO, ["STDOUT"])
