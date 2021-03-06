package com.tngtech.archunit.maventest;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption.DontIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchRules;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.tngtech.archunit.maventest", importOptions = DontIncludeTests.class)
public class ArchUnitSmokeTest {
    @ArchTest
    public static void runs_without_exception(JavaClasses classes) {
        int count = 0;
        for (JavaClass javaClass : classes) {
            count++;
        }
        assertEquals("Expected 2 classes", 2, count);

        assertEquals("Number of fields in ClassOne", classes.get(ClassOne.class).getFields().size(), 1);
        assertEquals("Number of methods in ClassOne", classes.get(ClassOne.class).getMethods().size(), 0);
        assertEquals("Number of fields in ClassTwo", classes.get(ClassTwo.class).getFields().size(), 0);
        assertEquals("Number of methods in ClassTwo", classes.get(ClassTwo.class).getMethods().size(), 1);
    }

    @ArchTest
    public static final ArchRules hierarchical_rules_to_be_checked_for_evaluation_in_second_surefire_run =
            ArchRules.in(ArchLibrary.class);
}
