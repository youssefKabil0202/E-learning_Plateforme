Rapport sur un mini projet Plateforme E-learning : 

📋 Contexte
Dans le cadre de l'enseignement moderne, il est essentiel de digitaliser le suivi académique des apprenants. Cette plateforme e-learning permet une gestion complète des cours, apprenants, et de leur progression. Le système remplace les méthodes traditionnelles de suivi manuel par une solution informatisée efficace.

❗ Problématique
Les institutions éducatives rencontrent des difficultés dans :
- Le suivi individualisé de chaque apprenant
- L'évaluation continue et traçable
- La gestion des cours et des filières
- La visualisation des performances académiques
- La génération de rapports et statistiques

🎯 Objectif:
Développer une application Java complète de gestion e-learning avec :
- Suivi précis de la progression des apprenants
- Gestion des cours et filières
- Évaluation et notation automatisée
- Tableaux de bord et statistiques visuelles
- Interface utilisateur intuitive

✨ Fonctionnalités Principales
 1. Gestion des Apprenants
- **Ajouter/Modifier/Supprimer** des apprenants
- **Visualisation complète** des profils apprenants
- **Suivi individuel** de la progression
- **Historique** des inscriptions et scores

 2. Gestion des Cours
- **Création** de nouveaux cours (titre, catégorie, durée)
- **Organisation** par filières
- **Assignation** de durée et catégories
- **Visualisation** de l'historique des cours

 3. Suivi de Progression
- **Enregistrement** des scores (0-100)
- **Date-stamping** automatique des évaluations
- **Calcul** des moyennes par cours
- **Suivi chronologique** des performances

 4. Filtrage et Recherche Avancée
- **Filtrer par catégorie** de cours
- **Filtrer par score** (minimum/maximum)
- **Recherche** d'apprenants par nom/email
- **Tri** par date de progression

 5. Visualisation et Rapports
- **Graphiques** des moyennes de scores par cours
- **Statistiques** par filière
- **Tableaux de bord** interactifs
- **Export** des données de progression

 🏗️ Conception de la base de données
 
📊 Diagrammes :

Diagramme use case:


<img width="637" height="906" alt="Screenshot 2026-01-31 144231" src="https://github.com/user-attachments/assets/a705fa8d-5df9-4f86-b769-2bb744c850e4" />

Diagramme de classe :

<img width="483" height="394" alt="Screenshot 2026-01-31 144330" src="https://github.com/user-attachments/assets/d9428b25-c2c5-4819-89c7-00807a125154" />

🗃 Requêtes sql:

CREATE TABLE apprenant (

    id INT PRIMARY KEY AUTO_INCREMENT,
    
    nom VARCHAR(100) NOT NULL,
    
    email VARCHAR(100) UNIQUE NOT NULL,
    
    dateInscription DATE DEFAULT CURRENT_DATE
);


-- Table des Cours

CREATE TABLE cours 

    id INT PRIMARY KEY AUTO_INCREMENT,
    
    titre VARCHAR(200) NOT NULL,
    
    categorie VARCHAR(50),
    
    dureeHeures DECIMAL(5,2)
);


-- Table de Progression

CREATE TABLE progression (

    id INT PRIMARY KEY AUTO_INCREMENT,
    
    cours_id INT,
    
    apprenant_id INT,
    
    date DATE DEFAULT CURRENT_DATE,
    
    score DECIMAL(5,2) CHECK (score BETWEEN 0 AND 100),
    
    FOREIGN KEY (cours_id) REFERENCES cours(id),
    
    FOREIGN KEY (apprenant_id) REFERENCES apprenant(id)
);

-- Table des Filières

CREATE TABLE filiere (

    id INT PRIMARY KEY AUTO_INCREMENT,
    
    nom VARCHAR(100) NOT NULL,
    
    description TEXT
    
);

-- Table Admin

CREATE TABLE admin (

    id INT PRIMARY KEY AUTO_INCREMENT,
    
    login VARCHAR(50) UNIQUE NOT NULL,
    
    password VARCHAR(255) NOT NULL
);

🛠 Technologies Utilisées:

Framework d'interface graphique : Java Swing

Base de données : MySQL

Accès aux données : JDBC

Outils de développement : NetBeans (IDE Java) 

Gestion de base de données : phpMyAdmin

🎥 Démo video


https://github.com/user-attachments/assets/26eb03c4-a6b1-4f3b-98f2-66103f6e7b82






