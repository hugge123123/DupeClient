# DupeClient

A Fabric client-mod starter for Minecraft Java 1.21.11.

## Current status

This repository was empty when inspected. This commit adds a minimal, buildable Fabric project structure and an automatic GitHub Actions build.

The current starter only verifies that the client mod loads. It does **not** include online-server duplication exploits, anti-cheat bypasses, or ban-evasion features. Further item-testing functionality should remain limited to worlds you own or have permission to test.

## Build

Requires Java 21 and Gradle 8.14.3 or compatible.

```bash
gradle build
```

The remapped mod JAR is created in `build/libs/`.

## GitHub Actions

Every push to `main` runs the build workflow and uploads the JAR as an artifact.
