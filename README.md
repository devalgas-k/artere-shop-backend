# Artere Shop Backend

Application backend pour Artere Shop, basée sur **Spring Boot 3**, **Java 21**, **Spring Modulith** et les principes du **Domain-Driven Design (DDD)** via **jMolecules**.

## 🏗️ Structure du Projet

Le projet suit une architecture hexagonale (Ports & Adapters) organisée autour de modules métiers (Modulith) :

- `cart/` : Module de gestion du panier (paniers, articles).
- `catalogue/` : Module de gestion du catalogue (produits, arborescence des catégories).
- `shared/` : Code partagé (types communs comme `Money`, exceptions globales).

Chaque module est structuré de manière stricte :
- `domain/` : Modèle métier (Entities, Value Objects, Agrégats) et Ports (interfaces `in` et `out`). Ne dépend d'aucun framework technique.
- `application/` : Implémentation des cas d'utilisation (Use Cases / Services métiers orchestrant le domaine).
- `infrastructure/` : Adapters techniques (Web/REST Controllers, Persistence JPA, Mappers MapStruct, configuration).

## 🚀 Lancer le projet

### Prérequis
- Java 21
- Maven

### Build
Pour nettoyer, recompiler le projet et lancer les tests :
```bash
mvn clean install
```

### Run
Pour démarrer l'application localement :
```bash
mvn spring-boot:run
```
L'application démarre par défaut sur le port **8080**.

## 📖 Swagger UI & API Docs

Une fois l'application démarrée, l'interface Swagger UI est disponible pour explorer et tester facilement les APIs exposées :

👉 **[Accéder à Swagger UI](http://localhost:8080/swagger-ui.html)**
👉 **[Accéder à la spec OpenAPI JSON](http://localhost:8080/v3/api-docs)**

## 🧪 Jeux de données pour tester via Swagger

Voici des exemples de *payloads* (JSON) à utiliser dans Swagger UI pour tester les différents endpoints.

### 1. Créer une Catégorie (`POST /api/v1/categories`)
```json
{
  "name": "Électronique",
  "description": "Appareils électroniques et gadgets",
  "parentId": null
}
```
*Note : Vous pouvez ensuite créer une sous-catégorie en renseignant le `parentId` avec l'ID de la catégorie fraîchement créée.*

### 2. Récupérer l'arborescence des catégories (`GET /api/v1/categories`)
Permet de visualiser l'arbre complet des catégories créées.

### 3. Créer un Panier (`POST /api/v1/carts`)
*Aucun payload n'est nécessaire.*
Cliquez simplement sur "Execute". La réponse vous retournera un objet contenant l'`id` du panier créé (ex: `1`), à utiliser pour les requêtes suivantes.

### 4. Ajouter un article au panier (`POST /api/v1/carts/{cartId}/items`)
*(Remarque : La validation de l'ajout nécessite qu'un produit avec le `productId` renseigné existe en base de données. Vous pouvez injecter des produits de test via la console H2 ou des scripts d'initialisation).*
```json
{
  "productId": 1,
  "quantity": 2
}
```

### 5. Consulter le panier (`GET /api/v1/carts/{cartId}`)
Renseignez le `cartId` dans les paramètres Swagger pour récupérer le détail des articles et le montant total calculé.
