package org.camunda.commons.testing.rule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static org.assertj.core.api.Assertions.*;

public class ChainedTestRuleTest {

  private String result = "";

  public final TestRule outerRule = new TestRule() {
    @Override
    public Statement apply(Statement base, Description description) {
      return new Statement() {
        @Override
        public void evaluate() throws Throwable {
          result += "foo";
        }
      };
    }
  };

  @Rule
  public final ChainedTestRule<TestRule, TestRule> chainedTestRule = ChainedTestRule.newChain(outerRule, new TestRule() {
    @Override
    public Statement apply(Statement base, Description description) {
      return new Statement() {
        @Override
        public void evaluate() throws Throwable {
          result += "bar";
        }
      };
    }
  });

  @Test
  public void should_execute_outer_and_inner_rule() {
    assertThat(result).isEqualTo("foobar");
  }
}
