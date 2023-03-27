# With Json ( OVO )

**| [简体中文](Readme_CN.md) | [臺灣正體](Readme_TW.md) | >English< |** <br />
**|How to modification ManaMetalMod (Building)|**

**There may be grammatical errors, welcome to correct!**

> ## Menu
> ###  <a href="#about">  **About**                 </a>
>   1. <a href="#ovo">    What is WhthJson(ovo) ?   </a>
>   2. <a href="#start">  How we start?             </a>
>   3. <a href="#plan">   Plans                     </a>
> 
> ### <a href="#goStart"> **Let's us start!**       </a>
> ### <a href="#base">    Base                      </a>
>   * <a href="#package"> OVO Folder and OVO Package</a>
>   * <a href="#modid">   Create a modid            </a>
>
> ### <a href="#create">  Create             </a>
>   * <a href="#tab">     Creative Tab       </a>
>   * <a href="#item">    Item               </a>
>   * <a href="#block">   Block              </a>
>   * <a href="#food">    Food               </a>
>   * <a href="#gift">    Gift               </a>
>   * <a href="#tool">    Tool               </a>
>   * <a href="#weapon">  Weapon             </a>
>   * <a href="#recipe">  Recipe             </a>
>   * <a href="#biome">   Biome              </a>
>   * <a href="#texture"> Texture and assets </a>
>   * <a href="#error">   Hey! It not work!  </a>
>
> ### **Other**
> * License
> * Lib

## **About**
### **What is WithJson(ovo) ?**<a id="ovo"></a>
WithJson is a mod for Minecraft, and target is make you "coding" with JSON!  <br />
Now WithJson allows you to use on JSON to create the elements in game! It easy and you should not learn any Java! <br />

### **How we start?**<a id="start"></a>
We need a folder with name "ovo" in your minecraft's folder. And some work you should do please read <a href="#package">*OVO Folder and OVO Package*</a>. <br />
**<font color=Red> Note ：To show you how it works, I'll use "//" as the annotation, but the annotation shouldn't appear in your file because JSON doesn't have annotation tags.**</font>

### **Plans**<a id="plan"></a>
We have more plans like make WithJson can run in a Jar and make you can create an Armour and more 🥓 ! <br />
If you have more idea, Send an Issues! But if they are unsafe in code, I will remove them on plans. Sorry but I really didn't want to see the player distressed by some of unsafe elements that make the game crash.

## **Let's us start!**<a id="goStart"></a>
### **Base**
Here are some simple concepts. <p />

 **OVO Folder and OVO Package** <a id="package"></a> <br />
WithJson will not create anything by itself. All the work it will do just read.<br />
So you should a folder with name `ovo` to put all the file.<br />
The "OVOPackage" is much folder in this folder, and a "OVOPackage" corresponding a "Fake mod". A mod need a "modid". Well, lets we create a new file named `ovo_package.json` in folder `ovo`. And then create a folder named any name you like in folder `ovo`, I create a folder named `pineapple_test_package`, and my folder `ovo` look like this:
```
dir: .minecraft

> ovo
>> pineapple_test_package
>> ovo_package.json
```
Good! Now open file `ovo_package.json`, this is a Json to manage every OVOPackage you have, and put your OVOPackage in it, now my `ovo_package.json` look like this:
```Json
[
    "pineapple_test_package"
]
```
And if we have much package? Add them in this list!
```Json
[
    "pineapple_test_package",
    "other_package"
]
```
*Note: In this case, `pineapple_test_package` will take precedence over `other_package`.*

### **Create a modid** <a id="modid"></a><br />
Got it! And you know now, "A mod need a modid" right? Go to the folder you just created and create a file named `info.json`， this file is your modid's manager. <br />
Now let's make a modid in any you like and put it in this file. And you can only use the char in ASCALL.
```Json
{
    "modid": "pineapple"
}
```
OK! Now you've done all the preparations! Good job! <br />
If your modid is too odd that WithJson cannot know it, WithJson will make it named "ovo". <br /><br />

