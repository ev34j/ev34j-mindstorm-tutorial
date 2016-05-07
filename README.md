# Mindstorm Tutorial Setup

Before you can run Java programs on your EV3 you have to:

1. Install software on your Mac 
2. Create a bootable image of the [ev3dev](http://www.ev3dev.org) Linux distro on an SD card
3. Setup a network connection
4. Connect to the EV3

# Software installation for your Mac

## Install Xcode and the OSX CommandLine Tools
  Download the latest version of Xcode from the [Apple developer website](https://developer.apple.com/xcode/download/) 
or get it [using the Mac App Store](http://itunes.apple.com/us/app/xcode/id497799835).

  Once you have Xcode installed, open a Terminal window and type:
   
```bash
xcode-select --install
```

Click the Install button to install the required command line developer tools. 
Don't worry if you see a message telling you the software cannot be installed because it is 
not currently available from the Software Update Server. This usually means you already have 
the latest version installed. You can also get the command line tools from the
[Apple developer website](https://developer.apple.com/downloads/index.action).

## Install IntelliJ IDE
Download and install the [Community edition of the IntelliJ IDE]((https://www.jetbrains.com/idea/)). You can
also use the Ultimate edition if you already have that version installed.

## Install git 
You can install [git](https://en.wikipedia.org/wiki/Git_\(software\)) in either of two ways:

* With [brew](http://brew.sh) using: 

``` bash
brew install git
```

* With the [git installer]((https://git-scm.com)).

## Install sshpass
  Install sshpass using [these instructions](https://gist.github.com/arunoda/7790979).
Using sshpass is ordinarily a bad idea because it exposes passwords. But in this case,
the EV3 password is not sensitive and sshpass is easier to deal with than 
[ssh-keygen](https://www.digitalocean.com/community/tutorials/how-to-set-up-ssh-keys--2).
If you are comfortable using ssh-keygen, you can forgo installing sshpass and remove 
the sshpass command prefix from the [Makefile](https://github.com/ev3dev-lang-java/mindstorm-tutorial/blob/master/Makefile).

## Create a Github account
If you do not already have a [Github](https://github.com) account, 
go to Github and [sign up for one](https://github.com/join).

## Fork the Mindstorm Tutorial repo
Go to the [Mindstorm Tutorial repo](https://github.com/ev3dev-lang-java/mindstorm-tutorial) and click on the 
Fork button in the uppoer right hand corner. This will create a copy of the repo in your Github account.

## Clone your forked Mindstorm Tutorial repo to your Mac

* Go to your [GitHub home](https://github.com) and click on your newly forked Mindstorm Tutorial repo.

* Copy the [HTTPS URL](https://help.github.com/articles/which-remote-url-should-i-use/) into your copy/paste buffer.

* Open a Terminal window and, using the URL you just copied as the **git clone** argument, clone the repo with:

```bash
mkdir git
cd git
# username in the next line should be your github account name
git clone https://github.com/username/mindstorm-tutorial.git
cd mindstorm-tutorial
ls
```

# Create a bootable image for the EV3

These steps summarize [these instructions](http://www.ev3dev.org/docs/getting-started/):

1. [Download](https://github.com/ev3dev/ev3dev/releases) the latest ev3dev image .zip file for the EV3: 
[ev3dev-jessie-2015-12-30.img](https://github.com/ev3dev/ev3dev/releases/download/ev3dev-jessie-2015-12-30/ev3-ev3dev-jessie-2015-12-30.img.zip) 

Make sure the file you download has an **ev3-** prefix in the name. After the file is downloaded, OSX will unzip the file and
you will have a **ev3-ev3dev-jessie-2015-12-30.img** file in your download folder.

2. Copy the image to an SD card
  
You can do this from the [command line](http://www.ev3dev.org/docs/tutorials/writing-sd-card-image-osx-command-line/)
or using a GUI tool like [Apple Pi Baker](http://www.tweaking4all.com/hardware/raspberry-pi/macosx-apple-pi-baker/).

3. Boot the EV3 with the ev3dev SD card

Insert the SD card into the EV3 and power it up. 
If things are setup properly on the SD card, you will see LEDS flash orange and the Brickman app load in the display.

# Setup a network connection
 
  Sadly, the *only* wi-fi dongle that works with the native EV3 firmware is the bulky
[NETGEAR N150 Wi-Fi USB Adapter (WNA1100)](http://www.amazon.com/NETGEAR-N150-Wi-Fi-Adapter-WNA1100/dp/B0036R9XRU).
The [ev3dev](http://www.ev3dev.org) distro supports the NETGEAR N150, but it also works with smaller dongles, e.g.,
the [Edimax EW-7811Un](http://www.amazon.com/Edimax-EW-7811Un-150Mbps-Raspberry-Supports/dp/B003MTTJOY).
Thus, if you are content using Bluetooth for connectivity in the native mode and Wi-Fi in the 
[ev3dev](http://www.ev3dev.org)
mode, then get the smaller dongle. If you want Wi-Fi support in both modes, then you should get the NETGEAR N150.

Please note: the [NETGEAR G54/N150] (http://www.amazon.com/dp/B004VDR37K/ref=twister_B00F4PTSKY) 
does not work with either mode.

Insert a Wi-Fi dongle in the EV3. Navigate to **Wireless and Networks** in Brickman and then click on **Wi-Fi**.
You will be able to determine at this point if the Wi-Fi dongle is supported by ev3dev. If it is, you will see the option
select **Powered** and then **Start Scan**.  You should choose an SID and enter your password. 

# Connect to the EV3 

Once booted and connected to the network, the EV3 should be reachable as **ev3dev** from your Mac. 
To verify this, open a Terminal window and type:

```bash
ping ev3dev
```

You should see something like:

```bash
PING ev3dev (192.168.1.230): 56 data bytes
64 bytes from 192.168.1.230: icmp_seq=0 ttl=64 time=556.833 ms
64 bytes from 192.168.1.230: icmp_seq=1 ttl=64 time=12.056 ms
64 bytes from 192.168.1.230: icmp_seq=2 ttl=64 time=12.828 ms
64 bytes from 192.168.1.230: icmp_seq=3 ttl=64 time=8.587 ms
```

You can terminate the ping command with Control-C.

Now log into **ev3dev** as the user **robot** (using the password **maker**) with ssh:

```bash
ssh robot@ev3dev
```

Update the the ev3dev distro to the latest bits with:

```bash
sudo apt-get update
sudo apt-get upgrade
sudo apt-get dist-upgrade

/sbin/shutdown --reboot now
```

These commands will download and install the latest and greatest distro bits.
The last command will reboot the EV3.

