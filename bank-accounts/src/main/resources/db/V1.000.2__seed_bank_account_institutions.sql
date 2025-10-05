-- This file seeds the bank_account_institutions table with initial data.
INSERT INTO bank_accounts.bank_account_institutions (
    code, name, short_name, website_url, active
)
VALUES
  ('341','Itaú Unibanco S.A.','Itaú','https://www.itau.com.br', true),
  ('237','Banco Bradesco S.A.','Bradesco','https://www.bradesco.com.br', true),
  ('260','Nu Pagamentos S.A.','Nubank','https://nubank.com.br', true),
  ('001','Banco do Brasil S.A.','BB','https://www.bb.com.br', true),
  ('033','Banco Santander (Brasil) S.A.','Santander','https://www.santander.com.br', true)
ON CONFLICT DO NOTHING;
