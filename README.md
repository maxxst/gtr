# Software Praktikum 2013 (GTR)


Rogue-like game, written in Java. Heute nur ein Prototyp, morgen schon ein Dragotöter. 

Es heiße: `Grand Theft Rogue`

## Installation
Wir haben eine ausführbare JAR Datei beigefügt. Es sollte also genügen, sofern JAVA installiert ist, sich allein `gtr.jar` herunterzuladen und auzuführen.

## Steuerung
Die Hauptfigur `@` agiert wie folgt:

### Laufen
- q ↖
- w ↑
- e ↗
- a ←
- s ↓
- d →
- z ↙
- c ↘

### Schießen
- u ↖
- i ↑
- o ↗
- j ←
- k ↓
- l →
- m ↙
- . ↘


## Geschichte
Gestranded auf einer spärlich besiedelten Insel wacht $name auf. Nackt. Neben ihm liegt nur eine Pistole mit einem halben Magazin. $name kommt langsam zu sich und nimmt entfernte Stimmen wahr…

## Spielmechanik

### Szenerie
- Zeit erinnert an heute
- keine Magie
- $name hat keine Erinnerung an seine Fähigkeiten oder irgendwas
- in Dialogen kann man sich im laufe des Spiels entscheiden wie man sich spezialisiert
- Moral: keine … bisher

### Schwierigkeitsgrad & Speichermöglichkeiten
Jede Insel/Stadt/Stadtteil ist in sich abgeschlossen und sterben wirft an den Anfang dieser Insel. So kann jede Insel eine neue Geschichte mit anderem Schwierigkeitsgrad bieten. Der Beginn kann aber immer gleich bleiben.

__Der Prototyp soll eher als Tutorial fungieren__

### Attribute
#### Person (noch nicht vorhanden)
- Sehkraft (Reichweite)  
- Ruhe (Genauigkeit)
- Kraft (Gesundheit)
- Glück
- Angst
- Level

#### Waffen
Es gibt verschiedene Waffentypen die Namen bekommen und dann zufällige Werte bekommen
Außerdem soll etwas wie "Sockel" vorgesehen sein, sodass man Waffen mit Items aufrüsten kann
(z.B. Railgun des Glücks )

- Schaden (sofort || per Runde)
- Reichweite von bis
- Durchschlagskraft (Hoher durchschlag kann meherere Gegner treffen) (fehlt noch)
- Umgebungsschaden
- Geschwindigkeit (Felder/Runde || sofort)

### Technik
#### Geschichte voranbringen (nope)
Es gibt an zufälligen Orten generierte "Storyteller" (Reihenfolge im Quellcode), die die Geschichte voran bringen sollen und Spezialisierungen ermöglichen

#### Wünsche
- Tag / Nacht Zyklus mit Sichtweitenbeschränkung
- Alle Gebäude sind begehbar - manche beherbergen Dungeons 
- EasterEggs `Q`, `Telefonzellen` 

__Umgebungen/Szenerie/Story/Waffen/Gegner/Dialoge müssen extern geladen werden können, damit man auch andere Storylines umgesetzt werden können__


#### Tastatur
- `WASD` - Steuerung
- `Leertaste` - Schießen
- `I` Inventar

#### NPC's 
- Händler
- Storyteller (Bringen die Story vorran)
- Gegner
