/**
 * Copyright © 2019 admin (admin@infrastructurebuilder.org)
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
package org.infrastructurebuilder.maven;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name = "generate-java-version", defaultPhase = LifecyclePhase.GENERATE_SOURCES, threadSafe = true)
public class GenerateJavaMojo extends AbstractGenerateMojo {

  @Parameter(defaultValue = "${project.build.directory}/generated-sources/generated-version")
  private File outputDirectory;

  @Parameter(defaultValue = "${project.build.directory}/generate-version", required = true, readonly = false)
  private File workDirectory;

  @Override
  public File getOutputDirectory() {
    return outputDirectory;
  }

  @Override
  public Path getWorkDirectory() {
    return workDirectory.toPath().toAbsolutePath();
  }

  public void setOutputDirectory(final File outputDirectory) {
    this.outputDirectory = outputDirectory;
  }

  @Override
  public void setWorkDirectory(final File workDir) {
    workDirectory = Objects.requireNonNull(workDir);
  }

  @Override
  protected void addSourceFolderToProject(final MavenProject mavenProject) {
    mavenProject.addCompileSourceRoot(getOutputDirectory().getAbsolutePath());
  }
}
