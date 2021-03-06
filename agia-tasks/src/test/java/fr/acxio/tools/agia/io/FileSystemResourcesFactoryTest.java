package fr.acxio.tools.agia.io;

/*
 * Copyright 2014 Acxio
 * 
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

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.core.io.Resource;

public class FileSystemResourcesFactoryTest {

    @Test
    public void testConstantPattern() throws Exception {
        FileSystemResourcesFactory aFactory = new FileSystemResourcesFactory();
        aFactory.setPattern("file.ext");
        Resource[] aResources = aFactory.getResources();
        assertNotNull(aResources);
        assertEquals(1, aResources.length);
        assertEquals("file.ext", aResources[0].getFilename());
    }

    @Test
    public void testWildcardPattern() throws Exception {
        FileSystemResourcesFactory aFactory = new FileSystemResourcesFactory();
        aFactory.setPattern("file:src/test/resources/testFiles/input*.csv");
        Resource[] aResources = aFactory.getResources();
        assertNotNull(aResources);
        assertEquals(2, aResources.length);
        assertEquals("input.csv", aResources[0].getFilename());
        assertEquals("input1000.csv", aResources[1].getFilename());
    }
    
    @Test
    public void testNoMatchWildcardPattern() throws Exception {
        FileSystemResourcesFactory aFactory = new FileSystemResourcesFactory();
        aFactory.setPattern("file:src/test/resources/testFiles/*.unknown");
        Resource[] aResources = aFactory.getResources();
        assertNotNull(aResources);
        assertEquals(0, aResources.length);
    }
    
    @Test
    public void testEmptyPattern() throws Exception {
        FileSystemResourcesFactory aFactory = new FileSystemResourcesFactory();
        aFactory.setPattern("");
        Resource[] aResources = aFactory.getResources();
        assertNotNull(aResources);
        assertEquals(1, aResources.length);
        assertEquals("", aResources[0].getFilename());
    }
    
    @Test
    public void testNullPattern() throws Exception {
        FileSystemResourcesFactory aFactory = new FileSystemResourcesFactory();
        aFactory.setPattern(null);
        Resource[] aResources = aFactory.getResources();
        assertNull(aResources);
    }
}
