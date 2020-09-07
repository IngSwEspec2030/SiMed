FROM node:14-alpine AS build
WORKDIR /usr/src/app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build
RUN npm run build --prod

FROM nginx:1.19-alpine
COPY --from=builder /usr/src/app/dist/simed-portal/ /usr/share/nginx/html