FROM mariadb:latest

# You should be able to upload your own sql file by writting it in the
# volume mount (the mount is written in the docker-compose.yml file)
ADD user.sql /docker-entrypoint-initdb.d/99-inittable.sql
ADD bashconf /root/.bashrc
