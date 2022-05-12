package com.wellsfargo.name.pronunciation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NamePronunciationException extends Exception {

    private String status;
    private String errorType;
    private String message;
    // Supplier<NamePronunciation> namePronunciationExceptionSupplier(final String status, final String errorType, final String message) = () -> new NamePronunciationException(s);

}
