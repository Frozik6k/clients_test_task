package ru.Frozik6k.clients.valid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import ru.Frozik6k.clients.dto.ContactDto;

@Slf4j
public class ContactAtLeastOneNotBlankValidator implements ConstraintValidator<AtLeastOneNotBlank, ContactDto> {
    @Override
    public boolean isValid(ContactDto contactDto, ConstraintValidatorContext constraintValidatorContext) {
        log.info("email='{}', phone='{}'", contactDto.email(), contactDto.phone());
        if (contactDto == null) return true;

        return StringUtils.hasText(contactDto.email()) || StringUtils.hasText(contactDto.phone());
    }
}
