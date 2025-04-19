# Проверяем все типы руд на текущей позиции и создаем маркеры

# Железная руда
execute if block ~ ~ ~ minecraft:iron_ore run summon minecraft:armor_stand ~ ~ ~ {NoGravity:1b,Invisible:1b,Glowing:1b,CustomNameVisible:1b,Tags:["ore_marker"],CustomName:'{"text":"Железная руда","color":"gray"}'}
execute if block ~ ~ ~ minecraft:deepslate_iron_ore run summon minecraft:armor_stand ~ ~ ~ {NoGravity:1b,Invisible:1b,Glowing:1b,CustomNameVisible:1b,Tags:["ore_marker"],CustomName:'{"text":"Железная руда","color":"gray"}'}

# Золотая руда
execute if block ~ ~ ~ minecraft:gold_ore run summon minecraft:armor_stand ~ ~ ~ {NoGravity:1b,Invisible:1b,Glowing:1b,CustomNameVisible:1b,Tags:["ore_marker"],CustomName:'{"text":"Золотая руда","color":"gold"}'}
execute if block ~ ~ ~ minecraft:deepslate_gold_ore run summon minecraft:armor_stand ~ ~ ~ {NoGravity:1b,Invisible:1b,Glowing:1b,CustomNameVisible:1b,Tags:["ore_marker"],CustomName:'{"text":"Золотая руда","color":"gold"}'}
execute if block ~ ~ ~ minecraft:nether_gold_ore run summon minecraft:armor_stand ~ ~ ~ {NoGravity:1b,Invisible:1b,Glowing:1b,CustomNameVisible:1b,Tags:["ore_marker"],CustomName:'{"text":"Золотая руда","color":"gold"}'}

# Медная руда
execute if block ~ ~ ~ minecraft:copper_ore run summon minecraft:armor_stand ~ ~ ~ {NoGravity:1b,Invisible:1b,Glowing:1b,CustomNameVisible:1b,Tags:["ore_marker"],CustomName:'{"text":"Медная руда","color":"#c77c3e"}'}
execute if block ~ ~ ~ minecraft:deepslate_copper_ore run summon minecraft:armor_stand ~ ~ ~ {NoGravity:1b,Invisible:1b,Glowing:1b,CustomNameVisible:1b,Tags:["ore_marker"],CustomName:'{"text":"Медная руда","color":"#c77c3e"}'}

# Алмазная руда
execute if block ~ ~ ~ minecraft:diamond_ore run summon minecraft:armor_stand ~ ~ ~ {NoGravity:1b,Invisible:1b,Glowing:1b,CustomNameVisible:1b,Tags:["ore_marker"],CustomName:'{"text":"Алмазная руда","color":"aqua"}'}
execute if block ~ ~ ~ minecraft:deepslate_diamond_ore run summon minecraft:armor_stand ~ ~ ~ {NoGravity:1b,Invisible:1b,Glowing:1b,CustomNameVisible:1b,Tags:["ore_marker"],CustomName:'{"text":"Алмазная руда","color":"aqua"}'}

# Редстоуновая руда
execute if block ~ ~ ~ minecraft:redstone_ore run summon minecraft:armor_stand ~ ~ ~ {NoGravity:1b,Invisible:1b,Glowing:1b,CustomNameVisible:1b,Tags:["ore_marker"],CustomName:'{"text":"Редстоун","color":"red"}'}
execute if block ~ ~ ~ minecraft:deepslate_redstone_ore run summon minecraft:armor_stand ~ ~ ~ {NoGravity:1b,Invisible:1b,Glowing:1b,CustomNameVisible:1b,Tags:["ore_marker"],CustomName:'{"text":"Редстоун","color":"red"}'}

# Лазуритовая руда
execute if block ~ ~ ~ minecraft:lapis_ore run summon minecraft:armor_stand ~ ~ ~ {NoGravity:1b,Invisible:1b,Glowing:1b,CustomNameVisible:1b,Tags:["ore_marker"],CustomName:'{"text":"Лазурит","color":"blue"}'}
execute if block ~ ~ ~ minecraft:deepslate_lapis_ore run summon minecraft:armor_stand ~ ~ ~ {NoGravity:1b,Invisible:1b,Glowing:1b,CustomNameVisible:1b,Tags:["ore_marker"],CustomName:'{"text":"Лазурит","color":"blue"}'}

# Изумрудная руда
execute if block ~ ~ ~ minecraft:emerald_ore run summon minecraft:armor_stand ~ ~ ~ {NoGravity:1b,Invisible:1b,Glowing:1b,CustomNameVisible:1b,Tags:["ore_marker"],CustomName:'{"text":"Изумруд","color":"green"}'}
execute if block ~ ~ ~ minecraft:deepslate_emerald_ore run summon minecraft:armor_stand ~ ~ ~ {NoGravity:1b,Invisible:1b,Glowing:1b,CustomNameVisible:1b,Tags:["ore_marker"],CustomName:'{"text":"Изумруд","color":"green"}'}

# Руда незерита (древние обломки)
execute if block ~ ~ ~ minecraft:ancient_debris run summon minecraft:armor_stand ~ ~ ~ {NoGravity:1b,Invisible:1b,Glowing:1b,CustomNameVisible:1b,Tags:["ore_marker"],CustomName:'{"text":"Древние обломки","color":"dark_purple"}'}

# Кварцевая руда
execute if block ~ ~ ~ minecraft:nether_quartz_ore run summon minecraft:armor_stand ~ ~ ~ {NoGravity:1b,Invisible:1b,Glowing:1b,CustomNameVisible:1b,Tags:["ore_marker"],CustomName:'{"text":"Кварц","color":"white"}'}

# Уголь
execute if block ~ ~ ~ minecraft:coal_ore run summon minecraft:armor_stand ~ ~ ~ {NoGravity:1b,Invisible:1b,Glowing:1b,CustomNameVisible:1b,Tags:["ore_marker"],CustomName:'{"text":"Уголь","color":"dark_gray"}'}
execute if block ~ ~ ~ minecraft:deepslate_coal_ore run summon minecraft:armor_stand ~ ~ ~ {NoGravity:1b,Invisible:1b,Glowing:1b,CustomNameVisible:1b,Tags:["ore_marker"],CustomName:'{"text":"Уголь","color":"dark_gray"}'}

# Рекурсивно идем по осям X, Y, Z для сканирования всего объема
# По оси X
execute if block ~1 ~ ~ #origins_winter_fabric:ores positioned ~1 ~ ~ run function origins_winter_fabric:dwarf/ore_scan_loop

# По оси Y
execute if block ~ ~1 ~ #origins_winter_fabric:ores positioned ~ ~1 ~ run function origins_winter_fabric:dwarf/ore_scan_loop

# По оси Z
execute if block ~ ~ ~1 #origins_winter_fabric:ores positioned ~ ~ ~1 run function origins_winter_fabric:dwarf/ore_scan_loop