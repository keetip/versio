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

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.keetip.ddd.domain.Entity;

/**
 * @author keetip
 * 
 */
public class Revision implements Entity<Revision> {
	
	private final UUID mUuid;
	private final Project mProject;
	private final Integer mRevisionNumber;

	private Date mDate;
	private String mAuthor;
	private String mComment;

	/**
	 * Construct a new persisted Revision entity
	 * 
	 * @param project
	 * @param uuid
	 * @param revisionNumber
	 */
	public Revision(Project project, UUID uuid, Integer revisionNumber) {
		Validate.notNull(project, "Project is required");
		Validate.notNull(uuid, "Revision id is required");
		Validate.notNull(revisionNumber, "Revision number is required");
		mProject = project;
		mUuid = uuid;
		mRevisionNumber = revisionNumber;
	}

	/**
	 * Construct a new transient Revision entity
	 * 
	 * @param project
	 * @param revisionNumber
	 * @return
	 */
	public static Revision newRevision(Project project, Integer revisionNumber){
		return new Revision(project, UUID.randomUUID(), revisionNumber);
	}
	
	public Project getProject() {
		return mProject;
	}

	public UUID getUuid() {
		return mUuid;
	}

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = date;
	}

	public String getAuthor() {
		return mAuthor;
	}

	public void setAuthor(String author) {
		mAuthor = author;
	}

	public String getComment() {
		return mComment;
	}

	public void setComment(String comment) {
		mComment = comment;
	}

	public Integer getRevisionNumber() {
		return mRevisionNumber;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(mUuid)
			.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Revision)){
			return false;
		}
		Revision other = (Revision)obj;
		return sameIdentityAs(other);
	}

	public boolean sameIdentityAs(Revision other) {
		return new EqualsBuilder()
			.append(mUuid, other.getRevisionNumber())
			.isEquals();
	}

}
