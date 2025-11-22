# JSonic

JSON represents data as nested key-value structures. At the top level, a JSON document is either:<br>
An object → { "key": value, ... }<br>
An array → [ value1, value2, ... ]<br>
Everything else is a value.

## JSON data types

| JSON Type | Example                          | Notes                                                                               |
| --------- | -------------------------------- | ----------------------------------------------------------------------------------- |
| Object    | `{ "name": "Alice", "age": 25 }` | Key-value pairs, keys are strings, values can be any type                           |
| Array     | `[1, 2, 3]`                      | Ordered list of values, can contain mixed types                                     |
| String    | `"hello world"`                  | Enclosed in double quotes `"`. May include escaped characters like `\"`, `\\`, `\n` |
| Number    | `42`, `-3.14`, `2e10`            | Can be integer or floating-point, positive or negative, scientific notation allowed |
| Boolean   | `true`, `false`                  | Only two possible values                                                            |
| Null      | `null`                           | Represents the absence of a value                                                   |

## JSON structural symbols

| Symbol                  | Meaning                                            |
| ----------------------- | -------------------------------------------------- |
| `{ }`                   | Object start and end                               |
| `[ ]`                   | Array start and end                                |
| `:`                     | Key-value separator in objects                     |
| `,`                     | Separator between elements (in objects and arrays) |
| `"`                     | String delimiters                                  |
| `true`, `false`, `null` | Literal values                                     |

## What a JSON object handles

A parser must read a JSON string character by character and handle:
1. Whitespace → spaces, tabs, newlines (JSON allows whitespace between elements; parser must skip them where irrelevant)
2. Objects
- Parse { → start of object
- Read key string → expect :
- Parse value (could be object, array, string, number, boolean, null)
- Handle commas between key-value pairs
- Expect } → end of object.
3. Arrays
- Parse [ → start of array
- Parse values (recursively if nested)
- Handle commas between elements
- Expect ] → end of array
4. Strings
- Handle opening "
- Handle escape sequences: \", \\, \/, \b, \f, \n, \r, \t, \uXXXX
- Detect closing "
5. Numbers
- Recognize optional - sign
- Digits before and after optional decimal point
- Optional exponent with e or E and optional + or -
- Literals → true, false, null
- Recognize exact spelling
6. Errors
- Invalid syntax (e.g., missing , or })
- Unexpected characters
- Invalid numbers or malformed escape sequences

## JSON parsing strategy

Most simple JSON parsers use recursive descent:
- parseValue() → decides which type of value to parse (object, array, string, number, literal)
- parseObject() → handles {...}
- parseArray() → handles [...]
- parseString() → handles strings and escapes
- parseNumber() → handles numbers
- parseLiteral() → handles true/false/null

## JSON structure as a tree

```json
{
  "name": "Alice",
  "age": 25,
  "isStudent": false,
  "scores": [95, 87, 78],
  "address": {
    "city": "London",
    "zip": "E1 6AN"
  }
}
```

Tree of values:
```
Object
├─ "name" : String("Alice")
├─ "age" : Number(25)
├─ "isStudent" : Boolean(false)
├─ "scores" : Array
│    ├─ Number(95)
│    ├─ Number(87)
│    └─ Number(78)
└─ "address" : Object
     ├─ "city" : String("London")
     └─ "zip" : String("E1 6AN")

```