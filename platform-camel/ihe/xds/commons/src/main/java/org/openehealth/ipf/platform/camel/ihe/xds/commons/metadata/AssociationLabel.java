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
package org.openehealth.ipf.platform.camel.ihe.xds.commons.metadata;

/**
 * Association labeling values used for the associations of submission sets.
 * @author Jens Riemschneider
 */
public enum AssociationLabel {
    /** Label for associations to documents that are contained in the request. */
    ORIGINAL("Original"),
    /** Label for associations to documents that are only referenced in the request. */
    REFERENCE("Reference");
    
    private final String opcode;

    private AssociationLabel(String opcode) {
        this.opcode = opcode;
    }

    /**
     * @return the opcode representing the association in ebXML.
     */
    public String getOpcode() {
        return opcode;
    }
    
    /**
     * Null-safe version of {@link #getOpcode()}.
     * @param label
     *          the label. Cane be <code>null</code>.
     * @return the opcode of the label. <code>null</code> if the input was <code>null</code>.
     */
    public static String toOpcode(AssociationLabel label) {
        if (label == null) {
            return null;
        }
        
        return label.getOpcode();
    }
    
    /**
     * Returns the association label that is represented by the given opcode.
     * @param opcode
     *          the opcode to look for. Can be <code>null</code>.
     * @return the label. <code>null</code> if the input was <code>null</code>
     *          or if the opcode was not found.
     */
    public static AssociationLabel fromOpcode(String opcode) {
        if (opcode == null) {
            return null;
        }
        
        for (AssociationLabel label : AssociationLabel.values()) {
            if (opcode.equals(label.getOpcode())) {
                return label;
            }
        }
        
        return null;
    }
}
