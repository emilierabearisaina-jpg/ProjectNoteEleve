
INSERT INTO matieres (nom) VALUES ('JAVA'), ('PHP');

INSERT INTO resolution (nom) VALUES ('Petit'), ('Grand'), ('Moyenne');

INSERT INTO operateur (nom) VALUES ('<'), ('<='), ('>'), ('>=');


INSERT INTO parametres (id_matiere, id_resolution, valeur, id_operateur) VALUES (1, 2, 5, 4);
INSERT INTO parametres (id_matiere, id_resolution, valeur, id_operateur) VALUES (1, 3, 9, 1);

INSERT INTO eleves (nom) VALUES ('E1');

INSERT INTO correcteurs (nom) VALUES ('C1'), ('C2');

INSERT INTO notes (id_eleve, id_correcteur, id_matiere, note) VALUES (1, 1, 1, 10), (1, 2, 1, 17);


