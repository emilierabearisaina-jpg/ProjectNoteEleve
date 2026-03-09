# Déploiement Git avec branches séparées

Voici les étapes à suivre à la fin de votre projet pour héberger votre code sur GitHub/GitLab avec deux branches distinctes :
- **`code-entier`** (ou main) : Contient tout le projet Spring Boot.
- **`base-sql`** : Ne contient que les fichiers de la base de données.

---

## 1. Initialiser Git

Ouvrez un terminal à la racine de votre projet (`d:\MR NAINA\NoteEleve`) et connectez votre dépôt distant :

```bash
git init
git remote add origin https://github.com/VOTRE_PSEUDO/VOTRE_DEPO.git
```
*(N'oubliez pas de remplacer l'URL par l'URL de votre dépôt Git)*

---

## 2. Créer la branche avec TOUT LE CODE

C'est votre branche principale.

```bash
# 1. Créer la branche (si elle n'existe pas déjà) ou s'y placer
git checkout -b code-entier

# 2. Ajouter tous les fichiers (le .gitignore va empêcher d'envoyer les fichiers inutiles)
git add .

# 3. Marquer l'ajout
git commit -m "feat: ajout du projet complet Spring Boot"

# 4. Envoyer sur le dépôt distant (GitHub/GitLab)
git push -u origin code-entier
```

---

## 3. Créer la branche avec UNIQUEMENT LA BASE DE DONNÉES

On va utiliser une branche "orpheline" (totalement vide et indépendante) :

```bash
# 1. Créer une branche orpheline vide
git checkout --orphan base-sql

# 2. Vider complètement le suivi Git courant
git rm -rf .

# 3. Ajouter UNIQUEMENT le dossier de la base
git add base/

# 4. Marquer l'ajout
git commit -m "feat: scripts SQL initiaux"

# 5. Envoyer cette nouvelle branche sur le dépôt distant
git push -u origin base-sql
```

---

## Comment revenir sur votre code entier plus tard ?

Si vous avez besoin de modifier votre code Java après avoir créé la branche SQL, vous pourrez naviguer entre vos branches facilement avec :

```bash
# Revenir sur le code entier
git checkout code-entier

# Revenir sur les fichiers SQL
git checkout base-sql
```
