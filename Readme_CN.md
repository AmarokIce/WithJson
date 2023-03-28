# With Json ( OVO )

**| >简体中文< | [臺灣正體](Readme_TW.md) | [English](Readme.md) |** <br />
**|如何修改魔法金属（施工中）|**

> ## Menu
> ###  <a href="#关于">  **关于**                </a>
>   1<a href="#ovo">    这是什么？               </a>
>   2<a href="#start">  如何开始？               </a>
>   3<a href="#plan">   计划                    </a>
> 
> ### <a href="#goStart"> **让我们开始!**            </a>
> ### <a href="#基本">    基本                       </a>
>   * <a href="#package"> OVO文件夹与OVOPackage      </a>
>   * <a href="#modid">   创建Modid                  </a>
>
> ### <a href="#创作">    创作                </a>
>   * <a href="#tab">     创造模式物品栏       </a>
>   * <a href="#item">    物品                </a>
>   * <a href="#block">   方块                </a>
>   * <a href="#food">    食物                </a>
>   * <a href="#gift">    礼物                </a>
>   * <a href="#tool">    工具                </a>
>   * <a href="#weapon">  武器                </a>
>   * <a href="#食谱">    食谱                </a>
>   * <a href="#biome">   群系                </a>
>   * <a href="#texture"> 材质与资源文件       </a>
>   * <a href="#error">   嘿！它没有用！       </a>
>
> ### **其他**
> * 许可证
> * 前置

## **关于**
### **这是什么？**<a id="ovo"></a>
WithJson 是一个Minecraft模组，励志于让你能够使用Json “编程”！<br />
现在WithJson 能够让你完全使用Json创造一些游戏元素而你无需学习Java编程！

### **如何开始？**<a id="start"></a>
我们需要在你的Minecraft游戏根目录创建一个名为ovo的文件夹。然后你要做的请检阅<a href="#package">*OVO文件夹与OVOPackage*</a><br />
**<font color=Red>注意：为了向你展示它是怎么工作的，我将会使用“//”作为注解块。但是它们不应该出现在你的文件中，因为Json没有注解块标记。**</font>

### **计划**<a id="plan"></a>
我们有一些将要实现的计划，比如让WithJson 能够解析Jar中的文件以及让你能够创建一套盔甲以及更多🥓 ! <br />
如果你有一些想法，传递Issues！但是如果这个想法的实现是不安全的，那么我将会从计划中移出。很抱歉！但是我不希望看到玩家因为这些不安全的代码导致游戏崩溃而苦恼。

## **让我们开始！**<a id="goStart"></a>
### **基本**
这里有一些简单的概念是你需要知道的。 <p />

 **OVO文件夹与OVOPackage** <a id="package"></a> <br />
WithJson 将不会创建任何东西。它只会解析文件。<br />
所以你需要自己创建一个名为`ovo`的文件夹，然后把所有的东西放进去。<br />
“OVOPackage”是这个文件夹中的文件夹，而每个“OVOPackage”都是一个“伪造的模组”。一个模组就需要一个“Modid”。那么，让我们在`ovo`文件夹中创建一个名为`ovo_package.json`的文件。然后继续在`ovo`文件夹中创建一个任意你喜欢的名字的文件夹，我创建了一个叫`pineapple_test_package`的文件夹，现在我的`ovo`文件夹里看起来就像这样：
```
dir: .minecraft

> ovo
>> pineapple_test_package
>> ovo_package.json
```
很好！现在打开`ovo_package.json`，这是我们用于管理OVOPackage的Json，你需要把你所有的OVOPackage登记到这里。现在我的 `ovo_package.json`看起来是这样的：
```Json
[
    "pineapple_test_package"
]
```
如果我们有超多的OVOPackage，那么看起来应该是这样：
```Json
[
    "pineapple_test_package",
    "other_package"
]
```
*注意：这时候 `pineapple_test_package` 的解析优先级会高于 `other_package`。*

