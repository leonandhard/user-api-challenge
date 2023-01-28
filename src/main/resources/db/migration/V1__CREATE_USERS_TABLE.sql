CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(127) NOT NULL ,
    email VARCHAR(127) UNIQUE NOT NULL ,
    validity BOOLEAN NOT NULL ,
    monthly_salary NUMERIC(15,2) NOT NULL ,
    monthly_expenses NUMERIC(15,2) NOT NULL ,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL ,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);

