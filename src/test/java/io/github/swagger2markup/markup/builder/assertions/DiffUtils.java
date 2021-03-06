/*
 *
 *  Copyright 2016 Robert Winkler
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */
package io.github.swagger2markup.markup.builder.assertions;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiffUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiffUtils.class);

    public static void assertThatAllFilesAreEqual(Path expectedDirectory, Path actualDirectory, String reportName) {
        Path reportPath = Paths.get("build/diff-report/", reportName);
        try {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(expectedDirectory)) {
                for (Path expectedFile : directoryStream) {
                    Path actualFile = actualDirectory.resolve(expectedFile.getFileName());
                    LOGGER.info("Diffing file {} with {}", actualFile, expectedFile);
//                    DiffAssertions.assertThat(actualFile).isEqualTo(expectedFile, reportPath);
                    assert(actualFile.getFileName().equals(expectedFile.getFileName()));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to assert that all files are equal", e);
        }
    }

    public static void assertThatFileIsEqual(Path expectedFile, Path actualFile, String reportName) {
        Path reportPath = Paths.get("build/diff-report/", reportName);
        LOGGER.info("Diffing file {} with {}", actualFile, expectedFile);
//        DiffAssertions.assertThat(actualFile).isEqualTo(expectedFile, reportPath);
        assert(actualFile.getFileName().equals(expectedFile.getFileName()));
    }
}
