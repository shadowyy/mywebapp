package com.shadow.util.rest;

import com.shadow.util.json.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

/**
 * @author shadowyy
 * @version 2017/9/8 10:05
 */
public class RestClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);
    private static final RestTemplate REST_TEMPLATE;
    private static final HttpHeaders HTTP_HEADERS;


    static {
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(6000);
        simpleClientHttpRequestFactory.setReadTimeout(6000);
        REST_TEMPLATE = new RestTemplate(simpleClientHttpRequestFactory);

        HTTP_HEADERS = new HttpHeaders();
        HTTP_HEADERS.setContentType(APPLICATION_JSON_UTF8);
    }

    private static <T> T exchange(String url, HttpMethod httpMethod, Object params, ParameterizedTypeReference<T> responseType) {
        LOGGER.info("RestClient return:{}", url, httpMethod, params, responseType);
        T t = REST_TEMPLATE.exchange(url, httpMethod, new HttpEntity<String>(GsonUtil.toJson(params), HTTP_HEADERS), responseType).getBody();
        LOGGER.info("RestClient return:{}", t);
        return t;
    }

    public static <T> T get(String url, Object params, ParameterizedTypeReference<T> responseType) {
        return exchange(url, GET, params, responseType);
    }

    public static <T> T post(String url, Object params, ParameterizedTypeReference<T> responseType) {
        return exchange(url, POST, params, responseType);
    }

    public static <T> T put(String url, Object params, ParameterizedTypeReference<T> responseType) {
        return exchange(url, PUT, params, responseType);
    }

    public static <T> T delete(String url, Object params, ParameterizedTypeReference<T> responseType) {
        return exchange(url, DELETE, params, responseType);
    }

}
