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

package org.openehealth.ipf.platform.camel.ihe.xds.iti14.service;

import org.apache.camel.Exchange;
import org.openehealth.ipf.platform.camel.core.util.Exchanges;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.DefaultItiWebService;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.converters.EbXML21Converters;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.responses.ErrorCode;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.responses.ErrorInfo;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.responses.Response;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.responses.Status;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.stub.ebrs21.rs.RegistryResponse;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.stub.ebrs21.rs.SubmitObjectsRequest;

/**
 * Service implementation for the IHE ITI-14 transaction (Register Document Set).
 * <p>
 * This implementation delegates to a Camel consumer by creating an exchange.
 *
 * @author Jens Riemschneider
 */
public class Iti14Service extends DefaultItiWebService implements Iti14PortType {
    public RegistryResponse documentRegistryRegisterDocumentSet(SubmitObjectsRequest body) {
        Exchange result = process(body);
        if (result.getException() != null) {
            return EbXML21Converters.convert(createErrorResponse());
        }
        
        return Exchanges.resultMessage(result).getBody(RegistryResponse.class);            
    }

    private Response createErrorResponse() {
        Response errorResponse = new Response();
        errorResponse.setStatus(Status.FAILURE);
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(ErrorCode.REGISTRY_ERROR);
        errorResponse.getErrors().add(errorInfo);
        return errorResponse;
    }
}
