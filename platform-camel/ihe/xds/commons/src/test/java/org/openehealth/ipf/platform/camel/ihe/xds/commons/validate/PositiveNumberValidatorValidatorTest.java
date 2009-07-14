/*
 * Copyright 2009 the original author or authors.
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
 */
package org.openehealth.ipf.platform.camel.ihe.xds.commons.validate;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for {@link HashValidator}.
 * @author Jens Riemschneider
 */
public class PositiveNumberValidatorValidatorTest {
    private static final PositiveNumberValidator validator = new PositiveNumberValidator();

    @Test
    public void testValidateGoodCases() throws XDSMetaDataException {
        validator.validate("0");
        validator.validate("12");
        validator.validate("1234534576348657834658346586345");
    }
    
    @Test 
    public void testValidateBadCases() throws XDSMetaDataException {
        assertFails("");
        assertFails("a");
        assertFails("-12");
    }

    private static void assertFails(String value) {
        try {
            validator.validate(value);
            fail("Expected exception: " + XDSMetaDataException.class + " for " + value);
        } catch (XDSMetaDataException e) {
            // Expected
        }
    }
}
