#!/bin/bash
set -Eeuo pipefail

# --- Load environment variables from .env file (Removing CR) ---
if [[ -f .env ]]; then
  export $(tr -d '\r' < .env | grep -v '^\s*#' | xargs -I{} echo {})
fi

# --- Variables Configuration ---
PG_CONTAINER="${PG_CONTAINER}"
PG_USER="${PG_USER}"
PG_DB="${PG_DB}"
MIGRATIONS_DIR="/src/main/resources/db"
USER_MGMT_MIGRATIONS_DIR="user-management$MIGRATIONS_DIR"
BANK_ACCOUNTS_MIGRATIONS_DIR="bank-accounts$MIGRATIONS_DIR"
BILLS_MIGRATIONS_DIR="bills$MIGRATIONS_DIR"


# --- Log Methods ---
log() { printf '>> %s\n' "$*"; }
err() { printf '!! %s\n' "$*" >&2; }

# --- Validate if Container is Running ---
is_container_running() {
  local container_name="$1"
  log "Checking if container '$container_name' is running..."
  if docker ps --format '{{.Names}}' | grep -Fxq "$container_name"; then
    log "Container '$container_name' is running."
    return 0
  else
    err "Container '$container_name' is NOT running. Start with: docker compose up -d"
    return 1
  fi
}

# --- Apply Migrations from Directory ---
apply_dir_migrations() {
  local dir="$1"
  log "Applying migrations from directory: $dir"

  for file in "$dir"/*.sql; do
    [ -e "$file" ] || { log "No SQL files found in $dir"; return; }
    log "Applying migration: $(basename "$file")"
    docker exec -i "$PG_CONTAINER" psql -v ON_ERROR_STOP=1 -U "$PG_USER" -d "$PG_DB" -f - < "$file"
  done
}

# --- Main Script Execution ---
log "Starting database reset and migration process..."
log "Using Container: $PG_CONTAINER"
log "Using User: $PG_USER"
log "Using Database: $PG_DB"
log "Migrations Directory: \n\w $USER_MGMT_MIGRATIONS_DIR\n\w $BANK_ACCOUNTS_MIGRATIONS_DIR\n\w $BILLS_MIGRATIONS_DIR"
printf "\n"

is_container_running "$PG_CONTAINER" || exit 1 # Exit if container is not running

apply_dir_migrations "$USER_MGMT_MIGRATIONS_DIR"
apply_dir_migrations "$BANK_ACCOUNTS_MIGRATIONS_DIR"
apply_dir_migrations "$BILLS_MIGRATIONS_DIR"

log "Database reset and migration process completed."