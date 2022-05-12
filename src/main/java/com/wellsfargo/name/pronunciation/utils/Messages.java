package com.wellsfargo.name.pronunciation.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
public class Messages {

    @Autowired
    MessageSource messageSource;

    private MessageSourceAccessor messageSourceAccessor;

    @PostConstruct
    private void init() {
        messageSourceAccessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
    }

    public String get(String code) {
        return messageSourceAccessor.getMessage(code);
    }
}
