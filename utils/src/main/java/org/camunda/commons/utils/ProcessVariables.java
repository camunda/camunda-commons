package org.camunda.commons.utils;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;


public final class ProcessVariables {

  private ProcessVariables() {
    // do not instantiate, util class
  }

  /**
   * Helper method to easily construct a map of process variables
   *
   * @param   key (obligatory) key of first process variable
   * @param   value (obligatory) value of first process variable
   * @param   furtherKeyValuePairs (optional) key/value pairs for further
   *          process variables
   * @return  a map of process variables by passing a list of String
   *          -> Object key value pairs.
   */
  public static Map<String, Object> withVariables(final String key, final Object value, final Object... furtherKeyValuePairs) {
    assertNonNullString(key);
    final Map<String, Object> map = new HashMap<String, Object>();
    map.put(key, value);

    if (furtherKeyValuePairs != null) {
      if (furtherKeyValuePairs.length % 2 != 0) {
        throw new IllegalArgumentException(format("Illegal call of withVariables() - must have an even number of arguments, but found length = %s!", furtherKeyValuePairs.length + 2));
      }
      for (int i = 0; i < furtherKeyValuePairs.length; i += 2) {
        map.put(assertNonNullString(furtherKeyValuePairs[i]), furtherKeyValuePairs[i + 1]);
      }
    }
    return map;
  }

  private static String assertNonNullString(Object key) {
    if (key == null) {
      throw new IllegalArgumentException(format("Illegal call of withVariables() - keys must not be null!"));
    }
    if (!(key instanceof String)) {
      throw new IllegalArgumentException(format("Illegal call of withVariables() - keys must be strings, found object of type '%s'!",
          key != null ? key.getClass().getName() : null));
    }
    return (String) key;
  }


}
