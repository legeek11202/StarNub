Updated: 11 MAY 14

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
	- No need to change your Starbound configuration port number (We do that for you, We do not change it back though!)
- Server Monitor (Checks every 20 seconds)
	- Checks the Starbound Server process every 15 seconds. (Restarts on Server crashes)
	- Checks the Starbound Server for a response via (TCP). (Restarts on Server lockups)
	- Auto Re-starter (Configurable)
- Clean console output. Only see player connects, disconnects and joins.
- Localization (Wiki how to coming later)
- Network Proxy (Pass through only right now)

- Server and wrapper shutdown
	- Press "1" in console and press enter to shut the server down. 
	
Planned Features
================
Milestone:
- Network Functionality
	-Players connecting have IP and UUID checked for a ban.
		- If player IP is changed to avoid a ban, but the UUID is banned, we will ban the new IP for you and let you know we did in console and logs.
		- Invalid packets will be dropped, and after X bad packets we will drop the connection
			- If they try to connect and send bad packets after a few disconnects, we will ban the IP for you and let you know we did in console and logs.
	- Statistics: 
		- Unique Connections
		- Player Online Last
		- Player Play Time

Milestone:
Various statistics information
Information Processing and Storage Methods (SQLite)

Milestone:
- Plug-ins Frame Work (Java plug-ins (.jars) and potentially LUA Plug-in support)
- StarNub API
- Documentation
		
Milestone:
SQL Option over SQLite (Needs a class to auto migrate)

Milestone:
- Localization Flexibility (For plug-ins and ability to load from StarNub directory and merge of language files in case a language file is not updated, program will default to english for that string)
- Wiki Update

Milestone:
- Code refinement
	
Milestone:
(iPhone and Android Application) 
(To be determined, further away time wise, will probably cooperative project with Mitch528)
- Regular Users
	- Can chat in-game
	- Access Server Statistics
	- Access Online Player List
	
- Administrators and Moderators can administer servers without being in game 
	- Start, Stop, Restart Server
	- Access Online Player List + IP List 
	- Access Server Statistics
	- Ban, kick and mute players.
	
Installation
============
Moved to Wiki.
