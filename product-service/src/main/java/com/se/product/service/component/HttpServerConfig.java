package com.se.product.service.component;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Scanner;


/**
 * To log request and response headers and body you can register your log filter
 */
@Configuration
public class HttpServerConfig {


    private final static Logger log = LoggerFactory.getLogger(HttpServerConfig.class.getName());

    @Bean
    public FilterRegistrationBean logFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new Filter() {
            @Override
            public void init(FilterConfig fc) throws ServletException {
                log.info("Init LOG Request Filter");
            }

            private void logRequest(HttpServletRequest httpReq) throws IOException {
                // log request headers
                log.debug("### Request Headers:");
                for (String header : Collections.list(httpReq.getHeaderNames())) {
                    log.debug("\t* {}: {1}", header, httpReq.getHeader(header));
                }
                // log request body
                Scanner qs = new Scanner(httpReq.getInputStream()).useDelimiter("\\A");
                String qb = qs.hasNext() ? qs.next() : "[empty body]";

                log.info("### Request body: `{}` ###", qb);
            }

            private void logResponse(HttpServletResponse httpResp, ByteArrayOutputStream baos) {
                // log response headers
                log.debug("### Response [{0}] Headers:", httpResp.getStatus());
                for (String header : httpResp.getHeaderNames()) {
                    log.debug("\t* {0}: {1}", header, httpResp.getHeader(header));
                }
                // log response body
                log.debug("### Response body: `{}` ###", baos.toString());
            }

            @Override
            public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
                logRequest((HttpServletRequest) req);

                HttpServletResponse httpResp = (HttpServletResponse) resp;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final PrintStream ps = new PrintStream(baos);

                chain.doFilter(req, resp);
//                chain.doFilter(req, new HttpServletResponseWrapper(httpResp) {
//                    @Override
//                    public ServletOutputStream getOutputStream() throws IOException {
//                        return new DelegatingServletOutputStream(new TeeOutputStream(super.getOutputStream(), ps));
//                    }
//
//                    @Override
//                    public PrintWriter getWriter() throws IOException {
//                        return nnew PrintWriter(new DelegatingServletOutputStream(new TeeOutputStream(super.getOutputStream(), ps)));
//                    }
//                });
                logResponse(httpResp, baos);

            }

            @Override
            public void destroy() {
                log.debug("Destroy LOG Request Filter");
            }
        });

        return registration;
    }
}