## **Create**
### **Creative Tab** <a id="tab"></a>
So excited! We're finally started!<br />
Lets we create a `Creative Tab` for our Items!<br />
Why we cannot use the tab in vanilla? Coming soon! Because I spent some time thinking about how to get him working without any unexpected circumstances. ~~So will there be so many unexpected circumstances？~~ <br />

Create a File named `Group.json`. Create a new tab now!
```Json
[
  {
    "name": "pineappleGroup",   // A name for your group, the lang key is "itemGroup.%YourGroupName"
    "icon": "minecraft:apple"   // A LocalRegisterName or an item id all right done! If it cannot find the item, it will use apple.
  },
  {                             // If we would like have more creative tab ...
    ......
  }
]
```

### **Item**<a id="item"></a>
Well done! Now we create an item! <br />
Create a **File or Folder** named `Item.json` (If folder, named `Item`).<br />
If it a folder, all the file in folder will sign up! And if a file, you can only make all the item in this file's list. Here example：

**File:**
```Json
file: Item.json

[
  {
    "name": "itemtest",               // The RegisterName for item.
    "localization_name": "itemtest",  // The UnlocalName for item.
    "texture_name": "itemtest",       // The TextureName for item.
    "max_size": 64,                   // The maxstack for item.
    "group": "pineappleGroup"         // A creative tab for item.
  },
  {                                   // More cool item!
    ...
  }
]
```

**Folder:**
```Json
dir: Item

file: itemtest.json

[
  {
    "name": "itemtest",               // The RegisterName for item.
    "localization_name": "itemtest",  // The UnlocalName for item.
    "texture_name": "itemtest",       // The TextureName for item.
    "max_size": 64,                   // The maxstack for item.
    "group": "pineappleGroup"         // A creative tab for item.
  },
  {                                   // More cool item!
    ...
  }
]

file: itemtest2.json
[
  {
    ...
  }
]
```

> **So what is "RegisterName", "UnlocalName" and "TextureName" ?** <br />
> 
> RegisterName is a unique name for item, if here two or more item had same RegisterName, the game will crash by this. <br />
>
> UnLocalName is the language kay for item, if two or more item had same UnLocalName, they will name as same in game. The LocalLanguageKey is "item.%UnlocalName.name" if it is an item. And call "tile.%UnlocalName.name" if it is a block.<br />
>
> TextureName is the texture's name for item, if two ir more item had same TextureName, they will look same in game.

### **Block**<a id="block"></a>
Like item, a file or a folder.Named `Block.json` ( If folder, `Block` .)<br />
And let's go:

```Json
[
  {
    "name": "pineapple_block",
    "localization_name": "pineapple_block",
    "texture_name": "pineapple_block",      // All of name same as Item.
    "hard": 5,                              // Hard will affect mining efficiency.
    "hard_level": 1,                        // Which tool level should. range in 0-3 in vanilla（Wood，Stone，Iron，Diamond）
    "break_tool": "pickaxe",                // Which tool should：pickaxe or axe, or shovel.
    "is_glow": false,                       // Can it glow? If true, it will have glow level 15.
    "drop_item": "pineapple:pineapple",     // Which item will drop when it break? If have nothing, just key "null", or put a LocalRegisterName or ItemID.
    "group": "pineappleGroup"
  }
]
```

### **Food**<a id="food"></a>
The food named `ItemFood.json`( If folder, `ItemFood`) <br />
```Json
[
  {
    "name": "pineapple",
    "localization_name": "pineapple",
    "texture_name": "pineapple",
    "max_size": 64,
    "hunger": 5,                              // How much hunger will restore when finish eat? This is an Integer.
    "saturation": 0.8,                        // Saturation, Float.（2.0 is full）
    "wolf": true,                             // Can wolf (dog) eat it?
    "always_eat": false,                      // Can always eat?(Like GoldenApple)
    "fast_food": false,                       // Can eat fast? 
    "isDrink": false,                         // Is it a drink?
    "return_item": "null",                    // Have anything should return? If have nothing, "null". Or LocalRegisterName or ItemID.
    "group": "pineappleGroup"
  }
]
```

