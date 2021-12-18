package domain.interactor;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseUseCase<T, P> {

    private final Logger logger;

    public BaseUseCase(Logger logger) {
        this.logger = logger;
    }

    public abstract T run(P params);

    public T run() {
        return run(null);
    }

    protected void logInfo(String p, String t) {
        logger.log(Level.INFO, logger.getName() + " P: " + p + ", T: " + t);
    }

    protected void logError(Throwable throwable) {
        logger.log(Level.WARNING, throwable.getMessage(), throwable);
    }
}
