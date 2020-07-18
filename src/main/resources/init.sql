DROP TABLE IF EXISTS TRANSACTION;
DROP TABLE IF EXISTS DOCUMENTS;

CREATE TABLE DOCUMENTS(
                          id BIGINT PRIMARY KEY,
                          number varchar NOT NULL,
                          open_date TIMESTAMP NOT NULL,
                          company_name VARCHAR NOT NULL,
                          inn VARCHAR NOT NULL,
                          kpp VARCHAR NOT NULL
);

CREATE TABLE TRANSACTION(
                            id BIGINT PRIMARY KEY,
                            document_id BIGINT REFERENCES DOCUMENTS (id),
                            uuid uuid NOT NULL,
                            time TIMESTAMP NOT NULL,
                            sum NUMERIC NOT NULL,
                            transaction_fee NUMERIC NOT NULL
);