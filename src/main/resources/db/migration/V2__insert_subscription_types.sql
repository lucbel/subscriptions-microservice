-- Insert default subscription types
INSERT INTO subscription_types (name, price, description, created_at) VALUES
    ('FREE', 0.00, 'Free plan with basic features', CURRENT_TIMESTAMP),
    ('PLUS', 9.99, 'Plus plan with additional features', CURRENT_TIMESTAMP),
    ('UNLIMITED', 19.99, 'Unlimited plan with all features', CURRENT_TIMESTAMP);
