/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codenarc.rule.junit

import org.codenarc.rule.AbstractRuleTestCase
import org.codenarc.rule.Rule

/**
 * Tests for UseAssertTrueInsteadOfNegationRule
 *
 * @author 'Hamlet D'Arcy'
 * @version $Revision: 329 $ - $Date: 2010-04-29 04:20:25 +0200 (Thu, 29 Apr 2010) $
 */
class UseAssertTrueInsteadOfNegationRuleTest extends AbstractRuleTestCase {

    void testRuleProperties() {
        assert rule.priority == 2
        assert rule.name == "UseAssertTrueInsteadOfNegation"
    }

    void testSuccessScenario() {
        final SOURCE = '''
            class MyTest extends GroovyTestCase {
                public void testMethod() {
                    assertFalse(condition)
                    assertTrue(condition)
                    assertTrue(!condition, condition)
                    assertFalse(!condition, condition)
                }
            }
        '''
        assertNoViolations(SOURCE)
    }

    void testUsingThisReference() {
        final SOURCE = '''
            class MyTest extends GroovyTestCase {
                public void testMethod() {
                    assertFalse(!condition)
                }
            }
        '''
        assertSingleViolation(SOURCE, 4, 'assertFalse(!condition)', 'assertFalse(!condition) can be simplified to assertTrue(condition)')
    }

    void testUsingStaticReference() {
        final SOURCE = '''
            class MyTest extends GroovyTestCase {
                public void testMethod() {
                    Assert.assertFalse(!condition)
                }
            }
        '''
        assertSingleViolation(SOURCE, 4, 'Assert.assertFalse(!condition)', 'Assert.assertFalse(!condition) can be simplified to Assert.assertTrue(condition)')
    }

    protected Rule createRule() {
        new UseAssertTrueInsteadOfNegationRule()
    }
}