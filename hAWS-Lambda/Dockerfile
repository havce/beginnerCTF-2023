FROM ubuntu:20.04 as builder

RUN apt-get update && apt-get upgrade -y && \
  apt-get install -y build-essential libseccomp-dev

RUN mkdir -p /workdir

WORKDIR /workdir

RUN --mount=type=bind,target=. \
  gcc -o /chal ./chall.c -lseccomp 

FROM pwn.red/jail

COPY --from=builder / /srv

COPY ./hook.sh /jail/hook.sh

COPY --from=builder /chal /srv/app/run
