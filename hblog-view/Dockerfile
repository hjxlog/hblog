# 构建阶段
FROM node:20-alpine AS build
WORKDIR /app
COPY . .

# 设置淘宝 NPM 镜像（加速 npm install）
RUN npm config set registry https://registry.npmmirror.com

# 安装依赖并构建
RUN npm install
RUN npm run build:dev

# 运行阶段：用 nginx 提供静态资源服务
FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
COPY ./nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
