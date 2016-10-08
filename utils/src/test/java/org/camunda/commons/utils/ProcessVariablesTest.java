package org.camunda.commons.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.camunda.commons.utils.ProcessVariables.withVariables;

import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Martin Schimak <martin.schimak@plexiti.com>
 * @author Jan Galinski
 */
public class ProcessVariablesTest {

  private static final String KEYS_MUST_BE_STRINGS = "keys must be strings";
  private static final String KEYS_MUST_NOT_BE_NULL = "keys must not be null";
  public static final String MUST_HAVE_AN_EVEN_NUMBER_OF_ARGUMENTS = "must have an even number of arguments";

  @Rule
  public final ExpectedException thrown = ExpectedException.none();

  @Test
  public void fails_when_first_key_is_null() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage(KEYS_MUST_NOT_BE_NULL);
    withVariables(null,1);
  }

  @Test
  public void fails_when_other_key_is_null() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage(KEYS_MUST_NOT_BE_NULL);
    withVariables("foo",1,null,2);
  }

  @Test
  public void creates_map_of_key_value_pairs() {
    Map<String, Object> variables = withVariables("foo", 1, "bar", 2);
    assertThat(variables).containsOnly(entry("foo", 1), entry("bar", 2));
  }


  @Test
  public void fails_when_any_key_is_not_string() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage(KEYS_MUST_BE_STRINGS);
    withVariables("foo",1,"bar",2, 3, 4);
  }

  @Test
  public void accepts_null_values() {
    final Map<String, Object> variables = withVariables("foo", null, "bar", null);
    assertThat(variables).containsOnly(entry("foo", null), entry("bar", null));
  }

  @Test
  public void fails_when_keyValuePairs_odd_number() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage(MUST_HAVE_AN_EVEN_NUMBER_OF_ARGUMENTS);
    withVariables("foo",1,"bar");
  }

}
