# FROM node:14.17.0-alpine As builder
# WORKDIR /app
# COPY . .
# RUN npm install
# RUN npm run build

# FROM nginx:1.15.8-alpine
# FROM nginx:1.15.8-alpine
# COPY --from=builder /app/build/ /usr/share/nginx/html
FROM node:14.17.0-alpine
WORKDIR /app

COPY package.json .
RUN npm install
COPY . .
CMD ["npm", "start"]