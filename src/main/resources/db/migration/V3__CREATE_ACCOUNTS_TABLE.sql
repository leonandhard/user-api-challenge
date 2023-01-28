CREATE TABLE accounts
(
    id              BIGSERIAL PRIMARY KEY,
    account_number  VARCHAR(127) UNIQUE      NOT NULL,
    currency        CHAR(3)                  NOT NULL,
    deposit         NUMERIC(15, 2)           NOT NULL,
    credits         NUMERIC(15, 2)           NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITH TIME ZONE NOT NULL,
    validity        BOOLEAN                  NOT NULL,
    billing_address VARCHAR(127)             NOT NULL,
    contact_number  VARCHAR(127)             NOT NULL,
    user_id         BIGSERIAL REFERENCES users (id)
);