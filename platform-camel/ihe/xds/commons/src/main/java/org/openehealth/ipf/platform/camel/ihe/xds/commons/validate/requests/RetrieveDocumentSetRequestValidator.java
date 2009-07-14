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
package org.openehealth.ipf.platform.camel.ihe.xds.commons.validate.requests;

import static org.apache.commons.lang.Validate.notNull;
import static org.openehealth.ipf.platform.camel.ihe.xds.commons.validate.ValidationMessage.*;
import static org.openehealth.ipf.platform.camel.ihe.xds.commons.validate.ValidatorAssertions.metaDataAssert;

import org.openehealth.ipf.commons.core.modules.api.Validator;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.ebxml.EbXMLRetrieveDocumentSetRequest;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.RetrieveDocument;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.validate.ValidationProfile;

/**
 * Validates a {@link EbXMLRetrieveDocumentSetRequest}.
 * @author Jens Riemschneider
 */
public class RetrieveDocumentSetRequestValidator implements Validator<EbXMLRetrieveDocumentSetRequest, ValidationProfile>{
    public void validate(EbXMLRetrieveDocumentSetRequest request, ValidationProfile profile) {
        notNull(request, "request cannot be null");
        
        for (RetrieveDocument document : request.getDocuments()) {
            String repoId = document.getRepositoryUniqueID();
            metaDataAssert(repoId != null && !repoId.isEmpty(), REPO_ID_MUST_BE_SPECIFIED);
            
            String docId = document.getDocumentUniqueID();
            metaDataAssert(docId != null && !docId.isEmpty(), DOC_ID_MUST_BE_SPECIFIED);
        }
    }
}
