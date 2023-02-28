== OVO ==
---
---
Welcome to OVO!

**What This?**

This is a Minecraft Mod for 1.7.10, this mod will help you use json to make Items and Blocks!
Yes, in a update, it can run in Fabric but this readme just only for 1.7.10.

If you like, look for the "example file" and try by yourself can make you learn it more fast.

[The example for 1.7.10.](https://github.com/AmarokIce/WithJson/tree/master/ovo)

[The example for Fabric.](https://github.com/AmarokIce/WithJson/tree/Fabric-1.16.5/ovo)

**How to Start?**

Good question! Here are some simple example docs:

Create a new dir in your .minecraft . Now create a Json call "ovo_package.json".

Key something in it, like this:

```Json
[
  "ovotest"
]
```

It "ovotest". So I will create a new dir call "ovotest", and create a new json call "info.json".

info.json:
```JSON
{
  "modid": "ovotest"
}
```

Yes! Custom Modid! The items' and blocks' texture will use it so don't throw it.

If you want to share Json with your friends, you can pack your "package" for him!

Now custom our mod!

Group:
In order to prevent any player from ordering a fried rice, I did not join the vanilla group. If you need to put your items in a creation inventory, you must create a group yourself.

Item ID or RegisterName all right. If you want to use the ICON by your self because it is a cat photo, Maybe you should make a new package to re-Read someone item.

```JSON
[
  {
    "name": "testGroup",
    "icon": "minecraft:apple"
  }
]
```

Item:

Create dir "Item" or json "Item.json". If in a dir, you can put they in much json, or you should make then in once json, like this:
```JSON
[
  {
    "name": "pineapple",
    "localization_name": "pineapple",
    "texture_name": "pineapple",
    "max_size": 64,
    "group": "testGroup"
  },
  {
    "name": "pineapple_pie",
    "localization_name": "pineapple_pie",
    "texture_name": "pineapple_pie",
    "max_size": 16,
    "group": "testGroup"
  }
]
```
Block is same.

Block:
```JSON
[
  {
    "name": "pineapple_block",
    "localization_name": "pineapple_block",
    "texture_name": "pineapple_block",
    "hard": 5,
    "hard_level": 1,
    "break_tool": "pickaxe",
    "is_glow": false,
    "drop_item": "ovotest:pineapple",
    "group": "testGroup"
  }
]
```

Hey, they have no icon. Don't worry, make a texture-package! the modid is your info.json 's mod_id.

OK! Now you know what you should do right?

ItemFood:
```JSON
[
  {
    "name": "pineapple",
    "localization_name": "pineapple",
    "texture_name": "pineapple",
    "max_size": 64,
    "hunger": 114,
    "saturation": 51.4,
    "wolf": true,
    "always_eat": false,
    "fast_food": false,
    "isDrink": false,
    "return_item": "null",
    "group": "testGroup"
  }
]
```

ItemTool:
```JSON
[
  {
    "name": "pineapple_pickaxe",
    "localization_name": "pineapple_pickaxe",
    "texture_name": "pineapple_pickaxe",
    "toolkit": "pickaxe",
    "tool_meta": "iron",
    "group": "testGroup"
  }
]
```

ItemWeapons:
```JSON
    "name": "pineapple_sword",
    "localization_name": "pineapple_sword",
    "texture_name": "pineapple_sword",
    "tool_meta": "gold",
    "group": "testGroup"
```

ItemGift (Bata, Don't use it if you did not know what you doing):
```JSON
    "name": "pineapple_package",
    "localization_name": "pineapple_package",
    "texture_name": "pineapple",
    "max_size": 1,
    "items": [
        "pineapple", "pineapple"
      ]
    "group": "testGroup"
```

Recipe (It smells not good. Maybe you should try by your self.) :
```JSON
[
  {
    "recipe": "smelting",
    "output": "minecraft:apple",
    "items_list": [
      "minecraft:apple"
    ]
  },
  {
    "recipe": "crafting_shapeless",
    "output": "minecraft:apple",
    "items_list": [
      "minecraft:apple"
    ]
  },
  {
    "recipe": "crafting_shaped",
    "output": "minecraft:apple",
    "items_list": [
      "A A",
      " B ",
      "A", "minecraft:apple",
      "B", "pineapple:pineapple"
    ]
  },
  {
    "recipe": "crafting_shaped",
    "output": "minecraft:apple",
    "items_list": [
      "AA",
      "A", "minecraft:apple"
    ]
  }
]
```

<br /> <br />
<br /> <br />

**License**

=== Pineapple license ===

I will provide all or part of the code, as long as you use it within the limits of the law, you can use these codes as you like.

You can get full access to this source code without paying any royalties, which will be permanent and irrevocable if you are granted this license.

If you have made a copy of the Program with these code, you should include this license to keep the original code open source.

Of course this license doesn't used in court, just like you know that none of us want legal battles for these meaningless things, so I hope you will respect this brief license.

I do not want my code to be used for commercial purposes, including but not limited to paid downloads.

If you think my mod is good to learn something, if you like, invite me to a cup of pineapple juice.

If you do not follow this simple agreement and legal liability arises, this has nothing to do with me, please take responsibility for yourself.

If you're selling my source code or compiled code, I'll be very angry and accuse you smelling ugly.


If you are unable to accept a license other than those approved by the Open Source Initiative, you may use these codes using the AFL-3.0 license, instead of complying with this license.

If you choose to accept AFL-3.0, you may need to show in your README or somewhere else that is very convenient to see, you are using the AFL-3.0 license.


