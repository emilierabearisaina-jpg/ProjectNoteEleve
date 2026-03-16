INSERT INTO operateur (nom) VALUES
('<'),
('<='),
('>'),
('>=');

INSERT INTO resolution (nom) VALUES
('Petit'),
('Grand'),
('Moyenne');

INSERT INTO matieres (nom) VALUES
('JAVA'),
('PHP');

INSERT INTO eleves (nom) VALUES
('Candidat 1'),
('Candidat 2'); 

INSERT INTO correcteurs (nom) VALUES
('Correcteur 1'),
('Correcteur 2'),
('Correcteur 3');

INSERT INTO parametres (id_matiere,id_resolution,valeur,id_operateur) VALUES
(1,2,7,1),
(1,3,7,4),
(2,1,2,2),
(2,2,2,3);

INSERT INTO notes (id_eleve,id_correcteur,id_matiere,note) VALUES
(1,1,1,15), 
(1,2,1,10),
(1,3,1,12),      
(2,1,1,9),
(2,2,1,8),
(2,3,1,11),

(1,1,2,10),
(1,2,2,10),
(2,1,2,13),
(2,2,2,11);