### **创建Modid** <a id="modid"></a><br />
很好！那么你知道的，“一个模组需要一个Modid”对吧？进入你刚创建的文件夹并创建`info.json`，这是你的Modid的管理器。<br />
现在你可以取一个任意你喜欢的名字，但你只能使用ASCALL字符集的字符。
```Json
{
    "modid": "pineapple"
}
```
好极了！现在你做好了全部准备工作！干的好！ <br />
如果你的Modid太奇怪以至于无法识别，那么WithJson将会接管你的Modid并改为“ovo”。
<br /><br />

## **创作**
### **创造模式物品栏** <a id="tab"></a>
好耶！我们终于开始了！<br />
现在让我们为我们的物品创建一个`创造模式物品栏`吧！<br />
 为什么我们不能使用原版的创造模式物品栏？很快就可以了！因为我花费了一些时间思考如何使用它们而不会有任何意外。~~那么我们会有那么多意外吗？~~<br />

创建文件`Group.json`。创建一个新的物品组！
```Json
[
  {
    "name": "pineappleGroup",   // 你的物品组的名称，语言键为"itemGroup.%YourGroupName"
    "icon": "minecraft:apple"   // 本地注册名或物品ID，如果找不到物品则会变成苹果。
  },
  {                             // 如果我们需要更多物品组...
    ......
  }
]
```

### **Item**<a id="item"></a>
完成！现在创建一个物品！<br />
创建一个**文件或文件夹** ，命名为`Item.json` （如果是文件夹，`Item`）。
如果它是一个文件夹，那么里面所有的文件都会被！如果是一个文件，那么所有物品必须在这个文件的列表里。这里是示例：

