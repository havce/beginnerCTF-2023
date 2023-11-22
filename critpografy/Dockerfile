FROM golang:1.21.4-alpine3.17 AS builder

WORKDIR /app

COPY go.mod ./
RUN go mod download

COPY main.go ./

RUN go build -o crytpografy main.go

FROM gcr.io/distroless/static-debian12

COPY --from=builder /app/crytpografy /crytpografy

EXPOSE 1337

CMD ["/crytpografy"]
