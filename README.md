# EV34J Mindstorm Tutorial 

## System setup

Before you can run Java programs on your EV3 you have to:

1. [Install required software](https://github.com/ev3dev-lang-java/ev34j-mindstorm-tutorial/wiki/Install-required-Mac-software) on your Mac.
2. [Create a bootable image](https://github.com/ev3dev-lang-java/ev34j-mindstorm-tutorial/wiki/Create-a-bootable-image-for-the-EV3) of the [ev3dev distro](http://www.ev3dev.org) on a micro SD card.
3. [Establish a network connection](https://github.com/ev3dev-lang-java/ev34j-mindstorm-tutorial/wiki/Establish-a-network-connection) on your EV3.
4. [Install required software](https://github.com/ev3dev-lang-java/ev34j-mindstorm-tutorial/wiki/Install-required-EV3-software) on your EV3.

## Download the EV34J Mindstorm Tutorial

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
$ # Compile the code
$ make clean build
$ # Copy it to your EV3
$ make scp
$ # Run the app on your EV3
$ make run
```

## Using IntelliJ to edit and run programs









