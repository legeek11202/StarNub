Updated: 28Apr14

StarNub
=======
StarNub was created to solve a lot of Starbound server issues. This is a work in progress.

Authors: Underbalanced & Teihoo

NOTE: This code is not final and will be modified for efficiency and clean up.

Requirements
============
- Java 8 (32 or 64 bit version)
- Any Windows or Linux OS

Features
========
- Easy Install
- No GUI - Console Only
- No need to start the Starbound Server
- Server Watchdog
	- Checks the Starbound Server process every 15 seconds. (Restarts on missing process(Crashes))
	- Checks the Starbound Server for a response via (UDP) every 15 seconds. (Restarts on Server lockups)
	- Auto Re-starter (Default 3 Hours)

Temporary Feature
	- Press "1" in console and press enter to shut the server down. (Will be replaced with a future menu)
	
Planned Features
================
Milestone 1:
Network pass through for internal testing (Netty IO)

Milestone 2
- Configuration Options (Auto Restart, Starbound and StarNub Ports)
- Formated console output, reducing clutter and pointless messages from the Starbound Process
- Create Player Name|IP hashmap for console banning	
	
Milestone 3:
Packet Classes
	
Milestone 4:
Plug Frame Work
StarNub API
	
Milestone 5:
- Wrapper Console Menu
	- Ungraceful Shutdown
	- Restart Server
	- Player/IP List (Created via Starbound process output stream)(No annoying color tags)
	- IP Banning
	- Editable Ban List

Installation
============
1. Place the StarNub.jar in your starbound directory.
		Windows Example: C:\Program Files (x86)\Steam\steamapps\common\Starbound\StarNub.jar
		Linux Example: /home/user/starbound/StarNub.jar
		
2. For Windows place server.bat into the same directory as StarNub.jar
   For Linux place server.sh into the same directory as StarNub.jar
 
3. Run the server.bat or for Linux type ./server.sh

(Coming Soon)4. First run will walk through a auto configuration of the wrapper through the console. If you do not select options 
	defaults will be selected for you. They can be changes by accessing and editing the configuration called
	StarNub.*** (Coming Soon).
	
Java 8 Installation on Linux
============================
Method 1:

1. sudo add-apt-repository ppa:webupd8team/java
2. sudo apt-get update
3. sudo apt-get install oracle-java8-installer

Method 2:

- Automated installation (auto accept license)
1. echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections

On Completion: 

1. java -version
	- Should display the following line and then some extra data "java version 1.8.0"
