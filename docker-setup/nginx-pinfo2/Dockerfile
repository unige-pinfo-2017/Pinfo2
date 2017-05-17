FROM nginx:latest

# Use a unique diffie-hellman prime
RUN apt-get update \
 && apt-get install --no-install-recommends --no-install-suggests -y openssl
RUN openssl dhparam -out /etc/ssl/certs/dhparam.pem 2048
RUN apt-get remove -y openssl

COPY nginx.conf /etc/nginx/nginx.conf
