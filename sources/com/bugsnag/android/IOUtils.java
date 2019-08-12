package com.bugsnag.android;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URLConnection;

class IOUtils {
    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    public static void close(URLConnection conn) {
        if (conn instanceof HttpURLConnection) {
            ((HttpURLConnection) conn).disconnect();
        }
    }

    public static int copy(Reader input, Writer output) throws IOException {
        char[] buffer = new char[4096];
        long count = 0;
        while (true) {
            int n = input.read(buffer);
            if (-1 == n) {
                break;
            }
            output.write(buffer, 0, n);
            count += (long) n;
        }
        if (count > 2147483647L) {
            return -1;
        }
        return (int) count;
    }
}
