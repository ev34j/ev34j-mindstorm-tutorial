# Mindstorm Tutorial
Before you can start programming the EV3 you have to:

1. Install a Wi-Fi dongle in your EV3
2. Install software on your OSX machine 
3. Create a bootable image of the [EV3DEV](http://www.ev3dev.org) Linux distro on an SD card

# Wi-Fi dongle installation
  Sadly, the *only* wi-fi dongle that works with the EV3 is the bulky
[NETGEAR N150 Wi-Fi USB Adapter (WNA1100)](http://www.amazon.com/NETGEAR-N150-Wi-Fi-Adapter-WNA1100/dp/B0036R9XRU).
For unknow reasons, Lego does not support any smaller dongles.  

# Software installation for your OSX machine

## Install Xcode and the OSX CommandLine Tools
  Download the latest version of Xcode from the [Apple developer website](https://developer.apple.com/xcode/download/) 
or get it [using the Mac App Store](http://itunes.apple.com/us/app/xcode/id497799835).

  Once you have Xcode installed, open a terminal, run **xcode-select --install**, 
and click the Install button to install the required command line developer tools. 
Don't worry if you see a message telling you the software cannot be installed because it is 
not currently available from the Software Update Server. This usually means you already have 
the latest version installed. You can also get the command line tools from 
[the Apple developer website](https://developer.apple.com/downloads/index.action).

## Install IntelliJ IDE
[Download](https://www.jetbrains.com/idea/) and install the Community edition of the IntelliJ IDE. You can
also use the Ultimate edition, if you already have that version installed.

## Install git 
You can install [git](https://en.wikipedia.org/wiki/Git_\(software\)) in either of two ways:

* Install with [brew](http://brew.sh) using : **brew install git**
* [Download](https://git-scm.com) and install the git package.

## Install sshpass
  Install sshpass using [these instructions](https://gist.github.com/arunoda/7790979).
Using sshpass is ordinarily a bad idea because it exposes passwords, but in this case,
the EV3 password is not sensitive and sshpass is easier to deal with than 
[ssh-keygen](https://www.digitalocean.com/community/tutorials/how-to-set-up-ssh-keys--2).

## Create a Github account
If you do not already have a [Github](https://github.com) account, 
go to Github and [sign up for one](https://github.com/join).

## Fork the Mindstorm Tutorial repo
Go to the [Mindstorm Tutorial repo](https://github.com/ev3dev-lang-java/mindstorm-tutorial) and click on the 
fork button in the uppoer right hand corner. This will create a copy of the repo in your Github account.

## Clone your forked Mindstorm Tutorial repo to your OSX machine

* Go to your [GitHub home](https://github.com) and click on your newly forked Mindstorm Tutorial repo.

* Copy the [HTTPS URL](https://help.github.com/articles/which-remote-url-should-i-use/) into your copy/paste buffer.

* Open a terminal and using the URL you just copied as the **git clone** argument, type the following.

```bash
mkdir git
cd git
git clone https://github.com/username/mindstorm-tutorial.git
cd mindstorm-tutorial
ls
```

# Creating a bootable image 

