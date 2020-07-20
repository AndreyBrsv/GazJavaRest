DROP TABLE IF EXISTS TRANSACTION;
DROP TABLE IF EXISTS DOCUMENTS;

CREATE TABLE DOCUMENTS(
                          id SERIAL PRIMARY KEY,
                          number varchar NOT NULL,
                          open_date TIMESTAMP NOT NULL,
                          company_name VARCHAR NOT NULL,
                          inn VARCHAR NOT NULL,
                          kpp VARCHAR NOT NULL
);
ALTER TABLE DOCUMENTS
    ADD CONSTRAINT documents_unique_number UNIQUE (number);

COMMENT ON COLUMN DOCUMENTS.number is 'Номер документа';
COMMENT ON COLUMN DOCUMENTS.open_date is 'Дата создания документа';
COMMENT ON COLUMN DOCUMENTS.company_name is 'Наименование компании';
COMMENT ON COLUMN DOCUMENTS.inn is 'ИНН';
COMMENT ON COLUMN DOCUMENTS.kpp is 'КПП';

CREATE TABLE TRANSACTION(
                            id SERIAL PRIMARY KEY,
                            document_id BIGINT REFERENCES DOCUMENTS (id),
                            uuid uuid NOT NULL,
                            time TIMESTAMP NOT NULL,
                            sum NUMERIC NOT NULL,
                            fee NUMERIC NOT NULL
);
ALTER TABLE TRANSACTION
    ADD CONSTRAINT transaction_unique_uuid UNIQUE (uuid);
    CREATE INDEX document_id_index ON TRANSACTION (document_id);

COMMENT ON COLUMN TRANSACTION.document_id is 'ID документа, по которому проведена транзакция';
COMMENT ON COLUMN TRANSACTION.uuid is 'Уникальный номер транзакции';
COMMENT ON COLUMN TRANSACTION.time is 'Время проведения транзакции';
COMMENT ON COLUMN TRANSACTION.sum is 'Сумма транзакции';
COMMENT ON COLUMN TRANSACTION.fee is 'Комиссия транзакции';