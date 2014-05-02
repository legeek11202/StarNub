Updated: 28Apr14

StarNub
=======
StarNub was created to solve a lot of Starbound server issues. This is a work in progress.

Wiki - https://github.com/StarNub/StarNub/wiki

Authors: Underbalanced & Teihoo

NOTE: This code is not final and will be modified for efficiency and clean up.

Requirements
============
- Java 8 (32 or 64 bit version)
- Any Windows or Linux OS

Current Features
========
- Easy Install
- No GUI - Console Only
- No need to start the Starbound Server
- No need to write a configuration file (First run, we will ask you questions)
	- Configuration Options (Auto Restart Timer, Starbound and StarNub Ports)
- Server Watchdog
	- Checks the Starbound Server process every 15 seconds. (Restarts on missing process(Crashes))
	- Checks the Starbound Server for a response via (UDP) every 15 seconds. (Restarts on Server lockups)
	- Auto Re-starter (Configurable)
- Clean console output. Only see player connects, disconnects and joins.
- Localization (Wiki how to coming soon)

Temporary Feature
	- Press "1" in console and press enter to shut the server down. (Will be replaced with a future menu)
	
Planned Features
================
Milestone:
- Network pass through for internal testing (Netty IO)

Milestone
- Create Player Name|IP HashMap(Completed Feature)
	
Milestone:
- Packet Classes
	
Milestone:
- Plug Frame Work
- StarNub API
	
Milestone:
- Wrapper Console Menu
	- Ungraceful Shutdown
	- Restart Server
	- Player/IP List 
	- IP Banning
	- Editable Ban List
	- Server Stat's

Milestone:
- Localization Flexibility (For plug-ins and ability to load from StarNub directory)

Starbound
	- No need to change your configuration port number (We will do that for you)
	
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
The below tutorial was taken from: 
http://www.webupd8.org/2012/09/install-oracle-java-8-in-ubuntu-via-ppa.html

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
