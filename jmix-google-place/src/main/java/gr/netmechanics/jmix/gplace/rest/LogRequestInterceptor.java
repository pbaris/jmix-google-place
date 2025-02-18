package gr.netmechanics.jmix.gplace.rest;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;

/**
 * @author Panos Bariamis (pbaris)
 */
@Slf4j
public class LogRequestInterceptor implements ClientHttpRequestInterceptor {
    @NonNull
    @Override
    public ClientHttpResponse intercept(@NonNull final HttpRequest request, @NonNull final byte[] body,
                                        @NonNull final ClientHttpRequestExecution execution) throws IOException {

        if (log.isDebugEnabled()) {
            log.debug("{}", request.getURI().toASCIIString());
        }
        return execution.execute(request, body);
    }
}
