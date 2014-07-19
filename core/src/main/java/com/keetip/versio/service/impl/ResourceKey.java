/*
 * Copyright (c) 2014 Keetip
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * This product may include a number of subcomponents with separate copyright notices and
 * license terms. Your use of the source code for the these subcomponents is subject to
 * the terms and conditions of the subcomponent's license, as noted in the LICENSE file.
 */

package com.keetip.versio.service.impl;

import java.util.UUID;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.keetip.versio.domain.Release;

/**
 * @author keetip
 * 
 */
public class ResourceKey {

	private final UUID mProjectId;
	private final String mResourceName;

	public ResourceKey(UUID projectId, String resourceName) {
		mProjectId = projectId;
		mResourceName = resourceName;
	}

	public UUID getProjectId() {
		return mProjectId;
	}

	public String getResourceName() {
		return mResourceName;
	}

	public boolean matches(UUID projectId, String resourceName) {
		return new EqualsBuilder()
			.append(mProjectId, projectId)
			.append(mResourceName, resourceName)
			.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(mProjectId).append(mResourceName).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Release)) {
			return false;
		}
		ResourceKey other = (ResourceKey) obj;
		return new EqualsBuilder().append(mProjectId, other.getProjectId())
				.append(mResourceName, other.getResourceName()).isEquals();
	}
}
