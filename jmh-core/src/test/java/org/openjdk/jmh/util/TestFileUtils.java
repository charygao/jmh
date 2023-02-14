
package org.openjdk.jmh.util;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * Test for FileUtils
 */
public class TestFileUtils {

    @Test
    public void testExtractFromResource() throws Exception {
        File test = FileUtils.extractFromResource("/org/openjdk/jmh/results/format/output-golden.json");
        test.deleteOnExit();
        assertTrue(test.exists());
    }
}
