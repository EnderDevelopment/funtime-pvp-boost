# Minecraft PvP Client Mod for Fantime

Funtime PvP Boost is a client-side Minecraft Fabric mod for the Fantime server, providing lightweight visual overlays, performance-friendly HUD rendering, and PvP quality-of-life features for Minecraft 1.21.1.

## Install

1. Install Minecraft 1.21.1.
2. Install Fabric Loader 0.16.9 or newer.
3. Install Fabric API 0.107.0+1.21.1 or newer.
4. Use Java 21 or newer.
5. Build the project with `./gradlew build`, or obtain the compiled mod JAR.
6. Place `fantime-client-1.0.0.jar` in your `.minecraft/mods` folder.
7. Start Minecraft with the Fabric profile and join the Fantime server.

The mod is client side and does not need to be installed on the server.

## Use

The mod provides the following features:

- Compact HUD displaying FPS, ping, and XYZ coordinates.
- Keystrokes overlay for W, A, S, D, and Space.
- Armor HUD displaying equipped armor items and their durability information.
- Toggleable auto sprint that activates while moving forward, provided the player is not sneaking and has sufficient hunger.
- Toggleable fullbright that temporarily raises gamma and restores the previous gamma value when disabled.
- Minimal dark teal visual styling with lightweight per-frame rendering.

Default keybinds:

- `H` toggles the main HUD.
- `K` toggles the keystrokes overlay.
- `J` toggles the armor HUD.
- `R` toggles auto sprint.
- `B` toggles fullbright.

All keybinds can be changed in Minecraft's Controls menu under the Fantime Client category.

To build the mod, use Java 21 and run:

```bash
./gradlew build
```

On Windows, run:

```text
gradlew.bat build
```

The compiled JAR is created in the `build/libs` directory.

---

More Minecraft mod projects by EnderDevelopment: https://github.com/EnderDevelopment

Project page: https://dash.enderdevelopment.com/community/adf45bb8-525d-45a5-b351-954624b1d461?utm_source=github&utm_medium=readme&utm_campaign=auto_publish&utm_content=adf45bb8-525d-45a5-b351-954624b1d461
