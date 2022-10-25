package com.nhnacademy.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDownloadServlet extends HttpServlet {

    private static String dirPath = "D:\\download";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String fileName = req.getParameter("fileName");

        Path path = Paths.get(dirPath, fileName);
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; fileName=" + fileName);

        try (InputStream in = Files.newInputStream(path)) {
            in.readAllBytes();
        }
    }
}
