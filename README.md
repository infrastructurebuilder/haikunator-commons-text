# haikunator-commons-text

This uses the [Haikunator Java package](https://github.com/Atrox/haikunatorjava) to 
produce a Apache `commons-text` [StringLookup](https://commons.apache.org/proper/commons-text/apidocs/org/apache/commons/text/lookup/StringLookup.html).

To use within a codebase, you'll need to create a new (and correct) `StringLookupFactory` instance that includes the `HaikunatorStringLookup` to use to parse your strings.  It is a little wonky how the factory must be constructed, but the source is available and fairly easy to understand.

The [User Guide](https://commons.apache.org/proper/commons-text/userguide.html) for `commons-text` is probably the place to start.

Use of the `StringLookup` is fairly easy.


```
final StringSubstitutor interpolator = createMyStringSubstitutorProbablyFromAFactoryofSomeSort();
final String text = interpolator.replace(
    "Haikunator:        ${haikunator:DELIM:LEN:TOKENS:ADJECTIVES:NOUNS:RANDOM}");

```


Substituting:
 
- DELIM Delimiter (defaults to "-")
- LEN length defaults to 4
- TOKENS (boolean or list of acceptable characters as a string, defaults to true)
- ADJECTIVES (separated by `,`)
- NOUNS (separated by `,`)
- RANDOM  Random Seed (long value or blank)

Per the defaults(equivalent to `${haikunator:-:4:true}`), the result is a random adjective-random noun-4 hex character string.

If you do not pass a value into the slot, it will not override the existing values.
Thus `${haikunator::8::}` uses the default delimiter `-` and 8 hex characters.
  