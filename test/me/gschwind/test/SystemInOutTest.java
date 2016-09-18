/*
 * MIT License
 *
 * Copyright (c) 2016 Maurice Gschwind
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.gschwind.test;

import org.junit.After;
import org.junit.Before;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A base class to simplify testing for standard input/output cases.
 *
 * @author Maurice Gschwind
 */
public abstract class SystemInOutTest {

    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    protected final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    /**
     * Sets the name of the class under test. This must be the fully qualified class name with the package name, eg.
     * {@code com.example.MyClass}.
     *
     * @return the fully qualified class name.
     */
    protected abstract String classUnderTest();


    /**
     * Runs the main method of the {@link #classUnderTest classUnderTest} and sends the supplied String to standard in.
     *
     * @param data the String to run on the standard input.
     */
    protected final void runString(String data) {

        InputStream stdin = System.in;

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));

            runMain(null);
        } finally {
            System.setIn(stdin);
        }
    }

    /**
     * Reads the input of a file and redirects it to standard input. The file must have the ending .in. Only the part
     * in front of the ending must be supplied.
     *
     * @param filename The filename without the ending.
     */
    protected final void runFile(String filename) {

        runFile(filename, null);
    }


    /**
     * Reads the input of a file and redirects it to standard input. The file must have the ending .in. Only the part
     * in front of the ending must be supplied.
     *
     * @param filename The filename without the ending.
     * @param mainArgs the arguments for the main method as a String array or {@code null}.
     */
    protected final void runFile(String filename, String[] mainArgs) {
        final InputStream stdin = System.in;

        try {

            File f = new File(filename + ".in");
            System.setIn(new FileInputStream(f));

            runMain(mainArgs);

        // TODO error handling
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.setIn(stdin);
        }
    }

    /**
     * Tries to run the main method of the defined class.
     *
     * @param mainArgs the arguments for the main method as a String array or {@code null}.
     */
    private void runMain(String[] mainArgs) {
        try {
            final Class<?> toTest = Class.forName(classUnderTest());
            final Class[] argTypes = new Class[]{String[].class};
            //Use reflection to get main method
            final Method main = toTest.getMethod("main", argTypes);
            final Object[] param = {mainArgs};

            main.invoke(null, param);

        // TODO error handling
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
