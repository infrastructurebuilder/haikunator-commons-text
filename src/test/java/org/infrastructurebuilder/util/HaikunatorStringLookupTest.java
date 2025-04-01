/*
 * @formatter:off
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
 * @formatter:on
 */
package org.infrastructurebuilder.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HaikunatorStringLookupTest {

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {
  }

  private HaikunatorStringLookup lu;

  @BeforeEach
  void setUp() throws Exception {
    lu = new HaikunatorStringLookup();
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void testGetKey() {
    assertEquals(HaikunatorStringLookup.HAIKUNATOR, lu.getKey());
  }

  @Test
  void testNull() {
    assertNull(lu.lookup(null));
  }

  @Test
  void testLookup() {
    assertNotNull(lu.lookup("-:4:true"));
  }

  @Test
  void testLookup2() {
    String s = lu.lookup("-:4:0");
    assertNotNull(s);
    assertTrue(s.endsWith("-0000"));
  }

  @Test
  void testLookup3() {
    String s = lu.lookup("-:4:0:a:c:1000");
    assertNotNull(s);
    assertTrue(s.endsWith("a-c-0000"));
  }

}
