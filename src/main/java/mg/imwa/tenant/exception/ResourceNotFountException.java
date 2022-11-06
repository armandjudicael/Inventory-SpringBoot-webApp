package mg.imwa.tenant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFountException extends RuntimeException {
    private final static Long SERIALID_VERSION = 1L;

    public ResourceNotFountException(String message) {
        super(message);
    }
}