### **Gift**<a id="gift"></a>
Merry Christmas! Gift is a box, and it will give player something when player right-click.Named `ItemGift.json` ( If folder, `ItemGift`) <br />
It a testing element, so it will change in future （maybe？）
```Json
[
    {
        "name": "pineapple_package",
        "localization_name": "pineapple_package",
        "texture_name": "pineapple",
        "max_size": 1,
        "items": [                                  // All item in list will give player!
            "pineapple:pineapple",
            "pineapple:pineapple"
        ],
        "group": "pineappleGroup"
    }
]
```

### **Tool**<a id="tool"></a>
What's better than a tool? Two tools! Named `ItemTool.json` (If folder, `ItemTool`)
```Json
[
  {
    "name": "pineapple_pickaxe",
    "localization_name": "pineapple_pickaxe",
    "texture_name": "pineapple_pickaxe",
    "toolkit": "pickaxe",                     // The type of tool: pickaxe, axe, shovel, hoe
    "tool_meta": "iron",                      // The Material of tool, the durability and other all will affected by this: wood, stone, iron, gold, diamond
    "group": "pineappleGroup"
  }
]
```

### **Weapon**<a id="weapon"></a>
~~I can't think of how to make it look cool.~~ Named `ItemWeapons.json` (If folder, `ItemWeapons`)
```Json
[
    {
        "name": "pineapple_sword",
        "localization_name": "pineapple_sword",
        "texture_name": "pineapple_sword",
        "tool_meta": "gold",                    // Same as tool.
        "group": "pineappleGroup"
    }
]
```

### **Recipe**
Named `Recipe.json` (If folder, `Recipe`)<br />
If here any wrongs in Recipe, maybe it will make a crash.
**Old** <br />
Before WithJson 0.1.0, all the recipe should use old element. And it cannot use OreDict.
```Json
// Recipe.json
[
  {
    "recipe": "smelting",           // Smelting Recipe.
    "output": "minecraft:apple",    // Output.
    "items_list": [                 // Only the first item. LocalRegisterName or ItemID.
      "minecraft:apple"
    ]
  },
  {
    "recipe": "crafting_shapeless", // Shapeless Recipe. 
    "output": "minecraft:apple",    // Output
    "items_list": [                 // Range is 1-9 item(s).
      "minecraft:apple"
    ]
  },
  {
    "recipe": "crafting_shaped",  // Shaped Recipe.
    "output": "minecraft:apple",  // Output
    "items_list": [               // The shaped of recipe in Top three.
      "A A",
      " B ",
      "   ",
      "A", "minecraft:apple",     // Start in 0.0.2 you can use the char in customization.
      "B", "pineapple:pineapple"  // My pineapple!
    ]
  },
  {
    "recipe": "crafting_shaped",
    "output": "minecraft:apple",
    "items_list": [
      "AA",                       // Try by yourself?
      "A", "minecraft:apple"
    ]
  }
]
```

**New** <br />
0.1.0, now recipes Jsons will more like the datapackage.
```Json
[
  {
    "type": "minecraft:crafting_shaped", // Shaped Recipe
    "pattern": [
      "AAA",
      "   ",
      "AAA"
    ],
    "key": {
      "A": {
        "item": "minecraft:apple"
      }
    },
    "result": {
      "item": "minecraft:apple",
      "count": "10"                     // This is a String... It will in Integer in future.
    }
  },
  {
    "type": "minecraft:crafting_shapeless", // Shapeless Recipe
    "ingredients": [
      {
        "oredict": "plankWood"              // Yes! It an OreDict!
      },
      {
        "oredict": "plankWood"
      },
      {
        "oredict": "plankWood"
      },
      {
        "item": "minecraft:apple"
      },
      {
        "item": "minecraft:apple"
      },
      {
        "item": "minecraft:apple"
      }
    ],
    "result": {
      "item": "pineapple:pineapple",
      "count": "5"
    }
  },
  {
    "type": "minecraft:smelting", // Smelting.
    "ingredients": [
      {
        "item": "minecraft:apple" // Only first item.
      }
    ],
    "result": {
      "item": "minecraft:apple",
      "count": "2"
    }
  }
]
```

