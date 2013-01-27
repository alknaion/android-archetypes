/*
 * Copyright 2010 akquinet
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.alknaion.android.archetypes;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.maven.it.VerificationException;
import org.apache.maven.it.Verifier;
import org.junit.Before;
import org.junit.Test;


public class WithTestsArchetypeTest {

    @Before
    public void setUp() {
        Helper.defineArchetypeVersion();
    }

    /**
     * Checks the with-test archetype with no parameter.
     * @throws VerificationException
     * @throws IOException
     */
    @Test
    public void testWithTestDefault() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("with-test-default");

        Verifier verifier  = new Verifier( root.getAbsolutePath(), false );
        verifier.setAutoclean(false);

        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-with-test");
        cli.add("-DarchetypeGroupId=net.alknaion.android.archetypes");
        cli.add("-DarchetypeVersion=" + System.getProperty("archetype.version"));
        cli.add("-DgroupId=" + Constants.TEST_GROUP_ID);
        cli.add("-DartifactId=" + Constants.TEST_ARTIFACT_ID);
        cli.add("-DinteractiveMode=false");
        cli.add("-DarchetypeCatalog=local");
        cli.add("-DarchetypeRepository=local");

        verifier.executeGoal("org.apache.maven.plugins:maven-archetype-plugin:2.0:generate");


        // Check folder create.
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID);
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/AndroidManifest.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it/AndroidManifest.xml");
        verifier.assertFilePresent("android-test/pom.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/pom.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it/pom.xml");

        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/res/values/strings.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/res/layout/main.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/assets");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/src/main/java/android/archetypes/test/HelloAndroidActivity.java");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it/src/main/java/android/archetypes/test/test/HelloAndroidActivityTest.java");


        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "/pom.xml"), "<artifactId>android-maven-plugin</artifactId>");
        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "/pom.xml"), "<platform>16</platform>");
        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "/AndroidManifest.xml"), "<activity android:name=\".HelloAndroidActivity\">");
        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "/AndroidManifest.xml"), "package=\"android.archetypes.test\"");

        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/pom.xml"), "<artifactId>android-maven-plugin</artifactId>");
        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/pom.xml"), "<platform>16</platform>");
        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/AndroidManifest.xml"), "<uses-library android:name=\"android.test.runner\" />");
        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/AndroidManifest.xml"), "<instrumentation android:targetPackage=\"android.archetypes.test\"");

        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/src/main/java/android/archetypes/test/test/HelloAndroidActivityTest.java"), "super(HelloAndroidActivity.class);");



        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "/default.properties"), "target=android-16");
        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/default.properties"), "target=android-16");

    }

    /**
     * Checks the with-test archetype with the <tt>platform</tt> parameter.
     * @throws VerificationException
     * @throws IOException
     */
    @Test
    public void testWithTestWithPlatform() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("with-test-with-platform");

        Verifier verifier  = new Verifier( root.getAbsolutePath(), false );
        verifier.setAutoclean(false);

        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-with-test");
        cli.add("-DarchetypeGroupId=net.alknaion.android.archetypes");
        cli.add("-DarchetypeVersion=" + System.getProperty("archetype.version"));
        cli.add("-DgroupId=" + Constants.TEST_GROUP_ID);
        cli.add("-DartifactId=" + Constants.TEST_ARTIFACT_ID);
        cli.add("-DinteractiveMode=false");
        cli.add("-Dplatform=8");
        cli.add("-DarchetypeCatalog=local");
        cli.add("-DarchetypeRepository=local");

        verifier.executeGoal("org.apache.maven.plugins:maven-archetype-plugin:2.0:generate");


        // Check folder create.
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID);
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/AndroidManifest.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it/AndroidManifest.xml");
        verifier.assertFilePresent("android-test/pom.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/pom.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it/pom.xml");

        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/res/values/strings.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/res/layout/main.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/assets");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/src/main/java/android/archetypes/test/HelloAndroidActivity.java");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it/src/main/java/android/archetypes/test/test/HelloAndroidActivityTest.java");


        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "/pom.xml"), "<artifactId>android-maven-plugin</artifactId>");
        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "/pom.xml"), "<platform>8</platform>");
        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "/AndroidManifest.xml"), "<activity android:name=\".HelloAndroidActivity\">");
        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "/AndroidManifest.xml"), "package=\"android.archetypes.test\"");

        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/pom.xml"), "<artifactId>android-maven-plugin</artifactId>");
        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/pom.xml"), "<platform>8</platform>");
        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/AndroidManifest.xml"), "<uses-library android:name=\"android.test.runner\" />");
        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/AndroidManifest.xml"), "<instrumentation android:targetPackage=\"android.archetypes.test\"");

        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/src/main/java/android/archetypes/test/test/HelloAndroidActivityTest.java"), "super(HelloAndroidActivity.class);");

    }

    /**
     * Checks the with-test archetype with the <tt>platform</tt> and <tt>package</tt> parameters.
     * @throws VerificationException
     * @throws IOException
     */
    @Test
    public void testWithTestWithPlatformAndPackage() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("with-test-with-platform-and-package");

        Verifier verifier  = new Verifier( root.getAbsolutePath(), false );
        verifier.setAutoclean(false);

        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-with-test");
        cli.add("-DarchetypeGroupId=net.alknaion.android.archetypes");
        cli.add("-DarchetypeVersion=" + System.getProperty("archetype.version"));
        cli.add("-DgroupId=" + Constants.TEST_GROUP_ID);
        cli.add("-DartifactId=" + Constants.TEST_ARTIFACT_ID);
        cli.add("-DinteractiveMode=false");
        cli.add("-Dplatform=7");
        cli.add("-Dpackage=foo");
        cli.add("-DarchetypeCatalog=local");
        cli.add("-DarchetypeRepository=local");

        verifier.executeGoal("org.apache.maven.plugins:maven-archetype-plugin:2.0:generate");


        // Check folder create.
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID);
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/AndroidManifest.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it/AndroidManifest.xml");
        verifier.assertFilePresent("android-test/pom.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/pom.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it/pom.xml");

        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/res/values/strings.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/res/layout/main.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/assets");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/src/main/java/foo/HelloAndroidActivity.java");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it/src/main/java/foo/test/HelloAndroidActivityTest.java");

        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/pom.xml"), "2.1.2");

        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/" + Constants.TEST_ARTIFACT_ID + "/pom.xml"), "<artifactId>android-maven-plugin</artifactId>");
        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/" + Constants.TEST_ARTIFACT_ID + "/pom.xml"), "<platform>7</platform>");
        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/" + Constants.TEST_ARTIFACT_ID + "/AndroidManifest.xml"), "<activity android:name=\".HelloAndroidActivity\">");
        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/" + Constants.TEST_ARTIFACT_ID + "/AndroidManifest.xml"), "package=\"foo\"");

        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/pom.xml"), "<artifactId>android-maven-plugin</artifactId>");
        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/pom.xml"), "<platform>7</platform>");
        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/AndroidManifest.xml"), "<uses-library android:name=\"android.test.runner\" />");
        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/AndroidManifest.xml"), "<instrumentation android:targetPackage=\"foo\"");

        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/" + Constants.TEST_ARTIFACT_ID + "/default.properties"), "target=android-7");
        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/default.properties"), "target=android-7");

        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/src/main/java/foo/test/HelloAndroidActivityTest.java"), "super(\"foo\", HelloAndroidActivity.class);");
    }


    /**
     * Checks the with-test archetype with the emulator parameter.
     * @throws VerificationException
     * @throws IOException
     */
    @Test
    public void testWithTestWithEmulator() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("with-test-default");

        Verifier verifier  = new Verifier( root.getAbsolutePath(), false );
        verifier.setAutoclean(false);

        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-with-test");
        cli.add("-DarchetypeGroupId=net.alknaion.android.archetypes");
        cli.add("-DarchetypeVersion=" + System.getProperty("archetype.version"));
        cli.add("-DgroupId=" + Constants.TEST_GROUP_ID);
        cli.add("-DartifactId=" + Constants.TEST_ARTIFACT_ID);
        cli.add("-DinteractiveMode=false");
        cli.add("-DarchetypeCatalog=local");
        cli.add("-DarchetypeRepository=local");
        cli.add("-Demulator=test");

        verifier.executeGoal("org.apache.maven.plugins:maven-archetype-plugin:2.0:generate");


        // Check folder create.
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID);
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/AndroidManifest.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it/AndroidManifest.xml");
        verifier.assertFilePresent("android-test/pom.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/pom.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it/pom.xml");

        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/res/values/strings.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/res/layout/main.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/assets");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/src/main/java/android/archetypes/test/HelloAndroidActivity.java");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it/src/main/java/android/archetypes/test/test/HelloAndroidActivityTest.java");


        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "/pom.xml"), "<artifactId>android-maven-plugin</artifactId>");
        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "/pom.xml"), "<platform>16</platform>");
        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "/AndroidManifest.xml"), "<activity android:name=\".HelloAndroidActivity\">");
        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "/AndroidManifest.xml"), "package=\"android.archetypes.test\"");

        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/pom.xml"), "<artifactId>android-maven-plugin</artifactId>");
        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/pom.xml"), "<platform>16</platform>");
        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/AndroidManifest.xml"), "<uses-library android:name=\"android.test.runner\" />");
        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/AndroidManifest.xml"), "<instrumentation android:targetPackage=\"android.archetypes.test\"");

        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/src/main/java/android/archetypes/test/test/HelloAndroidActivityTest.java"), "super(HelloAndroidActivity.class);");

        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "/default.properties"), "target=android-16");
        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/default.properties"), "target=android-16");

        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/pom.xml"), "<avd>test</avd>");

    }

    /**
     * Checks the with-test archetype with the <tt>platform</tt> parameter bellow 7 for correct constructor.
     * @throws VerificationException
     * @throws IOException
     */
    @Test
    public void testWithTestConstructorCorrectness() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("with-test-with-platform");

        Verifier verifier  = new Verifier( root.getAbsolutePath(), false );
        verifier.setAutoclean(false);

        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-with-test");
        cli.add("-DarchetypeGroupId=net.alknaion.android.archetypes");
        cli.add("-DarchetypeVersion=" + System.getProperty("archetype.version"));
        cli.add("-DgroupId=" + Constants.TEST_GROUP_ID);
        cli.add("-DartifactId=" + Constants.TEST_ARTIFACT_ID);
        cli.add("-DinteractiveMode=false");
        cli.add("-Dplatform=7");
        cli.add("-DarchetypeCatalog=local");
        cli.add("-DarchetypeRepository=local");

        verifier.executeGoal("org.apache.maven.plugins:maven-archetype-plugin:2.0:generate");


        // Check folder create.
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID);
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/AndroidManifest.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it/AndroidManifest.xml");
        verifier.assertFilePresent("android-test/pom.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/pom.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it/pom.xml");

        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/res/values/strings.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/res/layout/main.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/assets");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "/src/main/java/android/archetypes/test/HelloAndroidActivity.java");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it/src/main/java/android/archetypes/test/test/HelloAndroidActivityTest.java");


        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "/pom.xml"), "<artifactId>android-maven-plugin</artifactId>");
        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "/pom.xml"), "<platform>7</platform>");
        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "/AndroidManifest.xml"), "<activity android:name=\".HelloAndroidActivity\">");
        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "/AndroidManifest.xml"), "package=\"android.archetypes.test\"");

        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/pom.xml"), "<artifactId>android-maven-plugin</artifactId>");
        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/pom.xml"), "<platform>7</platform>");
        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/AndroidManifest.xml"), "<uses-library android:name=\"android.test.runner\" />");
        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/AndroidManifest.xml"), "<instrumentation android:targetPackage=\"android.archetypes.test\"");

        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/src/main/java/android/archetypes/test/test/HelloAndroidActivityTest.java"), "super(\"android.archetypes.test\", HelloAndroidActivity.class);");

    }
}
