# PingPong

PingPong is a simple two-player Pong clone written in Java using the Swing framework. The project was originally created with NetBeans and includes an Ant `build.xml` for compiling and running the game.

## Features

- Local two player gameplay with keyboard controls (`W`/`S` for player one and `UP`/`DOWN` for player two).
- Login screen that requires each player to sign in before starting a match.
- Registration window for creating new users and a profile editor for updating existing accounts. User data is stored in `profili.txt` with SHA-256 hashed passwords.
- Score is kept during play; the first player to win three rounds is declared the winner.
- Simple end-game screen offering a rematch, return to the login screen or exiting the application.

## Building and Running

The project targets Java 21 and can be built using Ant.

```
ant jar   # compile and build JAR file
ant run   # run the game
```

Alternatively you can compile and run it manually:

```
# Compile
javac -d build/classes $(find src -name '*.java')

# Run
java -cp build/classes pingpong_atejzu.Pingpong_atejzu
```

When you first run the game you will see the login screen. Use the "Registracija" button to create accounts if none exist. After both players log in, click "Začni igro" to start playing.

## Repository Contents

- `src/` – Java source code for the game
- `profili.txt` – text file where user profiles are stored
- `build.xml` – Ant build script
- `nbproject/` – NetBeans project metadata

## License

This repository does not specify a license. If you intend to use or distribute the project, please contact the original author.

