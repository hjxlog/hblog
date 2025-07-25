# 使用 Maven 镜像进行构建
FROM maven:3.8.4-openjdk-8 AS builder

# 自定义 settings.xml 使用阿里云镜像
COPY settings.xml /usr/share/maven/conf/settings.xml

# 设置工作目录
WORKDIR /app

# 复制 pom.xml 和源代码
COPY pom.xml .
# 预先下载依赖到本地仓库
RUN mvn dependency:go-offline -B

COPY src ./src

# 执行 Maven 构建
RUN mvn clean package -DskipTests

# 使用运行时镜像
FROM eclipse-temurin:8-jre

# 设置工作目录
WORKDIR /app

# 从构建阶段复制生成的 JAR 文件
COPY --from=builder /app/target/hblog-api-1.0.jar app.jar

# 设置卷和端口
VOLUME /tmp
EXPOSE 8889

# 设置入口点
ENTRYPOINT ["java", "-jar", "app.jar"]
