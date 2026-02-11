CREATE TABLE trainers (
    id UUID PRIMARY KEY,
    version BIGINT NOT NULL DEFAULT 0,
    user_id UUID NOT NULL,
    profile_description VARCHAR(1024) NOT NULL,
    CONSTRAINT fk_trainers_user
        FOREIGN KEY (user_id) REFERENCES users(id)
);
