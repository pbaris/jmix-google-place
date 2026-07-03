package gr.netmechanics.jmix.gplace.rest;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @author Panos Bariamis (pbaris)
 */
@Slf4j
@NullMarked
public class LogRequestInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body,
                                        final ClientHttpRequestExecution execution) throws IOException {

        if (log.isDebugEnabled()) {
            log.debug("{}", request.getURI().toASCIIString());
        }

        return execution.execute(request, body);
    }
}
