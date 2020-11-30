package pl.nbd.api.configuration;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;

import java.text.MessageFormat;

public class RetryingCqlSessionFactoryBean extends CqlSessionFactoryBean {

    @Value("${cassandra.connect.attempts:10}")
    private int attempts;

    @Value("${cassandra.connect.attempt-timeout-ms:5000}")
    private long attemptTimeout;

    @SneakyThrows
    @Override
    public void afterPropertiesSet() {
        connect();
    }

    private void connect() throws InterruptedException {
        for (int attempt = 1; attempt <= attempts; attempt++) {
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
                                attempt, attempts, attemptTimeout));
                Thread.sleep(attemptTimeout);
            }
        }
    }
}
