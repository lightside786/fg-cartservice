package com.lightside.fg.web.locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author Anwar
 */

@Component
public class MessageResolverImpl implements MessageResolver {

    private MessageSource messageSource;

    public MessageResolverImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String resolveMessage(String messageKey) {
        return resolveMessage(messageKey, Locale.getDefault());
    }

    @Override
    public String resolveMessage(String messageKey, Locale locale) {
        return messageSource.getMessage(messageKey, null, locale);
    }

    @Override
    public String resolveMessage(String messageKey, Object[] args) {
        return resolveMessage(messageKey, args, Locale.getDefault());
    }

    @Override
    public String resolveMessage(String messageKey, Object[] args, Locale locale) {
        return messageSource.getMessage(messageKey, args, locale);
    }

    @Override
    public String resolveMessage(String messageKey, String defaultMessage) {
        return resolveMessage(messageKey, defaultMessage, Locale.getDefault());
    }

    @Override
    public String resolveMessage(String messageKey, String defaultMessage, Locale locale) {
        return resolveMessage(messageKey, null, defaultMessage, locale);
    }

    @Override
    public String resolveMessage(String messageKey, Object[] args, String defaultMessage, Locale locale) {
        return messageSource.getMessage(messageKey, args, defaultMessage, locale);
    }
}
