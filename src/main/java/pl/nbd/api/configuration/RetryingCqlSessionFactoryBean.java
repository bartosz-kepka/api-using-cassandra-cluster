package pl.nbd.api.configuration;

import lombok.SneakyThrows;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;

import java.text.MessageFormat;

public class RetryingCqlSessionFactoryBean extends CqlSessionFactoryBean {

    @SneakyThrows
    @Override
    public void afterPropertiesSet() {
        connect();
    }

    protected void connect() throws InterruptedException {
        int maxAttempts = 10;
        long attemptTimeout = 10000;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                super.afterPropertiesSet();
                return;
            } catch (Exception e) {
                logger.error(e.getMessage());

                if (attempt == 5) {
                    logger.error("Could not connect to cassandra. Closing application");
                    return;
                }

                logger.error(MessageFormat
                        .format("Try {0} of {1} failed. Retrying in {2} milliseconds",
                                attempt, maxAttempts, attemptTimeout));
                Thread.sleep(attemptTimeout);
            }
        }
    }
}
