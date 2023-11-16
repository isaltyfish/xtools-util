package net.verytools.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class IoUtil {

    /**
     * Read url as bytes.
     *
     * @param url     the url to read
     * @param timeout timeout in seconds
     * @return the bytes of the url resource
     */
    public byte[] readUrlAsBytes(String url, int timeout) throws IOException {
        URL urlObj = new URL(url);
        URLConnection conn = urlObj.openConnection();
        conn.setReadTimeout(timeout);
        try (InputStream in = new BufferedInputStream(conn.getInputStream());
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            return out.toByteArray();
        }
    }

    /**
     * Read url and save content to a file.
     *
     * @param url     the url to read
     * @param timeout timeout in seconds
     */
    public void readUrlToFile(String url, int timeout, String destPath) throws IOException {
        File destFile = new File(destPath);
        URL urlObj = new URL(url);
        URLConnection conn = urlObj.openConnection();
        conn.setReadTimeout(timeout);
        try (InputStream in = new BufferedInputStream(conn.getInputStream());
             OutputStream out = new BufferedOutputStream(new FileOutputStream(destFile))) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }

}
