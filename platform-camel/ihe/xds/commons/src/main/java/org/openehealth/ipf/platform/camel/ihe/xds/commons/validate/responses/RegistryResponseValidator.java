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
package org.openehealth.ipf.platform.camel.ihe.xds.commons.validate.responses;

import static org.apache.commons.lang.Validate.notNull;
import static org.openehealth.ipf.platform.camel.ihe.xds.commons.validate.ValidationMessage.*;
import static org.openehealth.ipf.platform.camel.ihe.xds.commons.validate.ValidatorAssertions.metaDataAssert;

import org.openehealth.ipf.commons.core.modules.api.Validator;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.ebxml.EbXMLRegistryResponse;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.responses.ErrorInfo;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.validate.ValidationProfile;

/**
 * Validate a {@link EbXMLRegistryResponse}.
 * @author Jens Riemschneider
 */
public class RegistryResponseValidator implements Validator<EbXMLRegistryResponse, ValidationProfile>{
    public void validate(EbXMLRegistryResponse response, ValidationProfile profile) {
        notNull(response, "response cannot be null");
        
        metaDataAssert(response.getStatus() != null, INVALID_STATUS_IN_RESPONSE);
        for (ErrorInfo errorInfo : response.getErrors()) {
            metaDataAssert(errorInfo != null, INVALID_ERROR_INFO_IN_RESPONSE);
            metaDataAssert(errorInfo.getErrorCode() != null, INVALID_ERROR_CODE_IN_RESPONSE);
            metaDataAssert(errorInfo.getSeverity() != null, INVALID_SEVERITY_IN_RESPONSE);
        }
    }
}
