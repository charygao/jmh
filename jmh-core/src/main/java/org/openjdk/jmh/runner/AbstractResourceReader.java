
package org.openjdk.jmh.runner;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

class AbstractResourceReader {

    private final String file;
    private final String resource;
    private final String strings;

    protected AbstractResourceReader(String file, String resource, String strings) {
        this.file = file;
        this.resource = resource;
        this.strings = strings;
    }

    /**
     * Helper method for creating a Reader for the list file.
     *
     * @return a correct Reader instance
     */
    protected List<Reader> getReaders() {
        if (strings != null) {
            return Collections.<Reader>singletonList(new StringReader(strings));
        }

        if (file != null) {
            try {
                return Collections.<Reader>singletonList(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            } catch (FileNotFoundException e) {
                throw new RuntimeException("ERROR: Could not find resource", e);
            }
        }

        if (resource != null) {
            Enumeration<URL> urls;
            try {
                urls = getClass().getClassLoader().getResources(
                        resource.startsWith("/")
                                ? resource.substring(1)
                                : resource
                );
            } catch (IOException e) {
                throw new RuntimeException("ERROR: While obtaining resource: " + resource, e);
            }

            if (urls.hasMoreElements()) {
                List<Reader> readers = new ArrayList<>();
                URL url = null;
                try {
                    while (urls.hasMoreElements()) {
                        url = urls.nextElement();
                        InputStream stream = url.openStream();
                        readers.add(new InputStreamReader(stream, StandardCharsets.UTF_8));
                    }
                } catch (IOException e) {
                    for (Reader r : readers) {
                        try {
                            r.close();
                        } catch (IOException e1) {
                            // ignore
                        }
                    }
                    throw new RuntimeException("ERROR: While opening resource: " + url, e);
                }
                return readers;
            } else {
                throw new RuntimeException("ERROR: Unable to find the resource: " + resource);
            }
        }

        throw new IllegalStateException();
    }


}
