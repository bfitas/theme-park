FROM amazoncorretto:17-alpine3.18
VOLUME /tmp
ADD /build/libs/*.jar theme-park-ride-gradle.jar
EXPOSE 5000
ENTRYPOINT exec java -jar theme-park-ride-gradle.jar
HEALTHCHECK --interval=5m --timeout=3s --retries=3 \
CMD curl -f http://localhost:5000/ || exit 1