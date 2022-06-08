package com.capstone.bhs.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpHeaders;

public final class HeaderUtil {

    private HeaderUtil() {
    }

    /**
     * <p>createAlert.</p>
     *
     * @param applicationName a {@link java.lang.String} object.
     * @param message a {@link java.lang.String} object.
     * @param param a {@link java.lang.String} object.
     * @return a {@link org.springframework.http.HttpHeaders} object.
     */
    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-bhs-alert", message);
        try {
            headers.add("bhs-params", URLEncoder.encode(param, StandardCharsets.UTF_8.toString()));
        } catch (UnsupportedEncodingException e) {
            // StandardCharsets are supported by every Java implementation so this exception will never happen
        }
        return headers;
    }

    /**
     * <p>createEntityCreationAlert.</p>
     *
     * @param applicationName a {@link java.lang.String} object.
     * @param enableTranslation a boolean.
     * @param entityName a {@link java.lang.String} object.
     * @param param a {@link java.lang.String} object.
     * @return a {@link org.springframework.http.HttpHeaders} object.
     */
    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        String message = "A new " + entityName + " is created with identifier " + param;
        return createAlert(message, param);
    }

    /**
     * <p>createEntityUpdateAlert.</p>
     *
     * @param applicationName a {@link java.lang.String} object.
     * @param enableTranslation a boolean.
     * @param entityName a {@link java.lang.String} object.
     * @param param a {@link java.lang.String} object.
     * @return a {@link org.springframework.http.HttpHeaders} object.
     */
    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        String message = "A " + entityName + " is updated with identifier " + param;
        return createAlert(message, param);
    }

    /**
     * <p>createEntityDeletionAlert.</p>
     *
     * @param applicationName a {@link java.lang.String} object.
     * @param enableTranslation a boolean.
     * @param entityName a {@link java.lang.String} object.
     * @param param a {@link java.lang.String} object.
     * @return a {@link org.springframework.http.HttpHeaders} object.
     */
    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        String message = "A " + entityName + " is deleted with identifier " + param;
        return createAlert(message, param);
    }

    /**
     * <p>createFailureAlert.</p>
     *
     * @param applicationName a {@link java.lang.String} object.
     * @param enableTranslation a boolean.
     * @param entityName a {@link java.lang.String} object.
     * @param errorKey a {@link java.lang.String} object.
     * @param defaultMessage a {@link java.lang.String} object.
     * @return a {@link org.springframework.http.HttpHeaders} object.
     */
    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        String message = defaultMessage;

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-bhs-error", message);
        headers.add("X-bhs-params", entityName);
        return headers;
    }
}