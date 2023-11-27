FROM gcc:13.2.0-bookworm as builder
COPY . /build
WORKDIR /build
RUN ./build.sh


FROM pwn.red/jail
COPY --from=ubuntu:22.04 / /srv
COPY --from=builder /build/guess-the-flag /srv/app/run
# ENV JAIL_ENV_FLAG=${FLAG_GUESS_THE_FLAG}