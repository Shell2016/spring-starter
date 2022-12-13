FROM ubuntu:latest

RUN apt update
RUN apt install openssh-server -y

RUN groupadd michaelshell

RUN useradd -d /home/ivan -m -s /bin/bash -g michaelshell ivan
RUN echo "123\n123" | passwd ivan

RUN useradd -d /home/michaelshell -m -s /bin/bash -g michaelshell michaelshell
RUN mkdir -p /home/michaelshell/.ssh
COPY id_rsa.pub /home/michaelshell/.ssh/authorized_keys
RUN chown michaelshell /home/michaelshell/.ssh/authorized_keys && chgrp michaelshell /home/michaelshell/.ssh/authorized_keys
RUN chmod 640 /home/michaelshell/.ssh/authorized_keys

RUN service ssh start
EXPOSE 22
CMD ["/usr/sbin/sshd", "-D"]
