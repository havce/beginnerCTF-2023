FROM ubuntu:18.04 as builder

RUN apt-get update && apt-get upgrade -y && \
  apt-get install -y build-essential

RUN mkdir -p /workdir

WORKDIR /workdir

RUN --mount=type=bind,target=. \
  gcc -o /chal ./chall.c

FROM pwn.red/jail

COPY --from=ubuntu:18.04 / /srv
COPY --from=builder /chal /srv/app/run
