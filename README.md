# LIO-Host

LIO-Host is an embedded software that can be used to control an addressable LED chain of the WS2812 type.
The software uses a ready-made library for setting individual LEDs (https://github.com/rpi-ws281x).

The framework allows network connections, whereby the system can be controlled by other programs, for example a mobile phone app or a Windows program.
There are some repositories that offer these programs for this purpose.
- github.com/iamniklas/LIO-Connect-Android (Android app) PUBLIC
- github.com/iamniklas/LIO-Service-Unity (Unity Engine Plug-In) PRIVATE
- github.com/iamniklas/LIO-Connect-PC (Windows WPF program) PRIVATE
- github.com/iamniklas/LIO-Service-CS (General C-Sharp Program) PRIVATE

A procedure factory is used to run animations or procedures. The following types are currently available:

**BootComplete**: Executed when the LED chain is ready
**ColorInstantSet**: Immediately sets the color of each pixel to a specific color
**FadeInFadeOut**: Fades in a color softly and goes back to the old color
**FadeToMultiColor**: Fades in a color array softly and goes back to the old state
**FadeToUniformColor**: Smooth transition from all LEDs to a single one
**Fill**: Fills the LED chain from left to right in a specific color
**FillInterpolated**: Fills the LED chain interpolated from left to right in a specific color
**Rainbow**: Animated rainbow
**RainbowMono**: Solid color rainbow
**NoLongerReady**: Is executed when the LED chain is no longer ready


A **Raspberry Pi** is required as an embedded system.
The program can be started with the command * *sudo java -jar LIO-Host.jar* *.
