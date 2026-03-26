INSERT INTO client (nom, contact) VALUES 
('Rakoto', '034 11 222 33'),
('Randria', '033 44 555 66');

INSERT INTO demande (date, lieu, district, client_id) VALUES 
('2023-05-10', 'Ambohibao', 'Ambohidratrimo', 1),
('2023-06-15', 'Ilafy', 'Antananarivo Avaradrano', 2);

INSERT INTO status (libelle) VALUES 
('crée'),
('Devis crée'),
('Acceptation devis'),
('Refusé');
