#Eksempel på hvad Addonet kan
```VBScript
on serverboost:
    broadcast "%event-offlineplayer% boostede serveren og den har nu %event-integer%"

on serverunboost:
    broadcast "%event-offlineplayer% fjernede sit boost fra serveren og den har nu %event-integer%"

command /info <offline player>:
    trigger:
        send "VIP dage: %arg-1's vip days%"
        send "STAFF role: %arg-1's role%"
        send "Rates: %arg-1's rates%"
        if arg-1 is staff:
            send "Staff: Ja"
        if arg-1 is vip:
            send "VIP: Ja"
        send "%9 er hvilken procent af 300%"
        send "%hvad er 25 procent af 400%"
        send "%25 er 75 procent af hvad%"
        send "%stining fra 89 til 100%"
        send "%fald fra 830 til 730%"
        give player 1 of get head "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzU1Zjc2ZGIyMzE3ZTA4ZDVkYzZlZWI0NmYxYTQzYjBmNjRiZDQ2ZjQ4NGY5Zjg3NzViYzkwYjQ0ZjhiZTMwIn19fQ=="```
