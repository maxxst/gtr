#Item drops
hier erste Überlegungen zu wie ein Item dropt

## Klassifizierung
Schaden und Rüstung von Gegenständen hängen vom Level des Spielers und von der Art des Monsters ab (Wahrscheinlichkeiten verschieben sich). 
__Eine Berechnungsgrundlage muss noch erarbeitet Werden__

### Common (weiß)
- einfache Gegenstände ohne besondere Fähigkeiten
- Schadensmultiplikator: 1
- häufig

### Uncommon (grün)
- Gegenstände mit ein bis zwei leichten Aufwertungen
- Schadensmultiplikator: 1
- max 1 Sockel

### Rare (blau)
- Gegenstände mit ein bis zwei leichten Aufwertungen
- Schadens-/Rüstungsmultiplikator: 1,5
- max 2 Sockel

### very Rare (lila)
- Gegenstände mit zwei bis drei starken Aufwertungen
- Multiplikator: 3
- max 3 Sockel
- sehr sehr selten

## Waffen
ein paar Beispielwaffen in YAML

	- name: "Schwert"
	  range:
		from: 1
		to: 1
	- name: "Pistole"
	  range:
		from: 2
		to: 3
	- name: "Racketenwerfer"
	  range:
		from: 5
		to: 10
	  speed: 2 #fields per turn
	  area: True
	- name: "Bogen des Robin Hood"
	  range:
		from: 1
		to: 12
	  speed: 4
	  breaktrough: 10
	  only_rare: True
	  
	  
### YAML Optionen
das kann man alles in YAML angeben wobei nur `name` und `range` Pflicht sind 

- `name`: Name der Waffe
- `range`: Reichweite mit
	- `from`: Mindestabstand
	- `to`: Maximalabstand
- `speed`: Geschwindigkeit (Verwandelt Waffe in Projektilwaffe)
- `area`: Umgebungsschaden
- `breakthrough`: Multiplikator für Durchschlag des Projektils
- `only_rare`: Waffe kann nur als 'blau' oder 'lila' gefunden werden
- `belongs_to_boss`: Waffe kann nur von einem Boss gedroppt werden (experimentell)

### Projektilwaffen
Für Projektilwaffen gilt die Mindestweite nicht. Das Projektil fliegt bis zur maximalen Reichweite oder bis es ein Objekt trifft.

### Umgebungsschaden
Umgebungsschaden betrifft alle acht angrenzenden Felder mit 90% des Orginalschadens

### Durchschlag
Waffen mit hohem Durchschlag konnen mehrere Gegner treffen, indem sie den ersten Gegner durchschlagen und weiterfliegen. Durch Türen schießen ist so auch denkbar

## Rüstung

	



