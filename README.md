# SpielerUmriss

Clientseitige Fabric-Mod fuer Minecraft Java 1.21.11. Sie schaltet einen Umriss fuer andere Spieler um, die dem Client bereits bekannt sind. Die Taste ist in **Optionen > Steuerung > Tastenbelegung** unter **Spielerumrisse umschalten** frei belegbar (Standard: K).

## Voraussetzungen

- Minecraft Java 1.21.11
- Fabric Loader 0.18.4 oder neuer
- Fabric API fuer 1.21.11
- Java 21 zum Bauen

## Lokal bauen

Im Projektordner:

```powershell
gradle build
```

Die Mod-JAR liegt danach unter `build/libs/spielerumriss-1.0.0.jar`.

## GitHub Actions

Repository auf GitHub hochladen. Der Workflow baut bei Push, Pull Request oder manuellem Start und stellt die JAR als Workflow-Artefakt `spielerumriss-1.21.11` bereit. Im jeweiligen Workflow-Lauf findest du es unter **Artifacts**.

## Verhalten

- Nur andere Spieler werden markiert; der eigene Spieler bleibt unveraendert.
- Die Einstellung wirkt nur auf deinem Client und benoetigt keine Serverinstallation.
- Beim Ausschalten stellt die Mod den vorherigen Glow-Zustand wieder her.
- Beim Verlassen der Welt werden gespeicherte Zustandsdaten verworfen.
- Der Umriss verwendet Minecrafts Glow-Effekt und dessen Teamfarbe.
