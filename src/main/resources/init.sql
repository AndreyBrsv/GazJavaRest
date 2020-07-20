DROP TABLE IF EXISTS TRANSACTION;
DROP TABLE IF EXISTS DOCUMENTS;

CREATE TABLE DOCUMENTS(
                          id BIGINT auto_increment PRIMARY KEY,
                          number varchar NOT NULL,
                          open_date TIMESTAMP NOT NULL,
                          company_name VARCHAR NOT NULL,
                          inn VARCHAR NOT NULL,
                          kpp VARCHAR NOT NULL
);

ALTER TABLE DOCUMENTS
    ADD CONSTRAINT documents_unique_number UNIQUE (number);

CREATE TABLE TRANSACTION(
                            id BIGINT PRIMARY KEY,
                            document_id BIGINT REFERENCES DOCUMENTS (id),
                            uuid uuid NOT NULL,
                            time TIMESTAMP NOT NULL,
                            sum NUMERIC NOT NULL,
                            fee NUMERIC NOT NULL
);

ALTER TABLE TRANSACTION
    ADD CONSTRAINT transaction_unique_uuid UNIQUE (uuid);
    CREATE INDEX document_id_index ON TRANSACTION (document_id);