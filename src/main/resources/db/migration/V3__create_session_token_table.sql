-- Create session_token table
CREATE TABLE session_token (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    token VARCHAR(500) NOT NULL UNIQUE,
    expires_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_session_token_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Create indexes
CREATE INDEX idx_session_token_token ON session_token(token);
CREATE INDEX idx_session_token_user_id ON session_token(user_id);
CREATE INDEX idx_session_token_expires_at ON session_token(expires_at);
