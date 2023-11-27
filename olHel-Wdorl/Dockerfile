# use the jail base image
FROM pwn.red/jail
# copy the root files from any Docker image
COPY --from=ubuntu / /srv
# set the flag env
ENV flag=havceCTF{oWdrlelloH-HelloWorld}
# copy the program
RUN mkdir /srv/app
COPY ./challenge.c /srv/app/run.c
