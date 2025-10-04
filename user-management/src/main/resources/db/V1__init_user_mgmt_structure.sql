
-- Create user management schema and users table
DROP SCHEMA IF EXISTS user_mgmt CASCADE;
CREATE SCHEMA user_mgmt;

CREATE TABLE user_mgmt.users (
  id UUID PRIMARY KEY,
  username VARCHAR(60) NOT NULL UNIQUE,
  password VARCHAR(120) NOT NULL,
  email VARCHAR(120) NOT NULL UNIQUE,
  name VARCHAR(120) NOT NULL,
  role VARCHAR(40) NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE UNIQUE INDEX IF NOT EXISTS ux_users_username ON user_mgmt.users(username);
CREATE UNIQUE INDEX IF NOT EXISTS ux_users_email ON user_mgmt.users(email);
