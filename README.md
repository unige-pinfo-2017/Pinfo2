# Pinfo2 dashboard

L'archive git du group SEXI pour le projet informatique du cours de l'UNIGE
section d'informatique année 2017.

Cette *repository* git contient le code source pour le serveur et le client
du *dashboard* du *smartlab*.

# Méthodes de de deploiement

Le meilleur moyen de démarrer l'environement de test docker est de lancer
le script `launch-servers.sh`, d'attendre que les serveurs soient près et
de lancer `hot-deploy.sh`. Je conceil de relancer les serveurs de développement
seulement lorsqu'il y a eu un changement dans la configuration docker. Pour
l'instant il est nécessaire de redémarrer le réseau docker si vous avez
redémarré votre ordinateur.

Si vous voulez redeployer du code sans redémarrer les images docker, utilisez
`hot-deploy.sh`, vous pouvez deployer individuellement le backend ou le
frontend avec les arguments `hot-deploy.sh back` et `hot-deploy.sh front`
respectivement.

# Scripts pour accélérer le *workflow*

* Ouvre le fichier de log docker le plus récent.

```bash
function dlog {
    less $(ls /tmp/docker-log-* | sort --reverse | head -1)
}
```

