
-- Create user management schema and users table
DROP SCHEMA IF EXISTS user_mgmt CASCADE;
DROP SEQUENCE IF EXISTS user_mgmt.users_id_seq CASCADE;

CREATE SCHEMA user_mgmt;
CREATE SEQUENCE user_mgmt.users_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;

CREATE TABLE user_mgmt.users (
  id BIGINT PRIMARY KEY DEFAULT nextval('user_mgmt.users_id_seq'),
  username VARCHAR(60) NOT NULL UNIQUE,
  password VARCHAR(120) NOT NULL,
  email VARCHAR(120) NOT NULL UNIQUE,
  name VARCHAR(120) NOT NULL,
  role VARCHAR(40) NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

ALTER SEQUENCE user_mgmt.users_id_seq OWNED BY user_mgmt.users.id;

CREATE UNIQUE INDEX IF NOT EXISTS ux_users_username ON user_mgmt.users(username);
CREATE UNIQUE INDEX IF NOT EXISTS ux_users_email ON user_mgmt.users(email);
