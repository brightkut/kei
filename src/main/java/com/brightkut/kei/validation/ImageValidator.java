package com.brightkut.kei.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class ImageValidator implements ConstraintValidator<ValidImage, MultipartFile> {

    @Override
    public void initialize(ValidImage constraintAnnotation) {
        // No initialization required in this case
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        // If the file is empty, consider it invalid
        if (multipartFile == null || multipartFile.isEmpty()) {
            return false;
        }

        String contentType = multipartFile.getContentType();
        return isSupportedContentType(contentType);
    }

    private boolean isSupportedContentType(String contentType) {
        // Validate that the content type is one of the allowed image types
        return contentType != null && (contentType.equals("image/jpeg") ||
                contentType.equals("image/png") ||
                contentType.equals("image/gif") ||
                contentType.equals("image/bmp") ||
                contentType.equals("image/webp"));
    }
}
