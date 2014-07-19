CREATE TABLE project(
	uuid VARCHAR(36),
	name VARCHAR(50),
	description VARCHAR(255),
	creation_date TIMESTAMP,
	PRIMARY KEY(uuid)
);

CREATE TABLE revision(	
	uuid VARCHAR(36),
	project_id VARCHAR(36),
	revision_number INTEGER,
	revision_date TIMESTAMP,
	author VARCHAR(255),
	comment TEXT,
	PRIMARY KEY(uuid),
	CONSTRAINT PROJECT_FK_KEY FOREIGN KEY (project_id) REFERENCES tb_project(uuid)
);

CREATE TABLE release(
	uuid VARCHAR(36),
	project_id VARCHAR(36),
	revision_id VARCHAR(36),
	name VARCHAR(255),
	comment TEXT,
	PRIMARY KEY(uuid),
	CONSTRAINT PROJECT_FK_KEY FOREIGN KEY (project_id) REFERENCES tb_project(uuid),
	CONSTRAINT REVISION_FK_KEY FOREIGN KEY (revision_id) REFERENCES tb_revision(uuid)
);