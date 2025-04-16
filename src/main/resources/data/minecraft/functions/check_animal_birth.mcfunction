# Эти функции должны быть созданы на вашем сервере или в датапаке

# Файл check_animal_birth.mcfunction (в папке data/minecraft/functions/)
# Проверяет новорожденных животных и добавляет близнецов с 40% шансом
execute as @e[type=#minecraft:breedable_animals,nbt={Age:-24000},tag=!twin_checked] if predicate minecraft:random_040 at @s run summon minecraft:armor_stand ~ ~ ~ {Invisible:1b,Marker:1b,Tags:["twin_maker"]}
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s if entity @e[type=minecraft:cow,distance=..0.1,limit=1] run summon minecraft:cow ~ ~ ~ {Age:-24000,Tags:["twin"]}
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s if entity @e[type=minecraft:sheep,distance=..0.1,limit=1] run summon minecraft:sheep ~ ~ ~ {Age:-24000,Tags:["twin"]}
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s if entity @e[type=minecraft:pig,distance=..0.1,limit=1] run summon minecraft:pig ~ ~ ~ {Age:-24000,Tags:["twin"]}
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s if entity @e[type=minecraft:chicken,distance=..0.1,limit=1] run summon minecraft:chicken ~ ~ ~ {Age:-24000,Tags:["twin"]}
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s if entity @e[type=minecraft:rabbit,distance=..0.1,limit=1] run summon minecraft:rabbit ~ ~ ~ {Age:-24000,Tags:["twin"]}
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s if entity @e[type=minecraft:horse,distance=..0.1,limit=1] run summon minecraft:horse ~ ~ ~ {Age:-24000,Tags:["twin"]}
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s if entity @e[type=minecraft:donkey,distance=..0.1,limit=1] run summon minecraft:donkey ~ ~ ~ {Age:-24000,Tags:["twin"]}
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s if entity @e[type=minecraft:mule,distance=..0.1,limit=1] run summon minecraft:mule ~ ~ ~ {Age:-24000,Tags:["twin"]}
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s if entity @e[type=minecraft:wolf,distance=..0.1,limit=1] run summon minecraft:wolf ~ ~ ~ {Age:-24000,Tags:["twin"]}
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s if entity @e[type=minecraft:ocelot,distance=..0.1,limit=1] run summon minecraft:ocelot ~ ~ ~ {Age:-24000,Tags:["twin"]}
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s if entity @e[type=minecraft:cat,distance=..0.1,limit=1] run summon minecraft:cat ~ ~ ~ {Age:-24000,Tags:["twin"]}
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s if entity @e[type=minecraft:llama,distance=..0.1,limit=1] run summon minecraft:llama ~ ~ ~ {Age:-24000,Tags:["twin"]}
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s if entity @e[type=minecraft:panda,distance=..0.1,limit=1] run summon minecraft:panda ~ ~ ~ {Age:-24000,Tags:["twin"]}
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s if entity @e[type=minecraft:fox,distance=..0.1,limit=1] run summon minecraft:fox ~ ~ ~ {Age:-24000,Tags:["twin"]}
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s if entity @e[type=minecraft:bee,distance=..0.1,limit=1] run summon minecraft:bee ~ ~ ~ {Age:-24000,Tags:["twin"]}
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s if entity @e[type=minecraft:turtle,distance=..0.1,limit=1] run summon minecraft:turtle ~ ~ ~ {Age:-24000,Tags:["twin"]}
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s if entity @e[type=minecraft:goat,distance=..0.1,limit=1] run summon minecraft:goat ~ ~ ~ {Age:-24000,Tags:["twin"]}
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s if entity @e[type=minecraft:axolotl,distance=..0.1,limit=1] run summon minecraft:axolotl ~ ~ ~ {Age:-24000,Tags:["twin"]}
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s if entity @e[type=minecraft:strider,distance=..0.1,limit=1] run summon minecraft:strider ~ ~ ~ {Age:-24000,Tags:["twin"]}
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s if entity @e[type=minecraft:hoglin,distance=..0.1,limit=1] run summon minecraft:hoglin ~ ~ ~ {Age:-24000,Tags:["twin"]}
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s run particle minecraft:heart ~ ~0.5 ~ 0.3 0.3 0.3 0.1 10 normal
execute as @e[type=minecraft:armor_stand,tag=twin_maker] at @s run playsound minecraft:entity.experience_orb.pickup player @a ~ ~ ~ 0.5 1.2
kill @e[type=minecraft:armor_stand,tag=twin_maker]
tag @e[type=#minecraft:breedable_animals,nbt={Age:-24000},tag=!twin_checked] add twin_checked

# Файл animal_ticker.mcfunction (в папке data/minecraft/functions/)
# Регулярно запускается для мониторинга новорожденных
execute as @a[tag=!mob_tick] run schedule function minecraft:animal_ticker 1s
tag @a[tag=!mob_tick] add mob_tick

# Файл random_040.json (в папке data/minecraft/predicates/)
# 40% шанс для предиката
{
  "condition": "minecraft:random_chance",
  "chance": 0.4
}