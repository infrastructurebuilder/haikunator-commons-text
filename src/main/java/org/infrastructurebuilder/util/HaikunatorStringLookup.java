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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.apache.commons.text.lookup.StringLookup;

import me.atrox.haikunator.Haikunator;

public final class HaikunatorStringLookup implements StringLookup {
  public static final String HAIKUNATOR = "haikunator";
  public static final HaikunatorStringLookup INSTANCE = new HaikunatorStringLookup();
  public final static Map<String, StringLookup> map = new HashMap<String, StringLookup>(
      Map.of(HAIKUNATOR, INSTANCE));
  private static final String SPLIT_STR = ":";


  public final String getKey() {
    return HAIKUNATOR;
  }

  @Override
  public String lookup(String key) {
    if (key == null) {
      return null;
    }
    final Haikunator h = new Haikunator();
    // 0 = delimiter
    // 1 = length
    // 2 = tokenhex or tokenchars
    // 3 = adjectives separated by ","
    // 4 = nouns separated by ","
    // 5 = Seed (long or blank)
    String[] config = new String[6];
    config[0] = "-";
    config[1] = "4";
    config[2] = "true";
    config[3] = null;
    config[4] = null;
    config[5] = null;

    String[] s = key.split(SPLIT_STR);
    for (int i = 0; i < config.length; ++i) {
      config[i] = null;
      if (i < s.length) {
        s[i] = s[i].trim();
        config[i] = (s[i].isEmpty()) ? null : s[i];
      }
      switch (i) {
      case 0:
        Optional.ofNullable(config[i]).ifPresent(a -> h.setDelimiter(a));
        break;
      case 1:
        Optional.ofNullable(config[i]).ifPresent(a -> h.setTokenLength(Integer.parseInt(a)));
        break;
      case 2:
        Optional.ofNullable(config[i]).ifPresent(a -> {
          if (Boolean.parseBoolean(a)) {
            h.setTokenHex(true);
          } else {
            h.setTokenChars(a);
          }
        });
        break;
      case 3:
        Optional.ofNullable(config[i]).ifPresent(a -> h.setAdjectives(a.split(",")));
        break;
      case 4:
        Optional.ofNullable(config[i]).ifPresent(a -> h.setNouns(a.split(",")));
        break;
      case 5:
        Optional.ofNullable(config[i]).ifPresent(a -> h.setRandom(new Random(Long.parseLong(a))));
        break;
      }
    }
    return h.haikunate();
  }

}
