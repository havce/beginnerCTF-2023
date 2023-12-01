FROM ubuntu:18.04 as builder

RUN apt-get update && apt-get upgrade -y && \
  apt-get install -y build-essential

RUN mkdir -p /workdir

WORKDIR /workdir

COPY ./chall.c /workdir/chall.c

RUN gcc -o chal -fno-stack-protector -no-pie ./chall.c

FROM pwn.red/jail

COPY --from=ubuntu:18.04 / /srv
COPY --from=builder /workdir/chal /srv/app/run
