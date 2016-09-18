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

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A sample test to demonstrate the usage of {@link SystemInOutTest}.
 *
 * @author Maurice Gschwind
 */
public class SumUpStdInTest extends SystemInOutTest {

    @Override
    protected String classUnderTest() {
        return "me.gschwind.test.SumUpStdIn";
    }

    /**
     * Runs a test against test data from a string
     */
    @Test
    public void testFromString() {

        final String data = "1 5";

        runString(data);

        assertEquals("Should be 6 but was\n" + outContent.toString(), "6\n", outContent.toString());
    }


    /**
     * Runs a test against test data from a file.
     */
    @Test
    public void testFromFile() {

        final String filename = "SumUpTest0";

        runFile(filename);

        assertEquals("Should be 42 but was " + outContent.toString(), "42\n", outContent.toString());
    }

}