Updated: 04 MAY 14

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

- Server and wrapper shutdown
	- Press "1" in console and press enter to shut the server down. 
	
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
- Localization Flexibility (For plug-ins and ability to load from StarNub directory and merge of language files in case a language file is not updated, program will default to english for that string)

Starbound
	- No need to change your configuration port number (We will do that for you)
	
Milestone:
- Remote or Local Console Administration Client (To be determined)
	- Start, Stop, Restart Server
	- Player / IP List 
	- IP Banning
	- Server Stat's (Restarts Data (AutoRestart Count, Unresponsive and Crashes)
	
Installation
============
Moved to Wiki.
