FROM node:lts as builder

COPY . /app/soi23-game-ui
WORKDIR /app/soi23-game-ui

RUN npm install -g nx
RUN npm install
RUN nx build


FROM httpd:2.4

COPY ./conf.d /usr/local/apache2/conf.d
COPY --from=builder /app/soi23-game-ui/dist/soi23-game-ui /usr/local/apache2/htdocs

# Extend the original configuration
RUN echo "Include conf.d/*.conf" >> /usr/local/apache2/conf/httpd.conf