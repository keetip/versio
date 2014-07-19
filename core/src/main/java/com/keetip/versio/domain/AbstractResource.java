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

package com.keetip.versio.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author keetip
 * 
 */
public abstract class AbstractResource implements Resource{	

	private final Id mId;
	
	public AbstractResource(String name, Revision revision) {	
		mId = new Id(name, revision);
	}
	
	public Id getId() {
		return mId;
	}

	public String getName() {
		return mId.getName();
	}

	public Revision getRevision() {
		return mId.getRevision();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(mId)
			.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof AbstractResource)){
			return false;
		}
		AbstractResource other = (AbstractResource)obj;
		return sameIdentityAs(other);
	}
	
	public boolean sameIdentityAs(AbstractResource other) {
		return new EqualsBuilder()
			.append(mId, other.getId())
			.isEquals();
	}
	
	public static final class Id {
		
		private final String mName;
		private final Revision mRevision;
		
		public Id(String name, Revision revision) {		
			mName = name;
			mRevision = revision;
		}
		
		public String getName() {
			return mName;
		}
		
		public Revision getRevision() {
			return mRevision;
		}
		
		@Override
		public int hashCode() {
			return new HashCodeBuilder()
				.append(mName)
				.append(mRevision)
				.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof AbstractResource.Id)){
				return false;
			}
			AbstractResource.Id other = (AbstractResource.Id)obj;
			return new EqualsBuilder()
				.append(mName, other.getName())
				.append(mRevision, other.getRevision())
				.isEquals();
		}

	}
	
}
