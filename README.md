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
$ # Copy it to your EV3
$ make scp
$ # Run the app on your EV3
$ make run
```

## The ev34j-mindstorm classes

The javadocs for the ev34j-mindstorm classes are at [http://docs.ev34j.com](http://www.ev34j.com).

### Motors
* [LargeMotor](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/motor/LargeMotor.html)
* [MediumMotor](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/motor/MediumeMotor.html)
* [SteeringMotors](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/motor/SteeringMotors.html)
* [TankMotors](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/motor/TankMotors.html)

### Batteries
* [Ev3Battery](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/battery/Ev3Battery.html)
* [RaspiBattery](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/battery/RaspiBattery.html)

### Buttons
* [Ev3Buttons](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/buttons/Ev3Buttons.html)

### Displays
* [Ev3Display](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/display/Ev3Display.html)

### Leds
* [Ev3StatusLights](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/leds/Ev3StatusLights.html)
* [BrickPiStatusLights](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/leds/BrickPiStatusLights.html)

### Sound
* [Ev3Sound](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/sound/Ev3Sound.html)

### Sensors
* [Ev3ColorSensor](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/sensors/Ev3ColorSensor.html)
* [Ev3GyroSensor](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/sensors/Ev3GyroSensor.html)
* [Ev3InfraredSensor](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/sensors/Ev3InfraredSensor.html)
* [Ev3PixySensor](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/sensors/Ev3PixySensor.html)
* [Ev3TouchSensor](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/sensors/Ev3TouchSensor.html)
* [Ev3UltrasonicSensor](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/sensors/Ev3UltrasonicSensor.html)
* [NxtGyroSensor](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/sensors/NxtGyroSensor.html)
* [NxtSoundSensor](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/sensors/NxtSoundSensor.html)
* [NxtTouchSensor](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/sensors/NxtTouchSensor.html)
* [NxtUltrasonicSensor](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/sensors/NxtUltrasonicSensor.html)
* [NxtV1ColorSensor](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/sensors/NxtV1ColorSensor.html)
* [NxtV2ColorSensor](http://ev34j.github.io/ev34j/index.html?com/ev34j/mindstorm/sensors/NxtV2ColorSensor.html)



## Using IntelliJ to edit and run programs










