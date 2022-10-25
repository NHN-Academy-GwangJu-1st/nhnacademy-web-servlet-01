package com.nhnacademy.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class WebApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        String counterFileName = servletContext.getInitParameter("counterFileName");
        /* packing 시에 아래 경로 밑에 resources 파일들이 생성된다. */
        String counterFilePath = "/WEB-INF/classes/" + counterFileName;

        int counter = 0;
        try (DataInputStream dis = new DataInputStream(servletContext.getResourceAsStream(counterFilePath))) {
            counter = dis.readInt();
        } catch (Exception e) {
            log.error("", e);
        }

        servletContext.setAttribute("counter", counter);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        ServletContext servletContext = sce.getServletContext();

        String counterFileName = servletContext.getInitParameter("counterFileName");
        String counterFilePath = "/WEB-INF/classes/" + counterFileName;


        int counter = (int) servletContext.getAttribute("counter");

        try (OutputStream os = Files.newOutputStream(Paths.get(servletContext.getResource(counterFilePath).toURI()))) {
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeInt(counter);
        } catch (IOException | URISyntaxException e) {
            log.error("" + e);
        }

//        File file = new File(servletContext.getResource(counterFilePath).toURI());
//        FileOutputStream fos = new FileOutputStream(file);
//        DataOutputStream dos = new DataOutputStream(fos);
//        dos.write(counter);

    }
}
