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

package com.keetip.ddd.domain;

/**
 * @author keetip
 * 
 */
public class RepositoryDataAccessException extends RuntimeException {

	private static final long serialVersionUID = 7022082235046581862L;

	/**
	 * 
	 */
	public RepositoryDataAccessException() {
	}

	/**
	 * @param message
	 */
	public RepositoryDataAccessException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public RepositoryDataAccessException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RepositoryDataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

}
