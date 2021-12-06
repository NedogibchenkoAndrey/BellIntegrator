package ru.bellintegrator.task.response.handler;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.bellintegrator.task.response.view.DataView;
import ru.bellintegrator.task.response.view.ResultView;

@RestControllerAdvice
public class ResponseWrapperAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if (body == null) {
            return new DataView(new ResultView("success"));
        } else if (body instanceof ResultView) {
            return body;
        } else if (request.getURI().getPath().startsWith("/api/")) {
            return new DataView(body);
        } else {
            return body;
        }
    }
}
