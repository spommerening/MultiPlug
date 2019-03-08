# MultiPlug - Minecraft Plugin

Another approach on building a minecraft plugin.

## Building

* Install Maven
* go to MultiPlug root directory
* execute `mvn clean package`
* find resulting jar file in `target` directory

## Installation & Usage

* copy jar file into minecraft server plugin directory
* start minecraft server

## Commands

`/mp announcement`

* Display announcement message.<br />
The announcement message is continously repeated every X minutes (configurable).

`/mp motd`

* Display MOTD (Message Of The Day).

`/mp ores`

* Displays a list of ores within a certain radius nearby the player.

`/mp reload`

* Reload (update) config.yml from plugin directory. Also restarts the announcement message timer. (Permission node mp.reload)

## Authors

**Stefan Pommerening** - *Initial work*, <step@dmsp.de>, https://www.dmsp.de

Minecraft Server: `play.wumato.de`
