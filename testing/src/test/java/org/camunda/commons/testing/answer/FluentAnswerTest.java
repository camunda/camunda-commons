package org.camunda.commons.testing.answer;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;

public class FluentAnswerTest {

  public static interface A {

    A withName(String name);

    String getName();
  }

  @Test
  public void returns_mock_for_fluent_method() {
    final A mock = FluentAnswer.createMock(A.class);

    assertThat(mock.withName("foo")).isEqualTo(mock);
    assertThat(mock.getName()).isNull();
    verify(mock).withName("foo");
  }
}
