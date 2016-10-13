/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openehealth.ipf.platform.camel.ihe.fhir.core;

import ca.uhn.fhir.rest.client.IClientInterceptor;
import org.apache.camel.Exchange;

/**
 * @author Dmytro Rud
 * @since 3.1
 */
public interface HapiClientInterceptorFactory {

    /**
     * Returns a new client interceptor
     *
     * @param endpoint fhir endpoint
     * @param exchange Camel exchange
     * @return client interceptor
     */
    IClientInterceptor newInstance(FhirEndpoint endpoint, Exchange exchange);

    /**
     * For backwards-compatibility only
     *
     * @param endpoint endpoint
     * @return client interceptor
     * @deprecated use {@link #newInstance(FhirEndpoint, Exchange)}
     */
    default IClientInterceptor newInstance(FhirEndpoint endpoint) {
        return newInstance(endpoint, null);
    }
}
