/*
 * Copyright 2009 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openehealth.ipf.commons.ihe.ws.server;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author Jens Riemschneider
 */
public class TestServers {
    /**
     * Tests the embedded Tomcat server
     * @throws Exception
     *          for anything problematic.
     */
    @Test
    public void testTomcat() throws Exception {
        checkServer(new TomcatServer(), 9090);
    }

    /**
     * Tests the embedded Jetty server
     * @throws Exception
     *          for anything problematic.
     */
    @Test
    public void testJetty() throws Exception {
        checkServer(new JettyServer(), 9091);
    }

    private void checkServer(ServletServer server, int port) throws Exception {
        ClassPathResource contextResource = new ClassPathResource("test.xml");

        server.setServlet(new Servlet());
        server.setPort(port);
        server.setContextPath("/testContext");
        server.setServletPath("/testServlet/*");
        server.setContextResource(contextResource.getURI().toString());
        server.start();
        checkPostRequest(port);
        server.stop();
    }

    private void checkPostRequest(int port) throws Exception {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod("http://localhost:" + port + "/testContext/testServlet/bla");
        RequestEntity requestEntity = new StringRequestEntity("hello world", "text/plain", null);
        method.setRequestEntity(requestEntity);
        try {
            assertEquals(200, client.executeMethod(method));
            assertEquals("hello world", method.getResponseBodyAsString());
        }
        finally {
            method.releaseConnection();
        }
    }

    /** Simple test servlet class */
    public static class Servlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

        @Override
        protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            IOUtils.copy(request.getInputStream(), response.getOutputStream());
        }
    }
}