**File:**
```Json
file: Item.json

[
  {
    "name": "itemtest",               // 物品注册名
    "localization_name": "itemtest",  // 物品本地名
    "texture_name": "itemtest",       // 物品材质名
    "max_size": 64,                   // 最大堆叠量
    "group": "pineappleGroup"         // 创造模式组
  },
  {                                   // 更多炫酷的..
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
    "name": "itemtest",               // 物品注册名
    "localization_name": "itemtest",  // 物品本地名
    "texture_name": "itemtest",       // 物品材质名
    "max_size": 64,                   // 最大堆叠量
    "group": "pineappleGroup"         // 创造模式组
  },
  {                                   // 更多炫酷的..
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

> **所以什么是“注册名”， “本地名”和“材质名”？** <br />
> 
> 注册名是物品的唯一名称，如果有俩个或更多的物品拥有相同的注册名，那么游戏会因此崩溃。<br />
>
> 本地名是语言文件中的键名，如果俩个或更多的物品拥有相同的本地名，那么它们在游戏的名称也是相同的。本地键的组合规则是：如果它们是物品，组合为 “item.%UnlocalName.name”， 如果它们是方块，组合为“tile.%UnlocalName.name”。<br />
>
> 材质名是材质文件的名称，如果两个或者更多的物品拥有相同的材质名，那么它们在游戏里看起来一样。



### **方块**<a id="block"></a>
就像物品，一个文件或文件夹。命名为`Block.json`（如果是文件夹，`Block`。）<br />
那么我们开始：
```Json
[
  {
    "name": "pineapple_block",
    "localization_name": "pineapple_block",
    "texture_name": "pineapple_block",      // 与物品相同
    "hard": 5,                              // 硬度影响挖掘效率
    "hard_level": 1,                        // 方块有多硬？原版的挖掘等级范围是0-3，对应Wood（木），Stone（石），Iron（铁），Diamond（钻）
    "break_tool": "pickaxe",                // 需要哪种工具：pickaxe（镐）， axe（斧）， 或 shovel（锹）
    "is_glow": false,                       // 会发光吗？如果为true，发出15级的光照
    "drop_item": "pineapple:pineapple",     // 被破坏时掉落什么？如果没有，填入“null”即可。或者填入物品的注册名或物品ID
    "group": "pineappleGroup"
  }
]
```

### **食物**<a id="food"></a>
食物的命名是 `ItemFood.json`( 如果是文件夹, `ItemFood`) <br />
```Json
[
  {
    "name": "pineapple",
    "localization_name": "pineapple",
    "texture_name": "pineapple",
    "max_size": 64,
    "hunger": 5,                              // 食用后恢复多少饥饿？整数
    "saturation": 0.8,                        // 食用后恢复多少饱食？浮点，2.0为满
    "wolf": true,                             // 狼（和狗）会吃它吗？
    "always_eat": false,                      // 能够吃不停吗？（就像金苹果）
    "fast_food": false,                       // 吃的很快？
    "isDrink": false,                         // 是饮料？
    "return_item": "null",                    // 吃完返还什么？如果没有填“null”。或是物品注册名或物品ID
    "group": "pineappleGroup"
  }
]
```

### **礼物**<a id="gift"></a>
圣诞快乐! 礼物是玩家右键后会给玩家一些物品的物品。命名是 `ItemGift.json` ( 如果是文件夹, `ItemGift`) <br />
这是一个测试的内容，未来可能会重构。（可能？）
```Json
[
    {
        "name": "pineapple_package",
        "localization_name": "pineapple_package",
        "texture_name": "pineapple",
        "max_size": 1,
        "items": [                                  // 列表的物品都会给玩家！
            "pineapple:pineapple",
            "pineapple:pineapple"
        ],
        "group": "pineappleGroup"
    }
]
```

### **工具**<a id="tool"></a>
有什么比一个工具更好的？两个工具！命名 `ItemTool.json` (如果是文件夹, `ItemTool`)
```Json
[
  {
    "name": "pineapple_pickaxe",
    "localization_name": "pineapple_pickaxe",
    "texture_name": "pineapple_pickaxe",
    "toolkit": "pickaxe",                     // 工具类型: pickaxe, axe, shovel, hoe
    "tool_meta": "iron",                      // 工具材质将会影响效率等工具元素: wood, stone, iron, gold, diamond
    "group": "pineappleGroup"
  }
]
```

### **武器**<a id="weapon"></a>
~~我不知道要说点什么让武器看起来酷一些了。~~ 命名 `ItemWeapons.json` (如果是文件夹, `ItemWeapons`)
```Json
[
    {
        "name": "pineapple_sword",
        "localization_name": "pineapple_sword",
        "texture_name": "pineapple_sword",
        "tool_meta": "gold",                    // 同工具
        "group": "pineappleGroup"
    }
]
```

### **食谱**
命名 `Recipe.json` (如果是文件夹, `Recipe`)<br />
如果食谱中有一些问题，可能会导致游戏崩溃。<br />
**旧样式** <br />
在WithJson 0.1.0之前，所有食谱必须食用旧样式的。
```Json
[
  {
    "recipe": "smelting",           // 熔炼食谱
    "output": "minecraft:apple",    // 产出物品
    "items_list": [                 // 仅第一个有效，注册名或物品ID
      "minecraft:apple"
    ]
  },
  {
    "recipe": "crafting_shapeless", // 无序食谱
    "output": "minecraft:apple",    // 产出物品
    "items_list": [                 // 范围可选1-9个物品
      "minecraft:apple"
    ]
  },
  {
    "recipe": "crafting_shaped",  // 有序食谱
    "output": "minecraft:apple",  // 产出物品
    "items_list": [               // 前三位是顺序排列
      "A A",
      " B ",
      "   ",
      "A", "minecraft:apple",     // 0.0.2版本开始允许你自定义字符
      "B", "pineapple:pineapple"  // 我的凤梨！
    ]
  },
  {
    "recipe": "crafting_shaped",
    "output": "minecraft:apple",
    "items_list": [
      "AA",                       // 自己尝试一下这样的效果？
      "A", "minecraft:apple"
    ]
  }
]
```

**新样式** <br />
0.1.0, 现在Recipe.json看起来更像数据包了。
```Json
[
  {
    "type": "minecraft:crafting_shaped", // 有序食谱
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
      "count": "10"                     // 这里是字符串..未来会改为整数 (#1)
    }
  },
  {
    "type": "minecraft:crafting_shapeless", // 无序食谱
    "ingredients": [
      {
        "oredict": "plankWood"              // 是的！矿物辞典！
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
    "type": "minecraft:smelting", // 熔炼食谱
    "ingredients": [
      {
        "item": "minecraft:apple" // 仅第一个生效
      }
    ],
    "result": {
      "item": "minecraft:apple",
      "count": "2"
    }
  }
]
```

**删除食谱** <br />
删除一个游戏中的食谱，来自原版或者模组。命名`DeleteRecipe.json` (如果是文件夹, `DeleteRecipe` .) 
```Json
[
  "minecraft:bread",
  "minecraft:cake",
  "minecraft:cooked_potato"
]
```

### **群系**
一个新的群系准备好在世界中生成！命名 `Biomes` (如果是文件夹, `Biomes` .)<br />
新增一个群系复杂于其他的。
```Json
[
    {
        "id": 123,                      // 群系的ID，不可与其他群系相同！
        "name": "Pineapple Biome",      // 群系的名称。和其他的群系相同？随你喜欢！
        "weight": 10,                   // 生成权重
        "weather_type": "WARM",         // 气候标签，见下方
        "biomes_tag": "HOT",            // 群系标签，见下方
        "flower": true,                 // 会生成花吗？
        "color": 114514,                // 颜色，十进制的颜色。
        "grass_color": 114514,          // 草的颜色，十进制的颜色。
        "rain": true,                   // 会下雨（雪）吗？
        "water_color": 114514           // 水的颜色，十进制颜色。
    }
]
```

> **气候标签:** <br />
> 决定它会下雨还是下雪。 <br />
> | Type | Rain or Snowy |
> |------|---------------|
> |DESERT|无             |
> |WARM  |雨             |
> |COOL  |雨             |
> |ICY   |雪             |

> **群系标签** <br />
> 他会在哪里生成? <br /> 
> <br /> *基本*       : HOT，COLD
> <br /> *密度*       : SPARSE，DENSE
> <br /> *干燥*       : WET，DRY
> <br /> *群系*       : SAVANNA，CONIFEROUS，JUNGLE，SPOOKY，DEAD，LUSH，NETHER，END，MUSHROOM，MAGICAL，OCEAN，RIVER
> <br /> *复合*       : WATER (Ocean and river)
> <br /> *高级*       : MESA，FOREST，PLAINS，MOUNTAIN，HILLS，SWAMP，SANDY，SNOWY，WASTELAND，BEACH


### **材质与资源文件** <a id="texture"></a>
制作一个资源包或在其他Jar中新增资源文件。<br />
进入assets文件夹，创建一个与你的 `info.json`中Modid相同的文件夹 .<br />
创建這些文件夹: `lang` 和 `textures`。 材质文件夹中需要区分方块与物品的文件夹。就像这样： <br />
```
dir: pineapple

> assets
> > lang
> > > en_US.lang            // 主要的语言文件！如果无法找到对应的语言将会默认读取en_US.lang。 **因此不要让他空着！**
> > > zh_CN.lang
> > > zh_TW.lang
> > textures
> > > blocks
> > > > pineapple_block.png // 是的！材质名称!
> > > items
> > > > pineapple.png       // 是的！材质名称!
.......
```

### **嘿！它没有用！** <a id="error"></a>
哦不！太糟糕了！但是请放轻松，因为我将会提醒你一些你可能会犯下的错误！犯错是很令狼沮丧的，但是这也是一部分，所以不用担心！修复它们也是很炫酷的过程！<br />

> **一个错误的注册名** <br />
> 这是一个很常见的问题，让我们重新检查物品（和方块）的名称。一个Minecraft 1.7.10本地化注册名应当是 “Modid + ItemRegisterName”的组合 （注册名，本地化名和材质名在1.7.10中是允许不一样的！）。或许我们需要一个工具来导出所有的物品的本地化注册名 ...？ ~~好主意，加入计划！(?)~~
> <br />
>
> **Json结构错误** <br />
> 另一个常見的错误~~，而且我经常犯~~！一个“，”？或者你缺少了必要的元素？拥有默认参数很快就会完成！（#1）
> <br />
>
> **一个注解** <br />
> 哦不！Json没有注解块！再去看看 *如何开始* 中的注意事项！
> <br /> 
> 
> **缺少“info.json”， 或者你没有让你的ovopackage在“info.json”中登记** <br />
> 请确定ovopackage已经被登记了。
> <br />
>
> **我很确定是WithJson的问题！** <br />
> 提交一份Issues能够帮助WithJson做的更好！非常感谢你! :)
>

## **Other**
### **License**
**[Pineapple License](LICENSE)**