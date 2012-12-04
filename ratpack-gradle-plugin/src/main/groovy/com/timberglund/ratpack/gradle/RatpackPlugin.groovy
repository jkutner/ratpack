/*
 * Copyright 2012 Tim Berglund
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Tim Berglund
 * http://timberglund.com
 *
 */

package com.timberglund.ratpack.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.GroovyPlugin
import org.gradle.api.tasks.JavaExec
import org.gradle.api.plugins.ApplicationPlugin

class RatpackPlugin implements Plugin<Project> {

  void apply(Project project) {
    def meta = RatpackPluginMeta.fromResource(getClass().classLoader)

    project.plugins.apply(GroovyPlugin)
    project.plugins.apply(ApplicationPlugin)

    project.mainClassName = 'com.bleedingwolf.ratpack.RatpackMain'

    project.repositories {
      mavenCentral()
    }

    project.dependencies {
      runtime 'org.slf4j:slf4j-simple:1.6.3'
      compile "com.augusttechgroup:ratpack-core:${meta.ratpackVersion}"
    }

    project.run {
      main = 'com.bleedingwolf.ratpack.RatpackMain'
      workingDir = project.file("src/ratpack")
    }

    project.installApp {
      from "src/ratpack"
    }
  }

}

