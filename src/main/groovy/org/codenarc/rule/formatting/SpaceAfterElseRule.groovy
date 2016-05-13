/*
 * Copyright 2012 the original author or authors.
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
package org.codenarc.rule.formatting

import org.codehaus.groovy.ast.stmt.IfStatement
import org.codenarc.rule.AbstractAstVisitorRule

/**
 * Check that there is exactly one space (blank) after the else keyword and before the opening parenthesis.
 */
class SpaceAfterElseRule extends AbstractAstVisitorRule {

    String name = 'SpaceAfterElse'
    int priority = 3
    Class astVisitorClass = SpaceAfterElseAstVisitor
}

class SpaceAfterElseAstVisitor extends AbstractSingleSpaceAfterKeywordAstVisitor {

    @Override
    void visitIfElse(IfStatement ifElse) {
        checkForSingleSpaceAndOpeningParenthesis(ifElse, 'else')
        super.visitIfElse(ifElse)
    }

}