**Delete Recipe** <br />
Delete a recipe from game, vanilla or other mods. Named `DeleteRecipe.json` (If folder, `DeleteRecipe` .) 
```Json
[
  "minecraft:bread",
  "minecraft:cake",
  "minecraft:cooked_potato"
]
```

### **Biome**
A new Biome is already to generate in your world! Named `Biomes` (If folder, `Biomes` .)<br />
Add a biome is more intricate than others.
```Json
[
    {
        "id": 123,                      // The ID for Biome, never make it same with other biomes!
        "name": "Pineapple Biome",      // A name for Biome. Same as other? If you like!
        "weight": 10,                   // Generate weight, Integer.
        "weather_type": "WARM",         // Weather Type, see following.
        "biomes_tag": "HOT",            // Biome Type, see following.
        "flower": true,                 // Will biome generate flower?
        "color": 114514,                // Color, a  decimal color.
        "grass_color": 114514,          // Grass's color, decimal color.
        "rain": true,                   // Is it raining (or snowy) ?
        "water_color": 114514           // Water color, decimal color.
    }
]
```

> **Weather Type:** <br />
> It will affects rain or snow. <br />
> | Type | Rain or Snowy |
> |------|---------------|
> |DESERT|Null           |
> |WARM  |Rain           |
> |COOL  |Rain           |
> |ICY   |Snowy          |

> **Biome Type** <br />
> Where will it generate? <br /> 
> <br /> *Base*       : HOT，COLD
> <br /> *Density*    : SPARSE，DENSE
> <br /> *Dry*        : WET，DRY
> <br /> *Biome*      : SAVANNA，CONIFEROUS，JUNGLE，SPOOKY，DEAD，LUSH，NETHER，END，MUSHROOM，MAGICAL，OCEAN，RIVER
> <br /> *Compound*   : WATER (Ocean and river)
> <br /> *Advanced*   : MESA，FOREST，PLAINS，MOUNTAIN，HILLS，SWAMP，SANDY，SNOWY，WASTELAND，BEACH


### **Texture and assets** <a id="texture"></a>
Make a TexturePackage or open anyone mod's Jar File.<br />
Go to the assets' folder, create a new folder name your modid in `info.json` .<br />
Create folder: `lang` and `textures`. The textures should distinguish blocks and items. Like this: <br />
```
dir: pineapple

> assets
> > lang
> > > en_US.lang    // The main lang! If the lang cannot find with your system lang, The Minecraft will read with en_US.lang! So DO NOT MAKE IT EMPTY !
> > > zh_CN.lang
> > > zh_TW.lang
> > textures
> > > blocks
> > > > pineapple_block.png // Right, the TextureName here!
> > > items
> > > > pineapple.png       // Right, the TextureName here!
.......
```

### **Hey! It not work!** <a id="error"></a>
Oh no! Too bad! But now you can take it easy, because I will remind you something mistakes you may make! Making mistakes is frustrating, but it's also a process, so don't worry! Fix them is a cool process! <br />

> **An incorrect register name** <br />
> It's a usually mistakes, let's check the name of Items (or Blocks). A LocalRegisterName is "Modid + ItemRegisterName" in Minecraft 1.7.10 (The UnLocalItemName, TexturesName and ItemRegisterName in Minecraft 1.7.10 is allowed to be different！). Maybe we should a tool to export all of item's LocalRegisterName to help us ...? ~~Okay, take it in plan! (?)~~
> <br />
>
> **An incorrect Json** <br />
> Another usually mistakes ~~and I always make it~~! A ","? Or you're missing an elements？ The default element is coming soon! (#1)
> <br />
>
> **A annotation** <br />
> Oh no! Json have no annotation tag! Please read the note in *How we start*!
> <br /> 
> 
> **Missing "info.json" or you didn't make your ovopackage in "info.json"** <br />
> Make sure your ovopackage's name is right put in "info.json".
>
> **I really sure it's WithJson make it broken!**
> Send an Issues to report this will make WithJson do better! Thank you! :)
>

## **Other**
### **License**
**[Pineapple License](LICENSE)**