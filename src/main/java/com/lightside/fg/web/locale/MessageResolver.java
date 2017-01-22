package com.lightside.fg.web.locale;

import java.util.Locale;

/**
 * @author Anwar
 */
public interface MessageResolver {

    String resolveMessage(final String messageKey);

    String resolveMessage(final String messageKey, final Locale locale);

    String resolveMessage(final String messageKey, Object[] args);

    String resolveMessage(final String messageKey, Object[] args, final Locale locale);

    String resolveMessage(final String messageKey, final String defaultMessage);

    String resolveMessage(final String messageKey, final String defaultMessage, final Locale locale);

    String resolveMessage(final String messageKey, Object[] args, final String defaultMessage, final Locale locale);

}
