package ru.Frozik6k.clients.valid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;
import ru.Frozik6k.clients.dto.ClientRequest;

public class ClientAtLeastOneNotBlankValidator implements ConstraintValidator<AtLeastOneNotBlank, ClientRequest> {
    @Override
    public boolean isValid(ClientRequest clientRequest, ConstraintValidatorContext constraintValidatorContext) {
        if (clientRequest == null) return true;

        return StringUtils.hasText(clientRequest.name()) || StringUtils.hasText(clientRequest.lastName());
    }
}
