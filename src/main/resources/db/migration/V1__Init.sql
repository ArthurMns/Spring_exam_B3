CREATE TABLE roles
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE users
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    username         VARCHAR(255) NOT NULL,
    password         VARCHAR(255) NOT NULL,
    enabled          BIT(1)       NOT NULL,
    token_expiration datetime NULL,
    claim_token      VARCHAR(255) NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    roles_id BIGINT NOT NULL,
    users_id BIGINT NOT NULL
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES roles (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (users_id) REFERENCES users (id);


CREATE TABLE dogs
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    breed VARCHAR(255) NOT NULL,
    img_url  VARCHAR(512)          NULL,
    CONSTRAINT pk_dogs PRIMARY KEY (id)
);