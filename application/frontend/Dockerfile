FROM node:14-alpine AS build-step
WORKDIR /usr/src/app
COPY package.json package.json
RUN npm install    
RUN npm install -g @angular/cli
COPY . .
RUN npm run build --prod
FROM nginx:1.19-alpine
COPY nginx.config /etc/nginx/conf.d/default.conf
COPY --from=build-step /usr/src/app/dist/simed-portal /usr/share/nginx/html
# expose port 80
EXPOSE 80
# run nginx
CMD ["nginx", "-g", "daemon off;"]
