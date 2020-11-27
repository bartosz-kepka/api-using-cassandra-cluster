package pl.nbd.api.configuration;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;

import java.text.MessageFormat;

public class RetryingCqlSessionFactoryBean extends CqlSessionFactoryBean {

    @Value("${cassandra.max-reconnection-attempts}")
    int maxAttempts = 10;

    @Value("${cassandra.reconnection-timeout-milliseconds}")
    long attemptTimeout = 5000;

    @SneakyThrows
    @Override
    public void afterPropertiesSet() {
        connect();
    }

    protected void connect() throws InterruptedException {
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
