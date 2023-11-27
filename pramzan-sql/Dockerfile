FROM node:latest

# Create app directory
WORKDIR /usr/src/app
COPY package*.json ./
RUN npm ci --only=production


# Bundle app source
COPY . .

#Run NPM install in frontend project
WORKDIR /usr/src/app/frontend
COPY frontend/package*.json ./
RUN npm ci --only=production

#Build frontend
RUN npm run build

#Back to backend workdir
WORKDIR /usr/src/app

EXPOSE 80

CMD [ "node", "App.js" ]