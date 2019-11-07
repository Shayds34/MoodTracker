# MoodTracker

![banner](https://i.goopics.net/pk3vq.png)

## I. Introduction
### I.1 Objet
  Ce document décrit les fonctionnalités pour la mise en œuvre d’une application mobile pour le Ministère du Bonheur et de la Bonne Humeur, créé pour l’occasion par
le Gouvernement.

### I.2 Contexte
  Monsieur Joyeux, à la tête du Ministère nouvellement créé, voudrait développer une application
mobile Android s’inspirant de [Mr Mood](https://apps.apple.com/fr/app/mr-mood/id557107386) pour iOS.

Chaque jour, l’utilisateur note son humeur dans l’application. Il peut, s’il le souhaite, ajouter un
commentaire afin de décrire les raisons de sa bonne ou mauvaise humeur.

## II. Fonctionnalités
### II.1 Choix de l’humeur
  L’application démarre sur son écran d’accueil qui est une humeur joyeuse. Symbolisée par le
smiley « :) » au format d’une image sur un fond vert clair.

![smiley](https://i.goopics.net/xXNm4.png)

  Le choix de l’humeur se fait en faisant glisser le doigt sur l’écran : de bas en haut pour l’humeur
joyeuse suivante et de haut en bas pour l’humeur précédente, de moins en moins joyeuse.

  L’application mémorisera définitivement la dernière humeur choisie à minuit pile.

### II.2 Ajouter un commentaire
  Le bouton de gauche propose à l’utilisateur de poster un commentaire sur l’humeur qu’il choisit.
Ce commentaire sera enregistré s’il est associé avec la dernière humeur choisie lors du passage
au jour suivant.

![comment](https://i.goopics.net/vGb1o.png)

### II.3 Historique
  Le bouton de droite permet d’afficher l’historique des humeurs des sept derniers jours, classées
de la plus récente à la plus ancienne.

  Les humeurs sont classées en conservant pour couleur de fond leur couleur d’origine. Le
fragment permettant d’afficher l’humeur est d’autant plus grand que l’humeur est joyeuse.
  Si un commentaire a été associé avec cette humeur, un bouton est alors affiché permettant de
visionner le commentaire via un message Toast (message éphémère centré en bas de l’écran de
l’utilisateur).

  Le bouton de partage propose de partager l’humeur et le commentaire (s’il y en a un) en utilisant
les applications installées sur le mobile de l’utilisateur (SMS, email, Facebook, Twitter,
WhatsApp, Discord, etc.).

