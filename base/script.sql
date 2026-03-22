CREATE DATABASE fourrage_eau;

\c fourrage_eau;

CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    contact VARCHAR(255) NOT NULL
);

CREATE TABLE demande (
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    lieu VARCHAR(255) NOT NULL,
    district VARCHAR(255) NOT NULL,
    client_id INT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client(id)
);

CREATE TABLE type_devis (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(255) NOT NULL
);

CREATE TABLE devis (
    id SERIAL PRIMARY KEY,
    montant_total DOUBLE PRECISION NOT NULL,
    date DATE NOT NULL,
    type_devis_id INT REFERENCES type_devis(id),
    demande_id INT REFERENCES demande(id)
);

CREATE TABLE details_devis (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(255) NOT NULL,
    montant DOUBLE PRECISION NOT NULL,
    devis_id INT REFERENCES devis(id)
);

CREATE TABLE status (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(255) NOT NULL
);

CREATE TABLE travaux (
    id SERIAL PRIMARY KEY,
    demande_id INT REFERENCES demande(id)
);

CREATE TABLE demande_statut (
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    travaux_id INT REFERENCES travaux(id),
    status_id INT REFERENCES status(id)
);
