/*
 * Copyright 2000-2016 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.hummingbird.namespace;

import com.vaadin.hummingbird.StateNode;
import com.vaadin.hummingbird.dom.ClassList;

/**
 * Namespace for CSS class names for an element.
 *
 * @since
 * @author Vaadin Ltd
 */
public class ClassListNamespace extends SerializableListNamespace<String> {

    private static class ClassListView extends ListNamespace.SetView<String>
            implements ClassList {

        private ClassListView(ClassListNamespace namespace) {
            super(namespace);
        }

        @Override
        protected void validate(String className) {
            if (className == null) {
                throw new IllegalArgumentException("Class name cannot be null");
            }

            if ("".equals(className)) {
                throw new IllegalArgumentException(
                        "Class name cannot be empty");
            }
            if (className.indexOf(' ') != -1) {
                throw new IllegalArgumentException(
                        "Class name cannot contain spaces");
            }
        }

        @Override
        public boolean set(String className, boolean set) {
            if (set) {
                return add(className);
            } else {
                return remove(className);
            }
        }
    }

    /**
     * Creates a new class list namespace for the given node.
     *
     * @param node
     *            the node that the namespace belongs to
     */
    public ClassListNamespace(StateNode node) {
        super(node);
    }

    /**
     * Creates a view into this namespace.
     *
     * @return a view into this namespace
     */
    public ClassList getClassList() {
        return new ClassListView(this);
    }
}
