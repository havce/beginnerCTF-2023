FROM ubuntu:latest
RUN apt update && apt install -y python3 python3-pip

COPY ./src/requirements.txt /tmp/requirements.txt
RUN pip install --no-cache-dir -r /tmp/requirements.txt

COPY ./src /app
WORKDIR /app

RUN mkdir -p /app/instance && chown nobody:nogroup /app/instance

USER nobody
CMD ["gunicorn", "-w", "2", "-b", "0.0.0.0:5000", "app:app"]