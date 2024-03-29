package com.example.businessinstance.compression;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.DeflaterInputStream;
import java.util.zip.GZIPInputStream;

/**
 * @author Hodur
 * @date 2021/04/30
 */
@Component
@Slf4j
public class ContentEncodingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,@NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) throws ServletException, IOException {

        String contentEncoding = request.getHeader("Content-Encoding");
        log.trace("Content-Encoding: {}", contentEncoding);
        if (("gzip".equalsIgnoreCase(contentEncoding) || "deflate".equalsIgnoreCase(contentEncoding))) {
            chain.doFilter(new GzipRequestWrapper(request), response);
            return;
        }

        chain.doFilter(request, response);
    }

    @Slf4j
    static class GzipRequestWrapper extends HttpServletRequestWrapper {

        protected final HttpServletRequest request;

        public GzipRequestWrapper(HttpServletRequest request) {
            super(request);
            this.request = request;
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            ServletInputStream sis = request.getInputStream();
            InputStream is;
            String contentEncoding = request.getHeader("Content-Encoding");
            if ("gzip".equalsIgnoreCase(contentEncoding)) {
                is = new GZIPInputStream(sis);
            } else if ("deflate".equalsIgnoreCase(contentEncoding)) {
                is = new DeflaterInputStream(sis);
            } else {
                throw new UnsupportedEncodingException(contentEncoding + " is not supported.");
            }
            final InputStream compressInputStream = is;
            return new ServletInputStream() {
                ReadListener readListener;

                @Override
                public int read() throws IOException {
                    int b = compressInputStream.read();
                    if (b == -1 && readListener != null) {
                        readListener.onAllDataRead();
                    }
                    return b;
                }

                @Override
                public boolean isFinished() {
                    try {
                        return compressInputStream.available() == 0;
                    } catch (IOException e) {
                        log.error("error", e);
                        if (readListener != null) {
                            readListener.onError(e);
                        }
                        return false;
                    }
                }

                @Override
                public boolean isReady() {
                    try {
                        return compressInputStream.available() > 0;
                    } catch (IOException e) {
                        log.error("error", e);
                        if (readListener != null) {
                            readListener.onError(e);
                        }
                        return false;
                    }
                }

                @Override
                public void setReadListener(final ReadListener readListener) {
                    this.readListener = readListener;
                    sis.setReadListener(new ReadListener() {
                        @Override
                        public void onDataAvailable() throws IOException {
                            log.trace("onDataAvailable");
                            if (readListener != null) {
                                readListener.onDataAvailable();
                            }
                        }

                        @Override
                        public void onAllDataRead() {
                            log.trace("onAllDataRead");
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            log.error("onError", throwable);
                            if (readListener != null) {
                                readListener.onError(throwable);
                            }
                        }
                    });
                }
            };
        }
    }
}

