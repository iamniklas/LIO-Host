# LIO-Host

LIO-Host ist eine Embedded-Software, mit der eine adressierbare LED-Kette vom Typ WS2812 gesteuert werden kann. 
Dafür nutzt die Software eine vorgefertigte Library zum Setzen einzelner LEDs (https://github.com/rpi-ws281x).

Das Framework lässt Netzwerk-Verbindungen zu, wodurch das System durch andere Programme, beispielsweise eine Handy App oder ein Windows-Programm, gesteuert werden kann.

Zum Ablauf von Animationen oder Prozeduren wird eine Procedure-Factory genutzt.
