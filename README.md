# Ev34j Mindstorm Tutorial

## System setup

Before you can run Java programs on your EV3 you have to:

1. [Install required software](https://github.com/ev3dev-lang-java/ev34j-mindstorm-tutorial/wiki/Install-required-Mac-software) on your Mac.
2. [Create a bootable image](https://github.com/ev3dev-lang-java/ev34j-mindstorm-tutorial/wiki/Create-a-bootable-image-for-the-EV3) of the [ev3dev distro](http://www.ev3dev.org) on a micro SD card.
3. [Establish a network connection](https://github.com/ev3dev-lang-java/ev34j-mindstorm-tutorial/wiki/Establish-a-network-connection) on your EV3.
4. [Install required software](https://github.com/ev3dev-lang-java/ev34j-mindstorm-tutorial/wiki/Install-required-EV3-software) on your EV3.

## Clone the ev34j-mindstorm tutorial

Open a [Terminal](https://en.wikipedia.org/wiki/Terminal_(OS_X)) window and clone the
[Mindstorm Tutorial repo](https://github.com/ev3dev-lang-java/ev34j-mindstorm-tutorial) from [GitHub](https://github.com) with:

```bash
$ cd ~
$ mkdir git
$ cd git
$ git clone https://github.com/ev3dev-lang-java/mindstorm-tutorial.git
$ cd ev34j-mindstorm-tutorial
$ ls
```

Verify that you everything it setup properly with:

```bash
$ # Build the jar
$ make clean build
$ # Copy the jar to your EV3
$ make scp
$ # Run the app on your EV3
$ make run
```

## The ev34j-mindstorm classes

The ev34j-mindstorm classes are outlined
[here](https://github.com/ev34j/ev34j-mindstorm-tutorial/wiki/Ev34j-Mindstorm-Object-Summary)
and the javadocs are [here](http://docs.ev34j.com).

## Building and running programs

### pom.xml
The [pom.xml](https://github.com/ev34j/ev34j-mindstorm-tutorial/blob/master/pom.xml)
is used to specify the dependancies of your program. The two relevant properties are:
* **<ev34j.version>** updated only when the underlying ev34j library is updated.
* **<mainclass.name>** set this to the name of the Java class that you want to execute with the uber-jar.


### Makefile
The [Makefile](https://github.com/ev34j/ev34j-mindstorm-tutorial/blob/master/Makefile) is provided to
make building and running programs easy. The configuration variables at the top *Makefile* include:
* **EV3_HOSTNAME** Update if you updated the value in /etc/hostname on the EV3
* **EV3_PASSWORD** Update if you change the default password on the EV3
* **JAR_NAME** No reason to change
* **LOG_PROP_NAME** No reason to change
* **SSH_PREFIX** Set to blank if you [set up SSH keys](https://www.digitalocean.com/community/tutorials/how-to-set-up-ssh-keys--2)
instead of [sshpass](https://gist.github.com/arunoda/7790979)

The *Makefile* has the following targets:

| Target              | Action                                                  |
| ------------------- | ------------------------------------------------------- |
| **clean**           | erase everything in the target directory                |
| **build**           | build the uber-jar file                                 |
| **scp**             | copy the jar file to the EV3                            |
| **run**             | execute the jar file on the EV3                         |
| **debug**           | execute the jar file in the debgugger mode on the EV3   |
| **logging**         | execute the jar file with logging enabled on the EV3    |
| **kill**            | kill all java processes running on the EV3              |

The steps to build and run a program from the command line would be:

```bash
$ cd ev34j-mindstorm
$ make build
$ make run
```


## Building and running programs with IntelliJ










