-- Réinitialisation des données
TRUNCATE TABLE 
    demande_statut, 
    travaux, 
    status, 
    details_devis, 
    devis, 
    type_devis, 
    demande, 
    client 
RESTART IDENTITY CASCADE;

 