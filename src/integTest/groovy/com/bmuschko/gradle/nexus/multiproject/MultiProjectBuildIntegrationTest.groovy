/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bmuschko.gradle.nexus.multiproject

import com.bmuschko.gradle.nexus.AbstractIntegrationTest

/**
 * Multi-project integration test.
 *
 * @author Benjamin Muschko
 */
abstract class MultiProjectBuildIntegrationTest extends AbstractIntegrationTest {
    File settingsFile
    List<String> subprojects = ['subproject1', 'subproject2', 'subproject3']

    def setup() {
        settingsFile = createNewFile(integTestDir, 'settings.gradle')

        buildFile << """
subprojects {
    apply plugin: 'java'
    apply plugin: com.bmuschko.gradle.nexus.NexusPlugin
}
"""
        subprojects.each { subproject ->
            settingsFile << "include '$subproject'\n"
            createNewDir(integTestDir, subproject)
        }
    }
}
