package com.brightkut.kei.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

    @Override
    public void initialize(ValidFile constraintAnnotation) {
        // No initialization required in this case
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {

        boolean result = true;

        String contentType = multipartFile.getContentType();
        if (!StringUtils.hasText(contentType) ||  !isSupportedContentType(contentType) ) {
            result = false;
        }

        return result;
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType.equals("application/pdf")
                || contentType.equals("text/csv")
                || contentType.equals("text/plain");
    }
}
