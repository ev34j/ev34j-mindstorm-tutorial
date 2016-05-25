# Ev34j Mindstorm Tutorial

## System setup

Before you can run Java programs on the EV3 there is some setup required:

1. [Install required software](https://github.com/ev3dev-lang-java/ev34j-mindstorm-tutorial/wiki/Install-required-Mac-software) on your Mac.
2. [Create a bootable image](https://github.com/ev3dev-lang-java/ev34j-mindstorm-tutorial/wiki/Create-a-bootable-image-for-the-EV3) of the [ev3dev distro](http://www.ev3dev.org) on a micro SD card.
3. [Establish a network connection](https://github.com/ev3dev-lang-java/ev34j-mindstorm-tutorial/wiki/Establish-a-network-connection) on your EV3.
4. [Install required software](https://github.com/ev3dev-lang-java/ev34j-mindstorm-tutorial/wiki/Install-required-EV3-software) on your EV3.

## Clone the ev34j-mindstorm tutorial repo

Open a [Terminal](https://en.wikipedia.org/wiki/Terminal_(OS_X)) window and clone the
[Mindstorm Tutorial repo](https://github.com/ev3dev-lang-java/ev34j-mindstorm-tutorial) from [GitHub](https://github.com)
with:

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

## Build and run programs

### pom.xml
The [pom.xml](https://github.com/ev34j/ev34j-mindstorm-tutorial/blob/master/pom.xml)
contains the configuration information required to build the program. The two relevant properties are:

| Property                   | Value                                                            |
|:---------------------------|:-----------------------------------------------------------------|
| **&lt;ev34j.version&gt;**  | update when the underlying ev34j library changes                 |
| **&lt;mainclass.name&gt;** | set this to the name of the java class that you want to execute  |

### Makefile
The [Makefile](https://github.com/ev34j/ev34j-mindstorm-tutorial/blob/master/Makefile) is provided to
make building and running programs easy. The configuration variables at the top *Makefile* include:

| Variable                | Value                                                       |
|:------------------------|:------------------------------------------------------------|
| **EV3_HOSTNAME**        | Update if you updated the value in /etc/hostname on the EV3 |
| **EV3_PASSWORD**        | Update if you change the default password on the EV3        |
| **JAR_NAME**            | No reason to change                                         |
| **LOG_PROP_NAME**       | No reason to change                                         |
| **SSH_PREFIX**          | Set to blank if you use [SSH keys](https://www.digitalocean.com/community/tutorials/how-to-set-up-ssh-keys--2) instead of [sshpass](https://gist.github.com/arunoda/7790979) |

The *Makefile* has the following targets:

| Target              | Action                                                  |
|:--------------------|:--------------------------------------------------------|
| **clean**           | erase everything in the target directory                |
| **build**           | build the uber-jar file                                 |
| **scp**             | copy the jar file to the EV3                            |
| **run**             | execute the jar file on the EV3                         |
| **debug**           | execute the jar file in the debgugger mode on the EV3   |
| **logging**         | execute the jar file with logging enabled on the EV3    |
| **kill**            | kill all java processes running on the EV3              |
| **copy-scripts**    | copy command-line execution scripts the EV3             |

The steps to build and run a program from the command line would be:

```bash
$ cd ev34j-mindstorm
$ # Build the jar
$ make build
$ # Copy the jar to the EV3
$ make scp
$ # Run the app on the EV3
$ make run
```

You could also execute the program while logged into the EV3:

```bash
$ make copy-scripts
$ ssh robot@ev3dev
robot@ev3dev:~$ run.sh
robot@ev3dev:~$ # Or you can invoke the jar directly with java
robot@ev3dev:~$ java -jar ev3robot-jar-with-dependencies.jar
```

**Remember** to rebuild the jar and copy it to the EV3 after making changes in the
source code. Also, if you rename your main java class or want to execute a different class, make sure you
update the **&lt;mainclass.name&gt;** value in the pom.xml.

## Building and running programs with IntelliJ










