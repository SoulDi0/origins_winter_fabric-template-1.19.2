
effect give @s minecraft:regeneration 4 3 false

particle minecraft:soul ~ ~1 ~ 0.5 0.5 0.5 0.1 15 normal
particle minecraft:smoke ~ ~1 ~ 0.5 0.5 0.5 0.01 10 normal
playsound minecraft:entity.wither.ambient player @s ~ ~ ~ 0.5 1.2

# Сбросить счетчик убийств
scoreboard players set @s reaper_kills 0