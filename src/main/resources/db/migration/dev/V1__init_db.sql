CREATE TABLE users
(
    id        UUID PRIMARY KEY,
    username  VARCHAR(50)  NOT NULL,
    password  VARCHAR(100) NOT NULL,
    authority VARCHAR(50)  NOT NULL,
    enabled   Boolean DEFAULT true
);

CREATE TABLE notes
(
    id          UUID PRIMARY KEY,
    title       VARCHAR(100)   NOT NULL,
    content     VARCHAR(10000) NOT NULL,
    access_type VARCHAR(10)    NOT NULL,
    user_id     UUID            NOT NULL,
    CONSTRAINT fk_notes_user FOREIGN KEY (user_id) REFERENCES users (id)
);

